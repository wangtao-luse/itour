package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.CommentReply;
import com.itour.model.travel.TravelComment;
import com.itour.persist.CommentReplyMapper;
import com.itour.service.CommentReplyService;
import com.itour.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		commentReplyVo.setStatus(Constant.COMMON_STATUS_CHECK);
		this.updateById(commentReplyVo);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
