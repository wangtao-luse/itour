package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.WorkConnector;

@Controller
@RequestMapping("/work")
public class WorkController {
	@Autowired
	WorkConnector workConnector;
@RequestMapping("/workMd")
public String workMd() {
	return "/work/info/workinfoMd";
}
/**
 * 日志标签列表
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/queryLabelList")
@ResponseBody
public ResponseMessage queryLabelList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage resp = this.workConnector.queryLabelList(jsonObject, request);
	return resp;
}
/**
 * 日志分类专栏列表
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/queryColumnList")
@ResponseBody
public ResponseMessage queryColumnList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage resp = this.workConnector.queryColumnList(jsonObject, request);
	return resp;
}
/**
 * 日志新增编辑
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/savaOrUpdateWorkInfo")
@ResponseBody
public ResponseMessage savaOrUpdateWorkInfo(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage resp = this.workConnector.savaOrUpdateWorkInfo(jsonObject, request);
	return resp;
}
}
