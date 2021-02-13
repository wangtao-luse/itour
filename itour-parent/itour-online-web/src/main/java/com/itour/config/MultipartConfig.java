package com.itour.config;


import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;


@Configuration
public class MultipartConfig {
	 @Bean
	    public MultipartConfigElement multipartConfigElement() {
	        MultipartConfigFactory factory = new MultipartConfigFactory();
	        //文件最大
	        factory.setMaxFileSize(DataSize.parse("100MB"));
	        // 设置总上传数据总大小
	        factory.setMaxRequestSize(DataSize.parse("100MB"));
	        return factory.createMultipartConfig();
	    }

}
