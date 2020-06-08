package com.itour.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//https://blog.csdn.net/xiaoliuliu2050/article/details/76615740
@Controller
public class ExceptionController implements ErrorController{
	
/**
 *   因为404错误是不经过controller的，所以无法通过上述办法处理。
 *   但是 SpringBoot 默认提供了一个全局的 handler 来处理所有的 HTTP 错误,
 *   并把它映射为 /error。当发生一个 HTTP 错误, 例如 404 错误时, 
 *   SpringBoot 内部的机制会将页面重定向到 /error 中,
 *   所以我们只要实现一个 /error 映射的 Controller 即可来处理它即可
 * @return
 */
@RequestMapping("/error")
public String error404() {
	return "/error/404";
}

@Override
public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
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
