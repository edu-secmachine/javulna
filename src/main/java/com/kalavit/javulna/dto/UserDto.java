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
public class UserDto {

    private String id;
    private String name;
    private String sex;
    private String emailAddress;
    private String password;
    private String motto;
    private String webPageUrl;

    public UserDto() {
    }
    
    public UserDto(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setSex(user.getSex());
        this.setEmailAddress(user.getEmailAddress());
        this.setMotto(user.getMotto());
        this.setWebPageUrl(user.getWebPageUrl());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMotto() {
        return motto;
    }

    public final void setMotto(String motto) {
        this.motto = motto;
    }

    public String getWebPageUrl() {
        return webPageUrl;
    }

    public final void setWebPageUrl(String webPageUrl) {
        this.webPageUrl = webPageUrl;
    }

}
