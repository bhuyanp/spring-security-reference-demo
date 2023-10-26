package com.example.springsecurityexamples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SpringSecurityDemoApplication implements CommandLineRunner{
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;



	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Load user: {}",userDetailsServiceImpl.loadUserByUsername("prasanta.k.bhuyan@gmail.com"));
	}
}
