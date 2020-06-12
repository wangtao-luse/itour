package com.itour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
@PropertySource(value={"classpath:beetl.properties"})
@EnableCaching//开启基于注解的缓存
public class OnlienApplication {
public static void main(String[] args) {
	SpringApplication.run(OnlienApplication.class, args);
}
}
