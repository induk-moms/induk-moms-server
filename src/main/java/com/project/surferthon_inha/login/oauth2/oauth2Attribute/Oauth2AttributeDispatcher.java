package com.project.surferthon_inha.login.oauth2.oauth2Attribute;

import java.util.Map;

public class Oauth2AttributeDispatcher {

    public static OAuth2UserAttributes dispatch(String registrationId, Map<String, Object> attributes) {
        return switch (registrationId) {
            case "google" -> new GoogleOAuth2UserAttributes(attributes);
            case "kakao" -> new KakaoOAuth2UserAttributes(attributes);
            case "naver" -> new NaverOAuth2UserAttributes(attributes);
            case "github" -> new GithubOAuth2UserAttributes(attributes);
            default -> throw new IllegalArgumentException();
        };
    }
}
