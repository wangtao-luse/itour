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
		/**
		 * 旅行攻略评论审核
		 */
		commentCheckService.updateCommentStatus();
		//工作日志评论审核
		commentCheckService.updateWorkCommentStatus();
	}

}
