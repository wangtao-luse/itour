package com.itour.quartz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.TravelApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.RedisKey;
import com.itour.exception.BaseException;
import com.itour.model.travel.CommentNice;
import com.itour.model.travel.TravelInfo;
import com.itour.util.ArrayUtils;
import com.itour.util.FastJsonUtil;

@Service
public class TravelCommentNiceService {
	private final static Logger logger=LoggerFactory.getLogger(TravelNiceService.class);
	@Autowired
	RedisManager redisManager;
	@Autowired
	TravelApi travelApi;
	public void insertCommentNice() {
		//1.从缓存中获取评论点赞数
		//2.查看用户是否点赞过对应评论
		//3.点赞过修改状态,没有点赞过新增
		//4.更新评论点赞数
		//5.本次数据从缓存中移除

		try {
			// 1.从Redis缓存中取出点赞的数据;
			Map<Object, Object> map = redisManager.hget(RedisKey.KEY_ITOUR_COMMENT_NICE);
			// 2.查看该用户是否已经点赞
			List<CommentNice> saveOrupdateList = new ArrayList<CommentNice>();
			List<String> tidList = new ArrayList<String>();
			List<TravelInfo> travelInfoList = new ArrayList<TravelInfo>();
			map.forEach((key, v) -> {
				JSONObject jsonObject = FastJsonUtil.objToJSONObject(v);
				CommentNice nice = jsonObject.toJavaObject(CommentNice.class);
			    JSONObject tmpJson = new JSONObject();
				CommentNice tmpObj = new CommentNice();
				     tmpObj.setCid(nice.getCid());
				     tmpObj.setUid(nice.getUid());
				RequestMessage requestMessage = HttpDataUtil.postData(tmpJson, null);
				ResponseMessage responseMessage = travelApi.getCommentNice(requestMessage);
				if (Constant.SUCCESS_CODE.equals(responseMessage.getResultCode())) {
					// 3.同步数据到数据库
					Map<String, Object> map1 = responseMessage.getReturnResult();
					if (null != map1.get(Constant.COMMON_KEY_RESULT)) {// 点赞或取消点赞过
						// 修改点赞状态（批量修改）
						CommentNice like = FastJsonUtil.mapToObject(map1, CommentNice.class, Constant.COMMON_KEY_RESULT);
						nice.setId(like.getId());
						nice.setStatus(nice.getStatus());
						saveOrupdateList.add(like);
					} else {// 插入点赞表（批量插入）
						saveOrupdateList.add(nice);
					}

				}
				tidList.add(String.valueOf(nice.getCid()));//批量更新点赞数;
			});
			// 批量同步点赞数据到数据库
			if (saveOrupdateList.size() > 0) {
				JSONObject tmpJSon = new JSONObject();
				tmpJSon.put(Constant.COMMON_KEY_ARR, saveOrupdateList);
				RequestMessage postData = HttpDataUtil.postData(tmpJSon, null);
				ResponseMessage saveOrUpdateBatchNice = travelApi.saveOrUpdateBatchCommentNice(postData);
			}
			// 4.更新点赞数
			//4.1 统计文章点赞数;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("tids", ArrayUtils.listToStr(tidList));
			RequestMessage postData = HttpDataUtil.postData(jsonObject, null);
			ResponseMessage respose = travelApi.countCommentNiceList(postData);
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
						String hashKey=key.getUid()+"::"+key.getCid();
						boolean hdel = this.redisManager.hdel(RedisKey.KEY_ITOUR_COMMENT_NICE, hashKey);	
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
