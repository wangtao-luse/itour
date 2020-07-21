package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.DictionaryConnetor;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
	@Autowired
	private DictionaryConnetor dictionaryConnetor;
	/**
	 * 字典列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDictionaryList")
	@RequiresPermissions("/dictionary/getDictionaryList")
	@ResponseBody
	public ResponseMessage getDictionaryList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
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
	@ResponseBody
	@RequiresPermissions("/dictionary/getViewDictionaryList")
	public ResponseMessage getViewDictionaryList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		return dictionaryConnetor.getViewDictionaryList(jsonObject, request);
	}
	/**
	 * 字典列表页面
	 * @return
	 */
	@RequestMapping("/dictPage")
	@RequiresPermissions("/dictionary/dictPage")
	public String dictPage() {
		return "/system/dictionary/dictionaryManager";
	}
	/**
	 * 字典列表页面
	 * @return
	 */
	@RequestMapping("/dictUpdatePage")
	public String dictUpdatePage(String id,ModelMap model) {
		model.addAttribute("id", id);
		return "/system/dictionary/dictUpdate";
	}
	/**
	 * 字典表查询
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDictData")
	@ResponseBody
	public ResponseMessage getDictData(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		return dictionaryConnetor.getDictData(jsonObject, request);
	}
	/**
	 * 字典表查询
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateDictionary")
	@ResponseBody
	public ResponseMessage updateDictionary(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		return dictionaryConnetor.updateDictionary(jsonObject, request);
	}
	/**
	 * 字典表查询
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDictionary")
	@ResponseBody
	public ResponseMessage getDictionary(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		return dictionaryConnetor.getDictionary(jsonObject, request);
	}
	
}
