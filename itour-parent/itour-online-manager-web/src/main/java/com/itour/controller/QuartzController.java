package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.QuartzConnector;

@Controller
@RequestMapping("/quartz")
public class QuartzController {
	@Autowired
private	QuartzConnector quartzConnector;
/**
 * 定时任务列表
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/queryJobList")
@ResponseBody
public ResponseMessage queryJobList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage queryJobList = quartzConnector.queryTriggersList(jsonObject, request);
	return queryJobList;
}
/**
 * 定时任务管理页面
 * @return
 */
@RequestMapping("/quartzManagerPage")
public String quartzManagerPage() {
	return "/system/quartz/quartzManager";
}
/**
 * 定时任务新增页面
 * @return
 */
@RequestMapping("/jobAddP")
public String jobAddP() {
	return "/system/quartz/quartzAdd";
}
/**
 * 定时任务新增
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/insertJob")
@ResponseBody
public ResponseMessage insertJob(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage insertJob = quartzConnector.insertJob(jsonObject, request);
	return insertJob;
}
}
