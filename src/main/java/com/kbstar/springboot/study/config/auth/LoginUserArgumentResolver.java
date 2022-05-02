package com.kbstar.springboot.study.config.auth;

import com.kbstar.springboot.study.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    /*
        컨트롤러의 메소드의 특정 파라미터를 지원하는지 판단
            파라미터에 @LoginUser 이런 어노테이션이 붙어있고, 파라미터 클래스 타입이 SessionUser 이면, true 반환하도록!
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());


        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}