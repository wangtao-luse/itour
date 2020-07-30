package com.itour.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
@FeignClient(name = "itour-travel-service")
public interface TravelApi {
	/**
	 * 旅行信息列表
	 * @param requestMessage
	 * @return
	 */
@RequestMapping(value = "/travel/queryTravelInfoList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage queryTravelInfoList(RequestMessage requestMessage);
/**
 * 旅行信息根据编号
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/selectTravelInfoById",produces = {"application/json;charset=UTF-8"})
public ResponseMessage selectTravelInfoById(RequestMessage requestMessage);
/**
 * 旅行信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelInfo",produces = {"application/json;charset=UTF-8"})
public ResponseMessage updateTravelInfo(RequestMessage requestMessage);
/**
 * 旅行信息删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delTravelInfo",produces = {"application/json;charset=UTF-8"})
public ResponseMessage delTravelInfo(RequestMessage requestMessage);
/**
 * 旅行信息新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertTravelInfo",produces = {"application/json;charset=UTF-8"})
public ResponseMessage insertTravelInfo(RequestMessage requestMessage);
}
