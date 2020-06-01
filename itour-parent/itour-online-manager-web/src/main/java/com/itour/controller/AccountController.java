package com.itour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
	@RequestMapping("/groupPage")
	public String groupPage() {
		return "/system/right/groupManager";
	}

}
