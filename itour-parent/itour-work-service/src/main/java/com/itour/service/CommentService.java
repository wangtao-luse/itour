package com.itour.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ConstantTravel;
import com.itour.exception.BaseException;
import com.itour.model.travel.dto.ViewCommentReply;
import com.itour.model.travel.dto.ViewTravelComment;
import com.itour.model.vo.PageInfo;
import com.itour.model.work.Comment;
import com.itour.model.work.CommentReply;
import com.itour.model.work.dto.CommentDto;
import com.itour.model.work.dto.CommentReplyDto;
import com.itour.persist.CommentMapper;
import com.itour.persist.CommentReplyMapper;
import com.itour.util.FastJsonUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {
	@Autowired
	CommentReplyMapper commentReplyMapper;
	/**
	 * 评论列表
	 * @param requestMessage
	 * @return
	 */
public ResponseMessage queryCommentList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		//1.获取参数
		JSONObject jsonObject = requestMessage.getBody().getContent();
		CommentDto vo = jsonObject.toJavaObject(CommentDto.class);
		JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
		String getuId = requestMessage.getBody().getuId();
		//2.获取对应文章评论		
		if(StringUtils.isEmpty(pageVo)) {//获取文章下的评论及回复
			//获取文章下的评论
			 List<CommentDto> commentList = this.baseMapper.commentList(vo);
			//3.获取对应文章评论下的回复
			Map<String, Object> cList = getCommentList(commentList,getuId);
			responseMessage.setReturnResult(cList);
		}else {
			PageInfo page = pageVo.toJavaObject(PageInfo.class);
			 List<CommentDto> cList = this.baseMapper.commentList(page, vo);
			 page.setRecords(cList);
			//3.获取对应文章评论下的回复
			Map<String, Object> commentList = getCommentList(page.getRecords(),getuId);
			List<ViewTravelComment> resultList = FastJsonUtil.mapToList(commentList, ViewTravelComment.class);
			Page resultPage = page.setRecords(resultList);
			responseMessage.setReturnResult(resultPage);
			long total = page.getTotal();
			if(total>0) {
				Integer  replaysize =(Integer)commentList.get(ConstantTravel.TRAVEL_REPLYSIZE);
				if(!StringUtils.isEmpty(replaysize)) {
					responseMessage.add(ConstantTravel.TRAVEL_COMMENTSIZE, total+replaysize);
				}
			}else {
				responseMessage.add(ConstantTravel.TRAVEL_COMMENTSIZE, total);
			}
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 组装评论回复
 * @param commentList
 * @param uid
 * @return
 */
public Map<String,Object> getCommentList(List<CommentDto> commentList,String uid) {
	Map<String,Object> result = new HashMap<String, Object>();
	String collect = commentList.stream().map(CommentDto::getId).map(String::valueOf).collect(Collectors.joining(","));
	QueryWrapper<CommentReply> wrapper = new QueryWrapper<CommentReply>();
	wrapper.in(!StringUtils.isEmpty(collect),"COMMENT_ID", collect.split(","));	
	wrapper.eq("STATUS", Constant.COMMON_STATUS_CHECKED);
	if(!StringUtils.isEmpty(uid)) {
		wrapper.or();
		wrapper.eq("STATUS", Constant.COMMON_STATUS_CHECKING);
		wrapper.eq("FROM_UID", uid);
		wrapper.in(!StringUtils.isEmpty(collect),"COMMENT_ID", collect.split(","));
	}
	wrapper.orderByDesc("RTIME");
	CommentReplyDto vo = new CommentReplyDto();
	List<CommentReplyDto> replyList = commentReplyMapper.queryCommentReplyList(vo);
	//4.组装评论下的回复信息
	for (CommentDto vComment : commentList) {
		List<CommentReplyDto> rList = new ArrayList<CommentReplyDto>();
		for (CommentReplyDto vReply : replyList) {
			Long id = vComment.getId();
			Long rid = vReply.getCommentId();
			if(id==rid) {
				rList.add(vReply);
			}
		}
		vComment.setCommentReplyList(rList);
	}
	result.put(ConstantTravel.TRAVEL_REPLYSIZE, replyList.size());
	result.put(Constant.COMMON_KEY_RESULT, commentList);
	return result;
}
}
