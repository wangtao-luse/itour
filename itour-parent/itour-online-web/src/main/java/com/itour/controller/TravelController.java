package com.itour.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.redis.RedisManager;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.AccountVo;
import com.itour.connector.AccountConnector;
import com.itour.connector.TravelConnector;
import com.itour.constant.Constant;
import com.itour.constant.ConstantTravel;
import com.itour.constant.RedisKey;
import com.itour.model.travel.Tag;
import com.itour.model.travel.TravelColumn;
import com.itour.util.DateUtil;
import com.itour.util.IpUtil;
import com.itour.util.MarkdownUtils;
import com.itour.util.SessionUtil;

@Controller
@RequestMapping("/travel")
public class TravelController {
	@Autowired
private  TravelConnector travelConnector;
	@Autowired
private AccountConnector accountConnector;
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
public String planPage(Long id,String title,HttpServletRequest request) {
	
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
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	
	return responseMessage;	
}


@RequestMapping("/md")
public String md() {
	return "/travel/info/md";
}
@RequestMapping("/detail")
public String detail(Long id,ModelMap model,HttpServletRequest request ) {
	//1.浏览量
		pageView(id);
		//2.独立访客
		 unique(request);
		//3.独立IP
		 ip(request);
	model.addAttribute("id", id);
	return "/travel/info/detail";
}
public void unique(HttpServletRequest request) {
	String strDate = DateUtil.getStrDate(new Date(), "yyyy-MM-dd");
	 Cookie[] cookies = request.getCookies();
	 String cookie_=null;
	 for (Cookie cookie : cookies) {
		System.out.println(cookie.getName()+"--"+cookie.getValue());
		if("JSESSIONID".equals(cookie.getName())) {
			cookie_=cookie.getValue();
		}
	}
	 String key=cookie_+"::"+strDate;
	 boolean hasStrKey = this.redisManager.hasStrKey(RedisKey.KEY_ITOUR_UNIQUEISITORS);
	 if(hasStrKey) {//有缓存查看缓存中是否有对应的key
		 boolean hHasKey = this.redisManager.hHasKey(RedisKey.KEY_ITOUR_UNIQUEISITORS, key);
		 if(!hHasKey) {
			 Map<Object, Object> m = this.redisManager.hget(RedisKey.KEY_ITOUR_UNIQUEISITORS);
			 	m.put(key, key);
				this.redisManager.hmset(RedisKey.KEY_ITOUR_UNIQUEISITORS, m);
				this.redisManager.incr(RedisKey.KEY_ITOUR_UNIQUEISITOR_COUNT, 1);  
		 }
		 
	 }else {
		 HashMap<String,Object > m= new HashMap<String, Object>();
			m.put(key, key);
			this.redisManager.hmSset(RedisKey.KEY_ITOUR_UNIQUEISITORS, m);
			this.redisManager.incr(RedisKey.KEY_ITOUR_UNIQUEISITOR_COUNT, 1);  
	 }
}
public void ip(HttpServletRequest request) {
	 //1.组装key
	 String strDate = DateUtil.getStrDate(new Date(), "yyyy-MM-dd");
	 String ipAddr = IpUtil.getIpAddr(request);
	 String key=ipAddr+"::"+strDate;
	 //key是否存在
	 boolean hasStrKey = this.redisManager.hasStrKey(RedisKey.KEY_ITOUR_IPS);
	 if(hasStrKey) {//有缓存看缓存中是否有对应的key
		 boolean hHasKey = this.redisManager.hHasKey(RedisKey.KEY_ITOUR_IPS, key);
		 if(!hHasKey) {
		 Map<Object, Object> m = this.redisManager.hget(RedisKey.KEY_ITOUR_IPS);
		 	m.put(key, key);
			this.redisManager.hmset(RedisKey.KEY_ITOUR_IPS, m);
			this.redisManager.incr(RedisKey.KEY_ITOUR_IP_COUNT, 1);
		 }
	 }else {//没有缓存直接放入缓存
		 HashMap<String,Object > m= new HashMap<String, Object>();
			m.put(key, key);
			this.redisManager.hmSset(RedisKey.KEY_ITOUR_IPS, m);
			this.redisManager.incr(RedisKey.KEY_ITOUR_IP_COUNT, 1);  
	 }
	 
	 
}
private void pageView(Long id) {
	//1.浏览量
	String key=RedisKey.KEY_ITOUR_PAGEVIEW+"::"+id;
	this.redisManager.incr(key, 1);
	boolean hasStrKey = this.redisManager.sisMember(RedisKey.ITOUR_PAGEVIEW_IDS,id);
	if(hasStrKey) {
		Set<Object> pvList = this.redisManager.smembers(RedisKey.ITOUR_PAGEVIEW_IDS);
		   pvList.add(id);
		this.redisManager.rpush(RedisKey.ITOUR_PAGEVIEW_IDS, pvList);
	}else {
		Set<Object> pvList = new HashSet<Object>();
		pvList.add(id);
		this.redisManager.rpush(RedisKey.ITOUR_PAGEVIEW_IDS, pvList);
	}
	
	
}

@RequestMapping("/insertweekTravel")
@ResponseBody
public ResponseMessage insertweekTravel(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	jsonObject.put("type", ConstantTravel.TRAVEL_INFO_WEEK);
	 AccountVo sessionUser = SessionUtil.getSessionUser();
	 jsonObject.put("uid", sessionUser.getuId());
	String markdown = jsonObject.getString("markdown");
	String markdownToHtml = MarkdownUtils.markdownToHtml(markdown);
	System.out.println(markdownToHtml);
	ResponseMessage insertTravelInfo = this.travelConnector.insertTravelInfo(jsonObject, request);
	return insertTravelInfo;
	
}
@RequestMapping("/tag")
public String tag(HttpServletRequest request,ModelMap model) {
	JSONObject jsonObject = new JSONObject();
	 Tag t = new Tag();
	 AccountVo sessionUser = SessionUtil.getSessionUser();
	 t.setUid(sessionUser.getuId());
	 jsonObject.put("vo", t);
	ResponseMessage queryTravelTagList = this.travelConnector.queryTravelTagList(jsonObject, request);
	Map<String, Object> returnResult = queryTravelTagList.getReturnResult();
	List<Tag> tagList =(List<Tag>)returnResult.get(Constant.COMMON_KEY_RESULT);
	model.addAttribute("tagList",tagList );
	return "/travel/info/tag";
}
@RequestMapping("/column")
public String column(HttpServletRequest request,ModelMap model) {
	JSONObject jsonObject = new JSONObject();
	TravelColumn c = new TravelColumn();
	AccountVo sessionUser = SessionUtil.getSessionUser();
	c.setUid(sessionUser.getuId());
	jsonObject.put("vo", c);
	ResponseMessage queryTravelColumnList = this.travelConnector.queryTravelColumnList(jsonObject, request);
	Map<String, Object> returnResult = queryTravelColumnList.getReturnResult();
	List<TravelColumn> colList = (List<TravelColumn>)returnResult.get(Constant.COMMON_KEY_RESULT);
	model.addAttribute("colList", colList);
	return "/travel/info/column";
}
@RequestMapping("/inserTravelTag")
@ResponseBody
public ResponseMessage inserTravelTag(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	System.out.println(jsonObject);
	ResponseMessage inserTravelTag = this.travelConnector.inserTravelTag(jsonObject, request);
	return inserTravelTag;
	
}
@RequestMapping("/inserTravelCol")
@ResponseBody
public ResponseMessage inserTravelCol(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage inserTravelTag = this.travelConnector.insertTravelColumn(jsonObject, request);
	return inserTravelTag;
	
}
@RequestMapping("/queryTravelColumnList")
@ResponseBody
public ResponseMessage queryTravelColumnList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage inserTravelTag = this.travelConnector.queryTravelColumnList(jsonObject, request);
	return inserTravelTag;
	
}
@RequestMapping("/cityPage")
public String cityPage(HttpServletRequest request,ModelMap model) {
	return "/travel/info/city";
}
@RequestMapping("/getCityList")
@ResponseBody
public ResponseMessage getCityList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage getCityList = this.travelConnector.getCityList(jsonObject, request);
	return getCityList;
	
}

}
