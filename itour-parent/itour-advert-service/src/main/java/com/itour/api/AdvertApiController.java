package com.itour.api;


import org.springframework.beans.factory.annotation.Autowired;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.AdvertService;

public class AdvertApiController implements AdvertApi{
	@Autowired
	AdvertService advertService;
	@Override
	public ResponseMessage queryAdvertList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return advertService.queryAdvertList(requestMessage);
	}
	
}
