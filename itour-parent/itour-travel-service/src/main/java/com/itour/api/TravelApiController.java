package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.LocationService;
import com.itour.service.TransportationInfoService;
import com.itour.service.TravelInfoService;

@RestController
public class TravelApiController implements TravelApi {
	@Autowired
	TravelInfoService travelInfoService;
	@Autowired
	LocationService locationService;
	@Autowired
	TransportationInfoService transportationInfoService;
	
	 /**
     * 旅游信息列表
     */
	@Override
	public ResponseMessage queryTravelInfoList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.queryTravelInfoList(requestMessage);
	}
	 /**
     * 旅游信息查询单条
     */
	@Override
	public ResponseMessage selectTravelInfoById(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.selectTravelInfoById(requestMessage);
	}
	 /**
     * 旅游信息修改
     */
	@Override
	public ResponseMessage updateTravelInfo(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.updateTravelInfo(requestMessage);
	}
	 /**
     * 旅游信息删除
     */
	@Override
	public ResponseMessage delTravelInfo(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.delTravelInfo(requestMessage);
	}
    /**
     * 旅游信息新增
     */
	@Override
	public ResponseMessage insertTravelInfo(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.insertTravelInfo(requestMessage);
	}
	/**
	 * 城市列表查询
	 */
	@Override
	public ResponseMessage queryLocationList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.queryLocationList(requestMessage);
	}
	/**
	 * 城市信息单条查询
	 */
	@Override
	public ResponseMessage getLocation(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.getLocation(requestMessage);
	}
	/**
	 * 城市信息修改
	 */
	@Override
	public ResponseMessage updateLocation(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.updateLocation(requestMessage);
	}
	/**
	 * 城市信息删除
	 */
	@Override
	public ResponseMessage deleteLocation(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.delelteLocation(requestMessage);
	}
	/**
	 * 交通信息列表查询
	 */
	@Override
	public ResponseMessage queryTransportationInfoList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.queryTransportationInfoList(requestMessage);
	}
	/**
	 * 交通信息单条
	 */
	@Override
	public ResponseMessage getTransportationInfoList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.getTransportationInfo(requestMessage);
	}
	/**
	 * 修改交通信息
	 */
	@Override
	public ResponseMessage updateTransportationInfoList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.updateTransportationInfo(requestMessage);
	}
	/**
	 * 删除交通信息
	 */
	@Override
	public ResponseMessage deleteTransportationInfoList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.delelteTransportationInfo(requestMessage);
	}

	
}
