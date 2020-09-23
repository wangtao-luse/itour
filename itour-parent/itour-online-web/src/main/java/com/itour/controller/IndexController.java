package com.itour.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.AdvertConnector;
import com.itour.connector.TravelConnector;
import com.itour.constant.Constant;
import com.itour.model.advert.Advert;
import com.itour.model.travel.dto.ViewTravelinfoOauth;
import com.itour.util.FastJsonUtil;



@Controller
public class IndexController {
	@Autowired
private	TravelConnector travelConnector;
	@Autowired
private AdvertConnector advertConnector;
	/**
	 * 旅行信息首页
	 * @param viewTravelinfo
	 * @param page
	 * @param request
	 * @param model
	 * @param ajaxCmd
	 * @return
	 */
@RequestMapping("/index")
public String index(ViewTravelinfoOauth viewTravelinfo,Page page,HttpServletRequest request,ModelMap model,String ajaxCmd) {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("page", page);
	jsonObject.put("vo", viewTravelinfo);
	//获取博客列表
	ResponseMessage travelMsg = travelConnector.queryViewTravelinfoOauthList(jsonObject, request);
	//植入广告
	jsonObject.clear();
	page.setSize(2);
	jsonObject.put("page", page);
	jsonObject.put("vo", viewTravelinfo);
	JSONArray result = advert(request, jsonObject, travelMsg);
	model.addAttribute("viewTravelList", result);
	return "index"+(StringUtils.isEmpty(ajaxCmd)?"":"#"+ajaxCmd);
}
/**
 * 植入广告
 * @param request
 * @param jsonObject
 * @param travelMsg
 * @return
 */
public JSONArray advert(HttpServletRequest request, JSONObject jsonObject, ResponseMessage travelMsg) {
	//1.旅行信息列表
	Map<String, Object> travelMsgMap = travelMsg.getReturnResult();
    Page page = FastJsonUtil.mapToObject(travelMsgMap, Page.class, Constant.COMMON_KEY_RESULT);
	List<ViewTravelinfoOauth> traveInfoList = page.getRecords();
	JSONArray travelInfoArr = JSONArray.parseArray(JSON.toJSONString(traveInfoList));
	//2.广告列表
	JSONObject adJSon = new JSONObject();
	ResponseMessage advertMsg =advertConnector.queryAdvertList(jsonObject, request);
	Map<String, Object> advertMap = advertMsg.getReturnResult(); 
	Page adPage = FastJsonUtil.mapToObject(advertMap, Page.class, Constant.COMMON_KEY_RESULT);
	List<Advert> advertList = adPage.getRecords();
	JSONArray advertArr = JSONArray.parseArray(JSON.toJSONString(advertList)); 
	//3.合并列表 
	int index=0;
	JSONArray result = new JSONArray(); 
	for (int i = 0; i < travelInfoArr.size(); i++) { 
		//将广告植入博客列表 
		//1.得到博客对象 
		JSONObject travelInfo = travelInfoArr.getJSONObject(i);
		  if(((i+1)%6==0)&&advertArr.size()>0) {
			  //2.循环广告列表 
			  for (int j = 0; index < advertArr.size(); j++) { 
				  JSONObject advert = advertArr.getJSONObject(index); 
				  travelInfo.put("ad", advert);
				  result.add(travelInfo); break; } index++; 
		  }else { 
			  result.add(travelInfo);
			  } 
	}
		 
	return result;
}
/**
 * 收藏弹出框
 * @return
 */
@RequestMapping("/favoriteModal")
public String favoriteModal(String id,ModelMap model) {
	model.addAttribute("id", id);
return "/travel/plan/favorite";	
}
/**
 * 
 * @param model
 * @return
 */
@RequestMapping("/favoriteAddModal")
public String favoriteAddModal() {
return "/travel/plan/favoriteAdd";	
}
}