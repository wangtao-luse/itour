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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
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
}
