package com.itour.service;

import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.RedisKey;
import com.itour.exception.BaseException;
import com.itour.model.travel.CommentReplyNice;
import com.itour.persist.CommentReplyNiceMapper;
import com.itour.service.CommentReplyNiceService;
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
 * @since 2021-04-20
 */
@Service
public class CommentReplyNiceService extends ServiceImpl<CommentReplyNiceMapper, CommentReplyNice> {
	@Autowired
	RedisManager redisManager;
	public ResponseMessage getCommentReplyNice(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			CommentReplyNice niceVo = jsonObject.toJavaObject(CommentReplyNice.class);
			QueryWrapper<CommentReplyNice> queryWrapper = new QueryWrapper<CommentReplyNice>();
			queryWrapper.eq(null!=niceVo.getRid(), "RID", niceVo.getRid());
			queryWrapper.eq(null!=niceVo.getId(), "ID", niceVo.getId());
			queryWrapper.eq(!StringUtils.isEmpty(niceVo.getUid()), "UID", niceVo.getUid());
			CommentReplyNice selectOne = this.baseMapper.selectOne(queryWrapper);
			responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	public ResponseMessage commentReplyNiceSub(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			CommentReplyNice commentNice = jsonObject.toJavaObject(CommentReplyNice.class);
			commentNice.setCreatedate(DateUtil.currentLongDate());
			//key 不存在直接放入缓存
			String uid = commentNice.getUid();
			Long cid = commentNice.getRid();
			HashMap<String, Object> m = new HashMap<String, Object>();
			 m.put(uid+"::"+cid, commentNice);
			 redisManager.hmset(RedisKey.KEY_ITOUR_COMMENTREPLY_NICE, m);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	/**
	 * 批量修改或保存评论回复点赞信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage saveOrUpdateBatchCommentReplyNice(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			List<CommentReplyNice> javaList = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(CommentReplyNice.class);
			this.saveOrUpdateBatch(javaList, javaList.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
		
	}
	/**
	 * 统计评论回复点赞数
	 * @param requestMessage
	 * @return
	 */	
	public ResponseMessage countCommentReplyNiceList(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String rids = jsonObject.getString("rids");
			List<Map<String, Object>> countNice = this.baseMapper.countCommentReplyNice(rids);
			response.setReturnResult(countNice);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
}
