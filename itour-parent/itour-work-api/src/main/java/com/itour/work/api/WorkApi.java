package com.itour.work.api;

import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;



public interface WorkApi {
	/**
	 * 个人博客列表(前台使用)
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/selectWorkInfoList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage selectWorkInfoList(RequestMessage requestMessage);	
	/**
	 * 个人博客单条(前台使用)
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/selectWorkInfo",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage selectWorkInfo(RequestMessage requestMessage);	
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

