package com.itour.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.AccountVo;
import com.itour.connector.MenuConnector;
import com.itour.constant.Constant;
import com.itour.util.FastJsonUtil;
import com.itour.util.SessionUtil;


@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
private MenuConnector menuConnector;
@ResponseBody
@RequestMapping(value = "/getMenuList")
public ResponseMessage getMenuList(HttpServletRequest request) {
	   JSONObject jsonObject = new JSONObject();
	    AccountVo sessionUser = SessionUtil.getSessionUser();
	    String getuId = sessionUser.getuId();
	    jsonObject.put("uid", getuId);
		ResponseMessage responseMessage = menuConnector.getMenuList(jsonObject, request);
		   Map<String, Object> returnResult = responseMessage.getReturnResult();
			JSONArray mapToJSONArray = FastJsonUtil.mapToJSONArray(returnResult, Constant.COMMON_KEY);     
		responseMessage.setReturnResult(mapToJSONArray);
	return responseMessage;
}
}
