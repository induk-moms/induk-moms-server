package com.project.surferthon_inha.login.oauth2.service;

import com.project.surferthon_inha.login.oauth2.entity.CustomOAuth2User;
import com.project.surferthon_inha.login.oauth2.oauth2Attribute.OAuth2UserAttributes;
import com.project.surferthon_inha.login.oauth2.oauth2Attribute.Oauth2AttributeDispatcher;
import com.project.surferthon_inha.login.user.entity.Role;
import com.project.surferthon_inha.login.user.entity.User;
import com.project.surferthon_inha.login.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserAttributes userAttributes = Oauth2AttributeDispatcher.dispatch(registrationId, oAuth2User.getAttributes());

        Optional<User> optionalUser = userService.loadUserBySub(userAttributes.getSub());

        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();

            // 정보가 변하지 않은 경우에 DB에 작성하지 않게 하기 위해
            if (!user.getName().equals(userAttributes.getName())) user.setName(userAttributes.getName());
        } else {
            user = User.builder()
                    .userType(userAttributes.getUserType())
                    .role(Role.USER)
                    .sub(userAttributes.getSub())
                    .name(userAttributes.getName())
                    .build();

            Long id = userService.register(user);
            user.setId(id);
        }

        return new CustomOAuth2User(user, userAttributes.getAttributes());
    }
}
