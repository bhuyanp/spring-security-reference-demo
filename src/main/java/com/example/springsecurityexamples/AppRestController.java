package com.example.springsecurityexamples;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class AppRestController {


    @GetMapping
    public Object home() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getAuthorities());
        String name = "";
        if (authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken) {
            name = oAuth2AuthenticationToken.getPrincipal().getAttribute("name");
            System.out.println(oAuth2AuthenticationToken.getPrincipal().getAuthorities());
        }
        if (authentication instanceof UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
            if (usernamePasswordAuthenticationToken.getPrincipal() instanceof User user) {
                System.out.println(user.getAuthorities());
                name = user.getUsername();
            }
        }
        return authentication;
    }


    @GetMapping("/admin")
    @Secured({"ROLE_ADMIN"})
    public String test(@AuthenticationPrincipal Principal userPrincipal) {
        System.out.println(userPrincipal);
        return "This is an admin only page";
    }
}
