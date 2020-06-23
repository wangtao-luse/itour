package com.itour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
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
@RequestMapping("/account/login")
public String login() {
	return "account/login";
}

}
