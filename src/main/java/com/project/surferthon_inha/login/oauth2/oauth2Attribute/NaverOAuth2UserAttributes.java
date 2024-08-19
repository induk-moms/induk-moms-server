package com.project.surferthon_inha.login.oauth2.oauth2Attribute;

import com.project.surferthon_inha.login.user.entity.UserType;
import lombok.Getter;

import java.util.Map;

@Getter
public class NaverOAuth2UserAttributes extends OAuth2UserAttributes {

    public NaverOAuth2UserAttributes(Map<String, Object> attributes) {
        super(attributes);
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        this.userType = UserType.NAVER;
        this.sub = (String) response.get("email");
        this.name = (String) response.get("name");
    }
}
