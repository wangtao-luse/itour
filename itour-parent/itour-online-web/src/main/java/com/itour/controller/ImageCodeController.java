package com.itour.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.VerifyImage;
import com.itour.constant.Constant;
import com.itour.util.DateUtil;
import com.itour.util.StringHelper;
import com.itour.util.VerifyImageUtil;


@Controller
public class ImageCodeController {
private	static final String  default_url  = "/static/img/code";
private	static final String  small_url  = "/static/img/code/login";
	/**
	 * @param @return 参数说明
	 * @return BaseRestResult 返回类型
	 * @throws IOException 
	 * @Description: 生成滑块拼图验证码
	 * https://blog.csdn.net/a183400826/article/details/90752724?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
	 */

	@RequestMapping(value = "/getVerifyImage", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseMessage VerifyImage(@RequestBody JSONObject jsonObject,HttpServletRequest request) {
		String p = jsonObject.getString("small");
		Map<String, Object> resultMap = new HashMap<>();
		try {		
		    //读取本地路径下的图片,随机选一条
			//https://www.guitu18.com/post/2019/02/23/28.html
			String url=default_url;
			if("small".equals(p)) {
				url=small_url;
			}
		    String path = this.getClass().getResource(url).getPath();
			InputStream resourceAsStream = this.getClass().getResourceAsStream("/static/img/code/login");
			
		    File file = new File(path);
		    File[] files = file.listFiles();
		    int n = new Random().nextInt(files.length);
		    File imageUrl = files[n];
			
				VerifyImage verifyImage = VerifyImageUtil.getVerifyImage(imageUrl);
				 resultMap.put("verifyImage", verifyImage);
				 resultMap.put("errcode", "10");
				 resultMap.put("errmsg", "success");
				 String key =StringHelper.getUUID();
				 resultMap.put("key_verify",key);
			    //用于校验验证码xWidth
				 HttpSession session = request.getSession();
				 session.setAttribute(key, verifyImage.getxPosition());
				 session.setMaxInactiveInterval(120);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}		
	   
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
	public ResponseMessage verifyImageCode(@RequestParam(value = "moveLength") String moveLength,@RequestParam(value="key-verify") String key,HttpServletRequest request) {
	    Double dMoveLength = Double.valueOf(moveLength);
	    try {
	        Integer xWidth = (Integer) request.getSession().getAttribute(key);
	        if (xWidth == null) {	            
	            return ResponseMessage.getFailed("验证码过期，请重试");
	        }
	        if (Math.abs(xWidth - dMoveLength) > 10) {	            
	            return ResponseMessage.getFailed("验证不通过");
	        } else {
	           
	            return ResponseMessage.getSucess("验证通过");
	        }
	    } catch (Exception e) {
	        return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	    } finally {
	    	request.getSession().removeAttribute(key);
	    }
	}
	
	/**
	 * 校验邮箱验证码
	 * @param code
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkemailCode", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseMessage checkemailCode(@RequestParam(value = "code") Object code,@RequestParam(value="key-email-code") String key,HttpServletRequest request) {
		   Object k = request.getSession().getAttribute(key);
		   String ecode=null;
		   String time=null;
		   if(k!=null) {
			   String[] split = k.toString().split(",");
			   ecode=split[0];
			   time=split[1];
		   }
		   
		   if(!code.equals(ecode)) {//
			   return ResponseMessage.getFailed("验证码错误");
		   }else {
			   //验证码有效期
			   long valueOf = Long.valueOf(time);
			   //当前时间
			   long longDate = DateUtil.currentLongDate();
			   if(longDate>valueOf) {//当前日期大于验证码有效期
				   return ResponseMessage.getFailed("验证码已失效");
			   }else {
				   return ResponseMessage.getSucess();
			   }
		   }
		
	}
public static void main(String[] args) {
	Date d1= new Date();
	long t1 = d1.getTime();
  System.out.println(t1);
   Date addSecond = DateUtil.addSecond(d1, 120);
   System.out.println(addSecond.getTime());
}
}
