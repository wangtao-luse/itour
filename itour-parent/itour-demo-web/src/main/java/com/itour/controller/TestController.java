package com.itour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.ReturnMessage;
@Controller
public class TestController{
	@RequestMapping("/index")
	public String index() {
		System.out.println("index.html");
		return "index";
	}
	@RequestMapping("/indexinner")
	public String indexinner() {
		return "/html/portal/index";
	}
	@RequestMapping("/barjson")
	public String barjson() {
		return "/json/echarts/bar.json";
	}
	@RequestMapping("/index1")
	public String index1() {
		return "index1";
	}
	
	@ResponseBody
	public ReturnMessage initTreeData() {
		ReturnMessage result = new ReturnMessage();
		result.setCode("10");
		result.setMsg("操作成果");
		String str = "[{\r\n" + 
				"    \"id\":1,\r\n" + 
				"    \"text\":\"会员管理\",\r\n" + 
				"    \"iconCls\":\"icon-save\",\r\n" + 
				"    \"children\":[{\r\n" + 
				"		\"text\":\"会员查询\",\r\n" + 
				"		\"checked\":true\r\n" + 
				"    },{\r\n" + 
				"		\"text\":\"会员组列表\",\r\n" + 
				"		\"state\":\"open\",\r\n" + 
				"		\"attributes\":{\r\n" + 
				"			\"url\":\"/demo/book/abc\",\r\n" + 
				"			\"price\":100\r\n" + 
				"		}\r\n" + 
				"    }]\r\n" + 
				"},{\r\n" + 
				"    \"text\":\"员工管理\",\r\n" + 
				"    \"state\":\"closed\",\r\n" + 
				"    \"children\":[{\r\n" + 
				"		\"text\":\"员工查询\"\r\n" + 
				"    },{\r\n" + 
				"		\"text\":\"员工组管理\"\r\n" + 
				"    }]\r\n" + 
				"}]";
		result.setData(str);
		return result;
	}
	
}