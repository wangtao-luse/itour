package com.itour.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.itour.common.resp.ResponseMessage;

@Controller
public class TestController {
	
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
		PageInfo page = new PageInfo<Student>(list);
		responseMessage.setReturnResult(page);
	
		return responseMessage;
	}

}
