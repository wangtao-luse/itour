package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.AdvertApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.resp.ResponseMessage;


@Service
public class AdvertConnector {
@Autowired
AdvertApi advertApi;
/**
 * 广告列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryAdvertList(JSONObject jsonObject,HttpServletRequest request) {
	return advertApi.queryAdvertList(HttpDataUtil.postData(jsonObject, request));
}
}
