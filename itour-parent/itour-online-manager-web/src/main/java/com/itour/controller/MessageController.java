package com.itour.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.MessageConnector;
import com.itour.constant.Constant;
import com.itour.constant.ConstantMessage;
import com.itour.model.msg.Messageinfo;
import com.itour.util.DateUtil;
import com.itour.util.MessageUtil;

@Controller
@RequestMapping("/msg")
public class MessageController {
	@Autowired
	MessageConnector meesageConnector;
	/**
	 * 注册发送验证码
	 * @param email
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sendCodetoEmail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseMessage sendCodetoEmail(@RequestParam(value = "email") String email,HttpServletRequest request) {
	    JSONObject  jsonObject = new JSONObject();
	    String code = MessageUtil.getCode();
	    Messageinfo msg = new Messageinfo();
	    msg.setSubject("[爱旅行]注册验证码");
	    msg.setTo(email);
	    msg.setText("[爱旅行] 欢迎注册爱旅行账号,验证码"+code+"请在注册页面填入验证码,完成注册!");	    
	    msg.setTo(email);
	    msg.setAim(ConstantMessage.MSG_AIM_REGISTER);
	    msg.setOrigin(ConstantMessage.MSG_ORIGIN_ONLINE);
	    jsonObject.put("vo", msg);	    
	   ResponseMessage responseMessage = this.meesageConnector.sendEmailCode(jsonObject, request);
	   if(Constant.SUCCESS_CODE.equals(responseMessage.getResultCode())) {
		   request.getSession().setAttribute("code", code);
		   Date addSecond = DateUtil.addSecond(new Date(), 120);
		   request.getSession().setAttribute("limittime",addSecond.getTime());
	   }
	    return responseMessage;
	}
	
	@RequestMapping("/msgPage")
	public String msgPage() {
		return "/system/msg/messageTextManager";
	}
	@RequestMapping(value = "/queryViewMessageList", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseMessage queryViewMessageList(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		ResponseMessage responseMessage = this.meesageConnector.queryViewMessageList(jsonObject, request);
		return responseMessage;
	}
	@RequestMapping(value = "/findpwdSendCodetoEmail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseMessage findpwdSendCodetoEmail(@RequestParam(value = "email") String email,HttpServletRequest request) {
	    JSONObject  jsonObject = new JSONObject();
	    String code = MessageUtil.getCode();
	    Messageinfo msg = new Messageinfo();
	    msg.setSubject("[爱旅行]找回密码验证码");
	    msg.setTo(email);
	    msg.setText("[爱旅行] 您正在使用邮箱完成找回密码操作,验证码"+code+"请在找回密码页面填入验证码,修改密码!");	    
	    msg.setTo(email);
	    msg.setAim(ConstantMessage.MSG_AIM_FINDPWD);
	    msg.setOrigin(ConstantMessage.MSG_ORIGIN_ONLINE);
	    jsonObject.put("vo", msg);	    
	   ResponseMessage responseMessage = this.meesageConnector.sendEmailCode(jsonObject, request);
	   if(Constant.SUCCESS_CODE.equals(responseMessage.getResultCode())) {
		   request.getSession().setAttribute("code", code);
		   Date addSecond = DateUtil.addSecond(new Date(), 120);
		   request.getSession().setAttribute("limittime",addSecond.getTime());
	   }
	    return responseMessage;
	}
}
