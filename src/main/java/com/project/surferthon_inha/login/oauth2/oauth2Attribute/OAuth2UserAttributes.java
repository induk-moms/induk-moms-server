package com.project.surferthon_inha.login.oauth2.oauth2Attribute;

import com.project.surferthon_inha.login.user.entity.UserType;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuth2UserAttributes {

    private Map<String, Object> attributes;

    protected UserType userType;
    protected String sub;
    protected String name;

    public OAuth2UserAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
