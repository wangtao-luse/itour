package com.itour.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;


@FeignClient(name = "itour-work-service")
public interface WorkApi {
	
	/**
	 * 日志标签查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/queryLabelList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryLabelList(RequestMessage requestMessage);		
	/**
	 * 日志标签查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/queryColumnList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryColumnList(RequestMessage requestMessage);		
	/***
	 *  新增或修改工作日志
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/savaOrUpdateWorkInfo",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage savaOrUpdateWorkInfo(RequestMessage requestMessage);		
	/***
	 *  工作日志单条
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/selectWorkInfoOne",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage selectWorkInfoOne(RequestMessage requestMessage);		
	/**
	 * 个人博客列表(前台使用包含用户图像，昵称等信息)
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/selectWorkInfoList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage selectWorkInfoList(RequestMessage requestMessage);	
	
	/**
	 * 工作日志审核列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/queryViewWorkInfoList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryViewWorkInfoList(RequestMessage requestMessage);	
	
	/**
	 * 工作日志修改(审核修改状态)
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/updateWorkInfoBatch",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage updateWorkInfoBatch(RequestMessage requestMessage);	
	/**
	 * 个人博客单条(前台使用)
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/selectWorkInfo",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage selectWorkInfo(RequestMessage requestMessage);
	/**
	 * 个人博客内容单条查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/selecWorktextOne",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage selecWorktextOne(RequestMessage requestMessage);	
	
	/**
	 * 个人博客标签查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/workTagList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage workTagList(RequestMessage requestMessage);	
	
	/**
	 * 个人博客专栏查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/workColList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage workColList(RequestMessage requestMessage);	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 个人博客搜索页列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/searchTextList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage searchTextList(RequestMessage requestMessage);	
	/**
	 * 个人博客个人中心列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/queryPersonCenterList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryPersonCenterList(RequestMessage requestMessage);	
	/**
	 * 个人博客个人中心统计
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/getInfoData",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getInfoData(RequestMessage requestMessage);	
	/**
	 * 个人博客修改浏览量
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/updatePvBatch",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage updatePvBatch(RequestMessage requestMessage);	
}

