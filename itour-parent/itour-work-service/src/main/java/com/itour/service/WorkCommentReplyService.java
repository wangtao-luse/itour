package com.itour.service;

import java.util.HashMap;
import java.util.List;

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
import com.itour.help.DateHelper;
import com.itour.help.StringHelper;
import com.itour.model.dto.PageInfo;
import com.itour.model.work.ReplyLike;
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
	@Autowired
	RedisManager redisManager;
	
	/**
	* 批量修改评论信息
	* @param requestMessage
	* @return
	*/
	@Transactional
	public ResponseMessage updateWorkCommentReplyBatch(RequestMessage requestMessage) {
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
			//1.获取参数
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkCommentReply workcomment = jsonObject.getJSONObject("vo").toJavaObject(WorkCommentReply.class);
			JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
			//2.构建SQL
			QueryWrapper<WorkCommentReply> queryWrapper = new QueryWrapper<WorkCommentReply>();
			queryWrapper.eq(!StringHelper.isEmpty(workcomment.getStatus()), "STATUS", workcomment.getStatus());
		    //3.执行SQL，返回结果
			if(!StringHelper.isEmpty(pageVo)) {
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
	/**
	 * 添加评论回复
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage insertWorkCommentReply(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkCommentReply commentReply = jsonObject.toJavaObject(WorkCommentReply.class);
			commentReply.setRtime(DateHelper.currentLongDate());
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
	public ResponseMessage delWorkCommentReply(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkCommentReply commentReplyVo = jsonObject.toJavaObject(WorkCommentReply.class);
			commentReplyVo.setStatus(Constant.COMMON_STATUS_DELETED);
			this.updateById(commentReplyVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	public ResponseMessage workCommentReplyLikeSub(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ReplyLike commentNice = jsonObject.toJavaObject(ReplyLike.class);
			commentNice.setCreatedate(DateHelper.currentLongDate());
			//key 不存在直接放入缓存
			String uid = commentNice.getUid();
			Long cid = commentNice.getRid();
			HashMap<String, Object> m = new HashMap<String, Object>();
			 m.put(uid+"::"+cid, commentNice);
			 redisManager.hmset(RedisKey.KEY_WORK_COMMENTREPLY_NICE, m);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
}
