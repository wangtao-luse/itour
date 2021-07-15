package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.work.api.WorkApi;

@Service
public class WorkConnector {
@Autowired
WorkApi workApi;
/**
 * 个人博客列表(前台使用)
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selectWorkInfoList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = workApi.selectWorkInfoList(requestMessage);
	return responseMessage;
}
/**
 * 个人博客单条(前台使用)
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selectWorkInfo(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage = workApi.selectWorkInfo(requestMessage);
	return responseMessage;
}
}
