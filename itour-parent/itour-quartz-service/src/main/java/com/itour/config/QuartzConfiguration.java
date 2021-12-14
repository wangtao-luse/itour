package com.itour.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itour.quartz.job.ArticleCheck;
import com.itour.quartz.job.CommentCheckJob;
import com.itour.quartz.job.TravelNiceJob;
import com.itour.quartz.job.TravelPageviewJob;

@Configuration
public class QuartzConfiguration {
	/**
	 * 攻略和日志审核30秒一次
	 * @return
	 */
	   @Bean
	    public JobDetail travelJobDetail(){
	        JobDetail jobDetail = JobBuilder.newJob(ArticleCheck.class).storeDurably().build();
	        return jobDetail;
	    }
	    @Bean
	    public Trigger travelTrigger(){
	    	//构建
	    	CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
	        Trigger trigger = TriggerBuilder.newTrigger()
	                .forJob(travelJobDetail())
	                .withSchedule(cronSchedule)
	                .build();
	        return trigger;
	    }
	    /**
	     * 攻略和日志评论审核30秒一次
	     * @return
	     */
	    @Bean
	    public JobDetail workJobDetail(){
	    	JobDetail jobDetail = JobBuilder.newJob(CommentCheckJob.class).storeDurably().build();
	    	return jobDetail;
	    }
	    @Bean
	    public Trigger workTrigger(){
	    	//构建
	    	CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
	    	Trigger trigger = TriggerBuilder.newTrigger()
	    			.forJob(workJobDetail())
	    			.withSchedule(cronSchedule)
	    			.build();
	    	return trigger;
	    }
	    /**
	     * 日志和攻略点赞数据更新 5分钟一次
	     * @return
	     */
	    @Bean
	    public JobDetail niceJobDetail(){
	    	JobDetail jobDetail = JobBuilder.newJob(TravelNiceJob.class).storeDurably().build();
	    	return jobDetail;
	    }
	    @Bean
	    public Trigger niceTrigger(){
	    	//构建
	    	CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0 0/5 * * * ?");
	    	Trigger trigger = TriggerBuilder.newTrigger()
	    			.forJob(workJobDetail())
	    			.withSchedule(cronSchedule)
	    			.build();
	    	return trigger;
	    }
	    /**
	     * 日志和攻略浏览量数据更新 5分钟一次
	     * @return
	     */
	    @Bean
	    public JobDetail pvJobDetail(){
	    	JobDetail jobDetail = JobBuilder.newJob(TravelPageviewJob.class).storeDurably().build();
	    	return jobDetail;
	    }
	    @Bean
	    public Trigger pvTrigger(){
	    	//构建
	    	CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0 0/5 * * * ?");
	    	Trigger trigger = TriggerBuilder.newTrigger()
	    			.forJob(workJobDetail())
	    			.withSchedule(cronSchedule)
	    			.build();
	    	return trigger;
	    }

}
