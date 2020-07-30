package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.TravelConnector;

@Controller
@RequestMapping("/travel")
public class TravelController {
	@Autowired
private  TravelConnector travelConnector;
	/**
	 * 旅行信息列表查询
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryTravelInfoList")
	@ResponseBody
public ResponseMessage queryTravelInfoList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage queryTravelInfoList = travelConnector.queryTravelInfoList(jsonObject, request);
	return queryTravelInfoList;
	
}
/**
 * 旅行信息查询单条
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/selectTravelInfoById")
@ResponseBody
public ResponseMessage selectTravelInfoById(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage queryTravelInfoList = travelConnector.selectTravelInfoById(jsonObject, request);
	return queryTravelInfoList;
	
}
/**
 * 旅行信息修改
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/updateTravelInfo")
@ResponseBody
public ResponseMessage updateTravelInfo(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage updateTravelInfo = travelConnector.updateTravelInfo(jsonObject, request);
	return updateTravelInfo;
	
}
/**
 * 旅行信息修改
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/delTravelInfo")
@ResponseBody
public ResponseMessage delTravelInfo(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage delTravelInfo = travelConnector.delTravelInfo(jsonObject, request);
	return delTravelInfo;
	
}
}
