package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.TravelApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

@Service
public class TravelConnector {
	@Autowired
private TravelApi travelApi;
	/**
	 * 旅行信息列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryTravelInfoList(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.queryTravelInfoList(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行信息单条
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage selectTravelInfoById(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.selectTravelInfoById(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行信息修改
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage updateTravelInfo(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.updateTravelInfo(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行信息删除
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage delTravelInfo(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.delTravelInfo(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行信息新增
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage insertTravelInfo(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.insertTravelInfo(requestMessage);
		return responseMessage;
	}
	/**
	 * 城市信息查询单条
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage getLocation(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.getLocation(requestMessage);
		return responseMessage;
	}
	/**
	 * 交通信息列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryTransportationInfoList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.queryTransportationInfoList(requestMessage);
		return responseMessage;
	}
	
}
