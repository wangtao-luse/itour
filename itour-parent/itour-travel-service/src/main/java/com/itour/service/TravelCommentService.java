package com.itour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.CommentReply;
import com.itour.model.travel.TravelComment;
import com.itour.persist.CommentReplyMapper;
import com.itour.persist.TravelCommentMapper;
import com.itour.util.DateUtil;

/**
 * <p>
 * 旅行信息评论表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-04-09
 */
@Service
public class TravelCommentService extends ServiceImpl<TravelCommentMapper, TravelComment>   {
	@Autowired
	CommentReplyMapper commentReplyMapper;	
	@Transactional
	public ResponseMessage insertComment(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelComment commentVo = jsonObject.toJavaObject(TravelComment.class);
			commentVo.setCtime(DateUtil.currentLongDate());
			commentVo.setStatus(Constant.COMMON_STATUS_CHECKING);
			this.baseMapper.insert(commentVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 逻辑删除评论
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage delComment(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelComment commentVo = jsonObject.toJavaObject(TravelComment.class);
			commentVo.setStatus(Constant.COMMON_STATUS_DELETED);
			this.updateById(commentVo);
			QueryWrapper<CommentReply> updateWrapper = new QueryWrapper<CommentReply>();
			updateWrapper.eq("COMMENT_ID", commentVo.getId());
			CommentReply entity = new CommentReply();
			entity.setStatus(Constant.COMMON_STATUS_DELETED);
			this.commentReplyMapper.update(entity , updateWrapper);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	* 批量修改评论信息
	* @param requestMessage
	* @return
	*/
	@Transactional
	public ResponseMessage updateCommentBatch(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			List<TravelComment> commentReplyVo = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(TravelComment.class);
			if(commentReplyVo.size()>0) {
				this.updateBatchById(commentReplyVo, commentReplyVo.size());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
    
}
