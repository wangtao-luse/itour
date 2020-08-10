package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.AdvertConnector;

@Controller
@RequestMapping("/ad")
public class AdvertController {
	@Autowired
	AdvertConnector advertConnector;
@ResponseBody
@RequestMapping("/queryAdvertList")
public ResponseMessage queryAdvertList(@RequestBody JSONObject jsonObject,HttpServletRequest request){
	ResponseMessage queryAdvertList = advertConnector.queryAdvertList(jsonObject, request);
	return queryAdvertList;
}
}
