/**
 * BBD Service Inc
 * All Rights Reserved @2019
 */
package com.charles.facade.impl.plugn;

import com.alibaba.fastjson.JSON;

import com.charles.common.authentication.CurrentUser;
import com.charles.common.authentication.LoginUser;
import com.charles.common.util.DigestUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 *实现controller方法参数注解@LoginUser功能
 * @author xiaos
 * @version $Id: LoginUserArgumentResolver.java, v0.1 2018/11/20 11:11 xiaos Exp $$
 */
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 写入request header的用户信息
     */
    private static final String REQUEST_USER_HEADER = "x-user";

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.getParameterAnnotation(LoginUser.class) != null && methodParameter.getParameterType() == CurrentUser.class) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory)
                                                                                                                                                                                             throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        String obj = request.getHeader(REQUEST_USER_HEADER);
        String userStr = new String(DigestUtils.decodeBase64(obj));
        return JSON.parseObject(userStr, CurrentUser.class);
    }
}
