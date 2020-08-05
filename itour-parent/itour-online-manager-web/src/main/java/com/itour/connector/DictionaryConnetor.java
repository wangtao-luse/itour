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
	/**
	 * 字典表列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
public ResponseMessage getDictionaryList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return dictionaryApi.getDictionaryList(requestMessage);
}
/**
 * 字典表列表视图
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getViewDictionaryList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return dictionaryApi.getViewDictionaryList(requestMessage);
}
/**
 * 字典查询
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getDictData(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return dictionaryApi.getDictData(requestMessage);
}
/**
 * 字典修改
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage updateDictionary(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return dictionaryApi.updateDictionary(requestMessage);
}
/**
 * 字典查询单条
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage getDictionary(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return dictionaryApi.getDictionary(requestMessage);
}
/**
 * 网站列表查询
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryWebsiteList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return dictionaryApi.queryWebsiteList(requestMessage);
}
}
