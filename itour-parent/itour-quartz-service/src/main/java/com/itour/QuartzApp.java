package com.itour;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement//开启springboot事务支持
@MapperScan("com.itour.persist")
@EnableScheduling
public class QuartzApp {
public static void main(String[] args) {
	SpringApplication.run(QuartzApp.class, args);
}
}
