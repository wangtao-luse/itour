package com.itour.quartz.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.itour.QuartzApp;
import com.itour.quartz.job.TravelNiceJob;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuartzApp.class)
public class TestQuartz {
@Test
public void testNice() {
	//1.创建job
	JobDetail jobDetail = JobBuilder.newJob(TravelNiceJob.class).storeDurably().build();
	//构建
	CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
    Trigger trigger = TriggerBuilder.newTrigger()
            .forJob(jobDetail)
            .withSchedule(cronSchedule)
            .build();
}
}
