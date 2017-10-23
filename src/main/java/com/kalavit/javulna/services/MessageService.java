/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.services;

import com.kalavit.javulna.dto.ChatDto;
import com.kalavit.javulna.dto.MessageDto;
import com.kalavit.javulna.dto.UserDto;
import com.kalavit.javulna.model.Message;
import com.kalavit.javulna.model.MessageType;
import com.kalavit.javulna.model.User;
import com.kalavit.javulna.services.autodao.MessageAutoDao;
import com.kalavit.javulna.services.autodao.UserAutoDao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author peti
 */
@Service
public class MessageService {

    private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

    @PersistenceContext
    EntityManager em;

    @Autowired
    private DozerBeanMapper beanMapper;

    @Autowired
    private UserAutoDao userAutoDao;

    @Autowired
    MessageAutoDao messageAutoDao;

    @Autowired
    SimpMessageSendingOperations webSocketSender;

    public List<MessageDto> getAllMessages() {
        List<Message> resultList = messageAutoDao.findAll();
        List<MessageDto> ret = new ArrayList<>();
        for (Message message : resultList) {
            MessageDto mdto = beanMapper.map(message, MessageDto.class);
            ret.add(mdto);
        }
        return ret;
    }

    public List<MessageDto> getMessagesBetweenActualAndOther(String otherUserName) {
        User actUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getMessagesBetweenUsers(actUser.getUsername(), otherUserName);
    }

    public List<MessageDto> getMessagesBetweenUsers(String fromUser, String toUser) {
        List<Message> resultList = em.createQuery("select m from Message m join m.addressees add "
                + "where (m.author.name = :actUserName and add.name = :otherUserName) or "
                + "(m.author.name = :otherUserName and add.name = :actUserName) "
                + "order by m.createdAt asc")
                .setParameter("actUserName", fromUser)
                .setParameter("otherUserName", toUser)
                .getResultList();
        List<MessageDto> ret = new ArrayList<>();
        for (Message message : resultList) {
            MessageDto mdto = beanMapper.map(message, MessageDto.class);
            ret.add(mdto);
        }
        return ret;
    }

    @Transactional
    public MessageDto sendChatMessage(ChatDto message) {
        Message m = new Message();
        m.setMessage(message.getText());
        m.setType(MessageType.chat);
        User toUser = userAutoDao.findUserByName(message.getToUser());
        User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User actUser = userAutoDao.findUserByName(currUser.getUsername());
        m.setAuthor(actUser);
        m.getAddressees().add(toUser);
        em.persist(m);
        em.flush();
        MessageDto mdto = beanMapper.map(m, MessageDto.class);
        webSocketSender.convertAndSendToUser(message.getToUser(), "/queue/notifications", mdto);     
        //webSocketSender.convertAndSend("/topic/messagefeed", mdto);
        return mdto;
    }

}
