package com.itour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
@RequestMapping("/northPage")
public String northPage() {
	return "/north";
}
@RequestMapping("/westPage")
public String westPage() {
	return "/west";
}
}
