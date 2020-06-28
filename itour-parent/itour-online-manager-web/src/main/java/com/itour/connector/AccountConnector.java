package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.account.api.AccountApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

@Service
public class AccountConnector {
	@Autowired
AccountApi accountApi;
public ResponseMessage getGroupList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.getGroupList(postData);
	return groupList;
	
}
public ResponseMessage insertGroup(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.insertGroup(postData);
	return groupList;
	
}
public ResponseMessage getRoleList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage postData = HttpDataUtil.postData(jsonObject, request);
	ResponseMessage groupList = accountApi.queryRoleList(postData);
	return groupList;
	
}
}
