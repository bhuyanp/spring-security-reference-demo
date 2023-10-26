package com.example.springsecurityexamples.oauth;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@ToString
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2User oAuth2User;
    private final Collection<? extends GrantedAuthority> grantedAuthorities;

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(oAuth2User.getAuthorities());
        authorities.addAll(grantedAuthorities);
        return authorities;
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("name");
    }

    public String getEmail() {
        return oAuth2User.<String>getAttribute("email");
    }
}
