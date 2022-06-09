package com.itour.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//https://blog.csdn.net/xiaoliuliu2050/article/details/76615740
@Controller
public class ExceptionController implements ErrorController{
	

@RequestMapping("/error")
public String error404() {
	return "/error/404";
}

@RequestMapping("/exception")
public String exception() {
	return "/error/exception";
}
@RequestMapping("/noAuth")
public String noAuth() {
	return "/error/noAuth";
}
}
