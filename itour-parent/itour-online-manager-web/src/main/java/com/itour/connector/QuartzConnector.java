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
	
}
