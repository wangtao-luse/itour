package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.DictionaryConnetor;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
	private DictionaryConnetor dictionaryConnetor;
	/**
	 * 字典列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDictionaryList")
	@ResponseBody
	public ResponseMessage getDictionaryList(JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage dictionaryList = dictionaryConnetor.getDictionaryList(jsonObject, request);
		return dictionaryList;
	}
	/**
	 * 字典列表 视图
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getViewDictionaryList")
	public ResponseMessage getViewDictionaryList(JSONObject jsonObject,HttpServletRequest request) {
		return dictionaryConnetor.getViewDictionaryList(jsonObject, request);
	}
	/**
	 * 字典列表页面
	 * @return
	 */
	@RequestMapping("/dictPage")
	public String dictionaryPage() {
		return "/system/dictionary/dictionaryManager";
	}
	
}
