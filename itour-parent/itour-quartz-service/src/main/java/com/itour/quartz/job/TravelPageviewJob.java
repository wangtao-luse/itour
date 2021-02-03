package com.itour.quartz.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.TravelApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestMessage;
import com.itour.constant.RedisKey;
import com.itour.model.travel.Pageview;

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
    	//1.从Redis中取出缓存
    	Map<Object, Object> map = redisManager.hget(RedisKey.KEY_PAGEVIEW);
    	//2.同步数据到数据库
    	List<Pageview> pageviewList = new ArrayList<Pageview>();
         JSONObject jsonObject = new JSONObject();
         jsonObject.put("arr", pageviewList);
         RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
         this.travelApi.saveOrUpdateBatchPageview(requestMessage);
    	
	   
   }
}
