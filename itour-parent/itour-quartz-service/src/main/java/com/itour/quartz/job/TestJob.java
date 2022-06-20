package com.itour.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Springboot集成quartz步骤
 * 1.导入相关依赖并配置quartz
 * 2.定义工作任务的Job
 * 3.构建JobDetail关联工作任务的Job
 * 4.定义触发器Trigger,将Trigger注册到Scheduler;
 * 
 */

public class TestJob extends QuartzJobBean { // 1.定义工作任务的Job
	private static final Logger logger = LoggerFactory.getLogger(QuartzJobBean.class);
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException { 
		// TODO Auto-generated
																								
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("TestJob----" + sdf.format(new Date()));
	}

}
 
