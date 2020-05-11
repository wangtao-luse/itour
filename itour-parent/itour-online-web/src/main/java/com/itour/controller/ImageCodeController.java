package com.itour.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.VerifyImage;
import com.itour.constant.Constant;
import com.itour.util.VerifyImageUtil;


@Controller
public class ImageCodeController {
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
	    String cutImage = verifyImage.getCutImage();
	    String srcImage = verifyImage.getSrcImage();
	    Integer xPosition = verifyImage.getXPosition();
	    Integer yPosition = verifyImage.getYPosition();		
		  resultMap.put("bigImage", srcImage);//大图 
		  resultMap.put("smallImage", cutImage);//小图
		  resultMap.put("xWidth",xPosition); 
		  resultMap.put("yHeight",yPosition);		 
	    request.getSession().setAttribute("xWidth", resultMap.get("xWidth"));
	    resultMap.remove("xWidth");
	    resultMap.put("errcode", "10");
	    resultMap.put("errmsg", "success");
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
	    Map<String, Object> resultMap = new HashMap<>();
	    try {
	        Integer xWidth = (Integer) request.getSession().getAttribute("xWidth");
	        if (xWidth == null) {
	            resultMap.put("errcode", 1);
	            resultMap.put("errmsg", "验证过期，请重试");
	            return new ResponseMessage(resultMap);
	        }
	        if (Math.abs(xWidth - dMoveLength) > 10) {
	            resultMap.put("errcode", 1);
	            resultMap.put("errmsg", "验证不通过");
	        } else {
	            resultMap.put("errcode", 0);
	            resultMap.put("errmsg", "验证通过");
	        }
	    } catch (Exception e) {
	        return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	    } finally {
	    	request.getSession().removeAttribute("xWidth");
	    }
	    return new ResponseMessage(resultMap);
	}
	@RequestMapping(value = "/sendCodetoEmail", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseMessage sendCodetoEmail(@RequestParam(value = "email") String email,HttpServletRequest request) {
	    Map<String, Object> resultMap = new HashMap<>();
	    try {
	          Random r = new Random();
	          int nextInt = r.nextInt(999999)+100000;
	          String sendCode = String.valueOf(nextInt);
	          
	          request.getSession().setAttribute("sendCode", sendCode+email);
	        if (StringUtils.isEmpty(email)) {
	            resultMap.put("errcode", 1);
	            resultMap.put("errmsg", "手机号码不能为空！");
	            return new ResponseMessage(resultMap);
	        }
	       System.out.println("验证码为：-------------------》"+sendCode);
	    } catch (Exception e) {
	        return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	    } finally {
	    	request.getSession().removeAttribute("sendCode");
	    }
	    return new ResponseMessage(resultMap);
	}
	

}
