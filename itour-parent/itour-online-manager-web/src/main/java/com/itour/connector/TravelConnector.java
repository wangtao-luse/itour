package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.TravelApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

@Service
public class TravelConnector {
	@Autowired
TravelApi travelApi;
	/**
	 * 旅行信息类型列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
public ResponseMessage queryTravelTypeList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.queryTravelTypeList(requestMessage);
	return responseMessage;
}
/**
 * 旅行信息类型单条查询
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getTravelType(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.getTravelType(requestMessage);
	return responseMessage;
}
/**
 * 旅行信息类型修改
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage updateTravelType(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.updateTravelType(requestMessage);
	return responseMessage;
}
/**
 * 旅行信息类型删除
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage delTravelType(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.delTravelType(requestMessage);
	return responseMessage;
}
/**
 * 旅行信息类型新增
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage insertTravelType(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.insertTravelType(requestMessage);
	return responseMessage;
}
/**
 * 旅行信息列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryTravelInfoList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.queryTravelInfoList(requestMessage);
	return responseMessage;
}
/**
 * 旅行信息新增
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage insertTravelInfo(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.insertTravelInfo(requestMessage);
	return responseMessage;
}
/**
 * 旅行信息查询单条
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selectTravelInfoById(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.selectTravelInfoOne(requestMessage);
	return responseMessage;
}
/**
 * 旅行信息修改
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage updateTravelInfo(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.updateTravelInfo(requestMessage);
	return responseMessage;
}
/**
 * 旅行信息专栏列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryTravelColumnList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.queryTravelColumnList(requestMessage);
	return responseMessage;
}
/**
 * 旅行信息专栏列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryTravelCommentList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.queryTravelCommentList(requestMessage);
	return responseMessage;
}
/**
 * 旅行信息位置列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryLocationList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage responseMessage =travelApi.queryLocationList(requestMessage);
	return responseMessage;
}
}
