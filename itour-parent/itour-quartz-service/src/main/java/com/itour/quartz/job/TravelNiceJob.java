package com.itour.quartz.job;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itour.api.TravelApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.TravelRedisKey;
import com.itour.model.travel.Nice;

public class TravelNiceJob extends QuartzJobBean {
	@Autowired
 RedisManager redisManager;
	@Autowired
 TravelApi travelApi;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		//1.从Redis缓存中取出点赞的数据;
		//2.查看该用户是否已经点赞
		//3.同步数据到数据库
		//5.每5分钟执行一次
		insertNice();
		
	}
@Transactional
private void insertNice() {
	//1.从Redis缓存中取出点赞的数据;
	Map<Object, Object> map = redisManager.hget(TravelRedisKey.KEY_NICE);
	List<Nice> saveOrupdateList = new ArrayList<Nice>();
	List<Integer> tidList = new ArrayList<Integer>();
	//2.查看该用户是否已经点赞
	  map.forEach((k,v)->{
		  Nice nice =(Nice)v;
		  JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(nice));
		  RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
		  ResponseMessage responseMessage = travelApi.getNice(requestMessage);
		  if(Constant.SUCCESS_CODE.equals(responseMessage.getResultCode())) {
			//3.同步数据到数据库	
			  Map<String, Object> returnResult = responseMessage.getReturnResult();
			  if(null!=returnResult.get(Constant.COMMON_KEY_RESULT)) {//点赞或取消点赞过
				 // 修改点赞状态
				Nice like = (Nice)returnResult.get(Constant.COMMON_KEY_RESULT);
				nice.setId(like.getId());
				saveOrupdateList.add(like);
			  }else {//插入点赞表
				  saveOrupdateList.add(nice);
			  }
			  
		  }
		  tidList.add(nice.getTid());
	  });
	  //批量同步数据
	  if(saveOrupdateList.size()>0) {
		  JSONObject tmpJSon = new JSONObject();
		  tmpJSon.put(Constant.COMMON_KEY_ARR, saveOrupdateList);
		  RequestMessage postData = HttpDataUtil.postData(tmpJSon, null);
		  ResponseMessage saveOrUpdateBatchNice = travelApi.saveOrUpdateBatchNice(postData);
	  }
	  //4.更新点赞数
	  JSONObject jsonObject = new JSONObject();
	  jsonObject.put("tids", tidList);
	  RequestMessage postData = HttpDataUtil.postData(jsonObject, null);
	  ResponseMessage countNice = travelApi.countNice(postData);
	  
	
}
}
