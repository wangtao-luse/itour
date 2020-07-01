package com.itour.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.AccountConnector;
import com.itour.model.account.GroupRole;
/**
 * 前台用户管理
 * @author wwang
 *
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountConnector accountConnector;
	/**
	 * 组管理页面
	 * @return
	 */
	@RequestMapping("/groupPage")
	public String groupPage() {
		return "/system/right/groupManager";
	}
	/**
	 * 新增组页面
	 * @return
	 */
	@RequestMapping("/groupAddP")
	public String groupAddP(String id,ModelMap model) {
		model.addAttribute("id", id);
		return "/system/right/groupAdd";
	}
	/**
	 * 查看组详情
	 * @return
	 */
	@RequestMapping("/selectGroup")
	public String selectGroup() {
		return "/system/right/selectGroup";
	}
	/**
	 * 用户组列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getGroupList")
	@ResponseBody
    public ResponseMessage getGroupList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage groupList = this.accountConnector.getGroupList(jsonObject, request);
		return groupList;
    }
	/**
	 * 用户组新增
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertGroup")
	@ResponseBody
	public ResponseMessage insertGroup(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage insertGroup = this.accountConnector.insertGroup(jsonObject, request);
		return insertGroup;
	}
	/**
	 * 角色列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRoleList")
	@ResponseBody
    public ResponseMessage getRoleList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage getRoleList = this.accountConnector.getRoleList(jsonObject, request);
		return getRoleList;
    }
	/**
	   * 授权角色列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/authorizeRoleList")
	@ResponseBody
	public ResponseMessage authorizeRole(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage authorizeRole = this.accountConnector.authorizeRoleList(jsonObject, request);
		return authorizeRole;
	}
	/**
	 * 授权角色页面
	 * @return
	 */
	@RequestMapping("/groupRoleP")
	public String groupRoleP(String groupId,ModelMap model) {
		model.addAttribute("groupId", groupId);
		return "/system/right/groupRole";
	}
	@RequestMapping("/powerRole")
	@ResponseBody
	public ResponseMessage powerRole(@RequestBody JSONArray jsonArray,HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("arr", jsonArray);
		ResponseMessage powerRole = this.accountConnector.powerRole(jsonObject, request);
		return powerRole;
	}
	@RequestMapping("/getGroup")
	@ResponseBody
	public ResponseMessage getGroup(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage getGroup = this.accountConnector.getGroup(jsonObject, request);
		return getGroup;
	}
	@RequestMapping("/updateGroup")
	@ResponseBody
	public ResponseMessage updateGroup(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage updateGroup = this.accountConnector.updateGroup(jsonObject, request);
		return updateGroup;
	}
}
