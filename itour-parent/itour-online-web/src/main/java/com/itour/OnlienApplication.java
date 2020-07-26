package com.itour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
@PropertySource(value={"classpath:beetl.properties"})

public class OnlienApplication {
public static void main(String[] args) {
	SpringApplication.run(OnlienApplication.class, args);
}
}
