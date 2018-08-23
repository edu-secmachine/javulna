/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.services;

import com.kalavit.javulna.dto.UserDto;
import com.kalavit.javulna.model.User;
import com.kalavit.javulna.services.autodao.UserAutoDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.commons.io.IOUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 * @author peti
 */
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserAutoDao uDao;

    @Autowired
    private DozerBeanMapper beanMapper;

    @Autowired
    RemotePasswordChangeService passwordChangeService;
    
    @Autowired
    PasswordEncoder encoder;

    public List<UserDto> findAllUsers() {
        List<User> users = uDao.findAll();
        List<UserDto> ret = new ArrayList<UserDto>();
        for (User user : users) {
            UserDto ud = new UserDto(user);
            ret.add(ud);
        }
        return ret;
    }

    @Transactional
    public UserDto createUser(UserDto ud) {
        ud.setId(null);
        User user = beanMapper.map(ud, User.class, "userMapNoNull");
        User saved = uDao.saveAndFlush(user);
        UserDto ret = beanMapper.map(saved, UserDto.class, "userMapFull");
        ret.setPassword(null);
        return ret;
    }

    @Transactional
    public void modifyUser(UserDto ud) {
        if (StringUtils.hasText(ud.getId())) {
            User user = beanMapper.map(ud, User.class, "userMapFull");
            uDao.saveAndFlush(user);
        }
        else{
            throw new RuntimeException("Id of the user must be specified during modification");
        }
    }

    @Transactional
    public boolean changePassword(String name, String oldPassword, String newPassword) {
        User u = uDao.findUserByName(name);
        if (u != null) {
            if (u.getPassword().equals(oldPassword)) {
                String pwdChangeXml = createXml(name, newPassword);
                return passwordChangeService.changePassword(pwdChangeXml);
            }
        }
        return false;
    }

    private String createXml(String name, String newPassword) {
        try {
            String xmlString = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("xml/PasswordChange.xml"), "UTF-8");
            xmlString = xmlString.replaceAll("PWD_TO_REPLACE", newPassword);
            xmlString = xmlString.replaceAll("USERNAME_TO_REPLACE", name);
            LOG.debug("xml string created: {}", xmlString);
            return xmlString;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean checkPassword(String name, String password) {
        User u = uDao.findUserByName(name);
        if (u != null) {           
            if (encoder.matches(password, u.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public User findUserWithAuthorities(String username) {
        User u = uDao.findUserByName(username);
        return u;
    }

}
