package com.itour.common.config;


import org.apache.el.stream.Optional;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
	
	    @Bean
	    public Docket createRestApi() {
	        return new Docket(DocumentationType.OAS_30)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
	                .paths(PathSelectors.any())
	                .build();
	    }
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("Swagger3接口文档")
	                .description("爱旅行API文档")
	                .contact(new Contact("wangtao", "www.wangtao.club", "wwangtaoc11@gmail.com"))
	                .version("springboot version:"+SpringBootVersion.getVersion())
	                .build();
	    }
	   
	    
	
}
