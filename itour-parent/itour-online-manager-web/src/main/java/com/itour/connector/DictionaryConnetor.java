package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.dictionary.api.DictionaryApi;

@Service
public class DictionaryConnetor {
	@Autowired
private DictionaryApi dictionaryApi;
public ResponseMessage getDictionaryList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return dictionaryApi.getDictionaryList(requestMessage);
}
public ResponseMessage getViewDictionaryList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return dictionaryApi.getViewDictionaryList(requestMessage);
}
}
