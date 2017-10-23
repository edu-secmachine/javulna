/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.controllers.rest;

import com.kalavit.javulna.dto.UserDto;
import com.kalavit.javulna.services.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peti
 */
@RestController
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    UserService userService;
    
    @PostMapping("rest/user/password")
    public String changePassword(@RequestParam String user, 
            @RequestParam String oldPassword, 
            @RequestParam String newPassword){
        boolean changePassword = userService.changePassword(user, oldPassword, newPassword);
        if(changePassword){
            return "OK";
        }
        else{
            return "Password not valid. Password did not change";
        }
    }
    
    @PostMapping("rest/user")
    public String modifyUser(@RequestBody UserDto user){
        userService.modifyUser(user);
        return "OK";
    }
    
    @PutMapping("rest/user")
    public @ResponseBody UserDto createUser(@RequestBody UserDto user){
        return userService.createUser(user);
    }
    
    @GetMapping("rest/user")
    public @ResponseBody List<UserDto> getUsers(){
        return userService.findAllUsers();
    }
}
