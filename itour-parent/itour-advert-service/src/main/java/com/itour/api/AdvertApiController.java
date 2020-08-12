package com.itour.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.AdvertService;
@RestController
public class AdvertApiController implements AdvertApi{
	@Autowired
	AdvertService advertService;
	@Override
	public ResponseMessage queryAdvertList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return advertService.queryAdvertList(requestMessage);
	}
	
}
