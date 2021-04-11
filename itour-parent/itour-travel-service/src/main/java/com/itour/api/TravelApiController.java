package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.CollectService;
import com.itour.service.FavoritesService;
import com.itour.service.HistoryService;
import com.itour.service.LocationService;
import com.itour.service.NiceService;
import com.itour.service.PageviewService;
import com.itour.service.RegionService;
import com.itour.service.TagService;
import com.itour.service.TransportationInfoService;
import com.itour.service.TransportationTypeService;
import com.itour.service.TravelColumnService;
import com.itour.service.TravelCommentService;
import com.itour.service.TravelInfoService;
import com.itour.service.TravelTypeService;
import com.itour.service.ViewTravelinfoOauthService;

@RestController
public class TravelApiController implements TravelApi {
	@Autowired
	TravelInfoService travelInfoService;
	@Autowired
	LocationService locationService;
	@Autowired
	TransportationInfoService transportationInfoService;
	@Autowired
	TravelTypeService travelTypeService;
	@Autowired
	TravelCommentService travelCommentService;
	@Autowired
	TravelColumnService travelColumnService;
	@Autowired
	TransportationTypeService transportationTypeService;
	@Autowired
	ViewTravelinfoOauthService viewTravelinfoOauthService;
	@Autowired
	NiceService niceService;
	@Autowired
	CollectService collectService;
	@Autowired
	FavoritesService favoritesService;
	@Autowired
	PageviewService pageviewService;
	@Autowired
	HistoryService historyService;
	@Autowired
	TagService tagService;
	@Autowired
	RegionService regionService;
	 /**
     * 旅游信息列表
     */
	@Override
	public ResponseMessage queryTravelInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.queryTravelInfoList(requestMessage);
	}
	 /**
     * 旅游信息查询单条
     */
	@Override
	public ResponseMessage selectTravelInfoById(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.selectTravelInfoById(requestMessage);
	}
	 /**
     * 旅游信息修改
     */
	@Override
	public ResponseMessage updateTravelInfo(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.updateTravelInfo(requestMessage);
	}
	/**
	 * 旅游信息批量修改
	 */
	@Override
	public ResponseMessage updateTravelInfoBatch(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.updateTravelInfoBatch(requestMessage);
	}
	/**
	 * 旅游信息浏览量批量修改
	 */
	@Override
	public ResponseMessage updatePvBatch(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.updatePvBatch(requestMessage);
	}
	 /**
     * 旅游信息删除
     */
	@Override
	public ResponseMessage delTravelInfo(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.delTravelInfo(requestMessage);
	}
    /**
     * 旅游信息新增
     */
	@Override
	public ResponseMessage insertTravelInfo(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.insertTravelInfo(requestMessage);
	}
	/**
	 * 旅行攻略点赞功能
	 */
	@Override
	public ResponseMessage niceSub(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelInfoService.niceSub(requestMessage);
	}
	/**
	 * 城市列表查询
	 */
	@Override
	public ResponseMessage queryLocationList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.queryLocationList(requestMessage);
	}
	/**
	 * 城市信息单条查询
	 */
	@Override
	public ResponseMessage getLocation(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.getLocation(requestMessage);
	}
	/**
	 * 城市信息修改
	 */
	@Override
	public ResponseMessage updateLocation(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.updateLocation(requestMessage);
	}
	/**
	 * 城市信息删除
	 */
	@Override
	public ResponseMessage deleteLocation(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return locationService.delelteLocation(requestMessage);
	}
	/**
	 * 交通信息列表查询
	 */
	@Override
	public ResponseMessage queryTransportationInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.queryTransportationInfoList(requestMessage);
	}
	/**
	 * 交通信息单条
	 */
	@Override
	public ResponseMessage getTransportationInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.getTransportationInfo(requestMessage);
	}
	/**
	 * 修改交通信息
	 */
	@Override
	public ResponseMessage updateTransportationInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.updateTransportationInfo(requestMessage);
	}
	/**
	 * 删除交通信息
	 */
	@Override
	public ResponseMessage deleteTransportationInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationInfoService.delelteTransportationInfo(requestMessage);
	}
	/**
	 * 旅行信息类型列表
	 */
	@Override
	public ResponseMessage queryTravelTypeList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelTypeService.queryTravelTypeList(requestMessage);
	}
	/**
	 * 旅行信息类型新增
	 */
	@Override
	public ResponseMessage insertTravelType(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelTypeService.insertTravelType(requestMessage);
	}
	/**
	 * 旅行信息类型修改
	 */
	@Override
	public ResponseMessage updateTravelType(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelTypeService.updateTravelType(requestMessage);
	}
	/**
	 * 旅行信息类型单条查询
	 */
	@Override
	public ResponseMessage getTravelType(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelTypeService.getTravelType(requestMessage);
	}
	/**
	 * 旅行信息类型删除
	 */
	@Override
	public ResponseMessage delTravelType(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelTypeService.delTravelType(requestMessage);
	}
	/**
	 * 旅行信息评论列表
	 */
	@Override
	public ResponseMessage queryTravelCommentList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelCommentService.queryTravelCommentList(requestMessage);
	}
	/**
	 * 旅行信息评论单条
	 */
	@Override
	public ResponseMessage getTravelComment(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelCommentService.getTravelComment(requestMessage);
	}
	/**
	 * 旅行信息评论修改
	 */
	@Override
	public ResponseMessage updateTravelComment(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelCommentService.updateTravelComment(requestMessage);
	}
	/**
	 * 旅行信息评论修改
	 */
	@Override
	public ResponseMessage deleteTravelComment(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelCommentService.delelteTravelComment(requestMessage);
	}
	/**
	 * 旅行信息专栏列表
	 */
	@Override
	public ResponseMessage queryTravelColumnList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelColumnService.queryTravelColumnList(requestMessage);
	}
	/**
	 * 旅行信息专栏单条
	 */
	@Override
	public ResponseMessage getTravelColumn(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelColumnService.getTravelColumn(requestMessage);
	}
	/**
	 * 旅行信息专栏修改
	 */
	@Override
	public ResponseMessage updateTravelColumn(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelColumnService.updateTravelColumn(requestMessage);
	}
	/**
	 * 旅行信息专栏新增
	 */
	@Override
	public ResponseMessage insertTravelColumn(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelColumnService.insertTravelColumn(requestMessage);
	}
	/**
	 * 旅行信息专栏删除
	 */
	@Override
	public ResponseMessage deleteTravelColumn(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return travelColumnService.delTravelColumn(requestMessage);
	}
	/**
	 * 旅行交通信息类型列表
	 */
	@Override
	public ResponseMessage queryTransportationTypeList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationTypeService.queryTransportationTypeList(requestMessage);
	}
	/**
	 * 旅行交通信息类型单条
	 */
	@Override
	public ResponseMessage getTransportationType(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return transportationTypeService.getTransportationType(requestMessage);
	}
	
	/**
	 * 旅行交通信息列表视图
	 */
	@Override
	public ResponseMessage queryViewTravelinfoOauthList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewTravelinfoOauthService.queryViewTravelinfoOauthList(requestMessage);
	}
	/**
	 * 旅行交通信息单条视图
	 */
	@Override
	public ResponseMessage selectViewTravelinfoOauthById(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewTravelinfoOauthService.selectViewTravelinfoOauthById(requestMessage);
	}
	/**
	 * 旅行信息点赞列表
	 */
	@Override
	public ResponseMessage queryNiceList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return niceService.queryNiceList(requestMessage);
	}
	/**
	 * 旅行信息点赞单条
	 */
	@Override
	public ResponseMessage getNice(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return niceService.getNice(requestMessage);
	}
	/**
	 * 旅行信息点赞信息新增
	 */
	@Override
	public ResponseMessage insertNice(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return niceService.insertNice(requestMessage);
	}
	/**
	 * 旅行信息点赞信息新增批量
	 */
	@Override
	public ResponseMessage insertNiceBatch(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return niceService.insertNiceBatch(requestMessage);
	}
	/**
	 * 修改旅行点赞信息（取消点赞)
	 */
	@Override
	public ResponseMessage updateNice(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return niceService.updateNice(requestMessage);
	}
	/**
	 * 统计点赞数
	 */
	@Override
	public ResponseMessage countNiceList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return niceService.countNiceList(requestMessage);
	}
	/**
	 * 批量保存或修改点赞信息
	 */
	@Override
	public ResponseMessage saveOrUpdateBatchNice(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return niceService.saveOrUpdateBatchNice(requestMessage);
	}
	/**
	 * 收藏记录列表
	 */
	@Override
	public ResponseMessage queryCollectList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return collectService.queryCollectList(requestMessage);
	}
	/**
	 * 旅行收藏夹列表
	 */
	@Override
	public ResponseMessage queryFavoriteList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return favoritesService.queryFavoriteList(requestMessage);
	}
	/**
	 * 旅行收藏夹新增
	 */
	@Override
	public ResponseMessage insertFavorite(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return favoritesService.insertFavorite(requestMessage);
	}
	/**
	 * 旅行收藏夹修改|删除
	 */
	@Override
	public ResponseMessage updateFavorite(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return favoritesService.updateFavorite(requestMessage);
	}
	/**
	 * 旅行收藏夹物理删除
	 */
	@Override
	public ResponseMessage delFavorite(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return favoritesService.delFavorite(requestMessage);
	}
	/**
	 * 浏览量列表
	 */
	@Override
	public ResponseMessage queryPageviewList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return pageviewService.queryPageviewList(requestMessage);
	}
	/**
	 * 浏览量批量新增或修改
	 */
	@Override
	public ResponseMessage saveOrUpdateBatchPageview(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return pageviewService.saveOrUpdateBatchPageview(requestMessage);
	}
	/**
	 * 浏览记录列表
	 */
	@Override
	public ResponseMessage queryHistoryList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return historyService.queryHistoryList(requestMessage);
	}
	/**
	 * 浏览记录列表新增
	 */
	@Override
	public ResponseMessage insertHistory(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return historyService.insertHistory(requestMessage);
	}
	/**
	 * 浏览记录清除
	 */
	@Override
	public ResponseMessage clearHistory(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return historyService.clearHistory(requestMessage);
	}
	@Override
	public ResponseMessage queryTravelTagList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return tagService.queryTravelTagList(requestMessage);
	}
	@Override
	public ResponseMessage insertTravelTag(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return tagService.insertTravelTag(requestMessage);
	}
	@Override
	public ResponseMessage updateTravelTag(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return tagService.updateTravelTag(requestMessage);
	}
	@Override
	public ResponseMessage delTravelTag(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return tagService.delTravelTag(requestMessage);
	}
	@Override
	public ResponseMessage getRegionList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return regionService.getRegionList(requestMessage);
	}
	@Override
	public ResponseMessage selecWeekInfotById(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
