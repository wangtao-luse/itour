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
/**
 * 城市信息列表信息列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryLocationList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryLocationList(RequestMessage requestMessage);
/**
 * 城市信息查询单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getLocation",produces = {"application/json;charset=UTF-8"})
ResponseMessage getLocation(RequestMessage requestMessage);
/**
 * 城市信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateLocation",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateLocation(RequestMessage requestMessage);
/**
 * 城市信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/deleteLocation",produces = {"application/json;charset=UTF-8"})
ResponseMessage deleteLocation(RequestMessage requestMessage);
/**
 * 交通信息列表查询
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTransportationInfoList(RequestMessage requestMessage);
/**
 * 交通信息单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTransportationInfoList(RequestMessage requestMessage);
/**
 * 交通信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTransportationInfoList(RequestMessage requestMessage);
/**
 * 交通信息删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/deleteTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage deleteTransportationInfoList(RequestMessage requestMessage);
/**
 * 旅行信息类型列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelTypeList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelTypeList(RequestMessage requestMessage);
/**
 * 旅行信息类型单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTravelType(RequestMessage requestMessage);
/**
 * 旅行信息类型新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertTravelType(RequestMessage requestMessage);
/**
 * 旅行信息类型删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage delTravelType(RequestMessage requestMessage);

/**
 * 旅行信息类型修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTravelType(RequestMessage requestMessage);
/**
 * 旅行信息评论列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelCommentList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelCommentList(RequestMessage requestMessage);
/**
 * 旅行信息评论单条查询
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTravelComment",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTravelComment(RequestMessage requestMessage);
/**
 * 旅行信息评论修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelComment",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTravelComment(RequestMessage requestMessage);
/**
 * 旅行信息评论删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/deleteTravelComment",produces = {"application/json;charset=UTF-8"})
ResponseMessage deleteTravelComment(RequestMessage requestMessage);
/**
 * 旅行信息专栏列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelColumnList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelColumnList(RequestMessage requestMessage);
/**
 * 旅行信息专栏单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTravelColumn(RequestMessage requestMessage);
/**
 * 旅行信息专栏修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTravelColumn(RequestMessage requestMessage);
/**
 * 旅行信息专栏删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/deleteTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage deleteTravelColumn(RequestMessage requestMessage);
/**
 * 旅行信息专栏新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertTravelColumn(RequestMessage requestMessage);
/**
 * 交通类型列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTransportationTypeList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTransportationTypeList(RequestMessage requestMessage);
/**
 * 交通类型单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTransportationType",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTransportationType(RequestMessage requestMessage);


}
