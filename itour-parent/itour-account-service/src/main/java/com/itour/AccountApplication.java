package com.itour;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication /* (exclude = DataSourceAutoConfiguration.class) */
@EnableEurekaClient
@EnableTransactionManagement//开启springboot事务支持
@MapperScan("com.itour.persist")
@EnableCaching//开启基于Spring注解的缓存
public class AccountApplication {
 public static void main(String[] args) {
	SpringApplication.run(AccountApplication.class, args);
}
}
