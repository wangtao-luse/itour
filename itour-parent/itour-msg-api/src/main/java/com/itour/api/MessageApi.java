package com.itour.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

@FeignClient(name = "itour-msg-service")
public interface MessageApi {
	/**
	 * 发送email信息
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/msg/sendEmailCode",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage sendEmailCode(RequestMessage requestMessage);
	/**
	 * 查询消息列表
	 */
	@RequestMapping(value = "/msg/queryMessageList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryMessageList(RequestMessage requestMessage);
	/**
	 * 查询消息列表（视图）
	 */
	@RequestMapping(value = "/msg/queryViewMessageList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryViewMessageList(RequestMessage requestMessage);
}
