/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.springconfig;


import com.kalavit.javulna.model.User;
import com.kalavit.javulna.services.UserService;
import com.kalavit.javulna.services.autodao.UserAutoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author peti
 */
public class UserDetailsServiceImpl implements UserDetailsService{
    
    Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    
    @Autowired
    UserAutoDao uDao;
    
    @Autowired
    UserService uService;
    

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.debug("We will load user: {}", username);
        User user = uService.findUserWithAuthorities(username);
        if(user==null) throw new UsernameNotFoundException("User " + username + " not found.");
        else return user;
    }
    
}
