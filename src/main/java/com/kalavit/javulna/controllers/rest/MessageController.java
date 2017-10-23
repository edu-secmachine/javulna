/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.controllers.rest;

import com.kalavit.javulna.dto.ChatDto;
import com.kalavit.javulna.dto.MessageDto;
import com.kalavit.javulna.services.MessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peti
 */
@RestController()
public class MessageController {
    
    @Autowired
    MessageService messageService;

    
    @GetMapping("rest/messages/chatAll")
    public @ResponseBody List<MessageDto> getChatMessagesBetweenActualAndOther(){
        return messageService.getAllMessages();
    }
    
    @GetMapping("rest/messages/chat")
    public @ResponseBody List<MessageDto> getChatMessagesBetweenActualAndOther(@RequestParam(name = "otherUser") String otherUser){
        return messageService.getMessagesBetweenActualAndOther(otherUser);
    }
    
    @PutMapping("rest/messages/chat")
    public @ResponseBody MessageDto sendChatMessage(@RequestBody ChatDto chat){
        return messageService.sendChatMessage(chat);
    }
    
}
