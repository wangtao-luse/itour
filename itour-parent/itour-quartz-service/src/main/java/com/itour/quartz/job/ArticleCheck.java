package com.itour.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.itour.quartz.service.ArticleCheckService;

public class ArticleCheck extends QuartzJobBean {
	@Autowired
	ArticleCheckService articleCheckService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		articleCheckService.checkTravel();
		
	}
	
}

