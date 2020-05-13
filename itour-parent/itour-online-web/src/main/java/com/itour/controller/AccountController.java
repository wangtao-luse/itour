package com.itour.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.AccountConnector;
import com.itour.model.account.Oauth;

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
public String reg() {
	return "account/register-person";
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
	ResponseMessage regSub = this.accountConnector.regSub(tmpJson,request);	
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
	ResponseMessage checkRegName = this.accountConnector.checkRegName(jsonObject, request);
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
	jsonObject.put("type", "email");
	jsonObject.put("regName", email);
//	/ResponseMessage checkRegName = this.accountConnector.checkRegName(jsonObject, request);
	return ResponseMessage.getSucess();
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
	@ResponseBody
	@RequestMapping("/loginSub")
public ResponseMessage loginSub(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	ResponseMessage loginSub = this.accountConnector.loginSub(jsonObject, request);
	return loginSub;
}
}
