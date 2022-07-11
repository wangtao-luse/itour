package com.itour.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.MessageConnector;
import com.itour.constant.Constant;
import com.itour.constant.ConstantMessage;
import com.itour.model.quartz.Messageinfo;
import com.itour.util.DateUtil;
import com.itour.util.MessageUtil;
import com.itour.util.StringHelper;

@Controller
@RequestMapping("/msg")
public class MessageController {
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
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
	public ResponseMessage sendCodetoEmail(@RequestParam(value = "email") String email,@RequestParam(value = "ip") String ip,HttpServletRequest request) {
	    JSONObject  jsonObject = new JSONObject();
	    String code = MessageUtil.getCode();
	    Messageinfo msg = new Messageinfo();
	    msg.setSubject("[爱旅行]注册验证码");
	    msg.setTo(email);
	    msg.setText("[爱旅行] 欢迎注册爱旅行账号,验证码"+code+"请在注册页面填入验证码,完成注册!");	    
	    msg.setTo(email);
	    msg.setAim(ConstantMessage.MSG_AIM_REGISTER);
	    msg.setOrigin(ConstantMessage.MSG_ORIGIN_ONLINE);
	    msg.setIp(ip);;
	    jsonObject.put("vo", msg);	  
	    logger.info(msg.getText());
	   ResponseMessage responseMessage = this.meesageConnector.sendEmailCode(jsonObject, request);
	   if(Constant.SUCCESS_CODE.equals(responseMessage.getResultCode())) {
		   String uuid = StringHelper.getUuid();
		   responseMessage.add("key_email_code", uuid);
		   Date addSecond = DateUtil.addSecond(new Date(), 120);
		   HttpSession session = request.getSession();
		   session.setAttribute(uuid, code+","+(addSecond.getTime()));
	   }
	    return responseMessage;
	}
	/**
	 * 找回密码发送验证码
	 * @param email
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findpwdSendCodetoEmail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseMessage findpwdSendCodetoEmail(@RequestParam(value = "email") String email,@RequestParam(value = "ip") String ip,HttpServletRequest request) {
		JSONObject  jsonObject = new JSONObject();
		String code = MessageUtil.getCode();
		Messageinfo msg = new Messageinfo();
		msg.setSubject("[爱旅行]找回密码验证码");
		msg.setTo(email);
		msg.setText("[爱旅行] 您正在使用邮箱完成找回密码操作,验证码"+code+"请在找回密码页面填入验证码,修改密码!");	    
		msg.setTo(email);
		msg.setAim(ConstantMessage.MSG_AIM_REGISTER);
		msg.setOrigin(ConstantMessage.MSG_ORIGIN_ONLINE);
		msg.setIp(ip);;
		jsonObject.put("vo", msg);	    
		ResponseMessage responseMessage = this.meesageConnector.sendEmailCode(jsonObject, request);
		if(Constant.SUCCESS_CODE.equals(responseMessage.getResultCode())) {
			String uuid = StringHelper.getUuid();
			responseMessage.add("key_email_code", uuid);
			Date addSecond = DateUtil.addSecond(new Date(), 120);
			request.getSession().setAttribute(uuid, code+","+(addSecond.getTime()));
		}
		return responseMessage;
	}
}
