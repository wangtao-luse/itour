package com.itour.quartz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.TravelApi;
import com.itour.api.WorkApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.init.SensitiveWordFilter;
import com.itour.model.travel.CommentReply;
import com.itour.model.travel.TravelComment;
import com.itour.model.vo.Orderby;
import com.itour.model.work.WorkComment;
import com.itour.model.work.WorkCommentReply;
import com.itour.model.work.dto.WorkCommentDto;
import com.itour.util.FastJsonUtil;

@Service
public class CommentCheckService {
	@Autowired
	private TravelApi travelApi;
	@Autowired
	private WorkApi workApi;
	@Autowired
    private SensitiveWordFilter sensitiveWordFilter;
	/**
	 * 评论审核
	 */
@Transactional
public void updateCommentStatus() {
	/**
	 * 旅行攻略评论审核
	 */
     checkComment();
     /**
      * 旅行攻略评论回复审核
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
	@Transactional
	public void updateWorkCommentStatus() {
		/**
		 * 旅行攻略评论审核
		 */
		checkWorkComment();
	     /**
	      * 旅行攻略评论回复审核
	      */
		checkworkCommentReply();
	}
	private void checkworkCommentReply() {
		// TODO Auto-generated method stub
		//1.查看评论回复表
		//2.批量修改评论回复表的状态
		JSONObject jsonObject = new JSONObject();
		WorkCommentReply cp = new WorkCommentReply();
		List<Orderby> oList = new ArrayList<Orderby>();
		oList.add(new Orderby(Orderby.DESC, "rtime"));
		cp.setOrderbyList(oList);
		cp.setStatus(Constant.COMMON_STATUS_CHECKING);
		jsonObject.put(Constant.COMMON_KEY_VO, cp);
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
		ResponseMessage replyList = this.workApi.queryWorkCommentReplyList(requestMessage);
		if(Constant.SUCCESS_CODE.equals(replyList.getResultCode())&&!StringUtils.isEmpty(replyList.getReturnResult())) {
			List<WorkCommentReply> mapToList = FastJsonUtil.mapToList(replyList.getReturnResult(), WorkCommentReply.class, Constant.COMMON_KEY_RESULT);
			for (WorkCommentReply commentReply : mapToList) {
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
			this.workApi.updateWorkCommentReplyBatch(req);
		}
	}
	private void checkWorkComment() {
		//1.查看评论表
		//2.批量修改评论表的状态
		JSONObject jsonObject = new JSONObject();
		WorkCommentDto comment = new WorkCommentDto();
		List<Orderby> olist = new ArrayList<Orderby>();
		olist.add(new Orderby(Orderby.DESC, "ctime"));
		comment.setOrderbyList(olist);
		comment.setStatus(Constant.COMMON_STATUS_CHECKING);
		jsonObject.put(Constant.COMMON_KEY_VO, comment);
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
		ResponseMessage workcommentList = workApi.queryWorkCommentList(requestMessage);
		if(Constant.SUCCESS_CODE.equals(workcommentList.getResultCode())&&!StringUtils.isEmpty(workcommentList.getReturnResult())) {
			List<WorkComment> mapToList = FastJsonUtil.mapToList(workcommentList.getReturnResult(), WorkComment.class, Constant.COMMON_KEY_RESULT);
			for (WorkComment workcomment : mapToList) {
				boolean sensitive = sensitiveWordFilter.isSensitive(workcomment.getComment());
				if(sensitive) {
					workcomment.setStatus(Constant.COMMON_STATUS_CHECK);
				}else {
					workcomment.setStatus(Constant.COMMON_STATUS_CHECKED);
				}
				
			}
			jsonObject.clear();
			jsonObject.put(Constant.COMMON_KEY_ARR, mapToList);
			RequestMessage req = HttpDataUtil.postData(jsonObject, null);
			this.workApi.updateWorkCommentBatch(req);
			
		}
	}
}

