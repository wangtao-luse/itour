package com.itour;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement//开启springboot事务支持
@MapperScan("com.itour.persist")
public class TravelApp {
    public static void main( String[] args ){
    	SpringApplication.run(TravelApp.class, args);
    }
}
