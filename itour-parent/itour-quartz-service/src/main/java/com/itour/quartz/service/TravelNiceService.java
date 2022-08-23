package com.itour.quartz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import com.itour.help.ArrayHelper;
import com.itour.model.travel.Nice;
import com.itour.model.travel.TravelInfo;
import com.itour.util.FastJsonUtil;
@Service
public class TravelNiceService {
	private final static Logger logger=LoggerFactory.getLogger(TravelNiceService.class);
	@Autowired
 RedisManager redisManager;
	@Autowired
 TravelApi travelApi;
	@Transactional
	/***
	 * 1.从缓存中获取点赞的数据列表;
	 * 2.查看用户是否已经点赞过,点赞过设置Id和status;
	 * 3.批量插入点赞表
	 * 4.统计文章的点赞数,封装为列表;
	 * 5.批量更新点赞数;
	 * 6.将缓存中的点赞数据删除;
	 */
	public void insertNice() {
		try {
			// 1.从Redis缓存中取出点赞的数据;
			Map<Object, Object> map = redisManager.hget(RedisKey.KEY_ITOUR_NICE);
			// 2.查看该用户是否已经点赞
			List<Nice> saveOrupdateList = new ArrayList<Nice>();
			List<String> tidList = new ArrayList<String>();
			List<TravelInfo> travelInfoList = new ArrayList<TravelInfo>();
			map.forEach((key, v) -> {
				JSONObject jsonObject = FastJsonUtil.objToJSONObject(v);
				Nice nice = jsonObject.toJavaObject(Nice.class);
			    JSONObject tmpJson = new JSONObject();
				Nice tmpObj = new Nice();
				     tmpObj.setTid(nice.getTid());
				     tmpObj.setUid(nice.getUid());
				RequestMessage requestMessage = HttpDataUtil.postData(tmpJson, null);
				ResponseMessage responseMessage = travelApi.getNice(requestMessage);
				if (Constant.SUCCESS_CODE.equals(responseMessage.getResultCode())) {
					// 3.同步数据到数据库
					Map<String, Object> map1 = responseMessage.getReturnResult();
					if (null != map1.get(Constant.COMMON_KEY_RESULT)) {// 点赞或取消点赞过
						// 修改点赞状态（批量修改）
						Nice like = FastJsonUtil.mapToObject(map1, Nice.class, Constant.COMMON_KEY_RESULT);
						nice.setId(like.getId());
						nice.setStatus(nice.getStatus());
						saveOrupdateList.add(like);
					} else {// 插入点赞表（批量插入）
						saveOrupdateList.add(nice);
					}

				}
				tidList.add(String.valueOf(nice.getTid()));//批量更新点赞数;
			});
			// 批量同步点赞数据到数据库
			if (saveOrupdateList.size() > 0) {
				JSONObject tmpJSon = new JSONObject();
				tmpJSon.put(Constant.COMMON_KEY_ARR, saveOrupdateList);
				RequestMessage postData = HttpDataUtil.postData(tmpJSon, null);
				ResponseMessage saveOrUpdateBatchNice = travelApi.saveOrUpdateBatchNice(postData);
			}
			// 4.更新点赞数
			//4.1 统计文章点赞数;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("tids", ArrayHelper.listToStr(tidList));
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
			// 4.2 批量更新点赞数
			jsonObject.clear();
			jsonObject.put(Constant.COMMON_KEY_ARR, travelInfoList);
			RequestMessage request = HttpDataUtil.postData(jsonObject, null);
			if(travelInfoList.size()>0) {
				ResponseMessage updateTravelInfoBatch = this.travelApi.updateTravelInfoBatch(request);
				if(Constant.SUCCESS_CODE.equals(updateTravelInfoBatch.getResultCode())) {
					//4.3清楚缓存
					saveOrupdateList.forEach(key->{
						String hashKey=key.getUid()+"::"+key.getTid();
						boolean hdel = this.redisManager.hdel(RedisKey.KEY_ITOUR_NICE, hashKey);	
					});
				}
			}
			
			
			
			
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
}
