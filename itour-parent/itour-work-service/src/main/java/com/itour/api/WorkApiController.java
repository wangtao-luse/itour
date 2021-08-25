package com.itour.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.CommentLikeService;
import com.itour.service.LabelService;
import com.itour.service.ViewWorkinfoWorktextService;
import com.itour.service.WorkColumnService;
import com.itour.service.WorkCommentReplyService;
import com.itour.service.WorkCommentService;
import com.itour.service.WorkInfoService;
import com.itour.service.WorktextService;
@RestController
public class WorkApiController implements WorkApi {
	@Autowired
	WorkInfoService workInfoService;
	@Autowired
	LabelService labelService;
	@Autowired
	WorkColumnService workColumnService;
	@Autowired
	ViewWorkinfoWorktextService viewWorkinfoWorktextService;
	@Autowired
	WorktextService worktextService;
	@Autowired
	WorkCommentService workCommentService;
	@Autowired
	CommentLikeService commentLikeService;
	@Autowired
	WorkCommentReplyService workCommentReplyService;
	/**
	 * 日志标签列表查询
	 */
	@Override
	public ResponseMessage queryLabelList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return labelService.queryLabelList(requestMessage);
	}
   /**
    * 日志分类专栏查询
    */
	@Override
	public ResponseMessage queryColumnList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workColumnService.queryColumnList(requestMessage);
	}
	/**
	 *  新增或修改工作日志
	 */
	@Override
	public ResponseMessage savaOrUpdateWorkInfo(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.saveOrUpdateWorkInfo(requestMessage);
	}
    /**
            *工作日志查询单条
     */
	@Override
	public ResponseMessage selectWorkInfoOne(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workInfoService.selectWorkInfoOne(requestMessage);
	}
	@Override
	public ResponseMessage queryViewWorkInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewWorkinfoWorktextService.queryViewWorkInfoList(requestMessage);
	}
	//批量修改工作日志
	@Override
	public ResponseMessage updateWorkInfoBatch(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workInfoService.updateWorkInfoBatch(requestMessage);
	}
	//个人博客内容查询
	@Override
	public ResponseMessage selecWorktextOne(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return worktextService.selecWorktextOne(requestMessage);
	}
	//个人博客标签查询
	@Override
	public ResponseMessage workTagList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workInfoService.workTagList(requestMessage);
	}
	//个人博客专栏查询
	@Override
	public ResponseMessage workColList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workInfoService.workColList(requestMessage);
	}
	//个人博客点赞提交
	@Override
	public ResponseMessage likeSub(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workInfoService.likeSub(requestMessage);
	}
	//获取评论列表
	@Override
	public ResponseMessage queryWorkCommentList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workCommentService.queryWorkCommentList(requestMessage);
	}
   //评论新增
	@Override
	public ResponseMessage insertComment(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workCommentService.insertComment(requestMessage);
	}
    //评论删除
	@Override
	public ResponseMessage delComment(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workCommentService.delComment(requestMessage);
	}
	 //评论点赞
	@Override
	public ResponseMessage commentLikeSub(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return commentLikeService.commentLikeSub(requestMessage);
	}
	//评论批量修改
	@Override
	public ResponseMessage updateWorkCommentBatch(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workCommentService.updateWorkCommentBatch(requestMessage);
	}
	//评论回复列表
	@Override
	public ResponseMessage queryWorkCommentReplyList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workCommentReplyService.queryWorkCommentReplyList(requestMessage);
	}
	//评论回复批量修改
	@Override
	public ResponseMessage updateWorkCommentReplyBatch(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workCommentReplyService.updateCommentReplyBatch(requestMessage);
	}
	//评论回复新增
	@Override
	public ResponseMessage insertWorkCommentReply(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workCommentReplyService.insertWorkCommentReply(requestMessage);
	}
	//评论回复删除
	@Override
	public ResponseMessage delWorkCommentReply(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workCommentReplyService.delWorkCommentReply(requestMessage);
	}
	//评论回复点赞
	@Override
	public ResponseMessage workCommentReplyLikeSub(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return workCommentReplyService.workCommentReplyLikeSub(requestMessage);
	}
	
	
	
	
	
	
	@Override
	public ResponseMessage selectWorkInfoList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.selectWorkInfoList(requestMessage);
	}

	@Override
	public ResponseMessage selectWorkInfo(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.selectWorkInfo(requestMessage);
	}

	@Override
	public ResponseMessage searchTextList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.searchTextList(requestMessage);
	}

	@Override
	public ResponseMessage queryPersonCenterList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.queryPersonCenterList(requestMessage);
	}

	@Override
	public ResponseMessage getInfoData(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.getInfoData(requestMessage);
	}

	@Override
	public ResponseMessage updatePvBatch(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return this.workInfoService.updatePvBatch(requestMessage);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
}
