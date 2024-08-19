package com.project.surferthon_inha.login.oauth2.oauth2Attribute;

import com.project.surferthon_inha.login.user.entity.UserType;
import lombok.Getter;

import java.util.Map;

@Getter
public class GithubOAuth2UserAttributes extends OAuth2UserAttributes {

    public GithubOAuth2UserAttributes(Map<String, Object> attributes) {
        super(attributes);
        this.userType = UserType.GITHUB;
        this.sub = (String) attributes.get("email");
        this.name = (String) attributes.get("name");
    }
}
