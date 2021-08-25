package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.CommentReplyNice;
import com.itour.model.work.ReplyLike;
import com.itour.persist.ReplyLikeMapper;
import com.itour.service.ReplyLikeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@Service
public class ReplyLikeService extends ServiceImpl<ReplyLikeMapper, ReplyLike> {
	public ResponseMessage getReplyLike(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ReplyLike niceVo = jsonObject.toJavaObject(ReplyLike.class);
			QueryWrapper<ReplyLike> queryWrapper = new QueryWrapper<ReplyLike>();
			queryWrapper.eq(null!=niceVo.getRid(), "RID", niceVo.getRid());
			queryWrapper.eq(null!=niceVo.getId(), "ID", niceVo.getId());
			queryWrapper.eq(!StringUtils.isEmpty(niceVo.getUid()), "UID", niceVo.getUid());
			ReplyLike selectOne = this.baseMapper.selectOne(queryWrapper);
			responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
