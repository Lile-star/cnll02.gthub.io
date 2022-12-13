package com.crm.config;

import com.crm.interceptors.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfig  implements WebMvcConfigurer {
    @Resource
    private PermissionInterceptor permissionInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(permissionInterceptor);

        interceptor.addPathPatterns("/workbench/**");

    }
}
