package com.itour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.quartz.api.QuartzApi;
import com.itour.service.MessageinfoService;
import com.itour.service.QrtzJobDetailsService;
import com.itour.service.ViewMMessageinfoService;
@RestController
public class QuartzApiController implements QuartzApi {
	@Autowired
	QrtzJobDetailsService qrtzJobDetailsService;
	@Autowired
	MessageinfoService messageinfoService;
	@Autowired
	ViewMMessageinfoService viewMMessageinfoService;
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
	public ResponseMessage updateJob(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.qrtzJobDetailsService.updateJob(requestMessage);
	}
	/**
	 * 暂停定时任务
	 */
	@Override
	public ResponseMessage pauseJob(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.qrtzJobDetailsService.pauseJob(requestMessage);
	}
	/**
	 * 恢复定时任务
	 */
	@Override
	public ResponseMessage resumeJob(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.qrtzJobDetailsService.resumeJob(requestMessage);
	}
	/**
	 * 删除定时任务
	 */
	@Override
	public ResponseMessage deleteJob(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.qrtzJobDetailsService.delJob(requestMessage);
	}
	/**
	 * 执行定时任务
	 */
	@Override
	public ResponseMessage startNowJob(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.qrtzJobDetailsService.startNowJob(requestMessage);
	}
	/**
	 * 定时任务查询单条
	 */
	@Override
	public ResponseMessage getTrigger(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.qrtzJobDetailsService.getTrigger(requestMessage);
	}
	/**
	 * 发送email
	 */
	@Override
	public ResponseMessage sendEmailCode(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return messageinfoService.sendEmail(requestMessage);
	}
	/**
	 * 查看消息列表
	 */
	@Override
	public ResponseMessage queryMessageList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return messageinfoService.queryMessageList(requestMessage);
	}
	/**
	 * 查看消息列表
	 */
	@Override
	public ResponseMessage queryViewMessageList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewMMessageinfoService.queryViewMessageList(requestMessage);
	}
}
