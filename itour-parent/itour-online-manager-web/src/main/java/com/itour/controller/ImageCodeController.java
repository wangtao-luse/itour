package com.itour.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.util.RandomValidateCode;

@Controller
@RequestMapping("/verify")
public class ImageCodeController {
	/*
     * 登录页面生成验证码
     */
    @RequestMapping(value = "/getVerify")
    @ResponseBody
    public ResponseMessage getVerify(HttpServletRequest request, HttpServletResponse response){
        ResponseMessage responseMessage = ResponseMessage.getSucess();
    	try {
            Map<String, Object> randcode = RandomValidateCode.getRandcode(request, response);//输出验证码图片方法
            responseMessage.add(randcode);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
        }
    	return responseMessage;
    }
    @RequestMapping(value = "/checkVerify")
    @ResponseBody
    public ResponseMessage checkVerify(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response){
        ResponseMessage responseMessage = ResponseMessage.getSucess();
    	try {
    		String verify = jsonObject.getString("verify");
    		String key = jsonObject.getString("key");
    		HttpSession session = request.getSession();
    		Object checkVerify = session.getAttribute(key);
    		if(null==checkVerify) {
    			return ResponseMessage.getFailed("验证码过期，请重试");
    		}else if(!verify.equalsIgnoreCase(checkVerify.toString())) {
    			return ResponseMessage.getFailed("验证码错误");
    		}
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
        }
    	return responseMessage;
    }

}
