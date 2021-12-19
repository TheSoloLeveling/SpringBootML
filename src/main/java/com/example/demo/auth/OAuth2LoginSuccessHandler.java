package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    //@Autowired
    //private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        CustomerOAuth2User oAuth2User = (CustomerOAuth2User) authentication.getPrincipal();
        System.out.println("OAuth2 username: " + oAuth2User.getName());
        System.out.println("OAuth2 email: " + oAuth2User.getEmail());
        System.out.println("OAuth2 Client: " + oAuth2User.getClientName());

        //User user = userService.getUserByEmail(oAuth2User.getEmail());
        //if (user != null)
        // User already exist
        // else
        // Add user to database


        super.onAuthenticationSuccess(request, response, authentication);
    }
}
