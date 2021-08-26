package com.itour.quartz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.WorkApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.RedisKey;
import com.itour.exception.BaseException;
import com.itour.model.work.Like;
import com.itour.model.work.WorkInfo;
import com.itour.util.ArrayUtils;
import com.itour.util.FastJsonUtil;
@Service
public class WorkLikeService {
	private final static Logger logger=LoggerFactory.getLogger(WorkLikeService.class);
	@Autowired
	 RedisManager redisManager;
	@Autowired
	WorkApi workApi;
	public void insertLike() {
		try {
			// 1.从Redis缓存中取出点赞的数据;
			Map<Object, Object> map = redisManager.hget(RedisKey.KEY_WORK_NICE);
			// 2.查看该用户是否已经点赞
			List<Like> saveOrupdateList = new ArrayList<Like>();
			List<String> tidList = new ArrayList<String>();
			List<WorkInfo> workinfoList = new ArrayList<WorkInfo>();
			map.forEach((key, v) -> {
				JSONObject jsonObject = FastJsonUtil.objToJSONObject(v);
				Like like = jsonObject.toJavaObject(Like.class);
			    JSONObject tmpJson = new JSONObject();
			    Like tmpObj = new Like();
				     tmpObj.setWid(like.getWid());
				     tmpObj.setUid(like.getUid());
				RequestMessage requestMessage = HttpDataUtil.postData(tmpJson, null);
				
				ResponseMessage responseMessage = workApi.getLike(requestMessage);
				if (Constant.SUCCESS_CODE.equals(responseMessage.getResultCode())) {
					// 3.同步数据到数据库
					Map<String, Object> map1 = responseMessage.getReturnResult();
					if (null != map1.get(Constant.COMMON_KEY_RESULT)) {// 点赞或取消点赞过
						// 修改点赞状态（批量修改）
						Like l = FastJsonUtil.mapToObject(map1, Like.class, Constant.COMMON_KEY_RESULT);
						l.setId(l.getId());
						l.setStatus(l.getStatus());
						saveOrupdateList.add(l);
					} else {// 插入点赞表（批量插入）
						saveOrupdateList.add(like);
					}

				}
				tidList.add(String.valueOf(like.getWid()));//批量更新点赞数;
			});
			// 批量同步点赞数据到数据库
			if (saveOrupdateList.size() > 0) {
				JSONObject tmpJSon = new JSONObject();
				tmpJSon.put(Constant.COMMON_KEY_ARR, saveOrupdateList);
				RequestMessage postData = HttpDataUtil.postData(tmpJSon, null);
				ResponseMessage saveOrUpdateBatchNice = workApi.saveOrUpdateBatchLike(postData);
			}
			// 4.更新点赞数
			//4.1 统计文章点赞数;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("tids", ArrayUtils.listToStr(tidList));
			RequestMessage postData = HttpDataUtil.postData(jsonObject, null);
			ResponseMessage respose = workApi.countLikeList(postData);
			if (Constant.SUCCESS_CODE.equals(respose.getResultCode())) {
				Map<String, Object> returnResult = respose.getReturnResult();
				if (null != returnResult) {
					List<Map<String, Object>> list = (List<Map<String, Object>>) returnResult
							.get(Constant.COMMON_KEY_RESULT);
					for (Map<String, Object> map2 : list) {
						WorkInfo workInfo = new WorkInfo();
						Object tid = map2.get("tid");
						Object count = map2.get("count");
						workInfo.setId(Long.valueOf(tid.toString()));
						workInfo.setLikeCount(Integer.valueOf(count.toString()));
						workinfoList.add(workInfo);
					}
				}

			}
			// 4.2 批量更新点赞数
			jsonObject.clear();
			jsonObject.put(Constant.COMMON_KEY_ARR, workinfoList);
			RequestMessage request = HttpDataUtil.postData(jsonObject, null);
			if(workinfoList.size()>0) {
				ResponseMessage updateTravelInfoBatch = this.workApi.updateWorkInfoBatch(request);
				if(Constant.SUCCESS_CODE.equals(updateTravelInfoBatch.getResultCode())) {
					//4.3清楚缓存
					saveOrupdateList.forEach(key->{
						String hashKey=key.getUid()+"::"+key.getWid();
						boolean hdel = this.redisManager.hdel(RedisKey.KEY_WORK_NICE, hashKey);	
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
