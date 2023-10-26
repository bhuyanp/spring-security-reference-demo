package com.example.springsecurityexamples.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    UserDetailsService userDetailsServiceImpl;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        String userEmail = user.getAttribute("email");//google
        String login = user.getAttribute("login");//github
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(null!=userEmail?userEmail:login);
        return new CustomOAuth2User(user,userDetails.getAuthorities());
    }
}


