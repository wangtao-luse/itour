package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.CommentReply;
import com.itour.model.vo.PageInfo;
import com.itour.model.work.WorkCommentReply;
import com.itour.persist.WorkCommentReplyMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@Service
public class WorkCommentReplyService extends ServiceImpl<WorkCommentReplyMapper, WorkCommentReply> {
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
			List<WorkCommentReply> commentReplyVo = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(WorkCommentReply.class);
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
	/**
	 * 评论回复列表
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage queryWorkCommentReplyList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkCommentReply workcomment = jsonObject.getJSONObject("vo").toJavaObject(WorkCommentReply.class);
			JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
			QueryWrapper<WorkCommentReply> queryWrapper = new QueryWrapper<WorkCommentReply>();
			queryWrapper.eq(!StringUtils.isEmpty(workcomment.getStatus()), "STATUS", workcomment.getStatus());
			if(!StringUtils.isEmpty(pageVo)) {
				PageInfo page = pageVo.toJavaObject(PageInfo.class);
				PageInfo selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
			}else {
				List<WorkCommentReply> selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
