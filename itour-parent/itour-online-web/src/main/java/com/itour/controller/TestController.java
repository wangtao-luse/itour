package com.itour.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.account.api.AccountApi;
import com.itour.common.resp.ResponseMessage;

@Controller
public class TestController {
	@Autowired
	AccountApi accountApi;
	
	
	@RequestMapping("/getList")
	@ResponseBody
	public ResponseMessage getList(@RequestBody JSONObject jsonObject) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		Student stu = new Student();
		List<Student> list= new ArrayList<>();
		list.add(new Student("01", "mike", "10"));
		list.add(new Student("02", "amy", "18"));
		list.add(new Student("03", "cindy", "20"));
		stu.setRecords(list);
		responseMessage.setReturnResult(stu);
		return responseMessage;
	}
	public static long setDate(Date date){		 
		 long result = date.getTime()/1000;
		 return result;
	}
	public String formateDate(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = new Date(time);
		 String format = sdf.format(date);
		  return  format;
	}
   public static void main(String[] args) {	   
	   long currentTimeMillis = System.currentTimeMillis();
	   System.out.println(currentTimeMillis);//1589103399072
	   long temp = 1589103399072L/1000;
	   Date date = new Date(temp);
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String format = sdf.format(date);
	 //  System.out.println(format);//1970-01-19 17:25:03  日期错误
	   Date d = new Date();
	  long t= d.getTime()/1000L;
	   String format2 = sdf.format(d);
	   System.out.println(format2);
	   Date d2= new Date(t*1000L);
	   String format3 = sdf.format(d2);
	   System.out.println(format3);
	   
	   
	     

}
   
}
