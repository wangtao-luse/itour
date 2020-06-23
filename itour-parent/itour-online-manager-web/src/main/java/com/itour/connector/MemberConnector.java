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
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		return accountApi.getGroupList(requestMessage);
	}

}
