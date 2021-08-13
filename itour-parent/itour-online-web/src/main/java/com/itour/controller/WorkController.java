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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.AccountVo;
import com.itour.connector.WorkConnector;
import com.itour.constant.Constant;
import com.itour.model.vo.PageInfo;
import com.itour.model.work.Label;
import com.itour.model.work.WorkColumn;
import com.itour.model.work.WorkInfo;
import com.itour.model.work.dto.WorkInfoDto;
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
public String index(Page page,ModelMap model,HttpServletRequest request) {
	JSONObject jsonObject = new JSONObject();
	WorkInfoDto vo = new WorkInfoDto();
	jsonObject.put(Constant.COMMON_KEY_VO, vo);
	jsonObject.put(Constant.COMMON_KEY_PAGE, page);
	ResponseMessage selectWorkInfoList = this.workConnector.selectWorkInfoList(jsonObject, request);
	if(ResponseMessage.isSuccessResult(selectWorkInfoList)) {
		PageInfo pageInfo = FastJsonUtil.mapToObject(selectWorkInfoList.getReturnResult(), PageInfo.class);
		List<WorkInfoDto> records = pageInfo.getRecords();
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
	workInfoData(model, request);
	//查询单条日志信息
	JSONObject jsonObject = new JSONObject();
	WorkInfo info = new WorkInfo();
	info.setId(id);
	jsonObject.put(Constant.COMMON_KEY_VO, info);
	ResponseMessage selectWorkInfo = this.workConnector.selectWorkInfoOne(jsonObject, request);
	if(ResponseMessage.isSuccessResult(selectWorkInfo)) {
		WorkInfoDto workInfo = FastJsonUtil.mapToObject(selectWorkInfo.getReturnResult(), WorkInfoDto.class);
		model.addAttribute("workInfo", workInfo);
	}
	return "/work/info/workUpdateMd";
}
/**
 * 标签和专栏
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
}
