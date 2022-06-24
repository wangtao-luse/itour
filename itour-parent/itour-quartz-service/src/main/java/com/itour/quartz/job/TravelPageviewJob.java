package com.itour.quartz.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.itour.constant.RedisKey;
import com.itour.model.travel.Pageview;
import com.itour.model.travel.TravelInfo;

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
    	//得到所有的浏览过文章的Key
    	Set<Object> smembers = this.redisManager.smembers(RedisKey.KEY_ITOUR_PVS);
    	List<TravelInfo> t = new ArrayList<TravelInfo>();
    	for (Object smember : smembers) {
    		TravelInfo info = new TravelInfo();
    		 String[] split = String.valueOf(smember).split("::");
    		 String key = split[1]+"::"+split[2];
    		 String pv = this.redisManager.get(key);
    		 info.setId(Long.valueOf(split[2]));
    		 info.setPv(Integer.valueOf(pv));
    		 t.add(info);
		}
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("arr", t);
    	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
    	ResponseMessage updatePvBatch = travelApi.updatePvBatch(requestMessage);
    	if(Constant.SUCCESS_CODE.equals(updatePvBatch.getResultCode())) {
    		//清除key
    		for (Object smember : smembers) {
        		 String[] split = String.valueOf(smember).split("::");
        		 String key = split[1]+"::"+split[2];
                 this.redisManager.del(key); 
                 this.redisManager.setRemove(RedisKey.KEY_ITOUR_PVS, smember);
        		 
    		}
    	}
	   
   }
	public void updateIp() {
		//1.获取独立IP数
    	 String ip = this.redisManager.get(RedisKey.KEY_ITOURINFO_IP_LIST);
    	//2.移除对应的key
	}
	public void updateUnique() {
		//1.获取独立访客数
		String unique = this.redisManager.get(RedisKey.KEY_ITOUR_UNIQUEISITOR_COUNT);     	 
    	 //2.移除对应的key
    	 Set<Object> smembers = this.redisManager.smembers(RedisKey.KEY_ITOUR_UNIQUEISITORS);
	}
    /**
     *  攻略文章浏览量
     */
	public List<Pageview> updateBatchPv() {
		//1.获取浏览过的浏览过的Key
    	Set<Object> pvKey = this.redisManager.smembers(RedisKey.ITOUR_PAGEVIEW_IDS);
    	//2.批量刷新旅行信息浏览数表
    	List<Pageview> pvList = new ArrayList<Pageview>();
    	for (Object object : pvKey) {
    		Pageview t = new Pageview();
    	      String[] split = String.valueOf(object).split("::");
    	      String pv = this.redisManager.get(String.valueOf(object));
    		  t.setPageVew(Integer.valueOf(pv));
    		  t.setTid(Long.valueOf(split[2]));
    		  pvList.add(t);
		}
    	
    	//3.从缓存中移除对应的key
    	return pvList;
	}
	
}
