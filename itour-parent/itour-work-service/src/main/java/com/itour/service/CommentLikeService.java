package com.itour.service;

import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.RedisKey;
import com.itour.exception.BaseException;
import com.itour.model.travel.CommentNice;
import com.itour.model.work.CommentLike;
import com.itour.persist.CommentLikeMapper;
import com.itour.service.CommentLikeService;
import com.itour.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@Service
public class CommentLikeService extends ServiceImpl<CommentLikeMapper, CommentLike> {
	@Autowired
	RedisManager redisManager;
	//评论点赞提交
	public ResponseMessage commentLikeSub(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			CommentLike commentNice = jsonObject.toJavaObject(CommentLike.class);
			commentNice.setCreatedate(DateUtil.currentLongDate());
			//key 不存在直接放入缓存
			String uid = commentNice.getUid();
			Long cid = commentNice.getCid();
			HashMap<String, Object> m = new HashMap<String, Object>();
			 m.put(uid+"::"+cid, commentNice);
			 redisManager.hmSset(RedisKey.KEY_ITOUR_COMMENT_NICE, m);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	//日志评论点赞单条
	public ResponseMessage getCommentLike(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			CommentLike likeVo = jsonObject.toJavaObject(CommentLike.class);
			QueryWrapper<CommentLike> queryWrapper = new QueryWrapper<CommentLike>();
			queryWrapper.eq(null!=likeVo.getCid(), "CID", likeVo.getCid());
			queryWrapper.eq(null!=likeVo.getId(), "ID", likeVo.getId());
			queryWrapper.eq(!StringUtils.isEmpty(likeVo.getUid()), "UID", likeVo.getUid());
			CommentLike selectOne = this.baseMapper.selectOne(queryWrapper);
			responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 批量修改或保存点赞信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage saveOrUpdateBatchCommentLike(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			List<CommentLike> javaList = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(CommentLike.class);
			this.saveOrUpdateBatch(javaList, javaList.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
		
	}
	/**
	 * 统计评论点赞信息
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage countCommentNiceList(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String cids = jsonObject.getString("cids");
			List<Map<String, Object>> countNice = this.baseMapper.countCommentLike(cids);
			response.setReturnResult(countNice);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
}
