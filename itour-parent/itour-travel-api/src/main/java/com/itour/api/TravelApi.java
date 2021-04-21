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
public ResponseMessage queryTravelInfoList( RequestMessage requestMessage);
/**
 * 旅行信息根据编号
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/selectTravelInfoById",produces = {"application/json;charset=UTF-8"})
public ResponseMessage selectTravelInfoById( RequestMessage requestMessage);
/**
 * 旅行信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelInfo",produces = {"application/json;charset=UTF-8"})
public ResponseMessage updateTravelInfo( RequestMessage requestMessage);
/**
 * 旅行信息批量修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelInfoBatch",produces = {"application/json;charset=UTF-8"})
public ResponseMessage updateTravelInfoBatch( RequestMessage requestMessage);
/**
 * 旅行信息浏览量批量修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updatePvBatch",produces = {"application/json;charset=UTF-8"})
public ResponseMessage updatePvBatch(RequestMessage requestMessage);
/**
 * 旅行信息删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delTravelInfo",produces = {"application/json;charset=UTF-8"})
public ResponseMessage delTravelInfo( RequestMessage requestMessage);
/**
 * 旅行信息新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertTravelInfo",produces = {"application/json;charset=UTF-8"})
public ResponseMessage insertTravelInfo( RequestMessage requestMessage);
/**
 * 城市信息列表信息列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryLocationList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryLocationList( RequestMessage requestMessage);
/**
 * 城市信息查询单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getLocation",produces = {"application/json;charset=UTF-8"})
ResponseMessage getLocation( RequestMessage requestMessage);
/**
 * 城市信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateLocation",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateLocation( RequestMessage requestMessage);
/**
 * 城市信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/deleteLocation",produces = {"application/json;charset=UTF-8"})
ResponseMessage deleteLocation( RequestMessage requestMessage);
/**
 * 交通信息列表查询
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTransportationInfoList( RequestMessage requestMessage);
/**
 * 交通信息单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTransportationInfoList( RequestMessage requestMessage);
/**
 * 交通信息修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTransportationInfoList( RequestMessage requestMessage);
/**
 * 交通信息删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/deleteTransportationInfoList",produces = {"application/json;charset=UTF-8"})
ResponseMessage deleteTransportationInfoList( RequestMessage requestMessage);
/**
 * 旅行信息类型列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelTypeList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelTypeList( RequestMessage requestMessage);
/**
 * 旅行信息类型单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTravelType( RequestMessage requestMessage);
/**
 * 旅行信息类型新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertTravelType( RequestMessage requestMessage);
/**
 * 旅行信息类型删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage delTravelType( RequestMessage requestMessage);

/**
 * 旅行信息类型修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelType",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTravelType( RequestMessage requestMessage);


/**
 * 旅行信息专栏列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelColumnList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelColumnList( RequestMessage requestMessage);
/**
 * 旅行信息专栏单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTravelColumn( RequestMessage requestMessage);
/**
 * 旅行信息专栏修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTravelColumn( RequestMessage requestMessage);
/**
 * 旅行信息专栏删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/deleteTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage deleteTravelColumn( RequestMessage requestMessage);
/**
 * 旅行信息专栏新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertTravelColumn",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertTravelColumn( RequestMessage requestMessage);
/**
 * 交通类型列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTransportationTypeList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTransportationTypeList( RequestMessage requestMessage);
/**
 * 交通类型单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getTransportationType",produces = {"application/json;charset=UTF-8"})
ResponseMessage getTransportationType( RequestMessage requestMessage);
/**
 * 旅行信息列表（视图）
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryViewTravelinfoOauthList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryViewTravelinfoOauthList( RequestMessage requestMessage);

/**
 * 旅行信息单条（视图）
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/selectViewTravelinfoOauthById",produces = {"application/json;charset=UTF-8"})
ResponseMessage selectViewTravelinfoOauthById( RequestMessage requestMessage);
/**
 * 旅行信息点赞列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryNiceList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryNiceList( RequestMessage requestMessage);
/**
 * 旅行信息点赞列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertNice( RequestMessage requestMessage);
/**
 * 旅行信息点赞单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage getNice( RequestMessage requestMessage);
/**
 * 旅行信息点赞修改(取消点赞)
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateNice( RequestMessage requestMessage);
/**
 * 统计点赞数
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/countNiceList",produces = {"application/json;charset=UTF-8"})
ResponseMessage countNiceList( RequestMessage requestMessage);
/**
 * 批量修改或保存点赞信息
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/saveOrUpdateBatchNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage saveOrUpdateBatchNice( RequestMessage requestMessage);
/**
 * 旅行文字收藏记录列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryCollectList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryCollectList(RequestMessage requestMessage);
/**
 * 旅行收藏夹列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryFavoriteList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryFavoriteList(RequestMessage requestMessage);
/**
 * 旅行收藏夹新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertFavorite",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertFavorite(RequestMessage requestMessage);
/**
 * 旅行收藏夹修改|删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateFavorite",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateFavorite(RequestMessage requestMessage);
/**
 * 旅行收藏夹物理删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delFavorite",produces = {"application/json;charset=UTF-8"})
ResponseMessage delFavorite(RequestMessage requestMessage);
/**
 * 旅行浏览记录列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryPageviewList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryPageviewList(RequestMessage requestMessage);

/**
 * 旅行浏览批量新增或修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/saveOrUpdateBatchPageview",produces = {"application/json;charset=UTF-8"})
ResponseMessage saveOrUpdateBatchPageview( RequestMessage requestMessage);
/**
 * 旅行信息浏览记录
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryHistoryList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryHistoryList( RequestMessage requestMessage);
/**
 * 旅行信息浏览记录新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertHistory",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertHistory( RequestMessage requestMessage);
/**
 * 旅行信息浏览记录清楚
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/clearHistory",produces = {"application/json;charset=UTF-8"})
ResponseMessage clearHistory(RequestMessage requestMessage);
/**
 * 旅行话题标签查询
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelTagList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelTagList(RequestMessage requestMessage);
/**
 * 旅行话题标签新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertTravelTag",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertTravelTag(RequestMessage requestMessage);
/**
 * 旅行话题标签修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateTravelTag",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateTravelTag(RequestMessage requestMessage);
/**
 * 旅行话题标签删除
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delTravelTag",produces = {"application/json;charset=UTF-8"})
ResponseMessage delTravelTag(RequestMessage requestMessage);

@RequestMapping(value = "/travel/getRegionList",produces = {"application/json;charset=UTF-8"})
ResponseMessage getRegionList(RequestMessage requestMessage);

@RequestMapping(value = "/travel/insertNiceBatch",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertNiceBatch(RequestMessage requestMessage);
/**
 * 旅行攻略点赞
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/niceSub",produces = {"application/json;charset=UTF-8"})
ResponseMessage niceSub(RequestMessage requestMessage);
/**
 * 获取周末攻略
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/selecWeekInfoOne",produces = {"application/json;charset=UTF-8"})
ResponseMessage selecWeekInfoOne(RequestMessage requestMessage);
/**
 * 获取文章标签
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryViewTravelTagList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryViewTravelTagList(RequestMessage requestMessage);
/**
 * 旅行攻略评论列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelCommentList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelCommentList(RequestMessage requestMessage);
/**
 * 插入旅行攻略评论
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertComment",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertComment(RequestMessage requestMessage);
/**
 * 插入旅行攻略评论回复
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryTravelCommentReplyList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryTravelCommentReplyList(RequestMessage requestMessage);
/**
 * 插入旅行攻略评论回复
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/insertCommentReply",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertCommentReply(RequestMessage requestMessage);
/**
 * 旅行攻略评论列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/queryCommentList",produces = {"application/json;charset=UTF-8"})
ResponseMessage queryCommentList(RequestMessage requestMessage);

/**
 * 删除评论
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delComment",produces = {"application/json;charset=UTF-8"})
ResponseMessage delComment(RequestMessage requestMessage);
/**
 * 删除评论回复
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/delCommentReply",produces = {"application/json;charset=UTF-8"})
ResponseMessage delCommentReply(RequestMessage requestMessage);
/**
 * 旅行攻略评论点赞
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/commentNiceSub",produces = {"application/json;charset=UTF-8"})
ResponseMessage commentNiceSub(RequestMessage requestMessage);
/**
 * 旅行攻略评论回复点赞
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/commentReplyNiceSub",produces = {"application/json;charset=UTF-8"})
ResponseMessage commentReplyNiceSub(RequestMessage requestMessage);
/**
 * 旅行攻略评论点赞单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getCommentNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage getCommentNice(RequestMessage requestMessage);
/**
 *批量插入或修改旅行攻略评论点赞记录
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/saveOrUpdateBatchCommentNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage saveOrUpdateBatchCommentNice(RequestMessage requestMessage);
/**
 * 旅行攻略评论回复点赞单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/getCommentReplyNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage getCommentReplyNice(RequestMessage requestMessage);
/**
 * 评论回复点赞记录批量新增或修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/saveOrUpdateBatchCommentReplyNice",produces = {"application/json;charset=UTF-8"})
ResponseMessage saveOrUpdateBatchCommentReplyNice(RequestMessage requestMessage);
/**
 * 评论点赞统计
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/countCommentNiceList",produces = {"application/json;charset=UTF-8"})
ResponseMessage countCommentNiceList(RequestMessage requestMessage);
/**
 * 评论回复点赞统计
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/countCommentReplyNiceList",produces = {"application/json;charset=UTF-8"})
ResponseMessage countCommentReplyNiceList(RequestMessage requestMessage);
/**
 * 批量修改评论回复信息
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateCommentReplyBatch",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateCommentReplyBatch(RequestMessage requestMessage);
/**
 * 批量修改评论信息
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/updateCommentBatch",produces = {"application/json;charset=UTF-8"})
ResponseMessage updateCommentBatch(RequestMessage requestMessage);
/**
 * 敏感字列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/travel/querySensitiveWordList",produces = {"application/json;charset=UTF-8"})
ResponseMessage querySensitiveWordList(RequestMessage requestMessage);



}
