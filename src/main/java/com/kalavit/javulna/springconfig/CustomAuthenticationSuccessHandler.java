/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kalavit.javulna.springconfig;

import com.kalavit.javulna.utils.SerializationUtil;
import java.io.IOException;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author peti
 */
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {        
        response.addCookie(createUserCookie(authentication.getPrincipal()));
        response.getWriter().write("{\"name\":\""+authentication.getName()+"\"}");
    }
    
    public static final String USER_AUTHENTICATION_EXTRA_SECURITY = "USER_AUTHENTICATION_EXTRA_SECURITY";

    private Cookie createUserCookie(Object principal) {
        String userData = Base64.getEncoder().encodeToString(SerializationUtil.serialize(principal));
        Cookie cookie = new Cookie(USER_AUTHENTICATION_EXTRA_SECURITY, userData);
        cookie.setMaxAge(Integer.MAX_VALUE);
        return cookie;
    }
}