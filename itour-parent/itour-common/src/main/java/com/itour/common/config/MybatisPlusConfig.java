package com.itour.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
@Configuration
public class MybatisPlusConfig {
	@Bean//分页
public PaginationInterceptor  paginationInterceptor() {
	PaginationInterceptor  paginationInterceptor  = new PaginationInterceptor();
	paginationInterceptor.setDialectType("mysql");
	return paginationInterceptor;
}
	
}
