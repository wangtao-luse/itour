package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.WorkApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

@Service
public class WorkConnector {
@Autowired
private WorkApi workApi;
/**
 * 日志标签列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryLabelList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.queryLabelList(requestMessage);
}
/**
 * 日志分类专栏列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryColumnList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.queryColumnList(requestMessage);
}
/**
 * 新增或修改工作日志
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage savaOrUpdateWorkInfo(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.savaOrUpdateWorkInfo(requestMessage);
}
}
