package com.itour.quartz.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

@FeignClient(name = "itour-quartz-service")
public interface QuartzApi {
	/**
	 * 定时任务查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/quartz/queryJobList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryJobList(RequestMessage requestMessage);
	/**
	 * 定时任务查询单条
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/quartz/getTrigger",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getTrigger(RequestMessage requestMessage);
	/**
	 * 添加定时任务
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/quartz/insertJob",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage insertJob(RequestMessage requestMessage);
	/**
	 * 修改定时任务
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/quartz/updateJob",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage updateJob(RequestMessage requestMessage);
	/**
	 * 停用定时任务
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/quartz/pauseJob",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage pauseJob(RequestMessage requestMessage);
	/**
	 * 启用定时任务
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/quartz/resumeJob",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage resumeJob(RequestMessage requestMessage);
	/**
	 * 删除定时任务
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/quartz/deleteJob",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage deleteJob(RequestMessage requestMessage);
	/**
	 * 执行定时任务
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/quartz/startNowJob",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage startNowJob(RequestMessage requestMessage);
	
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
