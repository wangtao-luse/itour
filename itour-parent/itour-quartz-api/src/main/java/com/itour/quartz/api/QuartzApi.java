package com.itour.quartz.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

@FeignClient(name = "itour-quartz-service")
public interface QuartzApi {
	@RequestMapping(value = "/quartz/queryTriggersList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryTriggersList(RequestMessage requestMessage);
}
