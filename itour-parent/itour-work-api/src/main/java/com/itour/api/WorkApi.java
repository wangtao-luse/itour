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
	/**
	 * 日志标签查询（用于页面展示包含统计信息）
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/getShowColumnList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getShowColumnList(RequestMessage requestMessage);		
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
	 * 个人博客点赞提交
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/likeSub",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage likeSub(RequestMessage requestMessage);	
	
	
	/**
	 * 个人博客评论列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/queryWorkCommentList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryWorkCommentList(RequestMessage requestMessage);	
	/**
	 * 个人博客添加评论
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/insertComment",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage insertComment(RequestMessage requestMessage);	
	
	/**
	 * 个人博客评论删除
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/delComment",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage delComment(RequestMessage requestMessage);	
	
	
	/**
	 * 个人博客评论点赞
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/commentLikeSub",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage commentLikeSub(RequestMessage requestMessage);	
	
	
	/**
	 * 个人博客评论批量修改
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/updateWorkCommentBatch",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage updateWorkCommentBatch(RequestMessage requestMessage);	
	/**
	 * 个人博客评论回复列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/queryWorkCommentReplyList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryWorkCommentReplyList(RequestMessage requestMessage);	
	/**
	 * 批量修改评论回复
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/updateWorkCommentReplyBatch",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage updateWorkCommentReplyBatch(RequestMessage requestMessage);	
	/**
	 * 评论回复新增
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/insertWorkCommentReply",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage insertWorkCommentReply(RequestMessage requestMessage);	
	/**
	 * 评论回复逻辑删除
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/delWorkCommentReply",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage delWorkCommentReply(RequestMessage requestMessage);	
	
	/**
	 * 评论回复点赞
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/workCommentReplyLikeSub",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage workCommentReplyLikeSub(RequestMessage requestMessage);	
	/**
	 * 点赞信息单条
	 */
	@RequestMapping(value = "work/getLike",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getLike(RequestMessage requestMessage);	
	
	/**
	 * 点赞新数据增或修改
	 */
	@RequestMapping(value = "work/saveOrUpdateBatchLike",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage saveOrUpdateBatchLike(RequestMessage requestMessage);	
	
	/**
	 * 个人日志统计点赞数
	 */
	@RequestMapping(value = "work/countLikeList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage countLikeList(RequestMessage requestMessage);	
	/**
	 * 个人日志统计点赞数
	 */
	@RequestMapping(value = "work/getCommentLike",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getCommentLike(RequestMessage requestMessage);	
	/**
	 * 个人日志统计点赞数
	 */
	@RequestMapping(value = "work/saveOrUpdateBatchCommentLike",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage saveOrUpdateBatchCommentLike(RequestMessage requestMessage);	
	
	/**
	 * 个人日志统计点赞数
	 */
	@RequestMapping(value = "work/countCommentNiceList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage countCommentNiceList(RequestMessage requestMessage);	
	
	
	
	
	/**
	 * 个人日志回复点赞表单条
	 */
	@RequestMapping(value = "work/getReplyLike",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getReplyLike(RequestMessage requestMessage);	
	
	/**
	 * 批量修改或保存评论回复点赞信息
	 */
	@RequestMapping(value = "work/saveOrUpdateBatchReplyLike",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage saveOrUpdateBatchReplyLike(RequestMessage requestMessage);	
	
	
	/**
	 *统计评论回复点赞数
	 */
	@RequestMapping(value = "work/countReplyLikeList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage countReplyLikeList(RequestMessage requestMessage);	
	
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
	/**
	 * 根据分类专栏编号查询个人专栏文章
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/queryWorkByColList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryWorkByColList(RequestMessage requestMessage);	
	/**
	 * 查询工作日志专栏单条
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/selectOneColumn",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage selectOneColumn(RequestMessage requestMessage);	
	/**
	 * 日志独立访问IP记录
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "work/saveOrupdateWorkIpInfo",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage saveOrupdateWorkIpInfo(RequestMessage requestMessage);	
}

