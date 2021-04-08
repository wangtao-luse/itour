package com.itour.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.itour.api.TravelApi;
import com.itour.common.redis.RedisManager;
import com.itour.constant.RedisKey;

public class TravelPageviewJob extends QuartzJobBean{
	@Autowired
	 RedisManager redisManager;
		@Autowired
	 TravelApi travelApi;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		saveOrUpdatePageview();
	}
    private void  saveOrUpdatePageview() {
    	//1.浏览量
    	
    	//2.独立访客
    	 String unique = this.redisManager.get(RedisKey.KEY_ITOUR_UNIQUEISITOR_COUNT);        
    	//3.独立IP
    	 String ip = this.redisManager.get(RedisKey.KEY_ITOUR_IP_COUNT);
    
    	
    	
	   
   }
}
