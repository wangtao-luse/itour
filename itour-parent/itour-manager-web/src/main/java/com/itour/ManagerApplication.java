package com.itour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value={"classpath:beetl.properties"})
public class ManagerApplication{
	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}
}