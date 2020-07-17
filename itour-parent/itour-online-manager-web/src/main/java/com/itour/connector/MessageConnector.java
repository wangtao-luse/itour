package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.MessageApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

@Service
public class MessageConnector {
	@Autowired
	MessageApi mssageApi;
	/**
	 * 发送验证码
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage sendEmailCode(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = mssageApi.sendEmailCode(postData);
		return responseMessage;
	}
	/**
	 * 消息列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryMessageList(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = mssageApi.queryMessageList(postData);
		return responseMessage;
	}
	/**
	 * 消息列表(视图)
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryViewMessageList(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = mssageApi.queryViewMessageList(postData);
		return responseMessage;
	}
}
