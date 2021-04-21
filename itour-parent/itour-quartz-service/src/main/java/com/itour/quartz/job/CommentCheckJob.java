package com.itour.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.itour.quartz.service.CommentCheckService;

public class CommentCheckJob extends QuartzJobBean{
	@Autowired
	CommentCheckService commentCheckService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		commentCheckService.updateCommentStatus();
	}

}
