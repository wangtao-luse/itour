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
	@RequestMapping(value = "/quartz/stopJob",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage stopJob(RequestMessage requestMessage);
	/**
	 * 启用定时任务
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "/quartz/startJob",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage startJob(RequestMessage requestMessage);
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
	@RequestMapping(value = "/quartz/nowStartJob",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage nowStartJob(RequestMessage requestMessage);
}
