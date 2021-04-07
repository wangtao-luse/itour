package com.itour;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication /* (exclude = DataSourceAutoConfiguration.class) *///pringboot的主配置类
@EnableEurekaClient//将服务注册到注册中心(eureka)
@EnableTransactionManagement//开启springboot事务支持
@MapperScan("com.itour.persist")//扫描指定的Mapper文件
@EnableCaching//开启基于Spring注解的缓存
public class AccountApp {
 public static void main(String[] args) {
	SpringApplication.run(AccountApp.class, args);
}
}
