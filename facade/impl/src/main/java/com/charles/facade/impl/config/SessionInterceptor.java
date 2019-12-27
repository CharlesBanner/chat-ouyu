package com.charles.facade.impl.config;

import com.alibaba.fastjson.JSON;

import com.charles.common.authentication.CurrentUser;
import com.charles.common.enums.AuthExceptionEnum;
import com.charles.common.exception.BusinessException;
import com.charles.common.util.Constants;
import com.charles.common.util.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description: 就是拦截器
 *
 * @author: GanZiB
 * Date: 2019-12-17
 * Time: 15:41
 */

public class SessionInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(SessionInterceptor.class);
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        //权限路径拦截
        Object object = arg0.getSession().getAttribute(Constants.REQUEST_ECO_USER_HEADER);
        if (null == object){
            object = arg0.getHeader(Constants.REQUEST_ECO_USER_HEADER);
        }
        CurrentUser user = null;
        try{
            String ecoUserStr = new String(DigestUtils.decodeBase64((String) object));
            user = JSON.parseObject(ecoUserStr, CurrentUser.class);
        }catch (Exception e){
            throw new BusinessException(AuthExceptionEnum.NEED_LOGIN);
        }
        if (user == null){
            throw new BusinessException(AuthExceptionEnum.NEED_LOGIN);
        }
        if (null == object) {
            throw new BusinessException(AuthExceptionEnum.NOT_LOGIN);
        }
        return true;
    }
}
