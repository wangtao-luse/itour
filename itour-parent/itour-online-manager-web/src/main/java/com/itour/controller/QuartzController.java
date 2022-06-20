package com.itour.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
 * 定时任务修改页面   
 * @return
 */
@RequestMapping("/jobUpdateP")
public String jobUpdateP(String jobGroup,String jobName,ModelMap model,HttpServletRequest request) {
//String jobName1 = request.getParameter("jobName");
	model.addAttribute("jobName", jobName);
	model.addAttribute("jobGroup", jobGroup);
	return "/system/quartz/quartzUpdate";
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
/**
 * 定时任务单条
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/getTrigger")
@ResponseBody
public ResponseMessage getTrigger(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage insertJob = quartzConnector.getTrigger(jsonObject, request);
	return insertJob;
}
/**
 * 修改定时任务
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/updateJob")
@ResponseBody
public ResponseMessage updateJob(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage insertJob = quartzConnector.updateJob(jsonObject, request);
	return insertJob;
}
/**
 * 暂停定时任务
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/pauseJob")
@ResponseBody
public ResponseMessage pauseJob(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage insertJob = quartzConnector.pauseJob(jsonObject, request);
	return insertJob;
}
/**
 * 恢复定时任务
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/resumeJob")
@ResponseBody
public ResponseMessage resumeJob(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage insertJob = quartzConnector.resumeJob(jsonObject, request);
	return insertJob;
}
/**
 * 执行定时任务
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/startNowJob")
@ResponseBody
public ResponseMessage startNowJob(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage insertJob = quartzConnector.startNowJob(jsonObject, request);
	return insertJob;
}

/**
 * 删除定时任务
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/deleteJob")
@ResponseBody
public ResponseMessage deleteJob(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage insertJob = quartzConnector.deleteJob(jsonObject, request);
	return insertJob;
}
}
