package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.quartz.api.QuartzApi;
@Service
public class QuartzConnector {
	@Autowired
private QuartzApi quartzApi;
	/**
	 * 定时任务列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryTriggersList(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage =quartzApi.queryJobList(requestMessage);
		return responseMessage;
	}
	/**
	 * 定时任务查询单条
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage getTrigger(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage =quartzApi.getTrigger(requestMessage);
		return responseMessage;
	}
	/**
	 * 新增定时任务
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage insertJob(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage =quartzApi.insertJob(requestMessage);
		return responseMessage;
	}
	/**
	 * 修改定时任务
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage updateJob(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage =quartzApi.updateJob(requestMessage);
		return responseMessage;
	}
	/**
	 * 暂停定时任务
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage pauseJob(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage =quartzApi.pauseJob(requestMessage);
		return responseMessage;
	}
	/**
	 * 恢复定时任务
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage resumeJob(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage =quartzApi.resumeJob(requestMessage);
		return responseMessage;
	}
	/**
	 * 执行定时任务
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage startNowJob(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage =quartzApi.startNowJob(requestMessage);
		return responseMessage;
	}
	/**
	 * 删除定时任务
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage deleteJob(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage =quartzApi.deleteJob(requestMessage);
		return responseMessage;
	}
	
}
