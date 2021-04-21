package com.itour.quartz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.TravelApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.init.SensitiveWordFilter;
import com.itour.model.travel.CommentReply;
import com.itour.model.travel.TravelComment;
import com.itour.util.FastJsonUtil;

@Service
public class CommentCheckService {
	@Autowired
	private TravelApi travelApi;
	@Autowired
    private SensitiveWordFilter sensitiveWordFilter;
	/**
	 * 评论审核
	 */
public void updateCommentStatus() {
	/**
	 * 评论审核
	 */
     checkComment();
     /**
      * 评论回复审核
      */
	checkCommentReply();
}
	private void checkCommentReply() {
		//1.查看评论回复表
		//2.批量修改评论回复表的状态
		JSONObject jsonObject = new JSONObject();
		CommentReply cp = new CommentReply();
		cp.setStatus(Constant.COMMON_STATUS_CHECKING);
		jsonObject.put(Constant.COMMON_KEY_VO, cp);
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
		ResponseMessage replyList = this.travelApi.queryTravelCommentReplyList(requestMessage);
		if(Constant.SUCCESS_CODE.equals(replyList.getResultCode())&&!StringUtils.isEmpty(replyList.getReturnResult())) {
			List<CommentReply> mapToList = FastJsonUtil.mapToList(replyList.getReturnResult(), CommentReply.class, Constant.COMMON_KEY_RESULT);
			for (CommentReply commentReply : mapToList) {
				boolean sensitive = sensitiveWordFilter.isSensitive(commentReply.getReply());
				if(sensitive) {
					commentReply.setStatus(Constant.COMMON_STATUS_CHECK);
				}else {
					commentReply.setStatus(Constant.COMMON_STATUS_CHECKED);
				}
				
			}
			jsonObject.clear();
			jsonObject.put(Constant.COMMON_KEY_ARR, mapToList);
			RequestMessage req = HttpDataUtil.postData(jsonObject, null);
			this.travelApi.updateCommentReplyBatch(req);
		}
	}
	private void checkComment() {
		//1.查看评论表
		//2.批量修改评论表的状态
		JSONObject jsonObject = new JSONObject();
		TravelComment comment = new TravelComment();
		comment.setStatus(Constant.COMMON_STATUS_CHECKING);
		jsonObject.put(Constant.COMMON_KEY_VO, comment);
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
		ResponseMessage travelCommentList = travelApi.queryTravelCommentList(requestMessage);
		if(Constant.SUCCESS_CODE.equals(travelCommentList.getResultCode())&&!StringUtils.isEmpty(travelCommentList.getReturnResult())) {
			List<TravelComment> mapToList = FastJsonUtil.mapToList(travelCommentList.getReturnResult(), TravelComment.class, Constant.COMMON_KEY_RESULT);
			for (TravelComment travelComment : mapToList) {
				boolean sensitive = sensitiveWordFilter.isSensitive(travelComment.getComment());
				if(sensitive) {
					travelComment.setStatus(Constant.COMMON_STATUS_CHECK);
				}else {
					travelComment.setStatus(Constant.COMMON_STATUS_CHECKED);
				}
				
			}
			jsonObject.clear();
			jsonObject.put(Constant.COMMON_KEY_ARR, mapToList);
			RequestMessage req = HttpDataUtil.postData(jsonObject, null);
			this.travelApi.updateCommentBatch(req);
			
		}
	}
}

