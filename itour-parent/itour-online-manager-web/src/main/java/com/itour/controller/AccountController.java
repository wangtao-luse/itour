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
import com.itour.constant.Constant;
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
	/**
	* 前台会员管理页面
	* @return
	*/
	@RequestMapping("/accountPage")
	public String accountPage() {
		return "/system/account/usr/memberManager";
	}
	/**
	   * 前台会员列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectAccountList")
	@ResponseBody
	public ResponseMessage selectAccountList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		jsonObject.put(Constant.COMMON_VO_NEEDTOTAL, "1");
		ResponseMessage selectAccountList = this.accountConnector.selectViewAccountList(jsonObject, request);
		return selectAccountList;
	}
	/**
	 * 前台会员列表(视图)
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectViewAccountList")
	@ResponseBody
	public ResponseMessage selectViewAccountList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		jsonObject.put(Constant.COMMON_VO_NEEDTOTAL, "1");
		ResponseMessage selectViewAccountList = this.accountConnector.selectViewAccountList(jsonObject, request);
		return selectViewAccountList;
	}
	/**
	 * 前台会员列表（视图）
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectViewRightList")
	@ResponseBody
	public ResponseMessage selectViewRightList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage selectViewRightList = this.accountConnector.selectViewRightList(jsonObject, request);
		return selectViewRightList;
	}
	/**
	 * 前台分配会员
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/accountGroupP")
	public String accountGroupP(String id,ModelMap model) {
		model.addAttribute("groupId", id);
		return "/system/right/group/accountGroup";
	}
	/**
	 * 前台获取指定组下的用户
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getViewAAccountGroupList")
	@ResponseBody
	public ResponseMessage getViewAAccountGroupList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage getGroupAccountList = this.accountConnector.getViewAAccountGroupList(jsonObject, request);
		return getGroupAccountList;
	}
	/**
	 * 前台分配会员页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/accountGroupGrantP")
	public String accountGroupGrantP(String id,ModelMap model) {
		model.addAttribute("groupId", id);
		return "/system/right/group/accountGroupGrant";
	}
	/**
	 *  分配会员
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/grantAccount")
	@ResponseBody
	public ResponseMessage grantAccount(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage grantAccount = this.accountConnector.grantAccount(jsonObject, request);
		return grantAccount;
	}
	/**
	 *  删除已分配会员的所属组
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteAccountGroup")
	@ResponseBody
	public ResponseMessage deleteAccountGroup(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage deleteAccountGroup = this.accountConnector.deleteAccountGroup(jsonObject, request);
		return deleteAccountGroup;
	}
	
	/**
	 * 后台注册前台会员页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/memberAddP")
	public String memberAddP(String id,ModelMap model) {
		model.addAttribute("id", id);
		return "/system/account/usr/memberAdd";
	}
	/**
	 *  新增前台会员
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertMember")
	@ResponseBody
	public ResponseMessage insertMember(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage insertMember = this.accountConnector.insertMember(jsonObject, request);
		return insertMember;
	}
	/**
	 * 后台查看前台用户详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/memberDetailP")
	public String memberDetailP(String uid,ModelMap model) {
		model.addAttribute("uid", uid);
		return "/system/account/usr/memberDetail";
	}
	/**
	 *  前台会员查看账号详情
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getViewOauthList")
	@ResponseBody
	public ResponseMessage getViewOauthList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage insertMember = this.accountConnector.getViewOauthList(jsonObject, request);
		return insertMember;
	}
	
}
