package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.TravelInfoService;

@RestController
public class TravelApiController implements TravelApi {
	@Autowired
	TravelInfoService travelInfoService;
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

	
}
