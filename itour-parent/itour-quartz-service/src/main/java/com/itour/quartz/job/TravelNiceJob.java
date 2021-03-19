package com.itour.quartz.job;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.TravelApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.RedisKey;
import com.itour.exception.BaseException;
import com.itour.model.travel.Nice;
import com.itour.model.travel.TravelInfo;
import com.itour.util.ArrayUtils;

public class TravelNiceJob extends QuartzJobBean {
private final static Logger logger=LoggerFactory.getLogger(TravelNiceJob.class);
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
		try {

			// 1.从Redis缓存中取出点赞的数据;
			Map<Object, Object> map = redisManager.hget(RedisKey.KEY_NICE);
			List<Nice> saveOrupdateList = new ArrayList<Nice>();
			List<String> tidList = new ArrayList<String>();
			List<TravelInfo> travelInfoList = new ArrayList<TravelInfo>();
			// 2.查看该用户是否已经点赞
			map.forEach((k, v) -> {
				Nice nice = (Nice) v;
				JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(nice));
				RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
				ResponseMessage responseMessage = travelApi.getNice(requestMessage);
				if (Constant.SUCCESS_CODE.equals(responseMessage.getResultCode())) {
					// 3.同步数据到数据库
					Map<String, Object> returnResult = responseMessage.getReturnResult();
					if (null != returnResult.get(Constant.COMMON_KEY_RESULT)) {// 点赞或取消点赞过
						// 修改点赞状态
						Nice like = (Nice) returnResult.get(Constant.COMMON_KEY_RESULT);
						nice.setId(like.getId());
						saveOrupdateList.add(like);
					} else {// 插入点赞表
						saveOrupdateList.add(nice);
					}

				}
				tidList.add(String.valueOf(nice.getTid()));
			});
			// 批量同步点赞数据
			if (saveOrupdateList.size() > 0) {
				JSONObject tmpJSon = new JSONObject();
				tmpJSon.put(Constant.COMMON_KEY_ARR, saveOrupdateList);
				RequestMessage postData = HttpDataUtil.postData(tmpJSon, null);
				ResponseMessage saveOrUpdateBatchNice = travelApi.saveOrUpdateBatchNice(postData);
			}
			// 4.更新点赞数
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("tids", ArrayUtils.listToStr(tidList));
			RequestMessage postData = HttpDataUtil.postData(jsonObject, null);
			ResponseMessage respose = travelApi.countNiceList(postData);
			if (Constant.SUCCESS_CODE.equals(respose.getResultCode())) {
				Map<String, Object> returnResult = respose.getReturnResult();
				if (null != returnResult) {
					List<Map<String, Object>> list = (List<Map<String, Object>>) returnResult
							.get(Constant.COMMON_KEY_RESULT);
					for (Map<String, Object> map2 : list) {
						TravelInfo travelInfo = new TravelInfo();
						Object tid = map2.get("tid");
						Object count = map2.get("count");
						travelInfo.setId(Long.valueOf(tid.toString()));
						travelInfo.setNiceCount(Integer.valueOf(count.toString()));
						travelInfoList.add(travelInfo);

					}
				}

			}
			// 批量更新点赞数
			jsonObject.clear();
			jsonObject.put(Constant.COMMON_KEY_ARR, travelInfoList);
			RequestMessage request = HttpDataUtil.postData(jsonObject, null);
			ResponseMessage updateTravelInfoBatch = this.travelApi.updateTravelInfoBatch(request);
		} catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
public static void main(String[] args) {
	//join();
	 String [] s = { "a", "b", "c", "d", "e" };
	 String join2 = String.join(",", s);
	 System.out.println(join2);
	 
	 
	List<String> tidList = new ArrayList<String>();
	tidList.add("1");
	tidList.add("2");
	tidList.add("3");
	tidList.add("4");
	String [] arr = new String[tidList.size()];
	String[] array = tidList.toArray(arr);
	System.out.println(String.join(",", array));
	
}
}
