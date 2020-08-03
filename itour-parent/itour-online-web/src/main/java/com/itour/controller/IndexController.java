package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.TravelConnector;
import com.itour.model.travel.dto.ViewTravelinfoOauth;

@Controller
public class IndexController {
	@Autowired
	TravelConnector travelConnector;
@RequestMapping("/index")
public String index(ViewTravelinfoOauth viewTravelinfo,HttpServletRequest request,ModelMap model) {
	JSONObject jsonObject = new JSONObject();
	ResponseMessage travelList = travelConnector.queryViewTravelinfoOauthList(jsonObject, request);
	model.addAttribute("viewTravelList", travelList);
	return "index";
}

}
