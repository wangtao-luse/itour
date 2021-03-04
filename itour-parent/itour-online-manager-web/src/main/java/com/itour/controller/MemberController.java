package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.ExUsernamePasswordToken;
import com.itour.connector.MemberConnector;
import com.itour.constant.ConstAccount;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.account.Oauth;
/**
 * 后台用户管理
 * @author wwang
 *
 */
@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberConnector memberConnector;	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		
		return "/system/account/login";
	}
	/**
	 * 登陆提交
	 * @param jsonObject
	 * @param request
	 * @return
	 */
		@RequestMapping("/loginSub")
		@ResponseBody
	public ResponseMessage loginSub(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
			try {
				String username = jsonObject.getString("loginname");
				String password = jsonObject.getString("nloginpwd");
				String ip = jsonObject.getString("ip");
				String cname = jsonObject.getString("city");
				//获取当前的 Subject
				Subject currentUser = SecurityUtils.getSubject();
				currentUser.getSession().setTimeout(1000*30);
				if(!currentUser.isAuthenticated()) {//当前用户是否已经被认证，即是否登录
					ExUsernamePasswordToken upt = new ExUsernamePasswordToken(username, password, ip,cname,jsonObject,request);
					upt.setRememberMe(false);
					try {
						//执行登录
						currentUser.login(upt);	
						currentUser.getSession().setAttribute("userName", username);
					}catch (UnknownAccountException e) {//用户不存在
						// TODO: handle exception
						e.printStackTrace();
						return ResponseMessage.getFailed(ExceptionInfo.EXCEPTION_ACCOUNTINFO);
					}catch (IncorrectCredentialsException e) {//用户存在，但密码不匹配
						// TODO: handle exception
						e.printStackTrace();
						return ResponseMessage.getFailed(ExceptionInfo.EXCEPTION_ACCOUNTINFO);
					}catch (LockedAccountException e) {//用户被锁定
						// TODO: handle exception
						e.printStackTrace();
						return ResponseMessage.getFailed(ExceptionInfo.EXCEPTION_STATUS);
				   }catch (AuthenticationException e) {
						// TODO: handle exception
						e.printStackTrace();
						return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
						
					}
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
			}
			
		return ResponseMessage.getSucess();
	}
	/**
	 * 管理员信息页面
	 * @return
	 */
	@RequestMapping("/adminPage")
	public String adminPage() {
		return "/system/admin/adminManager";
	}
	/**
	 * 管理员列表查询
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectAccountList")
	@ResponseBody
	public ResponseMessage selectAccountList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		jsonObject.put(Constant.COMMON_VO_NEEDTOTAL, "1");
		ResponseMessage list =this.memberConnector.selectAccountList(jsonObject,request);
		return list;
	}
	/**
	 * 管理员列表查询
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectViewAccountList")
	@ResponseBody
	public ResponseMessage selectViewAccountList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		jsonObject.put(Constant.COMMON_VO_NEEDTOTAL, "1");
		ResponseMessage list =this.memberConnector.selectViewAccountList(jsonObject,request);
		return list;
	}
	/**
	 * 组管理页面
	 * @return
	 */
	@RequestMapping("/groupPage")
	public String groupPage() {
		return "/system/member/group/groupManager";
	}
	/**
	 * 组管理页面
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getGroupList")
	@ResponseBody
	public ResponseMessage getGroupList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage groupList = this.memberConnector.groupList(jsonObject, request);
		return groupList;
	}
	/**
	 * 组授权角色页面
	 * @return
	 */
	@RequestMapping("/groupRoleP")
	public String groupRoleP(String groupId,ModelMap model) {
		model.addAttribute("groupId", groupId);
		return "/system/member/group/groupRole";
	}
	
	@RequestMapping("/authorizeRoleList")
	@ResponseBody
	public ResponseMessage authorizeRoleList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage authorizeRoleList = this.memberConnector.authorizeRoleList(jsonObject, request);
		return authorizeRoleList;
	}
	/**
	 * 对组进行角色授权
	 * @param jsonArray
	 * @param request
	 * @return
	 */
	@RequestMapping("/powerRole")
	@RequiresPermissions("/member/powerRole")
	@ResponseBody
	public ResponseMessage powerRole(@RequestBody JSONArray jsonArray,HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("arr", jsonArray);
		ResponseMessage powerRole = this.memberConnector.powerRole(jsonObject, request);
		return powerRole;
	}
	/**
	 * 组新增编辑页面
	 * @return
	 */
	
	@RequestMapping("/groupAddP")
	public String groupAddP(String id,ModelMap model) {
		model.addAttribute("id", id);
		return "/system/member/group/groupAdd";
	}
	/**
	 * 用户组新增
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertGroup")
	@RequiresPermissions("/member/insertGroup")
	@ResponseBody
	public ResponseMessage insertGroup(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage insertGroup = this.memberConnector.insertGroup(jsonObject, request);
		return insertGroup;
	}
	/**
	 * 组修改
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateGroup")
	@RequiresPermissions("/member/updateGroup")
	@ResponseBody
	public ResponseMessage updateGroup(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage updateGroup = this.memberConnector.updateGroup(jsonObject, request);
		return updateGroup;
	}
	/**
	 * 组查询单条
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getGroup")
	@ResponseBody
	public ResponseMessage getGroup(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage getGroup = this.memberConnector.getGroup(jsonObject, request);
		return getGroup;
	}
	/**
	 * 角色管理页面
	 * @return
	 */
	@RequestMapping("/rolePage")
	public String rolePage() {
		return "/system/member/role/roleManager";
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
		ResponseMessage roleList = this.memberConnector.roleList(jsonObject, request);
		return roleList;
	}
	/**
	 * 角色授权权限页面
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping("/roleRightP")
	public String roleRightP(String roleId,ModelMap model) {
		model.addAttribute("roleId", roleId);
		return "/system/member/role/roleRight";
	}
	/**
	 * 角色授权权限列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/authorizeRightList")
	@ResponseBody
	public ResponseMessage authorizeRightList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		return this.memberConnector.authorizeRightList(jsonObject,request);
	}
	/**
	 * 角色授权权限
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/powerRight")
	@RequiresPermissions("/member/powerRight")
	@ResponseBody
	public ResponseMessage powerRight(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		return this.memberConnector.powerRight(jsonObject, request);
	}
	/**
	 * 角色新增编辑页面
	 * @return
	 */
	@RequestMapping("/roleAddP")
	public String roleAddP(String id,ModelMap model) {
		model.addAttribute("id", id);
		return "/system/member/role/roleAdd";
	}
	/**
	 * 新增角色
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertRole")
	@RequiresPermissions("/member/insertRole")
	@ResponseBody
	public ResponseMessage insertRole(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage insertRole = this.memberConnector.insertRole(jsonObject, request);
		return insertRole;
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
		ResponseMessage getRoleList = this.memberConnector.getRole(jsonObject, request);
		return getRoleList;
    }
	/**
	 * 修改角色
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateRole")
	@RequiresPermissions("/member/updateRole")
	@ResponseBody
    public ResponseMessage updateRole(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		//ResponseMessage getRoleList = this.accountConnector.updateRole(jsonObject, request);
		return null;
    }
	/**
	 * 权限管理页面
	 * @return
	 */
	@RequestMapping("/rightPage")
	public String rightPage() {
		return "/system/member/right/rightManager";
	}
	/**
	 * 权限列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getRightList")
	@ResponseBody
	public ResponseMessage getRightList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		return this.memberConnector.rightList(jsonObject, request);
	}
	/**
	 * 权限列表（视图）
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getViewRightList")
	@ResponseBody
	public ResponseMessage getViewRightList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		return this.memberConnector.getViewRightList(jsonObject, request);
	}
	/**
	 * 获取指定组下的管理员
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getViewMAccountGroupList")
	@ResponseBody
	public ResponseMessage getViewMAccountGroupList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage getGroupAccountList = this.memberConnector.getViewMAccountGroupList(jsonObject, request);
		return getGroupAccountList;
	}
	/**
	 * 管理员分配会员页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/groupAccountP")
	public String groupAccountP(String id,ModelMap model) {
		model.addAttribute("groupId", id);
		return "/system/member/group/groupAccount";
	}
	/**
	 * 管理员分配新增管理员页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/groupAccountGrantP")
	public String accountGroupGrantP(String id,ModelMap model) {
		model.addAttribute("groupId", id);
		return "/system/member/group/groupAccountGrant";
	}
	/**
	 *  分配会员
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/grantAccount")
	@ResponseBody
    @RequiresPermissions("/member/grantAccount")
	public ResponseMessage grantAccount(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage grantAccount = this.memberConnector.grantAccount(jsonObject, request);
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
		ResponseMessage deleteAccountGroup = this.memberConnector.deleteAccountGroup(jsonObject, request);
		return deleteAccountGroup;
	}
	/**
	 * 管理员查看账号详情页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminDetailP")
	public String memberDetailP(String uid,ModelMap model) {
		model.addAttribute("uid", uid);
		return "/system/admin/adminDetail";
	}
	/**
	 *  管理员账号详情
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getViewOauthList")
	@ResponseBody
	public ResponseMessage getViewOauthList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage insertMember = this.memberConnector.getViewOauthList(jsonObject, request);
		return insertMember;
	}
	/**
	 * 管理员新增
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminAddP")
	public String adminAddP() {
		return "/system/admin/adminAdd";
	}
	/**
	 * 管理员修改
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminUpdateP")
	public String adminUpdateP(String id,ModelMap model) {
		model.addAttribute("id", id);
		return "/system/admin/adminUpdate";
	}
	/**
	 *  管理员查询单条
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAccount")
	@ResponseBody
	public ResponseMessage getAccount(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage insertMember = this.memberConnector.getViewOauthList(jsonObject, request);
		return insertMember;
	}
	/**
	 *  管理员修改
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateAccount")
	@ResponseBody
	public ResponseMessage updateAccount(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage insertMember = this.memberConnector.getViewOauthList(jsonObject, request);
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
		ResponseMessage checkOauthId = this.memberConnector.checkOauthId(tmpJson, request);
		if(Constant.FAILED_CODE.equals(checkOauthId.getResultCode())) {
			return ResponseMessage.getFailed(checkOauthId.getResultMessage());
		}
		//检查用户名是否可以
		tmpJson.clear();
		tmpJson.put("type", ConstAccount.UNAME);
		tmpJson.put("regName", oaauthVo.getNickname());
		ResponseMessage checkUanme = this.memberConnector.checkOauthId(tmpJson, request);
		if(Constant.FAILED_CODE.equals(checkUanme.getResultCode())) {
			return ResponseMessage.getFailed(checkUanme.getResultMessage());
		}
		ResponseMessage regSub = this.memberConnector.regSub(jsonObject, request);
		return regSub;
	}
}
