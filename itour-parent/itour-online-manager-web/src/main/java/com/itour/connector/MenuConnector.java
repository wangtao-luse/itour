package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.member.api.MemberApi;

@Service
public class MenuConnector {
	@Autowired
MemberApi memberApi;
	/**
	 * 管理平台菜单列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
public ResponseMessage getMenuList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage menuList = memberApi.getMenuList(postData);
	return menuList;
}
}
