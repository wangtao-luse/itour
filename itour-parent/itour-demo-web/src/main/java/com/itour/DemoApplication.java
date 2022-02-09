package com.itour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@PropertySource(value={"classpath:beetl.properties"})
public class DemoApplication{
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}