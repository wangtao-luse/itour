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
	public ResponseMessage queryTriggersList(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage =quartzApi.queryTriggersList(requestMessage);
		return responseMessage;
	}
	
}
