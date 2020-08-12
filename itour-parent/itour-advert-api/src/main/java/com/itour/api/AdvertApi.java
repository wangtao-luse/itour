package com.itour.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
@FeignClient(name = "itour-advert-service")
public interface AdvertApi {
	/**
	 * 广告列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/ad/queryAdvertList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryAdvertList(@RequestBody RequestMessage requestMessage);
}
