/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.dto;

import com.kalavit.javulna.model.User;

/**
 *
 * @author peti
 */
public class UserInMessageDto {

    private String id;
    private String name;
    private String sex;
    private String emailAddress;

    public UserInMessageDto() {
    }
    
    public UserInMessageDto(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setSex(user.getSex());
        this.setEmailAddress(user.getEmailAddress());
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public final void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public final void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
