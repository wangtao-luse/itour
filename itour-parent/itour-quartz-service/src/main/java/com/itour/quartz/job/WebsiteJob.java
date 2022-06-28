package com.itour.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.itour.quartz.service.WebsiteSerice;
/**
 * 网站访问量(独立IP、浏览量PV、访问次数VV、独立访客数据处理)
 * @author wwang
 *
 */
public class WebsiteJob extends QuartzJobBean{
	@Autowired
	WebsiteSerice websiteSerice;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		websiteSerice.insertWebsiteInfo();
	}

}
