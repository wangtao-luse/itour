package com.itour.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

@FeignClient(name = "itour-account-service")
public interface MessageApi {
	
	@RequestMapping(value = "msg/sendCode",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage sendCode(RequestMessage requestMessage);
	
}
