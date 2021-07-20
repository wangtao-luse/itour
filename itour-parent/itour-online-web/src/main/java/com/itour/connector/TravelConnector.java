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
	 * 旅行信息首页展示
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage selectTravelInfoList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.selectTravelInfoList(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行信息单条
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage selectTravelInfoOne(JSONObject jsonObject,HttpServletRequest request) {
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.selectTravelInfoOne(requestMessage);
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
	 * 旅行信息单条(详情显示)
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage selectTraveInfo(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.selectTraveInfo(requestMessage);
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
	 * 旅行信息收藏
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage collectArticle(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.collectArticle(requestMessage);
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
	/**
	 * 旅行攻略评论列表
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryCommentList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.queryCommentList(requestMessage);
		return responseMessage;
	}
	
	/**
	 * 删除评论
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage delComment(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.delComment(requestMessage);
		return responseMessage;
	}
	
	/**
	 * 删除评论回复
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage delCommentRely(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.delCommentReply(requestMessage);
		return responseMessage;
	}
	
	/**
	 * 旅行攻略评论点赞数
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage commentNiceSub(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.commentNiceSub(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行攻略评论回复点赞数
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage commentReplyNiceSub(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.commentReplyNiceSub(requestMessage);
		return responseMessage;
	}
	/**
	 * 旅行攻略专栏视图查询
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryViewTravelColumnList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.queryViewTravelColumnList(requestMessage);
		return responseMessage;
	}
	/**
	 * 地区查询单条
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage selectRegionOne(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.selectRegionOne(requestMessage);
		return responseMessage;
	}
	/**
	 * 我的主页列表查询
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage queryPersonCenterList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.queryPersonCenterList(requestMessage);
		return responseMessage;
	}
	/**
	 * 我的主页统计
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage getInfoData(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.getInfoData(requestMessage);
		return responseMessage;
	}
	/**
	 * 搜索页
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage searchTextList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.searchTextList(requestMessage);
		return responseMessage;
	}
	
	/**
	 * 收藏列表(前台展示)
	 * @param jsonObject
	 * @param request
	 * @return
	 */
	public ResponseMessage selectFavoritesList(JSONObject jsonObject, HttpServletRequest request) {
		// TODO Auto-generated method stub
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
		ResponseMessage responseMessage = travelApi.selectFavoritesList(requestMessage);
		return responseMessage;
	}
	
	
}
