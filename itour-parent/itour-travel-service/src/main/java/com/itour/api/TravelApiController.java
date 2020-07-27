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

	@Override
	public ResponseMessage queryTravelInfoList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.queryTravelInfoList(requestMessage);
	}

	@Override
	public ResponseMessage selectTravelInfoById(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.selectTravelInfoById(requestMessage);
	}

	@Override
	public ResponseMessage updateTravelInfo(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.updateTravelInfo(requestMessage);
	}

	@Override
	public ResponseMessage delTravelInfo(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.delTravelInfo(requestMessage);
	}

	
}
