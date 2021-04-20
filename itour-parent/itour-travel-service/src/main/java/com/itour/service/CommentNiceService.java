package com.itour.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.RedisKey;
import com.itour.exception.BaseException;
import com.itour.model.travel.CommentNice;
import com.itour.model.travel.Nice;
import com.itour.persist.CommentNiceMapper;
import com.itour.util.DateUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-04-20
 */
@Service
public class CommentNiceService extends ServiceImpl<CommentNiceMapper, CommentNice>  {
	@Autowired
	RedisManager redisManager;
	public ResponseMessage getCommentNice(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			CommentNice niceVo = jsonObject.toJavaObject(CommentNice.class);
			QueryWrapper<CommentNice> queryWrapper = new QueryWrapper<CommentNice>();
			queryWrapper.eq(null!=niceVo.getCid(), "CID", niceVo.getCid());
			queryWrapper.eq(null!=niceVo.getId(), "ID", niceVo.getId());
			queryWrapper.eq(!StringUtils.isEmpty(niceVo.getUid()), "UID", niceVo.getUid());
			CommentNice selectOne = this.baseMapper.selectOne(queryWrapper);
			responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	public ResponseMessage commentNiceSub(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			CommentNice commentNice = jsonObject.toJavaObject(CommentNice.class);
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
	/**
	 * 批量修改或保存点赞信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage saveOrUpdateBatchCommentNice(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			List<CommentNice> javaList = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(CommentNice.class);
			this.saveOrUpdateBatch(javaList, javaList.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
		
	}
	public ResponseMessage countCommentNiceList(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String cids = jsonObject.getString("cids");
			List<Map<String, Object>> countNice = this.baseMapper.countCommentNice(cids);
			response.setReturnResult(countNice);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
}
