package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.MemberConnector;

@Controller
@RequestMapping("/account")
public class MemberController {
	@Autowired
	MemberConnector memberConnector;
	/**
	 * 组管理页面
	 * @return
	 */
	@RequestMapping("/groupPage")
	public String groupPage() {
		return "/system/right/groupManager";
	}
	@RequestMapping("/getGroupList")
	@ResponseBody
    public ResponseMessage getGroupList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage groupList = this.memberConnector.groupList(jsonObject, request);
		return groupList;
    }
	@RequestMapping("/selectGroup")
	public String selectGroup() {
		return "/system/right/selectGroup";
	}
}
