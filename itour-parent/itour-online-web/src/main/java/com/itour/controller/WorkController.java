package com.itour.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.itour.connector.WorkConnector;
import com.itour.constant.Constant;
import com.itour.constant.ConstantTravel;
import com.itour.constant.RedisKey;
import com.itour.model.dto.PageInfo;
import com.itour.model.travel.vo.ViewCommentReply;
import com.itour.model.work.Label;
import com.itour.model.work.WorkColumn;
import com.itour.model.work.WorkInfo;
import com.itour.model.work.Worktext;
import com.itour.model.work.vo.WorkCommentVo;
import com.itour.model.work.vo.WorkInfoVo;
import com.itour.util.DateUtil;
import com.itour.util.FastJsonUtil;
import com.itour.util.IpUtil;
import com.itour.util.MarkdownUtils;
import com.itour.util.SessionUtil;


@Controller
@RequestMapping("/work")
public class WorkController {
	@Autowired
	WorkConnector workConnector;
	@Autowired
private RedisManager redisManager;
/**
 * 日志页面
 * @return
 */
@RequestMapping("/index")
public String index(Page page,ModelMap model,HttpServletRequest request) {
	JSONObject jsonObject = new JSONObject();
	WorkInfoVo vo = new WorkInfoVo();
	jsonObject.put(Constant.COMMON_KEY_VO, vo);
	jsonObject.put(Constant.COMMON_KEY_PAGE, page);
	ResponseMessage selectWorkInfoList = this.workConnector.selectWorkInfoList(jsonObject, request);
	if(ResponseMessage.isSuccessResult(selectWorkInfoList)) {
		PageInfo pageInfo = FastJsonUtil.mapToObject(selectWorkInfoList.getReturnResult(), PageInfo.class);
		List<WorkInfoVo> records = pageInfo.getRecords();
		model.addAttribute("page", pageInfo);
		model.addAttribute("workInfo", records);
	}
	return "/work/index";
}
/**
 * 日志新增页面
 * @return
 */
@RequestMapping("/workMd")
public String workMd(ModelMap model,HttpServletRequest request) {
	workInfoData(model, request);	
	return "/work/info/workinfoMd";
}
/**
 * 日志修改页面
 * @return
 */
@RequestMapping("/workUpdateMd")
public String workUpdateMd(Long id,ModelMap model,HttpServletRequest request) {
	//判断该用户是否有对该日志修改的权限
	AccountVo sessionUser = SessionUtil.getSessionUser();
	JSONObject jsonObject = new JSONObject();
	WorkInfo work = new WorkInfo();
	work.setId(id);
	work.setUid(sessionUser.getuId());
	jsonObject.put(Constant.COMMON_KEY_VO, work);
	ResponseMessage selectWorkInfoOne = this.workConnector.selectWorkInfoOne(jsonObject, request);
	if(ResponseMessage.isSuccessResult(selectWorkInfoOne)) {
		Map<String, Object> returnResult = selectWorkInfoOne.getReturnResult();
		if(StringUtils.isEmpty(returnResult.get(Constant.COMMON_KEY_RESULT))) {
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
public String search() {
	return "/work/search";
}


/**
 * 日志新增编辑
 * @param jsonObject
 * @param request
 * @return
 */
@RequestMapping("/savaOrUpdateWorkInfo")
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
	 //1.独立IP访问数作为显示的浏览量（浏览量功能相关）
	  ip(request,String.valueOf(id));
     //2.获取旅行信息
      workInfo(id, model, request);
    model.addAttribute("id", id);
	return "/work/info/detail";
}
private void ip(HttpServletRequest request,String id) {
	 //1.组装key
	 String strDate = DateUtil.getStrDate(new Date(), "yyyy-MM-dd");
	 String ipAddr = IpUtil.getIpAddr(request);
	 String key=ipAddr+"::"+strDate+"::"+id;
		 boolean isMember = this.redisManager.sisMember(RedisKey.KEY_WORK_IPS, key);		 
		 if(!isMember) {//缓存中没有则添加到缓存
		 	this.redisManager.sAdd(RedisKey.KEY_WORK_IPS,key);
			this.redisManager.incr(RedisKey.KEY_WORK_IP_COUNT, 1);
		 }
}
private void workInfo(Long id, ModelMap model, HttpServletRequest request) {
	JSONObject jsonObject = new JSONObject();
	WorkInfoVo tmp = new WorkInfoVo();
	AccountVo sessionUser = SessionUtil.getSessionUser();
	model.addAttribute("sessionUser", sessionUser);
	if(!StringUtils.isEmpty(sessionUser)) {
		tmp.setQueryUid(sessionUser.getuId());
	}
	tmp.setId(id);
	 jsonObject.put(Constant.COMMON_KEY_VO, tmp);
	ResponseMessage resp = this.workConnector.selectWorkInfo(jsonObject , request);
	if(ResponseMessage.isSuccessResult(resp)) {
		WorkInfoVo travelInfo = FastJsonUtil.mapToObject(resp.getReturnResult(), WorkInfoVo.class);			
		 model.addAttribute("workInfo", travelInfo);
		 //获取周末旅行攻略的内容
			 jsonObject.clear();
			 jsonObject.put("wid", id);
			
			ResponseMessage weekinfo =  this.workConnector.selecWorktextOne(jsonObject, request);
			if(Constant.SUCCESS_CODE.equals(weekinfo.getResultCode())) {
				Worktext worktext = FastJsonUtil.mapToObject(weekinfo.getReturnResult(), Worktext.class, Constant.COMMON_KEY_RESULT);
				String markdownToHtml = MarkdownUtils.markdownToHtml(worktext.getWcontent());
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
		PageInfo resultPage = FastJsonUtil.mapToObject(returnResult, PageInfo.class, Constant.COMMON_KEY_RESULT);
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
public ResponseMessage workCommentReplyLikeSub(JSONObject jsonObject, HttpServletRequest request) {
	ResponseMessage responseMessage = this.workConnector.workCommentReplyLikeSub(jsonObject, request);
	return responseMessage;
}
}
