package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.LocationService;
import com.itour.service.NiceService;
import com.itour.service.TransportationInfoService;
import com.itour.service.TransportationTypeService;
import com.itour.service.TravelColumnService;
import com.itour.service.TravelCommentService;
import com.itour.service.TravelInfoService;
import com.itour.service.TravelTypeService;
import com.itour.service.ViewTravelinfoOauthService;

@RestController
public class TravelApiController implements TravelApi {
	@Autowired
	TravelInfoService travelInfoService;
	@Autowired
	LocationService locationService;
	@Autowired
	TransportationInfoService transportationInfoService;
	@Autowired
	TravelTypeService travelTypeService;
	@Autowired
	TravelCommentService travelCommentService;
	@Autowired
	TravelColumnService travelColumnService;
	@Autowired
	TransportationTypeService transportationTypeService;
	@Autowired
	ViewTravelinfoOauthService viewTravelinfoOauthService;
	@Autowired
	NiceService niceService;
	 /**
     * 旅游信息列表
     */
	@Override
	public ResponseMessage queryTravelInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.queryTravelInfoList(requestMessage);
	}
	 /**
     * 旅游信息查询单条
     */
	@Override
	public ResponseMessage selectTravelInfoById(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.selectTravelInfoById(requestMessage);
	}
	 /**
     * 旅游信息修改
     */
	@Override
	public ResponseMessage updateTravelInfo(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.updateTravelInfo(requestMessage);
	}
	 /**
     * 旅游信息删除
     */
	@Override
	public ResponseMessage delTravelInfo(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.delTravelInfo(requestMessage);
	}
    /**
     * 旅游信息新增
     */
	@Override
	public ResponseMessage insertTravelInfo(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.insertTravelInfo(requestMessage);
	}
	/**
	 * 城市列表查询
	 */
	@Override
	public ResponseMessage queryLocationList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.queryLocationList(requestMessage);
	}
	/**
	 * 城市信息单条查询
	 */
	@Override
	public ResponseMessage getLocation(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.getLocation(requestMessage);
	}
	/**
	 * 城市信息修改
	 */
	@Override
	public ResponseMessage updateLocation(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.updateLocation(requestMessage);
	}
	/**
	 * 城市信息删除
	 */
	@Override
	public ResponseMessage deleteLocation(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.delelteLocation(requestMessage);
	}
	/**
	 * 交通信息列表查询
	 */
	@Override
	public ResponseMessage queryTransportationInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.queryTransportationInfoList(requestMessage);
	}
	/**
	 * 交通信息单条
	 */
	@Override
	public ResponseMessage getTransportationInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.getTransportationInfo(requestMessage);
	}
	/**
	 * 修改交通信息
	 */
	@Override
	public ResponseMessage updateTransportationInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.updateTransportationInfo(requestMessage);
	}
	/**
	 * 删除交通信息
	 */
	@Override
	public ResponseMessage deleteTransportationInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.delelteTransportationInfo(requestMessage);
	}
	/**
	 * 旅行信息类型列表
	 */
	@Override
	public ResponseMessage queryTravelTypeList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelTypeService.queryTravelTypeList(requestMessage);
	}
	/**
	 * 旅行信息类型新增
	 */
	@Override
	public ResponseMessage insertTravelType(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelTypeService.insertTravelType(requestMessage);
	}
	/**
	 * 旅行信息类型修改
	 */
	@Override
	public ResponseMessage updateTravelType(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelTypeService.updateTravelType(requestMessage);
	}
	/**
	 * 旅行信息类型单条查询
	 */
	@Override
	public ResponseMessage getTravelType(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelTypeService.getTravelType(requestMessage);
	}
	/**
	 * 旅行信息类型删除
	 */
	@Override
	public ResponseMessage delTravelType(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelTypeService.delTravelType(requestMessage);
	}
	/**
	 * 旅行信息评论列表
	 */
	@Override
	public ResponseMessage queryTravelCommentList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelCommentService.queryTravelCommentList(requestMessage);
	}
	/**
	 * 旅行信息评论单条
	 */
	@Override
	public ResponseMessage getTravelComment(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelCommentService.getTravelComment(requestMessage);
	}
	/**
	 * 旅行信息评论修改
	 */
	@Override
	public ResponseMessage updateTravelComment(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelCommentService.updateTravelComment(requestMessage);
	}
	/**
	 * 旅行信息评论修改
	 */
	@Override
	public ResponseMessage deleteTravelComment(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelCommentService.delelteTravelComment(requestMessage);
	}
	/**
	 * 旅行信息专栏列表
	 */
	@Override
	public ResponseMessage queryTravelColumnList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelColumnService.queryTravelColumnList(requestMessage);
	}
	/**
	 * 旅行信息专栏单条
	 */
	@Override
	public ResponseMessage getTravelColumn(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelColumnService.getTravelColumn(requestMessage);
	}
	/**
	 * 旅行信息专栏修改
	 */
	@Override
	public ResponseMessage updateTravelColumn(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelColumnService.updateTravelColumn(requestMessage);
	}
	/**
	 * 旅行信息专栏新增
	 */
	@Override
	public ResponseMessage insertTravelColumn(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelColumnService.insertTravelColumn(requestMessage);
	}
	/**
	 * 旅行信息专栏删除
	 */
	@Override
	public ResponseMessage deleteTravelColumn(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelColumnService.delTravelColumn(requestMessage);
	}
	/**
	 * 旅行交通信息类型列表
	 */
	@Override
	public ResponseMessage queryTransportationTypeList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationTypeService.queryTransportationTypeList(requestMessage);
	}
	/**
	 * 旅行交通信息类型单条
	 */
	@Override
	public ResponseMessage getTransportationType(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationTypeService.getTransportationType(requestMessage);
	}
	
	/**
	 * 旅行交通信息列表视图
	 */
	@Override
	public ResponseMessage queryViewTravelinfoOauthList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewTravelinfoOauthService.queryViewTravelinfoOauthList(requestMessage);
	}
	/**
	 * 旅行交通信息单条视图
	 */
	@Override
	public ResponseMessage selectViewTravelinfoOauthById(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewTravelinfoOauthService.selectViewTravelinfoOauthById(requestMessage);
	}
	/**
	 * 旅行信息点赞列表
	 */
	@Override
	public ResponseMessage queryNiceList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return niceService.queryNiceList(requestMessage);
	}
	/**
	 * 旅行信息点赞信息新增
	 */
	@Override
	public ResponseMessage insertNice(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return niceService.insertNice(requestMessage);
	}
	
	
	
}
