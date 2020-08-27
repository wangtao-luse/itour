package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.QuartzConnector;

@Controller
@RequestMapping("/quartz")
public class QuartzController {
	@Autowired
private	QuartzConnector quartzConnector;
@RequestMapping("/queryTriggersList")
public ResponseMessage queryTriggersList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage queryTriggersList = quartzConnector.queryTriggersList(jsonObject, request);
	return queryTriggersList;
}

@RequestMapping("/quartzManagerPage")
public String quartzManagerPage() {
	return "/system/quartz/quartzManager";
}
}
