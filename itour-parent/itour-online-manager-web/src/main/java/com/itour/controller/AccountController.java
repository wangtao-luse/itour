package com.itour.controller;


import java.io.Reader;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.AccountConnector;
import com.itour.constant.ConstAccount;
import com.itour.constant.Constant;
import com.itour.model.account.Oauth;
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
	@RequiresPermissions("/account/insertGroup")
	@ResponseBody
	public ResponseMessage insertGroup(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage insertGroup = this.accountConnector.insertGroup(jsonObject, request);
		return insertGroup;
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
	 * 对组进行角色授权
	 * @param jsonArray
	 * @param request
	 * @return
	 */
	@RequestMapping("/powerRole")
	@ResponseBody
	@RequiresPermissions("/account/powerRole")
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
	@RequiresPermissions("/account/updateGroup")
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
	 * 新增角色
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertRole")
	@RequiresPermissions("/account/insertRole")
	@ResponseBody
	public ResponseMessage insertRole(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage insertRole = this.accountConnector.insertRole(jsonObject, request);
		return insertRole;
	}
	/**
	 * 修改角色
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateRole")
	@RequiresPermissions("/account/updateRole")
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
	@RequiresPermissions("/account/powerRight")
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
	 * 权限列表（视图）
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
	* 前台会员管理页面
	* @return
	*/
	@RequestMapping("/accountPage")
	public String accountPage() {
		return "/system/account/usr/accountManager";
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
	 * 分配会员页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/groupAccountP")
	public String groupAccountP(String id,ModelMap model) {
		model.addAttribute("groupId", id);
		return "/system/right/group/groupAccount";
	}
	/**
	 * 获取指定组下的用户
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
	@RequiresPermissions("/account/grantAccount")
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
	@RequestMapping("/accountAddP")
	public String memberAddP() {
		return "/system/account/usr/accountAdd";
	}
	/**
	 * 后台注册前台会员页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/accountUpdateP")
	public String accountUpdateP(String id,ModelMap model) {
		model.addAttribute("id", id);
		return "/system/account/usr/accountUpdate";
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
	 * 查看前台用户详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/accountDetailP")
	public String memberDetailP(String uid,ModelMap model) {
		model.addAttribute("uid", uid);
		return "/system/account/usr/accountDetail";
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
	/**
	 * 注册
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/regSub")
	@ResponseBody
	public ResponseMessage regSub(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		Oauth oaauthVo = jsonObject.getJSONObject("vo").toJavaObject(Oauth.class);
		//检查邮箱是否可用
		JSONObject tmpJson = new JSONObject();
		tmpJson.put("type", ConstAccount.EMAIL);
		tmpJson.put("regName",oaauthVo.getOauthId());
		ResponseMessage checkOauthId = this.accountConnector.checkOauthId(tmpJson, request);
		if(Constant.FAILED_CODE.equals(checkOauthId.getResultCode())) {
			return ResponseMessage.getFailed(checkOauthId.getResultMessage());
		}
		//检查用户名是否可以
		tmpJson.clear();
		tmpJson.put("type", ConstAccount.UNAME);
		tmpJson.put("regName", oaauthVo.getNickname());
		ResponseMessage checkUanme = this.accountConnector.checkOauthId(tmpJson, request);
		if(Constant.FAILED_CODE.equals(checkUanme.getResultCode())) {
			return ResponseMessage.getFailed(checkUanme.getResultMessage());
		}
		ResponseMessage regSub = this.accountConnector.regSub(jsonObject, request);
		return regSub;
	}
	/**
	 * 用户查询单条
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAccount")
	@ResponseBody
	public ResponseMessage getAccount(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage getAccount = this.accountConnector.getAccount(jsonObject, request);
		return getAccount;
	}
	/**
	 * 用户修改
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateAccount")
	@ResponseBody
	public ResponseMessage updateAccount(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage updateAccount = this.accountConnector.updateAccount(jsonObject, request);
		return updateAccount;
	}
	/**
	 * 登录列表页面
	 * @return
	 */
	@RequestMapping("/loginListPage")
	public String loginListPage() {
		return "/system/account/usr/log/loginListManager";
	}
	 
	@RequestMapping("/queryLoginList")
	@ResponseBody
	public ResponseMessage queryLoginList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage queryLoginList = this.accountConnector.queryLoginList(jsonObject, request);
		return queryLoginList;
	}
}
