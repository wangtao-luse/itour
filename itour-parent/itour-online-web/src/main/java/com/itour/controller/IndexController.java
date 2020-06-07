package com.itour.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
@RequestMapping("/index")
@RequiresPermissions("/index")
public String index() {
	return "index";
}

}
