package com.itour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
/**
 * 首页
 * @return
 */
@RequestMapping("/index")
public String index() {
	return "index";
}
@RequestMapping("/northPage")
public String northPage() {
	return "/layout/north";
}
@RequestMapping("/westPage")
public String westPage() {
	return "/layout/west";
}


}
