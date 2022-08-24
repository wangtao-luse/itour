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
import com.itour.help.ArrayHelper;
import com.itour.model.work.CommentLike;
import com.itour.model.work.WorkInfo;
import com.itour.util.FastJsonUtil;
@Service
public class WorkCommentLikeService {
	private final static Logger logger=LoggerFactory.getLogger(WorkCommentLikeService.class);
	@Autowired
	RedisManager redisManager;
	@Autowired
	WorkApi workApi;
	public void insertCommentLike() {
		//1.从缓存中获取评论点赞数
		//2.查看用户是否点赞过对应评论
		//3.点赞过修改状态,没有点赞过新增
		//4.更新评论点赞数
		//5.本次数据从缓存中移除

		try {
			// 1.从Redis缓存中取出点赞的数据;
			Map<Object, Object> map = redisManager.hget(RedisKey.KEY_WORK_COMMENT_NICE);
			// 2.查看该用户是否已经点赞
			List<CommentLike> saveOrupdateList = new ArrayList<CommentLike>();
			List<String> tidList = new ArrayList<String>();
			List<WorkInfo> travelInfoList = new ArrayList<WorkInfo>();
			map.forEach((key, v) -> {
				JSONObject jsonObject = FastJsonUtil.objToJSONObject(v);
				CommentLike nice = jsonObject.toJavaObject(CommentLike.class);
			    JSONObject tmpJson = new JSONObject();
			    CommentLike tmpObj = new CommentLike();
				     tmpObj.setCid(nice.getCid());
				     tmpObj.setUid(nice.getUid());
				RequestMessage requestMessage = HttpDataUtil.postData(tmpJson, null);
				ResponseMessage responseMessage = workApi.getCommentLike(requestMessage);
				if (Constant.SUCCESS_CODE.equals(responseMessage.getResultCode())) {
					// 3.同步数据到数据库
					Map<String, Object> map1 = responseMessage.getReturnResult();
					if (null != map1.get(Constant.COMMON_KEY_RESULT)) {// 点赞或取消点赞过
						// 修改点赞状态（批量修改）
						CommentLike like = FastJsonUtil.mapToObject(map1, CommentLike.class, Constant.COMMON_KEY_RESULT);
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
				ResponseMessage saveOrUpdateBatchNice = workApi.saveOrUpdateBatchCommentLike(postData);
			}
			// 4.更新点赞数
			//4.1 统计文章点赞数;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("tids", ArrayHelper.listToStr(tidList));
			RequestMessage postData = HttpDataUtil.postData(jsonObject, null);
			ResponseMessage respose = workApi.countCommentNiceList(postData);
			if (Constant.SUCCESS_CODE.equals(respose.getResultCode())) {
				Map<String, Object> returnResult = respose.getReturnResult();
				if (null != returnResult) {
					List<Map<String, Object>> list = (List<Map<String, Object>>) returnResult
							.get(Constant.COMMON_KEY_RESULT);
					for (Map<String, Object> map2 : list) {
						WorkInfo travelInfo = new WorkInfo();
						Object tid = map2.get("tid");
						Object count = map2.get("count");
						travelInfo.setId(Long.valueOf(tid.toString()));
						travelInfo.setLikeCount(Integer.valueOf(count.toString()));
						travelInfoList.add(travelInfo);
					}
				}

			}
			// 4.2 批量更新点赞数
			jsonObject.clear();
			jsonObject.put(Constant.COMMON_KEY_ARR, travelInfoList);
			RequestMessage request = HttpDataUtil.postData(jsonObject, null);
			if(travelInfoList.size()>0) {
				ResponseMessage updateTravelInfoBatch = this.workApi.updateWorkCommentBatch(request);
				if(Constant.SUCCESS_CODE.equals(updateTravelInfoBatch.getResultCode())) {
					//4.3清楚缓存
					saveOrupdateList.forEach(key->{
						String hashKey=key.getUid()+"::"+key.getCid();
						boolean hdel = this.redisManager.hdel(RedisKey.KEY_WORK_COMMENT_NICE, hashKey);	
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