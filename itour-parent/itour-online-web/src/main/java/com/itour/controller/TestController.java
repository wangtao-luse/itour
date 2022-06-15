package com.itour.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("/login")
public Map login(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
	Map<String,Object> resultMap = new HashMap<String, Object>();
	String password = jsonObject.getString("password");
	String userName = jsonObject.getString("userName");
	try {
		Subject subject = SecurityUtils.getSubject();
		if(!subject.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			subject.login(token);
			resultMap.put("code", Constant.FAILED_CODE);
			resultMap.put("msg", Constant.SUCESS_MESSAGE);
		}
	} catch (UnknownAccountException e) {//用户不存在
        // TODO: handle exception
		e.printStackTrace();
		resultMap.put("code", Constant.FAILED_CODE);
		resultMap.put("msg", ExceptionInfo.EXCEPTION_ACCOUNTINFO);
	}catch (IncorrectCredentialsException e) {//用户存在，但密码不匹配
		// TODO: handle exception
		e.printStackTrace();
		resultMap.put("code", Constant.FAILED_CODE);
		resultMap.put("msg", ExceptionInfo.EXCEPTION_ACCOUNTINFO);
	}catch (LockedAccountException e) {//用户被锁定
		// TODO: handle exception
		e.printStackTrace();
		resultMap.put("code", Constant.FAILED_CODE);
		resultMap.put("msg", ExceptionInfo.EXCEPTION_STATUS);
	}catch (AuthenticationException e) {
		// TODO: handle exception
		e.printStackTrace();
		resultMap.put("code", Constant.FAILED_CODE);
		resultMap.put("msg", Constant.FAILED_SYSTEM_ERROR);
			
	}catch (Exception e) {
    	// TODO: handle exception
		e.printStackTrace();
		resultMap.put("msg", Constant.FAILED_SYSTEM_ERROR);
    }

	return resultMap;
}
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello";
	}
}
