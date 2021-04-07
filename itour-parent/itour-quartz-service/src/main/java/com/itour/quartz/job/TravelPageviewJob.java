package com.itour.quartz.job;

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
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.travel.TravelInfo;
import com.itour.util.FastJsonUtil;

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
    	//1.查询出所有的文章编号
    	JSONObject jsonObject = new JSONObject();
    	TravelInfo info = new TravelInfo();
    	jsonObject.put("vo", info);
    	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
    	ResponseMessage list = this.travelApi.queryTravelInfoList(requestMessage);
    	if(Constant.SUCCESS_CODE.equals(list.getResultCode())) {
    		Map<String, Object> result = list.getReturnResult();
    		List<TravelInfo> mapToList = FastJsonUtil.mapToList(result, TravelInfo.class, Constant.COMMON_KEY_RESULT);
    		for (TravelInfo travelInfo : mapToList) {
    			String key="";
				String string = this.redisManager.get(key);
			}
    	}
    	//2.根据Key从缓存中获取对应的独立IP访问数;
    	//3.更新独立IP访问数到数据库
    	
    	
	   
   }
}
