package com.itour.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


//https://www.jb51.net/article/140541.htm
//https://blog.csdn.net/typ1805/article/details/82998351
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
/**
 * 在实际项目中，我们一个系统可能会定义多个定时任务。那么多个定时任务之间是可以相互独立且可以并行执行的。
 * Spring默认会创建一个单线程池。当多个任务并发（或需要在同一时间）执行时，任务调度器就会出现时间漂移，任务执行时间将不确定。
 * 新增一个配置类，实现SchedulingConfigurer接口。重写configureTasks方法，通过taskRegistrar设置自定义线程池。
 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(20);
	}
	//解决SpringBoot使用Quartz无法注入Bean的问题
	//https://my.oschina.net/tianshl/blog/1818841
	/*
	 * private SpringJobFactory jobFactory; public void
	 * setJobFactory(SpringJobFactory jobFactory) { this.jobFactory = jobFactory; }
	 */
	/**
     * 配置SchedulerFactoryBean
     *
     * 将一个方法产生为Bean并交给Spring容器管理
     */
	/*
	 * @Bean public SchedulerFactoryBean schedulerFactoryBean() { //
	 * Spring提供SchedulerFactoryBean为Scheduler提供配置信息,并被Spring容器管理其生命周期
	 * SchedulerFactoryBean factory = new SchedulerFactoryBean(); // 设置自定义Job
	 * Factory，用于Spring管理Job bean factory.setJobFactory(jobFactory); return factory;
	 * }
	 * 
	 * @Bean(name = "scheduler") public Scheduler scheduler() { return
	 * schedulerFactoryBean().getScheduler(); }
	 */
}

 
