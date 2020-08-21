package com.itour;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.itour.persist")
@EnableTransactionManagement//开启springboot事务支持
public class DictionaryApplication {
public static void main(String[] args) {
	SpringApplication.run(DictionaryApplication.class, args);
}
}
