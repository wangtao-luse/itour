package com.itour.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.common.ReturnMessage;
import com.itour.entiy.Group;

import cn.hutool.json.JSONArray;
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
	@RequestMapping("/initWestTree")
	public ReturnMessage initTreeData() {
		ReturnMessage result = new ReturnMessage();
		result.setCode("10");
		result.setMsg("操作成功！");
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
		JSONArray jsonArray = new JSONArray();
		JSONObject o1 = new JSONObject();
		o1.put("id", "1");
		o1.put("text", "会员管理");
		o1.put("iconCls", "icon-save");
		
		JSONArray o1_child = new JSONArray();
		JSONObject o1_child_o1 = new JSONObject();	
		o1_child_o1.put("id", "1-1");
		o1_child_o1.put("text", "会员查询");
		o1_child_o1.put("state", "open");
		o1_child_o1.put("attributes",new JSONObject());
		o1_child.add(o1_child_o1);
		
		JSONObject o1_child_o2 = new JSONObject();
		o1_child_o2.put("id", "1-2");
		o1_child_o2.put("text", "会员组查询");
		o1_child_o2.put("state", "open");
		JSONObject attributes1 = new JSONObject();
		attributes1.put("url", "/datagrid");
		o1_child_o2.put("attributes", attributes1);
		
		o1_child.add(o1_child_o2);
		
		
		o1.put("children", o1_child);
		jsonArray.add(o1);
		result.setData(jsonArray);
		return result;
	}
	@RequestMapping("/datagrid")
	public String dataGridP() {
		return "/demo-datagrid";
	}
	@RequestMapping("/datagrid/data")
	@ResponseBody
	public ReturnMessage groupList() {
		ReturnMessage glist = this.getGlist();
		return glist;
		
	}
	public ReturnMessage getGlist() {
		ReturnMessage result = new ReturnMessage();
		result.setCode("10");
		result.setMsg("操作成功！");
		List<Group> glist = new ArrayList<Group>();
		glist.add(new Group(1L, "王维", "2022-2-1"));
		glist.add(new Group(1L, "苏轼", "2022-2-2"));
		glist.add(new Group(1L, "苏辙", "2022-2-3"));
		glist.add(new Group(1L, "李清照", "2022-2-4"));
		Page<Group> page = new Page<Group>();
		page.setRecords(glist);
		result.setData(page);
		return result;
	}
	
}