package com.itour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.quartz.api.QuartzApi;
import com.itour.service.QrtzTriggersService;
@RestController
public class QuartzApiController implements QuartzApi {
	@Autowired
	QrtzTriggersService qrtzTriggersService;
	/**
	 * 定时任务列表
	 * @param requestMessage
	 * @return
	 */
	@Override
public ResponseMessage queryTriggersList(@RequestBody RequestMessage requestMessage) {
	ResponseMessage queryTriggersList = qrtzTriggersService.queryTriggersList(requestMessage);
	return queryTriggersList;
}
}
