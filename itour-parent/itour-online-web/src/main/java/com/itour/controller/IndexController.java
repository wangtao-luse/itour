package com.itour.controller;



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
import com.itour.common.redis.RedisManager;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.AccountVo;
import com.itour.connector.TravelConnector;
import com.itour.constant.Constant;
import com.itour.model.travel.TravelInfo;
import com.itour.util.FastJsonUtil;
import com.itour.util.SessionUtil;



@Controller
public class IndexController {
	@Autowired
private	TravelConnector travelConnector;
	
private	RedisManager redisManager;
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
public String index(Page page,TravelInfo travelInfo, HttpServletRequest request,String ajaxCmd,ModelMap model) {
		return indexPage(page, travelInfo, request, ajaxCmd, model);
	
}
//解决退出问题
@RequestMapping("/")
public String defaultPage(Page page,TravelInfo travelInfo, HttpServletRequest request,String ajaxCmd,ModelMap model) {
	return indexPage(page, travelInfo, request, ajaxCmd, model);
}
private String indexPage(Page page, TravelInfo travelInfo, HttpServletRequest request, String ajaxCmd, ModelMap model) {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("vo", travelInfo);
	jsonObject.put("page", page);	
	ResponseMessage queryTravelInfoList = this.travelConnector.queryViewTravelinfoOauthList(jsonObject, request);
	Map<String, Object> returnResult = queryTravelInfoList.getReturnResult();
	Page p = FastJsonUtil.mapToObject(returnResult, Page.class, Constant.COMMON_KEY_RESULT);
	model.addAttribute("travel", p.getRecords());
	if(ajaxCmd==null) {
		return "index";
	}else {
		return "index#"+ajaxCmd;
	}
}

//点赞

@RequestMapping("/niceSub")
@ResponseBody
public ResponseMessage niceSub(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		AccountVo sessionUser = SessionUtil.getSessionUser();
		jsonObject.put("uid", sessionUser.getuId());
		responseMessage = this.travelConnector.niceSub(jsonObject, request);
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
return responseMessage;	
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