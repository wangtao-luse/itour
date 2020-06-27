package com.itour.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.itour.interceptor.SpringMVCInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	private SpringMVCInterceptor springMVCInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry ) {
		//SpringMVCInterceptor springMVCInterceptor = new SpringMVCInterceptor();
		//addPathPatterns("/**")表示拦截所有的请求
		//excludePathPatterns("/login")表示排除/login请求
		String [] addpath= {"/**"};
		String [] excludePath= {"/error","/css/**","/js/**","/img/**"};
		interceptorRegistry.addInterceptor(springMVCInterceptor).excludePathPatterns(excludePath).addPathPatterns(addpath);
	}

}
