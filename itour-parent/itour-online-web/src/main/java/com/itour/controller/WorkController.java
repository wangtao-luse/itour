package com.itour.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.common.redis.RedisManager;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.AccountVo;
import com.itour.connector.AccountConnector;
import com.itour.connector.WorkConnector;
import com.itour.constant.ConstAccount;
import com.itour.constant.Constant;
import com.itour.constant.ConstantTravel;
import com.itour.constant.RedisKey;
import com.itour.help.DateHelper;
import com.itour.help.StringHelper;
import com.itour.model.account.Oauth;
import com.itour.model.dto.PageInfo;
import com.itour.model.travel.dto.FavoritesDto;
import com.itour.model.travel.dto.TravelInfoDto;
import com.itour.model.travel.vo.ViewCommentReply;
import com.itour.model.work.Label;
import com.itour.model.work.Like;
import com.itour.model.work.WorkColumn;
import com.itour.model.work.WorkInfo;
import com.itour.model.work.Worktext;
import com.itour.model.work.dto.WorkInfoDto;
import com.itour.model.work.vo.WorkCommentVo;
import com.itour.model.work.vo.WorkInfoVo;
import com.itour.util.FastJsonUtil;
import com.itour.util.IpUtil;
import com.itour.util.MarkdownUtils;
import com.itour.util.SessionUtil;

import cn.hutool.core.util.StrUtil;


@Controller
@RequestMapping("/work")
public class WorkController {
    public static final Logger logger = LoggerFactory.getLogger(WorkController.class);
	@Autowired
	WorkConnector workConnector;
	@Autowired
private RedisManager redisManager;
	@Autowired 
	AccountConnector accountConnector;
/**
 * 日志页面
 * @return
 */
@RequestMapping("/index")
public String index(Page page,ModelMap model,HttpServletRequest request,String ajaxCmd) {
	JSONObject jsonObject = new JSONObject();
	WorkInfoVo vo = new WorkInfoVo();
	jsonObject.put(Constant.COMMON_KEY_VO, vo);
	jsonObject.put(Constant.COMMON_KEY_PAGE, page);
	ResponseMessage selectWorkInfoList = this.workConnector.selectWorkInfoList(jsonObject, request);
	if(ResponseMessage.isSuccessResult(selectWorkInfoList)) {
		PageInfo<WorkInfoVo> pageInfo = FastJsonUtil.mapToObject(selectWorkInfoList.getReturnResult(), PageInfo.class);
		List<WorkInfoVo> records = pageInfo.getRecords();
		model.addAttribute("page", pageInfo);
		model.addAttribute("workInfo", records);
	}
	if(StrUtil.isEmpty(ajaxCmd)) {
	   return "/work/index";
	}else {
     return "/work/index#"+ajaxCmd;
	}
}
/**
 * 日志新增页面
 * @return
 */
@RequestMapping("/workMd")
@RequiresPermissions("/work/workMd")
public String workMd(ModelMap model,HttpServletRequest request) {
	workInfoData(model, request);	
	return "/work/info/workinfoMd";
}
/**
 * 日志修改页面
 * @return
 */
@RequestMapping("/workUpdateMd")
@RequiresPermissions("/work/workUpdateMd")
public String workUpdateMd(Long id,ModelMap model,HttpServletRequest request) {
	//1.判断该用户是否有对该日志修改的权限
	AccountVo sessionUser = SessionUtil.getSessionUser();
	JSONObject jsonObject = new JSONObject();
	WorkInfo work = new WorkInfo();
	work.setId(id);
	work.setUid(sessionUser.getuId());
	jsonObject.put(Constant.COMMON_KEY_VO, work);
	ResponseMessage selectWorkInfoOne = this.workConnector.selectWorkInfoOne(jsonObject, request);
	if(ResponseMessage.isSuccessResult(selectWorkInfoOne)) {
		Map<String, Object> returnResult = selectWorkInfoOne.getReturnResult();
		if(StrUtil.isEmptyIfStr(returnResult.get(Constant.COMMON_KEY_RESULT))) {
			//提示没有权限操作该文章
			model.addAttribute("error", ConstantTravel.EXCEPTION_INFO_NOAUTHOR);
		}else {
			workInfoData(model, request);
			//查询单条日志信息
			jsonObject.clear();
			WorkInfo info = new WorkInfo();
			info.setId(id);
			jsonObject.put(Constant.COMMON_KEY_VO, info);
			ResponseMessage selectWorkInfo = this.workConnector.selectWorkInfoOne(jsonObject, request);
			if(ResponseMessage.isSuccessResult(selectWorkInfo)) {
				WorkInfoVo workInfo = FastJsonUtil.mapToObject(selectWorkInfo.getReturnResult(), WorkInfoVo.class);
				model.addAttribute("workInfo", workInfo);
			}
			 jsonObject.clear();
			 jsonObject.put("wid", id);	
			ResponseMessage textMsg =  this.workConnector.selecWorktextOne(jsonObject, request);
			if(ResponseMessage.isSuccessResult(textMsg)) {
				Worktext workText = FastJsonUtil.mapToObject(textMsg.getReturnResult(), Worktext.class);
				model.addAttribute("workText", workText);
			}
			//获取对应日志的标签
			jsonObject.clear();
			jsonObject.put("id", id);
			ResponseMessage workTagList = this.workConnector.workTagList(jsonObject, request);
			if(ResponseMessage.isSuccessResult(workTagList)) {
				List<Label> labelList = FastJsonUtil.mapToList(workTagList.getReturnResult(), Label.class);
				model.addAttribute("lList", labelList);
			}
			//获取对应日志的分类专栏
			jsonObject.clear();
			jsonObject.put("id", id);
			ResponseMessage workColList = this.workConnector.workColList(jsonObject, request);
			if(ResponseMessage.isSuccessResult(workColList)) {
				List<WorkColumn> colList = FastJsonUtil.mapToList(workColList.getReturnResult(), WorkColumn.class);
				model.addAttribute("cList", colList);
			}
			
		}
	}
	 model.addAttribute("id", id);
	return "/work/info/workinfoUpdateMd";
}
/**
 * 获取指定用户创建的标签和专栏
 * @param model
 * @param request
 */
private void workInfoData(ModelMap model, HttpServletRequest request) {
	AccountVo sessionUser = SessionUtil.getSessionUser();
	JSONObject jsonObject = new JSONObject();
	//1.标签列表
	Label label_vo = new Label();
	      label_vo.setUid(sessionUser.getuId());
	jsonObject.put(Constant.COMMON_KEY_VO, label_vo);
	ResponseMessage resp = this.workConnector.queryLabelList(jsonObject, request);
	if(ResponseMessage.isSuccessResult(resp)) {
		List<Label> labelList = FastJsonUtil.mapToList(resp.getReturnResult(), Label.class);
		model.addAttribute("labelList", labelList);
	}
	jsonObject.clear();
	WorkColumn col_vo = new WorkColumn();
	col_vo.setUid(sessionUser.getuId());
	jsonObject.put(Constant.COMMON_KEY_VO, col_vo);
	ResponseMessage result = this.workConnector.queryColumnList(jsonObject, request);
	if(ResponseMessage.isSuccessResult(result)) {
		List<WorkColumn> colList = FastJsonUtil.mapToList(result.getReturnResult(), WorkColumn.class);
		model.addAttribute("colList", colList);
	}
}
/**
 * 日志搜索页面
 * @return
 */
@RequestMapping("/search")
public String search(WorkInfoDto dto,PageInfo page,HttpServletRequest request,ModelMap model,String ajaxCmd) {
	String parameter = request.getParameter("search");
	JSONObject jsonObject = new JSONObject();
	dto.setTitle(parameter);
	jsonObject.put(Constant.COMMON_KEY_DTO, dto);
	jsonObject.put(Constant.COMMON_KEY_PAGE, page);
	ResponseMessage searchTextList = this.workConnector.searchTextList(jsonObject, request);
	PageInfo pageInfo = FastJsonUtil.mapToObject(searchTextList.getReturnResult(), PageInfo.class);
	
	List<WorkInfoVo> records = pageInfo.getRecords();
	model.addAttribute("tList", records);
	model.addAttribute("page", pageInfo);
	model.addAttribute("search", parameter);
	if(StrUtil.isEmpty(ajaxCmd)) {
		return "/work/search";
	}else {
		return "/work/search#"+ajaxCmd;
	}
	
}

/**
 * 日志新增编辑
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/savaOrUpdateWorkInfo")
@RequiresPermissions("/work/savaOrUpdateWorkInfo")
@ResponseBody
public ResponseMessage savaOrUpdateWorkInfo(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage resp = this.workConnector.savaOrUpdateWorkInfo(jsonObject, request);
	return resp;
}

/**
 * 日志详情页面
 * @return
 */
@RequestMapping("/detail")
public String detail(Long id,ModelMap model,HttpServletRequest request) {
	/**
	 * 1.pv(浏览量):页面浏览量,同一页面多次打开浏览量累计;
	 * 2.ip(独立ip):1天内使用不同IP地址的用户访问网站的数量,同一IP无论访问了网站里的多少个页面，独立IP数均为1。
	 * 3.vv(访问次数):从访客来到您网站到最终关闭网站的所有页面离开，计为1次访问。若访客连续30分钟没有新开和刷新页面，或者访客关闭了。
	 * 4.(uv)独立访客:1天内相同的访客多次访问您的网站只计算1个UV，以cookie为依据。
	 */
	//1.独立IP访问数作为显示的浏览量（浏览量功能相关）
	  ip(request,String.valueOf(id));
     //2.获取旅行信息
      WorkInfoVo workInfo = workInfo(id, model, request);
      //获取分类专栏
      queryColumnList(workInfo.getUid(),  request, model);
      model.addAttribute("id", id);
	return "/work/info/detail";
}
/**
 * 独立IP数也称IP数，指1天内使用不同IP地址的用户访问网站的数量，同一IP无论访问了几个页面，独立IP数均为1;
 * 实现思路：
 * 1.浏览时使用key=ip::yyyy-MM-dd::文章Id方式
 * @param request
 * @param id
 */
private void ip(HttpServletRequest request,String id) {
	
	 //1.组装key,ip::文章Id
	 String strDate = DateHelper.getStrDate(new Date(), DateHelper.FMT_DATETIME);
	 String ipAddr = IpUtil.getIpAddr(request);
	 //key=192.168.1.8::2022-6-22::1
	 String key=ipAddr+"::"+id;
	 //2.判断对应的key是否已经存在
	 boolean isMember = this.redisManager.sisMember(RedisKey.KEY_WORKINFO_IP_LIST, key);		 
		 if(!isMember) {//缓存中没有则添加到缓存
			 //3.对应的IP对应的key放入缓存
		 	this.redisManager.sAdd(RedisKey.KEY_WORKINFO_IP_LIST,key);		 	
		 	this.redisManager.sisMember(RedisKey.KEY_WORKINFO_IP_LIST_DATE, ipAddr+"::"+strDate+"::"+id);
		 }
}

private WorkInfoVo workInfo(Long id, ModelMap model, HttpServletRequest request) {
	JSONObject jsonObject = new JSONObject();
	WorkInfoVo tmp = new WorkInfoVo();
	AccountVo sessionUser = SessionUtil.getSessionUser();
	model.addAttribute("sessionUser", sessionUser);
	if(!StrUtil.isEmptyIfStr(sessionUser)) {
		tmp.setQueryUid(sessionUser.getuId());
	}
	tmp.setId(id);
	 jsonObject.put(Constant.COMMON_KEY_VO, tmp);
	ResponseMessage resp = this.workConnector.selectWorkInfo(jsonObject , request);
	WorkInfoVo travelInfo = new WorkInfoVo();
	if(ResponseMessage.isSuccessResult(resp)) {
		 travelInfo = FastJsonUtil.mapToObject(resp.getReturnResult(), WorkInfoVo.class);			
		 model.addAttribute("workInfo", travelInfo);
		 //获取工作日志的内容
			 jsonObject.clear();
			 jsonObject.put("wid", id);
			
			ResponseMessage weekinfo =  this.workConnector.selecWorktextOne(jsonObject, request);
			if(Constant.SUCCESS_CODE.equals(weekinfo.getResultCode())) {
				Worktext worktext = FastJsonUtil.mapToObject(weekinfo.getReturnResult(), Worktext.class, Constant.COMMON_KEY_RESULT);
				String markdownToHtml = MarkdownUtils.markdownToHtmlExtensitons(worktext.getWcontent());
				worktext.setWcontent(markdownToHtml);
				model.addAttribute("worktext", worktext);
			}
		 //获取旅行攻略的标签列表
		 jsonObject.clear();
		 jsonObject.put("id", id);
		 ResponseMessage tagResp = this.workConnector.workTagList(jsonObject, request);
		 if(Constant.SUCCESS_CODE.equals(tagResp.getResultCode())) {
			 List<Label> labelList = FastJsonUtil.mapToList(tagResp.getReturnResult(), Label.class, Constant.COMMON_KEY_RESULT);
			 model.addAttribute("labelList", labelList);
		 }
	}
	if(!StringHelper.isEmpty(sessionUser)) {
		jsonObject.clear();
		Like like = new Like();
		like.setWid(id);
		like.setUid(sessionUser.getuId());
		jsonObject.put(Constant.COMMON_KEY_VO,like);
		ResponseMessage checkIsLike = this.workConnector.checkIsLike(jsonObject, request);
		if(!ResponseMessage.resultIsEmpty(checkIsLike)) {
			Like likePojo = FastJsonUtil.mapToObject(checkIsLike.getReturnResult(), Like.class);
			model.addAttribute("like", likePojo);
		}
	}
	
	
	return travelInfo;
}
//点赞

@RequestMapping("/likeSub")
@ResponseBody
public ResponseMessage likeSub(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		AccountVo sessionUser = SessionUtil.getSessionUser();
		jsonObject.put("uid", sessionUser.getuId());
		responseMessage = this.workConnector.likeSub(jsonObject, request);
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
return responseMessage;	
}

@RequestMapping("/commentList")
public String commentList(@RequestBody JSONObject jsonObject,ModelMap model,String ajaxCmd,HttpServletRequest request ) {
	Long id = jsonObject.getLong("id");
	Page pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE).toJavaObject(Page.class);
	//1.获取旅行信息
	workInfo(id, model, request);
	//2.获取评论信息;
	commentList(jsonObject, model, request,pageVo);
	 model.addAttribute("id", id);
	 model.addAttribute("order", jsonObject.getString("order"));	 
	return "/work/info/commentList#"+ajaxCmd;	
}
private void commentList(JSONObject jsonTmp, ModelMap model, HttpServletRequest request,Page page) {
	JSONObject jsonObject = new JSONObject();
	 jsonObject.put("tid", jsonTmp.getLong("id"));
	 jsonObject.put("orderbyList", jsonTmp.getJSONArray("orderbyList"));
	 page.setSize(10);
	 jsonObject.put(Constant.COMMON_KEY_PAGE, page);
	ResponseMessage respMsg = this.workConnector.queryWorkCommentList(jsonObject, request);
	if(ResponseMessage.isSuccessResult(respMsg)) {
		Map<String, Object> returnResult = respMsg.getReturnResult();
		PageInfo<WorkCommentVo> resultPage = FastJsonUtil.mapToObject(returnResult, PageInfo.class, Constant.COMMON_KEY_RESULT);
		resultPage.pageNav();
		resultPage.getPs();
		List<WorkCommentVo> commentList = resultPage.getRecords();
		model.addAttribute("commentList", commentList);
		model.addAttribute(Constant.COMMON_KEY_PAGE, resultPage);
		model.addAttribute(ConstantTravel.TRAVEL_COMMENTSIZE, returnResult.get(ConstantTravel.TRAVEL_COMMENTSIZE));
	}
	
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
	ResponseMessage insertComment = this.workConnector.insertComment(jsonObject, request);
	return insertComment;
	
}
/**
 * 删除评论
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/delComment")
@ResponseBody
public ResponseMessage delComment(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage delComment = this.workConnector.delComment(jsonObject, request);
	return delComment;
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
	return "/work/info/commentReply#"+ajaxCmd;
}
//评论回复新增
@RequestMapping("/insertWorkCommentReply")
@ResponseBody
public ResponseMessage insertWorkCommentReply(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	AccountVo sessionUser = SessionUtil.getSessionUser();
	jsonObject.put("fromUid", sessionUser.getuId());
	ResponseMessage resp = this.workConnector.insertWorkCommentReply(jsonObject, request);
	return resp;
}
//评论回复删除
@RequestMapping("/delWorkCommentReply")
@ResponseBody
public ResponseMessage delWorkCommentReply(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
   ResponseMessage delCommentRely = this.workConnector.delWorkCommentReply(jsonObject, request);
	return delCommentRely;
}
/**
 * 工作日志评论回复点赞数
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/workCommentReplyLikeSub")
@ResponseBody
public ResponseMessage workCommentReplyLikeSub(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
	ResponseMessage responseMessage = this.workConnector.workCommentReplyLikeSub(jsonObject, request);
	return responseMessage;
}
/**
 * 工作日期分类专栏
 * @param jsonObject
 * @param request
 * @return
 */
public void queryColumnList(String uid, HttpServletRequest request,ModelMap model) {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("uid", uid);
	ResponseMessage queryColumnList = this.workConnector.getShowColumnList(jsonObject, request);
	if(ResponseMessage.isSuccessResult(queryColumnList)) {
		model.addAttribute("colShowList", queryColumnList.getReturnResult().get(Constant.COMMON_KEY_RESULT));
	}
}
@RequestMapping("/category/{id}")
public String category(@PathVariable(value = "id") Long id,Page page,ModelMap model,HttpServletRequest request) {
	JSONObject jsonObject = new JSONObject();
	WorkInfoVo vo = new WorkInfoVo();
	   vo.setColId(id);
	jsonObject.put(Constant.COMMON_KEY_VO, vo);
	jsonObject.put(Constant.COMMON_KEY_PAGE, page);
	ResponseMessage selectWorkInfoList = this.workConnector.queryWorkByColList(jsonObject, request);
	if(ResponseMessage.isSuccessResult(selectWorkInfoList)) {
		PageInfo<WorkInfoVo> pageInfo = FastJsonUtil.mapToObject(selectWorkInfoList.getReturnResult(), PageInfo.class);
		List<WorkInfoVo> records = pageInfo.getRecords();
		model.addAttribute("page", pageInfo);
		model.addAttribute("workInfo", records);
	}
	jsonObject.clear();
	WorkColumn col = new WorkColumn();
	col.setId(id);
	jsonObject.put(Constant.COMMON_KEY_VO, col);
	ResponseMessage selectOneColumn = this.workConnector.selectOneColumn(jsonObject, request);
	if(ResponseMessage.isSuccessResult(selectOneColumn)) {
		WorkColumn mapToObject = FastJsonUtil.mapToObject(selectOneColumn.getReturnResult(), WorkColumn.class);
		queryColumnList(mapToObject.getUid(),  request, model);
	}
	
	return "/work/info/category";
}

@RequestMapping("/infoManager")
public String infoManager(HttpServletRequest request,ModelMap model) {
	String uid = request.getParameter("rpm");
	AccountVo sessionUser = SessionUtil.getSessionUser();
	if(!StrUtil.isEmpty(uid)) {
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
	
	
	return "/work/info/infoManager";
}
@RequestMapping("/inofMangerList")
public String inofMangerList(@RequestBody JSONObject jsonObject,ModelMap model,String ajaxCmd,HttpServletRequest request) {
	WorkInfoDto workDto = jsonObject.toJavaObject(WorkInfoDto.class);
	PageInfo page = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE).toJavaObject(PageInfo.class);
	AccountVo sessionUser = SessionUtil.getSessionUser();
	JSONObject jsonTmp = new JSONObject();
	String mold = workDto.getMold();
	//默认查询动态
	if(StrUtil.isEmpty(mold)) {
		workDto.setMold("1");
	}
	//queryUid有值说明是查看别人主页
	String queryUid = jsonObject.getString("rpm");
	if(StrUtil.isEmpty(queryUid)) {//查看自己的的主页
		workDto.setUid(sessionUser.getuId());
		workDto.setOauthId( sessionUser.getOauthId());
		workDto.setQueryUid(sessionUser.getuId());
	}else {//查看别人的主页
		  //查看目标用户的用户信息
		  Oauth o = new Oauth();
		       o.setuId(queryUid);
		JSONObject parseObject = JSONObject.parseObject(JSONObject.toJSONString(o));
		ResponseMessage selectOauthtOne = accountConnector.selectOauthtOne(parseObject, request);
		Oauth oa = FastJsonUtil.mapToObject(selectOauthtOne.getReturnResult(), Oauth.class);
		workDto.setUid(queryUid);
		workDto.setOauthId(oa.getOauthId());
		workDto.setQueryUid(queryUid);
	}
	jsonTmp.put(Constant.COMMON_KEY_VO, workDto);
	jsonTmp.put(Constant.COMMON_KEY_PAGE, page);
	ResponseMessage responseMessage = workConnector.queryPersonCenterList(jsonTmp, request);
	if(ResponseMessage.isSuccessResult(responseMessage)) {
		PageInfo p = FastJsonUtil.mapToObject(responseMessage.getReturnResult(), PageInfo.class);
		List<JSONObject> records = p.getRecords();
		
		List<WorkInfoVo> rList = new ArrayList<WorkInfoVo>();
		for (JSONObject info : records) {
			WorkInfoVo dto = info.toJavaObject(WorkInfoVo.class);
			if(!ConstAccount.PERSONCENTER_COLLECT.equals(mold)) {
				dto.setCreateDateFmt(DateHelper.getDateStr(new Date(dto.getTime())));
			}
			rList.add(dto);	
		}
		//统计
		JSONObject tmpJSon = new JSONObject();
		WorkInfoDto dto = new WorkInfoDto();
		dto.setQueryUid(workDto.getQueryUid());
		if(!StrUtil.isEmpty(queryUid)) {//统计可见收藏夹个数
		dto.setVisual(ConstAccount.PERSONCENTER_VISUAL_SHOW);
		}
		tmpJSon.put(Constant.COMMON_KEY_VO, dto);
		ResponseMessage infoData = this.workConnector.getInfoData(tmpJSon , request);
		if(!ResponseMessage.resultIsEmpty(infoData)) {
			WorkInfoVo countInfo = FastJsonUtil.mapToObject(infoData.getReturnResult(), WorkInfoVo.class);
			model.addAttribute("dt", countInfo);	
		}
		//收藏
		JSONObject tmpJson = new JSONObject();
		FavoritesDto fdto = new FavoritesDto();
		if(StrUtil.isEmpty(queryUid)) {
			fdto.setUid(sessionUser.getuId());
			
		}else {
			fdto.setUid(queryUid);
			fdto.setVisual(ConstAccount.PERSONCENTER_VISUAL_SHOW);
		}
		
		tmpJson.put(Constant.COMMON_KEY_DTO, fdto);
		tmpJson.put(Constant.COMMON_KEY_PAGE,page );
		ResponseMessage queryfavList = this.workConnector.queryLikeList(tmpJson, request);
		if(!ResponseMessage.resultIsEmpty(queryfavList)) {
			PageInfo pageInfo = FastJsonUtil.mapToObject(queryfavList.getReturnResult(), PageInfo.class);
			List<FavoritesDto> fList = pageInfo.getRecords();
			model.addAttribute("fList", fList);		
		}		
		model.addAttribute("cList",rList);
		model.addAttribute("page",p);
		model.addAttribute("usr",sessionUser);
		model.addAttribute("qUid",workDto.getQueryUid());
		model.addAttribute("isAsc",jsonObject.getString("isAsc"));
		
	}
	model.addAttribute("travelInfoDto",workDto);
	model.addAttribute("mold",workDto.getMold());
	  return "/work/info/infoManagerList#"+ajaxCmd;
	
			
}
}
