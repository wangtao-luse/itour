package com.itour;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication /* (exclude = DataSourceAutoConfiguration.class) */
@EnableEurekaClient
@MapperScan("com.itour.persist")
public class AccountApplication {
 public static void main(String[] args) {
	SpringApplication.run(AccountApplication.class, args);
}
}
