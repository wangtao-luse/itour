package com.itour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaServer
public class EurekaApplication {
public static void main(String[] args) {
	//https://blog.csdn.net/lisheng19870305/article/details/104822886/
	SpringApplication springApplication = new SpringApplication(EurekaApplication.class);
	springApplication.setWebApplicationType(WebApplicationType.SERVLET);;
	springApplication.run(args);
	//SpringApplication.run(EurekaApplication.class, args);
}
}
