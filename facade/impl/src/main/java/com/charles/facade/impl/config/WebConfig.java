package com.charles.facade.impl.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.bbd.eco.plugin.LoginEcoUserArgumentResolver;
import com.bbd.eco.plugin.LoginUserArgumentResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@SuppressWarnings("all")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(list);
        converters.add(fastConverter);

        //处理Indicator响应序列化
        MappingJackson2HttpMessageConverter indicatorConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        indicatorConverter.setObjectMapper(objectMapper);
        List<MediaType> indicatorMediaTypes = new ArrayList<MediaType>();
        indicatorMediaTypes.add(new MediaType("application", "vnd.spring-boot.actuator.v1+json"));
        indicatorConverter.setSupportedMediaTypes(indicatorMediaTypes);
        converters.add(indicatorConverter);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new LoginUserArgumentResolver());
        argumentResolvers.add(new LoginEcoUserArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry ){
        //网站配置生成器：添加一个拦截器，拦截路径为整个项目
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/api/v1.0/eco/**")
                .excludePathPatterns("/api/v1.0/eco/admin/login"
                        ,"/api/v1.0/eco/portal/**"
                        ,"/api/v1.0/eco/file/**");
    }
}
