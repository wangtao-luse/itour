package com.itour.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itour.quartz.job.TravelJob;

@Configuration
public class QuartzConfiguration {
	   @Bean
	    public JobDetail travelJobDetail(){
	        JobDetail jobDetail = JobBuilder.newJob(TravelJob.class).storeDurably().build();
	        return jobDetail;
	    }
	    @Bean
	    public Trigger travelTrigger(){
	    	//构建
	    	CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
	        Trigger trigger = TriggerBuilder.newTrigger()
	                .forJob(travelJobDetail())
	                .withSchedule(cronSchedule)
	                .build();
	        return trigger;
	    }

}
