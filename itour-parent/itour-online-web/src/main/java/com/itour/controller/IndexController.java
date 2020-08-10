package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.AdvertConnector;
import com.itour.connector.TravelConnector;
import com.itour.model.travel.dto.ViewTravelinfoOauth;

@Controller
public class IndexController {
	@Autowired
private	TravelConnector travelConnector;
	@Autowired
private AdvertConnector advertConnector;
@RequestMapping("/index")
public String index(ViewTravelinfoOauth viewTravelinfo,HttpServletRequest request,ModelMap model) {
	JSONObject jsonObject = new JSONObject();
	//旅行信息列表
	ResponseMessage travelList = travelConnector.queryViewTravelinfoOauthList(jsonObject, request);
	model.addAttribute("viewTravelList", travelList);
	//广告列表
	ResponseMessage queryAdvertList = advertConnector.queryAdvertList(jsonObject, request);
	model.addAttribute("adList", queryAdvertList);
	return "index";
}

}
