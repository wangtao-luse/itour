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
	TravelConnector travelConnector;
	/**
	 * 旅行信息页面
	 * @return
	 */
@RequestMapping("/travelPage")
public String travelPage() {
	return "/system/travel/info/travelInfoManager";
}
/**
 * 旅行信息类型管理页面
 * @return
 */
@RequestMapping("/travelTypePage")
public String travelTypePage() {
	return "/system/travel/type/travelTypeManager";
}
/**
 * 旅行信息评论页面
 * @return
 */
@RequestMapping("/travelCommntPage")
public String travelCommntPage() {
	return "/system/travel/comment/travelCommentManager";
}
/**
 * 旅行信息列表
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
 * 旅行信息单条查询
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
	ResponseMessage queryTravelInfoList = travelConnector.updateTravelInfo(jsonObject, request);
	return queryTravelInfoList;
}
/**
 * 旅行信息新增
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/insertTravelInfo")
@ResponseBody
public ResponseMessage insertTravelInfo(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage queryTravelInfoList = travelConnector.insertTravelInfo(jsonObject, request);
	return queryTravelInfoList;
}
/**
 * 旅行信息专栏管理页面
 * @return
 */
@RequestMapping("/travelColPage")
public String travelColPage() {
	return "/system/travel/col/travelColManager";
}
/**
 * 旅行信息新增
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/queryTravelTypeList")
@ResponseBody
public ResponseMessage queryTravelTypeList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage queryTravelInfoList = travelConnector.queryTravelTypeList(jsonObject, request);
	return queryTravelInfoList;
}
}
