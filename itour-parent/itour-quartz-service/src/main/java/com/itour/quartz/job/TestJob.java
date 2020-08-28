package com.itour.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
/**
 * Spring集成quartz步骤
 * 1.定义工作任务的Job
 * 2.构建JobDetail关联工作任务的Job
 * 3.定义触发器Trigger,将Trigger注册到Scheduler;
 * 
 */
public class TestJob extends QuartzJobBean {
	//1.定义工作任务的Job
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("TestJob----" + sdf.format(new Date()));
	}

}
