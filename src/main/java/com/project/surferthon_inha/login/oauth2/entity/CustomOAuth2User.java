package com.project.surferthon_inha.login.oauth2.entity;

import com.project.surferthon_inha.login.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2User extends User implements OAuth2User {

    private Map<String, Object> attributes;



    public CustomOAuth2User(User user, Map<String, Object> attributes) {
        this.setId(user.getId());
        this.setSub(user.getSub());
        this.setName(user.getName());
        this.setPassword(user.getPassword());
        this.setRole(user.getRole());
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
