/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.dto;

import com.kalavit.javulna.model.MessageType;
import java.util.List;

/**
 *
 * @author peti
 */
public class MessageDto {
    private String id;
    private String message;   
    private UserInMessageDto author;
    private List<UserInMessageDto> addressees;
    private MessageType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInMessageDto getAuthor() {
        return author;
    }

    public void setAuthor(UserInMessageDto author) {
        this.author = author;
    }

    public List<UserInMessageDto> getAddressees() {
        return addressees;
    }

    public void setAddressees(List<UserInMessageDto> addressees) {
        this.addressees = addressees;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
    
}
