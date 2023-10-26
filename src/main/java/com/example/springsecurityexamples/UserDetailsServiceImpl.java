package com.example.springsecurityexamples;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Assert.notNull(userEmail,"email is required");
        Optional<UserDetails> optionalUserDetails = userDetails()
                .stream()
                .filter(it -> userEmail.equals(it.getUsername())).findAny();
        if (optionalUserDetails.isPresent())
            return optionalUserDetails.get();
        else
            throw new UsernameNotFoundException("No user found with username/email " + userEmail);
    }


    /*
     * Add your login users here.
     */
    private List<UserDetails> userDetails() {
        UserDetails prasanta = User.builder()
                .username("prasanta.k.bhuyan@gmail.com")
                .password("{bcrypt}$2a$10$BZWpsiGNF1CCFZnZOY66d.qlQkpt7UrjPLKVF6B9QBkAGjAIDWRey")
                .roles("ADMIN")
                .build();
        UserDetails bhuyanp = User.builder()
                .username("bhuyanp@gmail.com")
                .password("{bcrypt}$2a$10$BZWpsiGNF1CCFZnZOY66d.qlQkpt7UrjPLKVF6B9QBkAGjAIDWRey")
                .roles("USER")
                .build();

        UserDetails bhuyanpGitHub = User.builder()
                .username("bhuyanp")
                .password("")
                .roles("USER")
                .build();
        return List.of(
                prasanta, bhuyanp, bhuyanpGitHub
        );
    }

    public static void main(String[] args) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(encoder.encode("password"));
    }


}
