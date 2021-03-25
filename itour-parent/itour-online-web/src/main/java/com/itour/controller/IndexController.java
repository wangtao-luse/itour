package com.itour.controller;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.TravelConnector;
import com.itour.constant.Constant;
import com.itour.model.travel.TravelInfo;



@Controller
public class IndexController {
	@Autowired
private	TravelConnector travelConnector;
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
public String index(Page page, HttpServletRequest request,String ajaxCmd,ModelMap model) {
	JSONObject jsonObject = new JSONObject();
	TravelInfo travelInfo = new TravelInfo();
	jsonObject.put("vo", travelInfo);
	ResponseMessage queryTravelInfoList = this.travelConnector.queryTravelInfoList(jsonObject, request);
	Map<String, Object> returnResult = queryTravelInfoList.getReturnResult();
	List<TravelInfo> list = (List<TravelInfo>)returnResult.get(Constant.COMMON_KEY_RESULT);
	model.addAttribute("travel", list);
	return "index";
}
//解决退出问题
@RequestMapping("/")
public String defaultPage() {
	
	return "index";
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
 * 创建收藏夹
 * @param model
 * @return
 */
@RequestMapping("/favoriteAddModal")
public String favoriteAddModal() {
return "/travel/plan/favoriteAdd";	
}
/**
 * 创建收藏夹
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/insertFavorite")
@ResponseBody
public ResponseMessage insertFavorite(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage insertFavorite = this.travelConnector.insertFavorite(jsonObject, request);
	return insertFavorite;
}
/**
 * 收藏夹列表
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/queryFavoriteList")
@ResponseBody
public ResponseMessage queryFavoriteList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage insertFavorite = this.travelConnector.queryFavoriteList(jsonObject, request);
	return insertFavorite;
}

}