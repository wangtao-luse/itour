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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.ExUsernamePasswordToken;
import com.itour.connector.AccountConnector;
import com.itour.constant.ConstAccount;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.account.Oauth;
import com.itour.util.StringHelper;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountConnector accountConnector;
	/**
	 * 注册页面
	 * @return 注册页面
	 */
@RequestMapping("/reg")
public String reg(HttpServletRequest request,ModelMap model) {
	//生成UUID(相当于一个令牌)
    String randomUUID = StringHelper.getUUID();
    request.getSession().setAttribute("uuid", randomUUID);
   model.addAttribute("uuid", randomUUID);
	return "account/reg";
}
/**
 * 注册提交
 * @param jsonObject
 * @param request
 * @return
 */
@ResponseBody
@RequestMapping("/regSub")
public ResponseMessage regSub(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	 JSONObject tmpJson = new JSONObject();
	 Oauth oauth = new Oauth();
	       oauth.setOauthId(jsonObject.getString("email"));
	       oauth.setCredential(jsonObject.getString("kl"));
	       oauth.setNickname(jsonObject.getString("regName"));
	       
	       tmpJson.put("vo", oauth);
	       tmpJson.put("ipaddr", jsonObject);
	       Object oraginuid = request.getSession().getAttribute("uuid");
	       String uuid = jsonObject.getString("uuid");
	       if(!uuid.equals(oraginuid.toString())) {
	    	   return ResponseMessage.getFailed("请不要重复提交");
	       }
	ResponseMessage regSub = this.accountConnector.regSub(tmpJson,request);	
	if(Constant.SUCCESS_CODE.equals(regSub.getResultCode())) {
		request.getSession().setAttribute("uuid", "");
	}
	return regSub;
}
/**
 * 检查用户名是否可用
 * @param regName
 * @return
 */
@ResponseBody
@RequestMapping("/checkRegName")
public ResponseMessage checkRegName(@RequestParam(value = "regName")String regName,HttpServletRequest request) {
	JSONObject jsonObject =new JSONObject();
	jsonObject.put("type", "uname");
	jsonObject.put("regName", regName);
	ResponseMessage checkRegName = this.accountConnector.checkOauthId(jsonObject, request);
	return checkRegName;
}
/**
 * 检查邮箱是否可用
 * @param regName
 * @return
 */
@ResponseBody
@RequestMapping("/checkEmail")
public ResponseMessage checkEmail(@RequestParam(value = "email")String email,HttpServletRequest request) {
	JSONObject jsonObject =new JSONObject();
	jsonObject.put("type", ConstAccount.EMAIL);
	jsonObject.put("regName", email);
	ResponseMessage checkRegName = this.accountConnector.checkOauthId(jsonObject, request);
	return checkRegName;
}
/**
 * 检查邮箱是否在该系统中存在
 * @param email
 * @return
 */
@ResponseBody
@RequestMapping("/checkExistEmail")
public ResponseMessage checkExistEmail(@RequestParam(value = "email")String email,HttpServletRequest request) {
	JSONObject jsonObject =new JSONObject();
	jsonObject.put("type", ConstAccount.FINPWD);
	jsonObject.put("regName", email);
	ResponseMessage checkRegName = this.accountConnector.checkOauthId(jsonObject, request);
	return checkRegName;
}



/**
 * 登录页面
 * @return  登录页面
 */
@RequestMapping("/login")
public String login() {
	return "account/login";
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
			   }catch (AuthenticationException e) {
					// TODO: handle exception
					e.printStackTrace();
					return ResponseMessage.getFailed(e.getMessage());
					
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		
	return ResponseMessage.getSucess();
}
	/**
	 * 注册成功跳转页面
	 * @param regName
	 * @return
	 */
	@RequestMapping("/registerSucess")
	public String registerSucess(String regName) {		
		return "/account/register-success";
	}
	/**
	 * 忘记密码页面
	 * @return
	 */
	@RequestMapping("/setpwd")
	public String setpwd() {		
		return "/account/findpwd";
	}
	/**
	 * 修改密码
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	@RequestMapping("/findPwd")
	@ResponseBody
	public ResponseMessage findPwd(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage findpwd = this.accountConnector.findpwd(jsonObject, request);
		return findpwd;
	}
	/**
	 * 检查用户是否 存在
	 * @param email
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkUserName")
	public ResponseMessage checkUserName(@RequestParam(value = "email")String email,HttpServletRequest request) {
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("type", ConstAccount.FINPWD);
		jsonObject.put("regName", email);
		ResponseMessage checkRegName = this.accountConnector.checkOauthId(jsonObject, request);
		return checkRegName;
	}
}
