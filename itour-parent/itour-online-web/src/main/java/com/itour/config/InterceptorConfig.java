package com.itour.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.itour.interceptor.SpringMVCInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	public void addInterceptor(InterceptorRegistry interceptorRegistry ) {
		SpringMVCInterceptor springMVCInterceptor = new SpringMVCInterceptor();
		//addPathPatterns("/**")表示拦截所有的请求
		//excludePathPatterns("/login")表示排除/login请求
		interceptorRegistry.addInterceptor(springMVCInterceptor).addPathPatterns("/**");
	}

}
