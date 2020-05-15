package com.itour.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
	    msg.setSubject("[wangtao]注册验证码");
	    msg.setTo(email);
	    msg.setText("[wangtao] 欢迎注册wangtao博客,验证码"+code+"请在注册页面填入验证码,完成注册!");	    
	    msg.setTo(email);	    
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
