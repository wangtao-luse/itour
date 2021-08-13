package com.itour.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.AccountVo;
import com.itour.connector.WorkConnector;
import com.itour.constant.Constant;
import com.itour.model.work.Label;
import com.itour.model.work.WorkColumn;
import com.itour.util.FastJsonUtil;
import com.itour.util.SessionUtil;


@Controller
@RequestMapping("/work")
public class WorkController {
	@Autowired
	WorkConnector workConnector;
/**
 * 日志页面
 * @return
 */
@RequestMapping("/index")
public String index() {
	return "/work/index";
}
/**
 * 日志新增页面
 * @return
 */
@RequestMapping("/workMd")
public String workMd(ModelMap model,HttpServletRequest request) {
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
	
	return "/work/info/workinfoMd";
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
}
