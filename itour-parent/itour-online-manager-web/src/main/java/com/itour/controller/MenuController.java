package com.itour.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.common.resp.ResponseMessage;
import com.itour.model.account.Right;

@Controller
public class MenuController {
@ResponseBody
@RequestMapping(value = "/getMenuList")
public ResponseMessage getMenuList(@RequestBody(required = false) JSONObject jsonObject ,HttpServletRequest request) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		List<Right> list = new ArrayList<Right>();
		Right right = new Right();
		      right.setId(1);
		      right.setMenuNo("100");
		      right.setMenu("权限管理");
		      right.setMenuType("1");
		      right.setParentId(0);
		      list.add(right);
		Right right1 = new Right();
			  right1.setId(1);
		      right1.setMenuNo("100");
		      right1.setMenu("组管理");
		      right1.setMenuType("1");
		      right1.setParentId(1);
		      list.add(right1);
		      Right right2 = new Right();
			  right2.setId(1);
		      right2.setMenuNo("100");
		      right2.setMenu("角色管理");
		      right2.setMenuType("1");
		      right2.setParentId(1);
		      list.add(right2);
		      JSONArray jarr = new JSONArray();
		      
		      JSONObject json1 = new JSONObject();
		      json1.put("id", "1");
		      json1.put("text", "权限管理");
		      
		      JSONArray child = new JSONArray();
		      JSONObject child1 = new JSONObject();
		      child1.put("id", "2");		      
		      child1.put("checked", true);
		      child1.put("text", "组管理");
		      child.add(child1);
		      json1.put("children", child);
		      jarr.add(json1);
		      String str="[\r\n" + 
		      		"  {\r\n" + 
		      		"    \"children\": [\r\n" + 
		      		"      {\r\n" + 
		      		"        \"text\": \"组管理\",\r\n" + 
		      		"        \"attributes\": {\r\n" + 
		      		"          \"url\": \"/account/groupPage\"\r\n" + 
		      		"        }\r\n" + 
		      		"      },\r\n" + 
		      		"      {\r\n" + 
		      		"        \"text\": \"角色管理\"\r\n" + 
		      		"      },\r\n" + 
		      		"      {\r\n" + 
		      		"        \"text\": \"权限管理\"\r\n" + 
		      		"      }\r\n" + 
		      		"    ],\r\n" + 
		      		"    \"text\": \"权限管理\",\r\n" + 
		      		"    \"state\": \"closed\"\r\n" + 
		      		"  },\r\n" + 
		      		"  {\r\n" + 
		      		"    \"children\": [\r\n" + 
		      		"      {\r\n" + 
		      		"        \"text\": \"会员查询\"\r\n" + 
		      		"      },\r\n" + 
		      		"      {\r\n" + 
		      		"        \"text\": \"会员登录记录\"\r\n" + 
		      		"      },\r\n" + 
		      		"      {\r\n" + 
		      		"        \"text\": \"会员审核\"\r\n" + 
		      		"      }\r\n" + 
		      		"    ],\r\n" + 
		      		"    \"text\": \"会员管理\",\r\n" + 
		      		"    \"state\": \"open\"\r\n" + 
		      		"  }\r\n" + 
		      		"]";
		      JSONArray parseArray = JSONObject.parseArray(str);
		      responseMessage.setReturnResult(parseArray);
	return responseMessage;
}
}
