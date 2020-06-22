package com.itour.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.MenuConnector;
import com.itour.constant.Constant;
import com.itour.util.FastJsonUtil;


@Controller
public class MenuController {
	@Autowired
private MenuConnector menuConnector;
@ResponseBody
@RequestMapping(value = "/getMenuList")
public ResponseMessage getMenuList(@RequestBody(required = false) JSONObject jsonObject ,HttpServletRequest request) {
		ResponseMessage responseMessage = menuConnector.getMenuList(jsonObject, request);
		   Map<String, Object> returnResult = responseMessage.getReturnResult();
			JSONArray mapToJSONArray = FastJsonUtil.mapToJSONArray(returnResult, Constant.COMMON_KEY);     
		responseMessage.setReturnResult(mapToJSONArray);
	return responseMessage;
}
}
