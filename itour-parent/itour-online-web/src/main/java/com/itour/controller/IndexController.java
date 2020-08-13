package com.itour.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
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
	JSONArray travelInfoArr = JSONArray.parseArray(JSON.toJSONString(traveInfoList));
	ResponseMessage advertMsg = advertConnector.queryAdvertList(jsonObject, request);
	Map<String, Object> advertMap = advertMsg.getReturnResult();
	List<Advert> advertList = (List<Advert>) advertMap.get(Constant.COMMON_KEY_RESULT);
	JSONArray advertArr = JSONArray.parseArray(JSON.toJSONString(advertList));
	int index=0;
	JSONArray result = new JSONArray();
	for (int i = 0; i < travelInfoArr.size(); i++) {
		//将广告植入博客列表
		//1.得到博客对象
		JSONObject travelInfo = travelInfoArr.getJSONObject(i);
		if(((i+1)==3||(i+1)%9==0)&&advertArr.size()>0) {
			//2.循环广告列表
			for (int j = 0; index < advertArr.size(); j++) {
				JSONObject advert = advertArr.getJSONObject(index);
				travelInfo.put("ad", advert);
				result.add(travelInfo);
				break;
			}
			index++;
		}else {
		result.add(travelInfo);
		}
	}
	model.addAttribute("viewTravelList", result);
	
	return "index";
}

}
