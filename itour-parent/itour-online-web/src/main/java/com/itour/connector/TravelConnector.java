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
	
	/**
	 * 旅行信息列表(视图)
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryViewTravelinfoOauthList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.queryViewTravelinfoOauthList(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行信息单条(视图)
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage selectViewTravelinfoOauthById(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.selectViewTravelinfoOauthById(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行信息浏览记录列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryHistoryList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.queryHistoryList(requestMessage);
		return responseMessage;
	}
	
	/**
	 * 旅行信息浏览记录新增
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage insertHistory(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.insertHistory(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行信息收藏夹创建
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage insertFavorite(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.insertFavorite(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行信息收藏夹列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryFavoriteList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.queryFavoriteList(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行话题标签新增
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage inserTravelTag(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.insertTravelTag(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行话题标签列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryTravelTagList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.queryTravelTagList(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行专栏新增
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage insertTravelColumn(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.insertTravelColumn(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行专栏列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryTravelColumnList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.queryTravelColumnList(requestMessage);
		return responseMessage;
	}
	/**
	 * 获取城市列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage getCityList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.getRegionList(requestMessage);
		return responseMessage;
	}
	/***
	 * 旅行攻略点赞
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage niceSub(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.niceSub(requestMessage);
		return responseMessage;
	}
	/**
	 * 根据文章id获取周末旅行攻略内容
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage selecWeekInfoOne(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.selecWeekInfoOne(requestMessage);
		return responseMessage;
	}
	/**
	 * 获取旅行攻略文章的标签
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryViewTravelTagList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.queryViewTravelTagList(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行攻略添加评论
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage insertComment(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.insertComment(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行攻略添加评论回复
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage insertCommentReply(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.insertCommentReply(requestMessage);
		return responseMessage;
	}
	
	
}
