package com.itour.config;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.collect.Sets;

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
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
//相关资料：https://blog.csdn.net/qq_17213067/article/details/111573316
//https://blog.csdn.net/qq_34735938/article/details/109224339
@EnableOpenApi
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
	@Value("${swagger.enable}")
	private boolean enable;
  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.OAS_30)
        .pathMapping("/")
        // 定义是否开启swagger，false为关闭，可以通过变量控制
        .enable(enable)
        // 将api的元信息设置为包含在json ResourceListing响应中。
        .apiInfo(apiInfo())
        // 接口调试地址
        .host("http://localhost:9093")
        // 选择哪些接口作为swagger的doc发布
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.itour"))
        .paths(PathSelectors.any())
        .build()
        // 支持的通讯协议集合
        .protocols(Sets.newHashSet("http", "https"))
        // 授权信息设置，必要的header token等认证信息
        .securitySchemes(securitySchemes())
        // 授权信息全局应用
        .securityContexts(securityContexts());
  }

  /** API 页面上半部分展示信息 */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(" Api Doc")
        .description("SpringFox 3.0.0 发布: http://localhost:9093/itour/swagger-ui/index.html")
        .contact(new Contact("wangtao", null, "wwangtaoc11@163.com"))
        .version(
            "Application Version: "
                + "1.0.0"
                + ", Spring Boot Version: "
                + SpringBootVersion.getVersion())
        .build();
  }

  /** 设置授权信息 */
  private List securitySchemes() {
    ApiKey apiKey = new ApiKey("BASE_TOKEN", "token", In.HEADER.toValue());
    return Collections.singletonList(apiKey);
  }

  /** 授权信息全局应用 */
  private List securityContexts() {
    return Collections.singletonList(
        SecurityContext.builder()
            .securityReferences(
                Collections.singletonList(
                    new SecurityReference(
                        "BASE_TOKEN",
                        new AuthorizationScope[] {new AuthorizationScope("global", "")})))
            .build());
  }

  /** 通用拦截器排除swagger设置，所有拦截器都会自动加swagger相关的资源排除信息 */
  @SuppressWarnings("unchecked")
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    try {
      Field registrationsField =
          FieldUtils.getField(InterceptorRegistry.class, "registrations", true);
      List<InterceptorRegistration> registrations =
          (List<InterceptorRegistration>) ReflectionUtils.getField(registrationsField, registry);
      if (registrations != null) {
        for (InterceptorRegistration interceptorRegistration : registrations) {
          interceptorRegistration
              .excludePathPatterns("/swagger**/**")
              .excludePathPatterns("/webjars/**")
              .excludePathPatterns("/v3/**")
              .excludePathPatterns("/doc.html");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


