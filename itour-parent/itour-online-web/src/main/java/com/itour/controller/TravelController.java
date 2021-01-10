package com.itour.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.redis.RedisManager;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.AccountVo;
import com.itour.connector.TravelConnector;
import com.itour.constant.Constant;
import com.itour.constant.ConstantV;
import com.itour.constant.TravelRedisKey;
import com.itour.model.travel.History;
import com.itour.model.travel.Nice;
import com.itour.model.travel.Pageview;
import com.itour.util.DateUtil;
import com.itour.util.SessionUtil;

@Controller
@RequestMapping("/travel")
public class TravelController {
	@Autowired
private  TravelConnector travelConnector;
	@Autowired
private RedisManager redisManager;

	/**
	 * 旅行信息列表查询
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryTravelInfoList")
	@ResponseBody
public ResponseMessage queryTravelInfoList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage queryTravelInfoList = travelConnector.queryTravelInfoList(jsonObject, request);
	return queryTravelInfoList;
	
}
/**
 * 旅行信息查询单条
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/selectTravelInfoById")
@ResponseBody
public ResponseMessage selectTravelInfoById(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage queryTravelInfoList = travelConnector.selectTravelInfoById(jsonObject, request);
	return queryTravelInfoList;
	
}
/**
 * 旅行信息修改
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/updateTravelInfo")
@ResponseBody
public ResponseMessage updateTravelInfo(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage updateTravelInfo = travelConnector.updateTravelInfo(jsonObject, request);
	return updateTravelInfo;
	
}
/**
 * 旅行信息修改
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/delTravelInfo")
@ResponseBody
public ResponseMessage delTravelInfo(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage delTravelInfo = travelConnector.delTravelInfo(jsonObject, request);
	return delTravelInfo;
	
}
/**
 * 城市信息查询单条
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/getLocation")
@ResponseBody
public ResponseMessage getLocation(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage delTravelInfo = travelConnector.getLocation(jsonObject, request);
	return delTravelInfo;
	
}
/**
 * 交通信息列表
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/queryTransportationInfoList")
@ResponseBody
public ResponseMessage queryTransportationInfoList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage delTravelInfo = travelConnector.queryTransportationInfoList(jsonObject, request);
	return delTravelInfo;
	
}
/**
 * 旅行信息列表(视图)
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/queryViewTravelinfoOauthList")
@ResponseBody
public ResponseMessage queryViewTravelinfoOauthList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage queryViewTravelinfoOauthList = travelConnector.queryViewTravelinfoOauthList(jsonObject, request);
	return queryViewTravelinfoOauthList;
	
}
/**
 * 旅行信息单条(视图)
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/selectViewTravelinfoOauthById")
@ResponseBody
public ResponseMessage selectViewTravelinfoOauthById(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage selectViewTravelinfoOauthById = travelConnector.selectViewTravelinfoOauthById(jsonObject, request);
	return selectViewTravelinfoOauthById;
	
}
@RequestMapping("/planPage")
public String planPage(Integer id,String title,HttpServletRequest request) {
	//1.添加浏览记录
	JSONObject jsonObject = new JSONObject();
	History history = new History();
	history.setId(id);
	history.setTitle(title);
	history.setStatus(ConstantV.HISTORY_NORMAL);
	history.setCreatedate(DateUtil.currentLongDate());
	String loc="";
	history.setLoc(loc);
	AccountVo sessionUser = SessionUtil.getSessionUser();
	String uId=sessionUser.getuId();
	history.setuId(uId);
	this.travelConnector.insertHistory(jsonObject, request);
	//2.跳转页面
	return "/travel/plan/detail";
}
@RequestMapping("/infoAddPage")
public String infoAddPage() {
	return "/travel/info/infoAdd";
}
@RequestMapping("/thumbUp")
@ResponseBody
public ResponseMessage thumbUp(@RequestBody JSONObject jsonObject) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		//1.将点赞数据插入redis中,定时同步数据库
		//key =uid::pid;用户唯一号+文章编号;
		String status = jsonObject.getString("status");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Nice nice= new Nice();
		nice.setCreatedate(DateUtil.currentLongDate());
		nice.setStatus(status);
	    nice.setTid(jsonObject.getInteger("tid"));
	    AccountVo sessionUser = SessionUtil.getSessionUser();
	    nice.setUid(sessionUser.getuId());
	    String key = nice.getUid()+"::"+nice.getTid();
	    map.put(key, nice);
	    this.redisManager.hmset(TravelRedisKey.KEY_NICE, map,8*60);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	
return responseMessage;	
}
@RequestMapping("/pageview")
@ResponseBody
public ResponseMessage pageview(@RequestBody JSONObject jsonObject) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		//1.将浏览量数据插入redis中,定时同步数据库
		String tid = jsonObject.getString("tid");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Pageview pageview= new Pageview();
		pageview.setTid(Integer.valueOf(tid));
		pageview.setCreatedate(DateUtil.currentLongDate());
		boolean hHasKey = redisManager.hHasKey(TravelRedisKey.KEY_PAGEVIEW, tid);
		if(hHasKey) {//有浏览量
			Pageview view = (Pageview)redisManager.hget(TravelRedisKey.KEY_PAGEVIEW, tid);
			Integer pageVew = view.getPageVew();
			view.setPageVew(pageVew+1);
		}
		map.put(tid, pageview);
		//插入redis  key:TravelRedisKey.KEY_PAGEVIEW;时效:5天
		this.redisManager.hmset(TravelRedisKey.KEY_PAGEVIEW, map, 5*24*60*60);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	
	return responseMessage;	
}

}
