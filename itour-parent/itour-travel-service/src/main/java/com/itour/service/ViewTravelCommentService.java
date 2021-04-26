package com.itour.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.entity.PageInfo;
import com.itour.exception.BaseException;
import com.itour.model.travel.dto.ViewCommentReply;
import com.itour.model.travel.dto.ViewTravelComment;
import com.itour.persist.ViewCommentReplyMapper;
import com.itour.persist.ViewTravelCommentMapper;


/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-04-12
 */
@Service
public class ViewTravelCommentService extends ServiceImpl<ViewTravelCommentMapper, ViewTravelComment> {
	@Autowired
	private ViewCommentReplyMapper viewCommentReplyMapper;
public ResponseMessage queryCommentList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		//1.获取参数
		JSONObject jsonObject = requestMessage.getBody().getContent();
		ViewTravelComment comment = jsonObject.toJavaObject(ViewTravelComment.class);
		JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
		//2.获取对应文章评论
		QueryWrapper<ViewTravelComment> queryWrapper = new QueryWrapper<ViewTravelComment>();
		queryWrapper.eq(!StringUtils.isEmpty(comment.getTid()), "TID", comment.getTid());
		String getuId = requestMessage.getBody().getuId();
		queryWrapper.eq("STATUS", Constant.COMMON_STATUS_CHECKED);
		if(StringUtils.isEmpty(getuId)) {
			queryWrapper.or();
			queryWrapper.eq("STATUS", Constant.COMMON_STATUS_CHECKING);
			queryWrapper.eq("UID", getuId);
			queryWrapper.eq("TID", comment.getTid());
		}
		queryWrapper.orderByDesc("CTIME");
		if(StringUtils.isEmpty(pageVo)) {
			List<ViewTravelComment> commentList = this.baseMapper.selectList(queryWrapper);
			//3.获取对应文章评论下的回复
			List<ViewTravelComment> resultList = getCommentList(commentList,getuId);
			responseMessage.setReturnResult(resultList);
		}else {
			PageInfo page = pageVo.toJavaObject(PageInfo.class);
			PageInfo selectPage = this.baseMapper.selectPage(page, queryWrapper);
			//3.获取对应文章评论下的回复
			List<ViewTravelComment> resultList = getCommentList(selectPage.getRecords(),getuId);
			Page resultPage = selectPage.setRecords(resultList);			
			responseMessage.setReturnResult(resultPage);
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
public List<ViewTravelComment> getCommentList(List<ViewTravelComment> commentList,String uid) {
	String collect = commentList.stream().map(ViewTravelComment::getId).map(String::valueOf).collect(Collectors.joining(","));
	QueryWrapper<ViewCommentReply> wrapper = new QueryWrapper<ViewCommentReply>();
	wrapper.in(!StringUtils.isEmpty(collect),"COMMENT_ID", collect.split(","));
	if(!StringUtils.isEmpty(uid)) {
		wrapper.eq("FROM_UID", uid);
		String [] statusArr = {Constant.COMMON_STATUS_CHECKING,Constant.COMMON_STATUS_CHECKED};
		wrapper.in("STATUS", statusArr);
	}
	wrapper.orderByDesc("RTIME");
	List<ViewCommentReply> replyList = viewCommentReplyMapper.selectList(wrapper);		
	//4.组装评论下的回复信息
	for (ViewTravelComment vComment : commentList) {
		List<ViewCommentReply> rList = new ArrayList<ViewCommentReply>();
		for (ViewCommentReply vReply : replyList) {
			Long id = vComment.getId();
			Long rid = vReply.getCommentId();
			if(id==rid) {
				rList.add(vReply);
			}
		}
		vComment.setvCommentReplyList(rList);
	}
	return commentList;
}
}
