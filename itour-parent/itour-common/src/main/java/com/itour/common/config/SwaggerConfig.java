package com.itour.common.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
/**
 * Docket.enable(true):是否启动swagger,true开启,fasle关闭;
 * Docket.select():设置哪些接口暴露给Swagger展示
 * Docket.select().api(selector):配置扫描接口的方式
 *   basePackage(package):指定要扫描的包
 *   any():扫描全部
 *   none():不扫描
 *   withClassAnnotation(annotation):扫描类上的注解，多数是一个注解的反射对象
 *   withMethodAnnotation(annotation):扫描方法上的注解
 *   
 *   注解使用：
 *   Api用来指定一个controller中的各个接口的通用说明
 *   @Api(tags = "用户模块")
 *   Operation:用来说明一个方法
 *   @Operation(summary = "注册提交")
 *   ApiImplicitParam:用来说明一个请求参数 
 *   @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "int",paramType = "body")
 *   paramType="body" 代表参数应该放在请求的什么地方：
    header-->放在请求头。请求参数的获取：@RequestHeader(代码中接收注解)
    query-->用于get请求的参数拼接。请求参数的获取：@RequestParam(代码中接收注解)
    path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
    body-->放在请求体。请求参数的获取：@RequestBody(代码中接收注解)
    form（不常用）

 *   相关资料:
 *   https://www.cnblogs.com/architectforest/p/13470170.html
 * @author wangtao
 *
 */

//@Configuration
//@EnableOpenApi // 开启Swagger自定义接口文档
public class SwaggerConfig {
   @Bean
	public Docket docket() {
		return new Docket(DocumentationType.OAS_30)
				.enable(true)				
				.apiInfo(apiInfo())
				.select()
				//.apis(RequestHandlerSelectors.basePackage("com.itour.api"))
				//.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				//.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				//.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
		        .build()
		        // 支持的通讯协议集合
                .protocols(newHashSet("https", "http"))
				// 授权信息设置，必要的header token等认证信息
		        .securitySchemes(securitySchemes())
		        // 授权信息全局应用
		        .securityContexts(securityContexts());

		
				
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("爱旅行API文档")
				.description("API文档描述,swagger-ui/index.html")
				.contact(new Contact("wangtao", null, "wwangtaoc11@163.com"))
				.version("springboot Version:"+SpringBootVersion.getVersion())
				.build();
	}
	@SafeVarargs
	private final <T> Set<T> newHashSet(T... ts) {
	    if (ts.length > 0) {
	        return new LinkedHashSet<>(Arrays.asList(ts));
	    }
	    return null;
	}
	 /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("BASE_TOKEN", "token", In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }
    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }
   

}
