package com.itour.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.common.resp.ResponseMessage;
import com.itour.model.account.Right;

@Controller
public class MenuController {
@ResponseBody
@RequestMapping(value = "/getMenuList")
public ResponseMessage getMenuList(@RequestBody(required = false) JSONObject jsonObject ,HttpServletRequest request) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		List<Right> list = new ArrayList<Right>();
		Right right = new Right();
		      right.setId(1);
		      right.setMenuNo("100");
		      right.setMenu("权限管理");
		      right.setMenuType("1");
		      right.setParentId(0);
		      list.add(right);
		Right right1 = new Right();
			  right1.setId(1);
		      right1.setMenuNo("100");
		      right1.setMenu("组管理");
		      right1.setMenuType("1");
		      right1.setParentId(1);
		      list.add(right1);
		      Right right2 = new Right();
			  right2.setId(1);
		      right2.setMenuNo("100");
		      right2.setMenu("角色管理");
		      right2.setMenuType("1");
		      right2.setParentId(1);
		      list.add(right2);
		      Page page = new Page<Right>();
		      page.setRecords(list);
		      responseMessage.setReturnResult(page);
	return responseMessage;
}
}
