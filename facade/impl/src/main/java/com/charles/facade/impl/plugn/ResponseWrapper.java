package com.charles.facade.impl.plugn;

import com.charles.common.result.FoxResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@SuppressWarnings("rawtypes")
@ControllerAdvice(annotations = { RestController.class, Controller.class })
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public Object beforeBodyWrite(Object ret, MethodParameter paramMethodParameter, MediaType paramMediaType, Class paramClass, ServerHttpRequest paramServerHttpRequest,
                                  ServerHttpResponse paramServerHttpResponse) {
        //null表示该controller没有返回值
        if (null == ret) {
            return buildDefaultDataPacket();
        }

        return buildDataPacket(ret);
    }

    @Override
    public boolean supports(MethodParameter paramMethodParameter, Class paramClass) {
        return true;
    }

    /**
     * Wrap unified result
     * 
     * @param data
     * @return
     */
    private FoxResult buildDataPacket(Object data) {
        return new FoxResult(data);
    }

    /**
     * Build default respones
     * 
     * @return
     */
    protected FoxResult buildDefaultDataPacket() {
        return FoxResult.SUCCESS;
    }
}
