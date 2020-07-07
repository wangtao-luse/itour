package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
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
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
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
				String cname = jsonObject.getString("cname");
				//获取当前的 Subject
				Subject currentUser = SecurityUtils.getSubject();
				if(!currentUser.isAuthenticated()) {//当前用户是否已经被认证，即是否登录
					ExUsernamePasswordToken upt = new ExUsernamePasswordToken(username, password, ip,cname,jsonObject,request);
					upt.setRememberMe(true);
					try {
						//执行登录
						currentUser.login(upt);					
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
				   }catch (BaseException e) {
						// TODO: handle exception
						e.printStackTrace();
						return ResponseMessage.getFailed(e.getMessage());
				   }catch (AuthenticationException e) {
						// TODO: handle exception
						e.printStackTrace();
						return ResponseMessage.getFailed(e.getMessage());
						
					}
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
			}
			
		return ResponseMessage.getSucess();
	}
	/**
	 * 管理员管理页面
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
	@ResponseBody
	public ResponseMessage powerRole(@RequestBody JSONArray jsonArray,HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("arr", jsonArray);
		ResponseMessage powerRole = this.memberConnector.authorizeRoleList(jsonObject, request);
		return powerRole;
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
}
