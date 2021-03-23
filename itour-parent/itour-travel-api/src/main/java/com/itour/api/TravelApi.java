package com.itour.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
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
public ResponseMessage queryTravelInfoList(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息根据编号
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/selectTravelInfoById",produces = {"application/json;charset=UTF-8"})
public ResponseMessage selectTravelInfoById(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelInfo",produces = {"application/json;charset=UTF-8"})
public ResponseMessage updateTravelInfo(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息批量修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelInfoBatch",produces = {"application/json;charset=UTF-8"})
public ResponseMessage updateTravelInfoBatch(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delTravelInfo",produces = {"application/json;charset=UTF-8"})
public ResponseMessage delTravelInfo(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertTravelInfo",produces = {"application/json;charset=UTF-8"})
public ResponseMessage insertTravelInfo(@RequestBody RequestMessage requestMessage);
/**
 * 城市信息列表信息列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryLocationList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryLocationList(@RequestBody RequestMessage requestMessage);
/**
 * 城市信息查询单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getLocation",produces = {"application/json;charset=UTF-8"})
ResponseMessage getLocation(@RequestBody RequestMessage requestMessage);
/**
 * 城市信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateLocation",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateLocation(@RequestBody RequestMessage requestMessage);
/**
 * 城市信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/deleteLocation",produces = {"application/json;charset=UTF-8"})
ResponseMessage deleteLocation(@RequestBody RequestMessage requestMessage);
/**
 * 交通信息列表查询
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTransportationInfoList(@RequestBody RequestMessage requestMessage);
/**
 * 交通信息单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTransportationInfoList(@RequestBody RequestMessage requestMessage);
/**
 * 交通信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTransportationInfoList(@RequestBody RequestMessage requestMessage);
/**
 * 交通信息删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/deleteTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage deleteTransportationInfoList(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息类型列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelTypeList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelTypeList(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息类型单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTravelType(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息类型新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertTravelType(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息类型删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage delTravelType(@RequestBody RequestMessage requestMessage);

/**
 * 旅行信息类型修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTravelType(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息评论列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelCommentList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelCommentList(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息评论单条查询
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTravelComment",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTravelComment(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息评论修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelComment",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTravelComment(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息评论删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/deleteTravelComment",produces = {"application/json;charset=UTF-8"})
ResponseMessage deleteTravelComment(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息专栏列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelColumnList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelColumnList(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息专栏单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTravelColumn(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息专栏修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTravelColumn(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息专栏删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/deleteTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage deleteTravelColumn(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息专栏新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertTravelColumn(@RequestBody RequestMessage requestMessage);
/**
 * 交通类型列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTransportationTypeList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTransportationTypeList(@RequestBody RequestMessage requestMessage);
/**
 * 交通类型单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTransportationType",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTransportationType(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息列表（视图）
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryViewTravelinfoOauthList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryViewTravelinfoOauthList(@RequestBody RequestMessage requestMessage);

/**
 * 旅行信息单条（视图）
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/selectViewTravelinfoOauthById",produces = {"application/json;charset=UTF-8"})
ResponseMessage selectViewTravelinfoOauthById(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息点赞列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryNiceList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryNiceList(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息点赞列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertNice(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息点赞单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage getNice(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息点赞修改(取消点赞)
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateNice(@RequestBody RequestMessage requestMessage);
/**
 * 统计点赞数
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/countNiceList",produces = {"application/json;charset=UTF-8"})
ResponseMessage countNiceList(@RequestBody RequestMessage requestMessage);
/**
 * 批量修改或保存点赞信息
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/saveOrUpdateBatchNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage saveOrUpdateBatchNice(@RequestBody RequestMessage requestMessage);
/**
 * 旅行文字收藏记录列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryCollectList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryCollectList(@RequestBody RequestMessage requestMessage);
/**
 * 旅行收藏夹列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryFavoriteList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryFavoriteList(@RequestBody RequestMessage requestMessage);
/**
 * 旅行收藏夹新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertFavorite",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertFavorite(@RequestBody RequestMessage requestMessage);
/**
 * 旅行收藏夹修改|删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateFavorite",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateFavorite(@RequestBody RequestMessage requestMessage);
/**
 * 旅行收藏夹物理删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delFavorite",produces = {"application/json;charset=UTF-8"})
ResponseMessage delFavorite(@RequestBody RequestMessage requestMessage);
/**
 * 旅行浏览记录列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryPageviewList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryPageviewList(@RequestBody RequestMessage requestMessage);

/**
 * 旅行浏览批量新增或修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/saveOrUpdateBatchPageview",produces = {"application/json;charset=UTF-8"})
ResponseMessage saveOrUpdateBatchPageview(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息浏览记录
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryHistoryList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryHistoryList(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息浏览记录新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertHistory",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertHistory(@RequestBody RequestMessage requestMessage);
/**
 * 旅行信息浏览记录清楚
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/clearHistory",produces = {"application/json;charset=UTF-8"})
ResponseMessage clearHistory(@RequestBody RequestMessage requestMessage);
/**
 * 旅行话题标签查询
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelTagList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelTagList(@RequestBody RequestMessage requestMessage);
/**
 * 旅行话题标签新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertTravelTag",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertTravelTag(@RequestBody RequestMessage requestMessage);
/**
 * 旅行话题标签修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelTag",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTravelTag(@RequestBody RequestMessage requestMessage);
/**
 * 旅行话题标签删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delTravelTag",produces = {"application/json;charset=UTF-8"})
ResponseMessage delTravelTag(@RequestBody RequestMessage requestMessage);

@RequestMapping(value = "/travel/getRegionList",produces = {"application/json;charset=UTF-8"})
ResponseMessage getRegionList(@RequestBody RequestMessage requestMessage);



}
