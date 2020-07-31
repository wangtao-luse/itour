package com.itour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TravelController {
	/**
	 * 旅行信息页面
	 * @return
	 */
@RequestMapping("/travelPage")
public String travelPage() {
	return "/system/travel/info/travelInfoManager";
}
/**
 * 旅行信息类型管理页面
 * @return
 */
@RequestMapping("/travelTypePage")
public String travelTypePage() {
	return "/system/travel/type/travelTypeManager";
}
/**
 * 旅行信息评论页面
 * @return
 */
@RequestMapping("/travelCommntPage")
public String travelCommntPage() {
	return "/system/travel/comment/travelCommentManager";
}
}
