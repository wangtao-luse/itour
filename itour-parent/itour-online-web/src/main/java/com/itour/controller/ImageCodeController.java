package com.itour.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.VerifyImage;
import com.itour.connector.MessageConnector;
import com.itour.constant.Constant;
import com.itour.constant.ConstantMessage;
import com.itour.model.msg.MessageText;
import com.itour.util.VerifyImageUtil;


@Controller
public class ImageCodeController {
	@Autowired
	MessageConnector meesageConnector;
	/**
	 * @param @return 参数说明
	 * @return BaseRestResult 返回类型
	 * @throws IOException 
	 * @Description: 生成滑块拼图验证码
	 * https://blog.csdn.net/a183400826/article/details/90752724?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
	 */

	@RequestMapping(value = "/getVerifyImage", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseMessage VerifyImage(HttpServletRequest request) throws IOException {
	    Map<String, Object> resultMap = new HashMap<>();
	    //读取本地路径下的图片,随机选一条
	    String path = this.getClass().getResource("/static/img/code").getPath();
	    File file = new File(path);
	    File[] files = file.listFiles();
	    int n = new Random().nextInt(files.length);
	    File imageUrl = files[n];
	    VerifyImage verifyImage = VerifyImageUtil.getVerifyImage(imageUrl);		
	    resultMap.put("verifyImage", verifyImage);
	    resultMap.put("errcode", "10");
	    resultMap.put("errmsg", "success");
	    //用于校验验证码
	    request.getSession().setAttribute("xWidth", verifyImage.getxPosition());
	    return new ResponseMessage(resultMap);
	}
	
	/**
	 * 校验滑块拼图验证码
	 *
	 * @param moveLength 移动距离
	 * @return BaseRestResult 返回类型
	 * @Description: 生成图形验证码
	 */
	@RequestMapping(value = "/checkImageCode", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseMessage verifyImageCode(@RequestParam(value = "moveLength") String moveLength,HttpServletRequest request) {
	    Double dMoveLength = Double.valueOf(moveLength);
	    try {
	        Integer xWidth = (Integer) request.getSession().getAttribute("xWidth");
	        if (xWidth == null) {	            
	            return ResponseMessage.getFailed("验证过期，请重试");
	        }
	        if (Math.abs(xWidth - dMoveLength) > 10) {	            
	            return ResponseMessage.getFailed("验证不通过");
	        } else {
	           
	            return ResponseMessage.getSucess("验证通过");
	        }
	    } catch (Exception e) {
	        return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	    } finally {
	    	request.getSession().removeAttribute("xWidth");
	    }
	}
	@RequestMapping(value = "/sendCodetoEmail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseMessage sendCodetoEmail(@RequestParam(value = "email") String email,HttpServletRequest request) {
	    JSONObject  jsonObject = new JSONObject();
	    MessageText msg = new MessageText();
	    msg.setAim(ConstantMessage.REGCODE);
	    msg.setTo(email);
	    jsonObject.put("vo", msg);
	  // ResponseMessage responseMessage = this.meesageConnector.sendCode(jsonObject, request);
	    return ResponseMessage.getSucess();
	}
	

}
