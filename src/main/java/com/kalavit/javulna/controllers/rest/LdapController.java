/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.controllers.rest;

import com.kalavit.javulna.dto.LdapUserDto;
import com.kalavit.javulna.services.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author peti
 */
@RestController
public class LdapController {

    @Autowired
    LdapService ldapService;

    @GetMapping(path = "/rest/ldap")
    public LdapUserDto findUserInLDAP(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String pwd) {
        return ldapService.findUser(username, pwd);
    }

}
