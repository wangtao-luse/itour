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
public class MemberConnector {
	@Autowired
	MemberApi memberApi;
	public ResponseMessage groupList(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.getGroupList(requestMessage);
	}
	public ResponseMessage roleList(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.queryRoleList(requestMessage);
	}
	public ResponseMessage queryAccountRight(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.queryRoleList(requestMessage);
	}
	public ResponseMessage loginSub(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return memberApi.queryRoleList(requestMessage);
	}

}
