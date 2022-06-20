package com.itour;




import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*import com.alibaba.druid.pool.DruidDataSource;*/


@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement//开启springboot事务支持
@MapperScan("com.itour.persist")
@EnableFeignClients
//@EnableScheduling
public class QuartzApp {
public static void main(String[] args) {
	SpringApplication.run(QuartzApp.class, args);
}
/**
 * 主数据源
 * @return
 */
	/*
	 * @Primary
	 * 
	 * @Bean
	 * 
	 * @ConfigurationProperties(prefix =
	 * "spring.datasource.dynamic.datasource.master") public DruidDataSource
	 * druidDataSource() { return new DruidDataSource(); }
	 */
 
 
/**
 * 配置Quartz独立数据源的配置
 */
	/*
	 * @Bean
	 * 
	 * @QuartzDataSource
	 * 
	 * @ConfigurationProperties(prefix =
	 * "spring.datasource.dynamic.datasource.quartz") public DataSource
	 * quartzDataSource(){ return new DruidDataSource(); }
	 */


}
