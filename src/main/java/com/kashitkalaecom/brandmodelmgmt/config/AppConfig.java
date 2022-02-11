package com.kashitkalaecom.brandmodelmgmt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kashitkalaecom.brandmodelmgmt.interceptor.RequestInterceptor;

@Configuration
public class AppConfig implements WebMvcConfigurer
{
    

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new RequestInterceptor());
    }
}
