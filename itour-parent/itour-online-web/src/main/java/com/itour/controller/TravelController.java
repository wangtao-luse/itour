package com.itour.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.common.redis.RedisManager;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.AccountVo;
import com.itour.connector.AccountConnector;
import com.itour.connector.TravelConnector;
import com.itour.constant.ConstAccount;
import com.itour.constant.Constant;
import com.itour.constant.ConstantTravel;
import com.itour.constant.RedisKey;
import com.itour.entity.PageInfo1;
import com.itour.model.account.Oauth;
import com.itour.model.travel.Favorites;
import com.itour.model.travel.Region;
import com.itour.model.travel.Tag;
import com.itour.model.travel.TravelColumn;
import com.itour.model.travel.TravelInfo;
import com.itour.model.travel.WeekInfo;
import com.itour.model.travel.dto.FavoritesDto;
import com.itour.model.travel.dto.TravelInfoDto;
import com.itour.model.travel.dto.ViewCommentReply;
import com.itour.model.travel.dto.ViewTravelColumn;
import com.itour.model.travel.dto.ViewTravelComment;
import com.itour.model.travel.dto.ViewTravelTag;
import com.itour.model.work.dto.WorkInfoDto;
import com.itour.util.DateUtil;
import com.itour.util.FastJsonUtil;
import com.itour.util.IpUtil;
import com.itour.util.MarkdownUtils;
import com.itour.util.SessionUtil;
import com.itour.util.StringHelper;

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
@RequestMapping("/selectTravelInfoOne")
@ResponseBody
public ResponseMessage selectTravelInfoOne(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage queryTravelInfoList = travelConnector.selectTravelInfoOne(jsonObject, request);
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
return responseMessage;	
}

/**
 * 创作攻略页面
 * @return
 */
@RequestMapping("/mdEdit")
@RequiresPermissions("/travel/mdEdit")
public String md() {
	return "/travel/info/md";
}
/**
 * 攻略详情页面
 * @param id
 * @param model
 * @param page
 * @param request
 * @return
 */
@RequestMapping("/detail")
public String detail(Long id,ModelMap model,Page page,HttpServletRequest request ) {
		//1.独立IP访问数作为显示的浏览量（浏览量功能相关）
		 ip(request,String.valueOf(id));
	   //2.获取旅行信息
		 travelInfo(id, model, request);
	     model.addAttribute("id", id);
		return "/travel/info/detail";	
}
@RequestMapping("/commentList")
public String commentList(@RequestBody JSONObject jsonObject,ModelMap model,String ajaxCmd,HttpServletRequest request ) {
	Long id = jsonObject.getLong("id");
	Page pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE).toJavaObject(Page.class);
	//1.获取旅行信息
	 travelInfo(id, model, request);
	//2.获取评论信息;
	commentList(jsonObject, model, request,pageVo);
	 model.addAttribute("id", id);
	 model.addAttribute("order", jsonObject.getString("order"));	 
	return "/travel/info/commentList#"+ajaxCmd;	
}
private void travelInfo(Long id, ModelMap model, HttpServletRequest request) {
	JSONObject jsonObject = new JSONObject();
	TravelInfoDto tmp = new TravelInfoDto();
	AccountVo sessionUser = SessionUtil.getSessionUser();
	model.addAttribute("sessionUser", sessionUser);
	if(!StringUtils.isEmpty(sessionUser)) {
		tmp.setQueryUid(sessionUser.getuId());
	}
	tmp.setId(id);
	 jsonObject.put(Constant.COMMON_KEY_VO, tmp);
	ResponseMessage resp = this.travelConnector.selectTraveInfo(jsonObject , request);
	if(Constant.SUCCESS_CODE.equals(resp.getResultCode())&&null!=resp.getReturnResult()) {
		TravelInfoDto travelInfo = FastJsonUtil.mapToObject(resp.getReturnResult(), TravelInfoDto.class);			
		 model.addAttribute("travelInfo", travelInfo);
		 //获取周末旅行攻略的内容
		 if("2".equals(travelInfo.getType())) {
			 jsonObject.clear();
			 jsonObject.put("tid", id);
			ResponseMessage weekinfo = this.travelConnector.selecWeekInfoOne(jsonObject, request);
			if(Constant.SUCCESS_CODE.equals(weekinfo.getResultCode())) {
				WeekInfo mapToObject = FastJsonUtil.mapToObject(weekinfo.getReturnResult(), WeekInfo.class, Constant.COMMON_KEY_RESULT);
				String markdownToHtml = MarkdownUtils.markdownToHtml(mapToObject.getWeekContent());
				mapToObject.setWeekContent(markdownToHtml);
				model.addAttribute("weekinfo", mapToObject);
			}
		 }
		 //获取旅行攻略的标签列表
		 jsonObject.clear();
		 jsonObject.put("tid", id);
		 ResponseMessage tagResp = this.travelConnector.queryViewTravelTagList(jsonObject, request);
		 if(Constant.SUCCESS_CODE.equals(tagResp.getResultCode())) {
			 List<ViewTravelTag> mapToList = FastJsonUtil.mapToList(tagResp.getReturnResult(), ViewTravelTag.class, Constant.COMMON_KEY_RESULT);
			 model.addAttribute("tagList", mapToList);
		 }
	}
}
private void commentList(JSONObject jsonTmp, ModelMap model, HttpServletRequest request,Page page) {
	JSONObject jsonObject = new JSONObject();
	 jsonObject.put("tid", jsonTmp.getLong("id"));
	 jsonObject.put("orderbyList", jsonTmp.getJSONArray("orderbyList"));
	 page.setSize(10);
	 jsonObject.put(Constant.COMMON_KEY_PAGE, page);
	ResponseMessage respMsg = this.travelConnector.queryCommentList(jsonObject, request);
	if(Constant.SUCCESS_CODE.equals(respMsg.getResultCode())&&!StringUtils.isEmpty(respMsg.getReturnResult())) {
		Map<String, Object> returnResult = respMsg.getReturnResult();
		PageInfo1 resultPage = FastJsonUtil.mapToObject(returnResult, PageInfo1.class, Constant.COMMON_KEY_RESULT);
		resultPage.pageNav();
		resultPage.getPs();
		List<ViewTravelComment> commentList = resultPage.getRecords();
		model.addAttribute("commentList", commentList);
		model.addAttribute(Constant.COMMON_KEY_PAGE, resultPage);
		model.addAttribute(ConstantTravel.TRAVEL_COMMENTSIZE, returnResult.get(ConstantTravel.TRAVEL_COMMENTSIZE));
	}
	
}
public void unique(HttpServletRequest request,String id) {
	String strDate = DateUtil.getStrDate(new Date(), "yyyy-MM-dd");
	 Cookie[] cookies = request.getCookies();
	 String cookie_=null;
	 for (Cookie cookie : cookies) {
		System.out.println(cookie.getName()+"--"+cookie.getValue());
		if("JSESSIONID".equals(cookie.getName())) {
			cookie_=cookie.getValue();
		}
	}
	 String key=cookie_+"::"+strDate+"::"+id;
	 boolean isMember = this.redisManager.sisMember(RedisKey.KEY_ITOUR_UNIQUEISITORS, key);
	 if(!isMember) {//缓存中没有则添加到缓存
			this.redisManager.sAdd(RedisKey.KEY_ITOUR_UNIQUEISITORS, key);
			this.redisManager.incr(RedisKey.KEY_ITOUR_UNIQUEISITOR_COUNT, 1);  
	 }
}
private void ip(HttpServletRequest request,String id) {
	 //1.组装key
	 String strDate = DateUtil.getStrDate(new Date(), "yyyy-MM-dd");
	 String ipAddr = IpUtil.getIpAddr(request);
	 String key=ipAddr+"::"+strDate+"::"+id;
		 boolean isMember = this.redisManager.sisMember(RedisKey.KEY_ITOUR_IPS, key);
		 if(!isMember) {//缓存中没有则添加到缓存
		 	this.redisManager.sAdd(RedisKey.KEY_ITOUR_IPS,key);
			this.redisManager.incr(RedisKey.KEY_ITOUR_IP_COUNT, 1);
		 }
}
public void pv(HttpServletRequest request,String id) {
	//1.组装key
	String strDate = DateUtil.getStrDate(new Date(), "yyyy-MM-dd");
	String ipAddr = IpUtil.getIpAddr(request);
	String key=ipAddr+"::"+strDate+"::"+id;
	String k=strDate+"::"+id;
	boolean isMember = this.redisManager.sisMember(RedisKey.KEY_ITOUR_PVS, key);
	if(!isMember) {//缓存中没有则添加到缓存
		this.redisManager.sAdd(RedisKey.KEY_ITOUR_PVS,key);
		this.redisManager.incr(k, 1);
	}
}
private void pageView(String id) {
	//1.浏览量
	String strDate = DateUtil.getStrDate(new Date(), "yyyy-MM-dd");
	String key=RedisKey.KEY_ITOUR_PAGEVIEW+"::"+strDate+"::"+id;
	this.redisManager.incr(key, 1);
	boolean isMember = this.redisManager.sisMember(RedisKey.ITOUR_PAGEVIEW_IDS,id);
	if(!isMember) {
		this.redisManager.sAdd(RedisKey.ITOUR_PAGEVIEW_IDS,key);
	}
}
//保存草稿
@RequestMapping("/insertweekTravel")
@ResponseBody
public ResponseMessage insertweekTravel(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage insertTravelInfo = saveTravelInfo(jsonObject, request);
	return insertTravelInfo;
	
}

private ResponseMessage saveTravelInfo(JSONObject jsonObject, HttpServletRequest request) {
	AccountVo sessionUser = SessionUtil.getSessionUser();
	TravelInfo travelInfo = jsonObject.getJSONObject(Constant.COMMON_KEY_VO).toJavaObject(TravelInfo.class);
	travelInfo.setType(ConstantTravel.TRAVEL_INFO_WEEK);
	travelInfo.setUid(sessionUser.getuId());
	jsonObject.put(Constant.COMMON_KEY_VO, travelInfo);
	ResponseMessage insertTravelInfo = this.travelConnector.insertTravelInfo(jsonObject, request);
	return insertTravelInfo;
}
//保存
@RequestMapping("/savetweekTravel")
@ResponseBody
public ResponseMessage savetweekTravel(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	 ResponseMessage insertTravelInfo = saveTravelInfo(jsonObject, request);
		return insertTravelInfo;
	
}
//预览
@RequestMapping("/previewWeekInfo")
@ResponseBody
public ResponseMessage previewWeekInfo(@RequestBody JSONObject jsonObject,HttpServletRequest request,ModelMap model) {
	 ResponseMessage insertTravelInfo = saveTravelInfo(jsonObject, request);
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
/**
 * 添加评论
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/insertComment")
@ResponseBody
public ResponseMessage insertComment(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	AccountVo sessionUser = SessionUtil.getSessionUser();
	jsonObject.put("uid", sessionUser.getuId());
	ResponseMessage insertComment = this.travelConnector.insertComment(jsonObject, request);
	return insertComment;
	
}
/**
 * 插入旅行攻略评论回复
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/insertCommentReply")
@ResponseBody
public ResponseMessage insertCommentReply(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	AccountVo sessionUser = SessionUtil.getSessionUser();
	jsonObject.put("fromUid", sessionUser.getuId());
	ResponseMessage insertCommentReply = this.travelConnector.insertCommentReply(jsonObject, request);
	return insertCommentReply;
	
}
/**
 * 旅行攻略评论回复html
 * @param jsonObject
 * @param ajaxCmd
 * @param model
 * @return
 */
@RequestMapping("/commentReply")
public String commentReply(@RequestBody JSONObject jsonObject,String ajaxCmd,ModelMap model ) {
	ViewCommentReply reply = jsonObject.toJavaObject(ViewCommentReply.class);
	model.addAttribute("commentReply", reply);
	return "/travel/info/commentReply#"+ajaxCmd;
}

@RequestMapping("/delComment")
@ResponseBody
public ResponseMessage delComment(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage delComment = this.travelConnector.delComment(jsonObject, request);
	return delComment;
}
@RequestMapping("/delCommentReply")
@ResponseBody
public ResponseMessage delCommentReply(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
   ResponseMessage delCommentRely = this.travelConnector.delCommentRely(jsonObject, request);
	return delCommentRely;
}
/**
 * 旅行攻略评论回复点赞数
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/commentReplyNice")
@ResponseBody
public ResponseMessage commentReplyNice(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	AccountVo sessionUser = SessionUtil.getSessionUser();
	jsonObject.put("uid", sessionUser.getuId());
	ResponseMessage responseMessage = this.travelConnector.commentReplyNiceSub(jsonObject, request);
	return responseMessage;
}
/**
 * 旅行攻略评论点赞数
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/commentNice")
@ResponseBody
public ResponseMessage commentNice(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	AccountVo sessionUser = SessionUtil.getSessionUser();
	jsonObject.put("uid", sessionUser.getuId());
	ResponseMessage responseMessage = this.travelConnector.commentNiceSub(jsonObject, request);
	return responseMessage;
}
/**
 * 搜索页
 * @param request
 * @param model
 * @return
 */

@RequestMapping("/search")
public String search(TravelInfoDto dto,PageInfo1 page,HttpServletRequest request,ModelMap model,String ajaxCmd) {
	String parameter = request.getParameter("search");
	JSONObject jsonObject = new JSONObject();
	dto.setTitle(parameter);
	jsonObject.put(Constant.COMMON_KEY_VO, dto);
	jsonObject.put(Constant.COMMON_KEY_PAGE, page);
	ResponseMessage searchTextList = this.travelConnector.searchTextList(jsonObject, request);
	PageInfo1 pageInfo = FastJsonUtil.mapToObject(searchTextList.getReturnResult(), PageInfo1.class);
	
	List<TravelInfoDto> records = pageInfo.getRecords();
	model.addAttribute("tList", records);
	model.addAttribute("page", pageInfo);
	model.addAttribute("search", parameter);
	if(StringUtils.isEmpty(ajaxCmd)) {
		return "/travel/search";
	}else {
		return "/travel/search#"+ajaxCmd;
	}
	
}

/**
 * 个人中心
 * @param request
 * @param model
 * @return
 */

@RequestMapping("/personCenter")
public String personCenter(HttpServletRequest request,ModelMap model) {
	String uid = request.getParameter("rpm");
	AccountVo sessionUser = SessionUtil.getSessionUser();
	if(!StringUtils.isEmpty(uid)) {
		Oauth o = new Oauth();
	       o.setuId(uid);
	JSONObject parseObject = JSONObject.parseObject(JSONObject.toJSONString(o));
	ResponseMessage selectOauthtOne = accountConnector.selectOauthtOne(parseObject, request);
	Oauth oa = FastJsonUtil.mapToObject(selectOauthtOne.getReturnResult(), Oauth.class);
	    model.addAttribute("account", oa);
	}else {
		model.addAttribute("account", sessionUser);
	}
	
	model.addAttribute("sessionUser", sessionUser);
	model.addAttribute("rpm", uid);
	
	
	return "/account/personCenter";
}
/**
 * 编辑页面
 * @param id
 * @param request
 * @param model
 * @return
 */
@RequestMapping("/updateMd")
public String updateMd(Long id,HttpServletRequest request,ModelMap model) {
	AccountVo sessionUser = SessionUtil.getSessionUser();
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("id", id);
	jsonObject.put("uid", sessionUser.getuId());
	ResponseMessage selectTravelInfoOne = this.travelConnector.selectTravelInfoOne(jsonObject, request);
	if(ResponseMessage.isSuccessResult(selectTravelInfoOne)) {
		Map<String, Object> returnResult = selectTravelInfoOne.getReturnResult();
		if(StringUtils.isEmpty(returnResult.get(Constant.COMMON_KEY_RESULT))) {
			//提示没有权限操作该文章
			model.addAttribute("error", ConstantTravel.EXCEPTION_INFO_NOAUTHOR);
		}else {
			TravelInfo mapToObject = FastJsonUtil.mapToObject(returnResult, TravelInfo.class, Constant.COMMON_KEY_RESULT);
			model.addAttribute("travelInfo", mapToObject);
			String type = mapToObject.getType();
			//获取周末旅行攻略
			if(ConstantTravel.TRAVEL_INFO_WEEK.equals(type)) {
				jsonObject.clear();
				jsonObject.put("tid", mapToObject.getId());
				ResponseMessage selecWeekInfoOne = this.travelConnector.selecWeekInfoOne(jsonObject, request);
				if(ResponseMessage.isSuccessResult(selecWeekInfoOne)) {
					WeekInfo weekInfo = FastJsonUtil.mapToObject(selecWeekInfoOne.getReturnResult(), WeekInfo.class, Constant.COMMON_KEY_RESULT);
					model.addAttribute("weekInfo", weekInfo);
				}
			}
			//获取攻略标签
			jsonObject.clear();
			jsonObject.put("tid", id);
			 ResponseMessage tagResp = this.travelConnector.queryViewTravelTagList(jsonObject, request);
			 if(ResponseMessage.isSuccessResult(tagResp)) {
				 List<ViewTravelTag> tagList = FastJsonUtil.mapToList(tagResp.getReturnResult(), ViewTravelTag.class);
				 model.addAttribute("tagList", tagList);
				 
			 }
			//获取攻略分类
			 jsonObject.clear();
			 ViewTravelColumn v = new ViewTravelColumn();
			 v.setTid(id);
			 jsonObject.put(Constant.COMMON_KEY_VO, v);
			 ResponseMessage queryViewTravelColumnList = this.travelConnector.queryViewTravelColumnList(jsonObject, request);
			 if(ResponseMessage.isSuccessResult(queryViewTravelColumnList)) {
				 List<ViewTravelColumn> mapToList = FastJsonUtil.mapToList(queryViewTravelColumnList.getReturnResult(), ViewTravelColumn.class);
				 model.addAttribute("colList", mapToList);
			 }
			//获取攻略所在城市
			 jsonObject.clear();
			 Region r = new Region();
			 r.setRegionCode(mapToObject.getCode());
			 jsonObject.put(Constant.COMMON_KEY_VO,r);
			 ResponseMessage locationResp = this.travelConnector.selectRegionOne(jsonObject, request);
			 if(ResponseMessage.isSuccessResult(locationResp)) {
				 Region region = FastJsonUtil.mapToObject(locationResp.getReturnResult(), Region.class);
				 model.addAttribute("region", region);
			 }
			
			
		}
		
	}


	return "/travel/info/updateMd";
}
/**
 * 我的主页列表查询
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/queryPersonCenterList")
public String queryPersonCenterList(@RequestBody JSONObject jsonObject,ModelMap model,String ajaxCmd,HttpServletRequest request) {
	TravelInfoDto travelInfoDto = jsonObject.toJavaObject(TravelInfoDto.class);
	PageInfo1 page = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE).toJavaObject(PageInfo1.class);
	AccountVo sessionUser = SessionUtil.getSessionUser();
	JSONObject jsonTmp = new JSONObject();
	String mold = travelInfoDto.getMold();
	//默认查询动态
	if(StringUtils.isEmpty(mold)) {
		travelInfoDto.setMold("1");
	}
	//queryUid有值说明是查看别人主页
	String queryUid = jsonObject.getString("rpm");
	if(StringUtils.isEmpty(queryUid)) {//查看自己的的主页
		travelInfoDto.setUid(sessionUser.getuId());
		travelInfoDto.setOauthId( sessionUser.getOauthId());
		travelInfoDto.setQueryUid(sessionUser.getuId());
	}else {//查看别人的主页
		  //查看目标用户的用户信息
		  Oauth o = new Oauth();
		       o.setuId(queryUid);
		JSONObject parseObject = JSONObject.parseObject(JSONObject.toJSONString(o));
		ResponseMessage selectOauthtOne = accountConnector.selectOauthtOne(parseObject, request);
		Oauth oa = FastJsonUtil.mapToObject(selectOauthtOne.getReturnResult(), Oauth.class);
		travelInfoDto.setUid(queryUid);
		travelInfoDto.setOauthId(oa.getOauthId());
		travelInfoDto.setQueryUid(queryUid);
	}
	jsonTmp.put(Constant.COMMON_KEY_VO, travelInfoDto);
	jsonTmp.put(Constant.COMMON_KEY_PAGE, page);
	ResponseMessage responseMessage = travelConnector.queryPersonCenterList(jsonTmp, request);
	if(ResponseMessage.isSuccessResult(responseMessage)) {
		PageInfo1 p = FastJsonUtil.mapToObject(responseMessage.getReturnResult(), PageInfo1.class);
		List<JSONObject> records = p.getRecords();
		
		List<TravelInfoDto> rList = new ArrayList<TravelInfoDto>();
		for (JSONObject info : records) {
			TravelInfoDto dto = info.toJavaObject(TravelInfoDto.class);
			if(!ConstAccount.PERSONCENTER_COLLECT.equals(mold)) {
				dto.setCreateDateFmt(DateUtil.getDateStr(new Date(dto.getTime())));
			}
			rList.add(dto);	
		}
		//统计
		JSONObject tmpJSon = new JSONObject();
		TravelInfoDto dto = new TravelInfoDto();
		dto.setQueryUid(travelInfoDto.getQueryUid());
		if(!StringUtils.isEmpty(queryUid)) {//统计可见收藏夹个数
		dto.setVisual(ConstAccount.PERSONCENTER_VISUAL_SHOW);
		}
		tmpJSon.put(Constant.COMMON_KEY_VO, dto);
		ResponseMessage infoData = this.travelConnector.getInfoData(tmpJSon , request);
		boolean b = ResponseMessage.resultIsEmpty(infoData);
		if(!b) {
			TravelInfoDto countInfo = FastJsonUtil.mapToObject(infoData.getReturnResult(), TravelInfoDto.class);
			model.addAttribute("dt", countInfo);	
		}
		//收藏
		JSONObject tmpJson = new JSONObject();
		FavoritesDto fdto = new FavoritesDto();
		if(StringUtils.isEmpty(queryUid)) {
			fdto.setUid(sessionUser.getuId());
			
		}else {
			fdto.setUid(queryUid);
			fdto.setVisual(ConstAccount.PERSONCENTER_VISUAL_SHOW);
		}
		
		tmpJson.put(Constant.COMMON_KEY_VO, fdto);
		tmpJson.put(Constant.COMMON_KEY_PAGE,page );
		ResponseMessage queryfavList = this.travelConnector.queryfavList(tmpJson, request);
		if(!ResponseMessage.resultIsEmpty(queryfavList)) {
			PageInfo1 pageInfo = FastJsonUtil.mapToObject(queryfavList.getReturnResult(), PageInfo1.class);
			List<FavoritesDto> fList = pageInfo.getRecords();
			model.addAttribute("fList", fList);		
		}		
		model.addAttribute("cList",rList);
		model.addAttribute("page",p);
		model.addAttribute("usr",sessionUser);
		model.addAttribute("qUid",travelInfoDto.getQueryUid());
		model.addAttribute("isAsc",jsonObject.getString("isAsc"));
		
	}
	model.addAttribute("travelInfoDto",travelInfoDto);
	model.addAttribute("mold",travelInfoDto.getMold());
	  return "/account/personCenterList#"+ajaxCmd;
	
			
}
@RequestMapping("/favlistPage")
public String favlistPage(@RequestBody JSONObject jsonObject,ModelMap model,String ajaxCmd,HttpServletRequest request) {
	JSONObject tmpJson = new JSONObject();
	AccountVo sessionUser = SessionUtil.getSessionUser();
	FavoritesDto dto = new FavoritesDto();
	dto.setUid(sessionUser.getuId());
	tmpJson.put(Constant.COMMON_KEY_VO, dto);
	PageInfo1 page = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE).toJavaObject(PageInfo1.class);
	tmpJson.put(Constant.COMMON_KEY_PAGE,page );
	ResponseMessage queryfavList = this.travelConnector.queryfavList(tmpJson, request);
	if(!ResponseMessage.resultIsEmpty(queryfavList)) {
		PageInfo1 pageInfo = FastJsonUtil.mapToObject(queryfavList.getReturnResult(), PageInfo1.class);
		List<FavoritesDto> fList = pageInfo.getRecords();
		model.addAttribute("fList", fList);		
	}
	model.addAttribute("mold", jsonObject.getString("mold"));
	return "/account/favoratiesList#"+ajaxCmd;
}
/**
 * 收藏页面
 * @param model
 * @param ajaxCmd
 * @param request
 * @return
 */
@RequestMapping("/collectListPage")
public String collectListPage(@RequestBody JSONObject jsonObject, ModelMap model,String ajaxCmd,HttpServletRequest request) {
	FavoritesDto vo = new FavoritesDto();
	AccountVo sessionUser = SessionUtil.getSessionUser();
	Long tid = jsonObject.getLong("tid");
	vo.setUid(sessionUser.getuId());
	vo.setTid(tid);
	JSONObject tmpJson = new JSONObject();
	tmpJson.put(Constant.COMMON_KEY_VO, vo);
	ResponseMessage resp = this.travelConnector.selectFavoritesList(tmpJson, request);
	List<FavoritesDto> cList = FastJsonUtil.mapToList(resp.getReturnResult(), FavoritesDto.class);
	model.addAttribute("cList", cList);
	model.addAttribute("tid", tid);
	return "/travel/info/collect#"+ajaxCmd;
}
/**
 * 创建收藏夹
 * @param model
 * @param ajaxCmd
 * @param request
 * @return
 */
@RequestMapping("/insertFavorite")
@ResponseBody
public ResponseMessage insertFavorite(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
   ResponseMessage insertFavorite = this.travelConnector.insertFavorite(jsonObject, request);
	return insertFavorite;
}
/**
 * 收藏
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/collectArticle")
@ResponseBody
public ResponseMessage collectArticle(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
	
	ResponseMessage collectArticle = this.travelConnector.collectArticle(jsonObject, request);
	return collectArticle;
}
/**
 * 创建收藏夹页面
 * @param model
 * @param ajaxCmd
 * @param request
 * @return
 */
@RequestMapping("/favoratiesPage")
public String favoratiesPage(String tid,ModelMap model,String ajaxCmd,HttpServletRequest request) {
	model.addAttribute("tid", tid);
	return "/travel/info/favoraties";
}
/**
 * 会员中心创建收藏夹
 * @param id
 * @param model
 * @return
 */
@RequestMapping("/svaeOrUpdateFavoritesP")
public String svaeOrUpdateFavoritesP(Long id,ModelMap model,HttpServletRequest request) {
	if(null!=id) {
		JSONObject jsonObject = new JSONObject();
		Favorites f = new Favorites();
		f.setId(id);
		jsonObject.put(Constant.COMMON_KEY_VO, f);
		ResponseMessage selectOneFavortie = this.travelConnector.selectOneFavortie(jsonObject, request);
		if(!ResponseMessage.resultIsEmpty(selectOneFavortie)) {
			Favorites favorites = FastJsonUtil.mapToObject(selectOneFavortie.getReturnResult(), Favorites.class);
			model.addAttribute("favorites", favorites);
		}
		
	}
	
	model.addAttribute("id", id);
	return "/account/saveOrupdatefavoraties";
}
@RequestMapping("/updateFavortie")
@ResponseBody
public ResponseMessage updateFavortie(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
	ResponseMessage updateFavorite = this.travelConnector.updateFavorite(jsonObject, request);
	return updateFavorite;
	
}
@RequestMapping("/delFavortie")
@ResponseBody
public ResponseMessage delFavortie(Long id, HttpServletRequest request) {
	JSONObject jsonObject = new JSONObject();
	Favorites favorite = new Favorites();
	favorite.setId(id);
	favorite.setDeleteDate(DateUtil.currentLongDate());
	jsonObject.put(Constant.COMMON_KEY_VO, favorite);
	ResponseMessage updateFavorite = this.travelConnector.updateFavorite(jsonObject, request);
	return updateFavorite;
	
}
/**
 * 攻略预览
 * @param jsonObject
 * @return
 */
@RequestMapping("/pageview")
@ResponseBody
public ResponseMessage pageview(@RequestBody JSONObject jsonObject) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	//将对象保存到redis中
	String id = jsonObject.getString("id");
	//如果编号为空key为uuid,否则key为攻略编号;
	String key = StringHelper.getUuid();
	if(!StringUtils.isEmpty(id)) {
		key = id;
	}
	TravelInfoDto travelInfo = jsonObject.getJSONObject(Constant.COMMON_KEY_VO).toJavaObject(TravelInfoDto.class);
	HashMap<String, Object> m = new HashMap<String, Object>();
	m.put(key, travelInfo);
	redisManager.hmset(key, m, RedisManager.MINUTE_10);
	responseMessage.add("key", key);
	return responseMessage;	
}
//预览页面
@RequestMapping("/pageViewP")
public String pageViewP(String key,ModelMap model) {
	Map<Object, Object> hget = this.redisManager.hget(key);
   JSONObject objToJSONObject = FastJsonUtil.objToJSONObject(hget.get(key));
   TravelInfoDto travelInfo = objToJSONObject.toJavaObject(TravelInfoDto.class);
   model.addAttribute("travelInfo", travelInfo);
   model.addAttribute("id", key);
	return "/travel/info/pageview";
}
}
