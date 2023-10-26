package com.example.springsecurityexamples.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SecurityConfigOAuth {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/login.html")
                        .permitAll()
                        .requestMatchers("/admin")
                        .hasRole("ADMIN")
                        .requestMatchers("/**")
                        .authenticated()
                )
                .oauth2Login(oauthLogin -> oauthLogin.loginPage("/login.html"));
        return http.build();
    }

}
