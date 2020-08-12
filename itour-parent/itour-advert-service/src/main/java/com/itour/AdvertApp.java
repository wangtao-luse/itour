package com.itour;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient//订阅eureka服务
@EnableTransactionManagement//开启springboot事务支持
@MapperScan("com.itour.persist")
@EnableCaching//开启基于Spring注解的缓存
public class AdvertApp {
public static void main(String[] args) {
	SpringApplication.run(AdvertApp.class, args);
}
}
