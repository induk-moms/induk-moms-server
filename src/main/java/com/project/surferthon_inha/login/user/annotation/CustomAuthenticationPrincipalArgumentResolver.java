package com.project.surferthon_inha.login.user.annotation;

import com.project.surferthon_inha.login.jwt.entity.UserInformInToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class CustomAuthenticationPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthUser.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            log.error("Authentication is null");
            throw new NullPointerException("Authentication is null");
        }

        UserInformInToken userInform = (UserInformInToken) authentication.getPrincipal();
        if (userInform == null) {
            log.error("userInformInToken is null");
            throw new NullPointerException("userInformInToken is null");
        }

        return userInform;
    }
}
