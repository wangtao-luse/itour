package com.itour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}