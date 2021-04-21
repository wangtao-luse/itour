package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.entity.PageInfo;
import com.itour.exception.BaseException;
import com.itour.model.travel.CommentReply;
import com.itour.persist.CommentReplyMapper;
import com.itour.service.CommentReplyService;
import com.itour.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 旅行信息评论回复表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-04-09
 */
@Service
public class CommentReplyService extends ServiceImpl<CommentReplyMapper, CommentReply> {
	/**
	 * 评论回复列表
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage queryTravelCommentReplyList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			CommentReply travelComment = jsonObject.getJSONObject("vo").toJavaObject(CommentReply.class);
			JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
			QueryWrapper<CommentReply> queryWrapper = new QueryWrapper<CommentReply>();
			queryWrapper.eq(!StringUtils.isEmpty(travelComment.getStatus()), "STATUS", travelComment.getStatus());
			if(!StringUtils.isEmpty(pageVo)) {
				PageInfo page = pageVo.toJavaObject(PageInfo.class);
				PageInfo selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
			}else {
				List<CommentReply> selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
/**
 * 添加评论回复
 * @param requestMessage
 * @return
 */
public ResponseMessage insertCommentReply(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		CommentReply commentReply = jsonObject.toJavaObject(CommentReply.class);
		commentReply.setRtime(DateUtil.currentLongDate());
		commentReply.setStatus(Constant.COMMON_STATUS_CHECKING);
		this.baseMapper.insert(commentReply);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 逻辑删除评论回复
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage delCommentReply(RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		CommentReply commentReplyVo = jsonObject.toJavaObject(CommentReply.class);
		commentReplyVo.setStatus(Constant.COMMON_STATUS_DELETED);
		this.updateById(commentReplyVo);
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
public ResponseMessage updateCommentReplyBatch(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		List<CommentReply> commentReplyVo = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(CommentReply.class);
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
