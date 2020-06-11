package com.itour.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
@Configuration
public class MybatisPlusConfig {
	@Bean
public PaginationInterceptor  paginationInterceptor() {
	PaginationInterceptor  paginationInterceptor  = new PaginationInterceptor();
	return paginationInterceptor;
}
}
