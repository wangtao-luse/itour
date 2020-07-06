package com.itour.controller;


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
		return "/system/right/group/groupManager";
	}
	/**
	 * 新增组页面
	 * @return
	 */
	@RequestMapping("/groupAddP")
	public String groupAddP(String id,ModelMap model) {
		model.addAttribute("id", id);
		return "/system/right/group/groupAdd";
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
		return "/system/right/group/groupRole";
	}
	/**
	 * 对组进行角色授权
	 * @param jsonArray
	 * @param request
	 * @return
	 */
	@RequestMapping("/powerRole")
	@ResponseBody
	public ResponseMessage powerRole(@RequestBody JSONArray jsonArray,HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("arr", jsonArray);
		ResponseMessage powerRole = this.accountConnector.powerRole(jsonObject, request);
		return powerRole;
	}
	/**
	 * 组单条查询
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getGroup")
	@ResponseBody
	public ResponseMessage getGroup(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage getGroup = this.accountConnector.getGroup(jsonObject, request);
		return getGroup;
	}
	/**
	 * 组修改
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateGroup")
	@ResponseBody
	public ResponseMessage updateGroup(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage updateGroup = this.accountConnector.updateGroup(jsonObject, request);
		return updateGroup;
	}
	/**
	 * 角色管理页面
	 * @return
	 */
	@RequestMapping("rolePage")
	public String rolePage() {
		return "/system/right/role/roleManager";
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
	 * 角色新增编辑页面
	 * @return
	 */
	@RequestMapping("/roleAddP")
	public String roleAddP(String id,ModelMap model) {
		model.addAttribute("id", id);
		return "/system/right/role/roleAdd";
	}
	/**
	 * 修改角色
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateRole")
	@ResponseBody
    public ResponseMessage updateRole(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage getRoleList = this.accountConnector.updateRole(jsonObject, request);
		return getRoleList;
    }
	/**
	 * 角色查询单条
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRole")
	@ResponseBody
    public ResponseMessage getRole(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage getRoleList = this.accountConnector.getRole(jsonObject, request);
		return getRoleList;
    }
	/**
	 * 新增角色
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertRole")
	@ResponseBody
	public ResponseMessage insertRole(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage insertRole = this.accountConnector.insertRole(jsonObject, request);
		return insertRole;
	}
	@RequestMapping("/roleRightP")
	public String roleRightP(String roleId,ModelMap model) {
		model.addAttribute("roleId", roleId);
		return "/system/right/role/roleRight";
	}
	/**
	 * 角色授权权限列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/authorizeRightList")
	@ResponseBody
	public ResponseMessage authorizeRightList(@RequestBody JSONObject jsonObject,HttpServletRequest request){
		ResponseMessage authorizeRightList = this.accountConnector.authorizeRightList(jsonObject,request);
	return authorizeRightList;
		
	}
	/**
	 * 角色授权 权限
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/powerRight")
	@ResponseBody
	public ResponseMessage powerRight(@RequestBody JSONObject jsonObject,HttpServletRequest request){
		ResponseMessage powerRight = this.accountConnector.powerRight(jsonObject,request);
		return powerRight;
		
	}
    /**
            * 权限管理页面	
     * @return
     */
	@RequestMapping("/rightPage")
	public String rightPage() {
		return "/system/right/right/rightManager";
	}
	/**
	 * 权限列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectRightList")
	@ResponseBody
	public ResponseMessage selectRightList(@RequestBody JSONObject jsonObject,HttpServletRequest request){
		ResponseMessage powerRight = this.accountConnector.selectRightList(jsonObject,request);
		return powerRight;
		
	}
}
