package com.itour.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.AdvertConnector;
import com.itour.connector.TravelConnector;
import com.itour.constant.Constant;
import com.itour.model.advert.Advert;
import com.itour.model.travel.TravelInfo;
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
	ResponseMessage travelMsg = travelConnector.queryViewTravelinfoOauthList(jsonObject, request);
	Map<String, Object> travelMsgMap = travelMsg.getReturnResult();
	List<TravelInfo> traveInfoList = (List<TravelInfo>)travelMsgMap.get(Constant.COMMON_KEY_RESULT);
	List<Object> travelList= new ArrayList<Object>();
	travelList.add(traveInfoList);
	JSONArray travelInfoArr = new JSONArray(travelList);
	ResponseMessage advertMsg = advertConnector.queryAdvertList(jsonObject, request);
	Map<String, Object> advertMap = advertMsg.getReturnResult();
	List<Advert> advertList = (List<Advert>) advertMap.get(Constant.COMMON_KEY_RESULT);
	List<Object> adList= new ArrayList<Object>();
	travelList.add(advertList);
	JSONArray advertArr = new JSONArray(adList);
	int index=0;
	for (int i = 0; i < travelInfoArr.size(); i++) {
		if((i==3||i%9==0)&&advertArr.size()>0) {
			//将广告植入博客列表
			//1.得到博客对象
			JSONObject travelInfo = travelInfoArr.getJSONObject(i);
			//2.循环广告列表
			for (int j = 0; j < advertArr.size(); j++) {
				JSONObject advert = advertArr.getJSONObject(index);
				travelInfo.put("ad", advert);
				break;
			}
			
			index++;
		}
	}
	model.addAttribute("viewTravelList", travelInfoArr);
	
	return "index";
}

}
