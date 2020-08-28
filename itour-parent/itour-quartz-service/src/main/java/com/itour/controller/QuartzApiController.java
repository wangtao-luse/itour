package com.itour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.quartz.api.QuartzApi;
import com.itour.service.QrtzJobDetailsService;
@RestController
public class QuartzApiController implements QuartzApi {
	@Autowired
	QrtzJobDetailsService qrtzJobDetailsService;
	/**
	 * 定时任务列表
	 * @param requestMessage
	 * @return
	 */
	@Override
public ResponseMessage queryJobList(@RequestBody RequestMessage requestMessage) {
	ResponseMessage queryJobList = qrtzJobDetailsService.queryJobList(requestMessage);
	return queryJobList;
}
	/**
	 * 添加定任务
	 */
	@Override
	public ResponseMessage insertJob(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return qrtzJobDetailsService.insertJob(requestMessage);
	}
	/**
	 * 修改定时任务
	 */
	@Override
	public ResponseMessage updateJob(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 停止定时任务
	 */
	@Override
	public ResponseMessage stopJob(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 启动定时任务
	 */
	@Override
	public ResponseMessage startJob(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 删除定时任务
	 */
	@Override
	public ResponseMessage deleteJob(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 执行定时任务
	 */
	@Override
	public ResponseMessage nowStartJob(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}
}
