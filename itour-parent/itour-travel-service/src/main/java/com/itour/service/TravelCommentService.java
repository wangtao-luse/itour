package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.TravelComment;
import com.itour.persist.TravelCommentMapper;

/**
 * <p>
 * 旅行信息评论表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-29
 */
@Service
public class TravelCommentService extends ServiceImpl<TravelCommentMapper, TravelComment> {
	/**
	 * 评论列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryTravelCommentList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelComment travelCommentVo = jsonObject.getJSONObject("vo").toJavaObject(TravelComment.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<TravelComment> queryWrapper = new QueryWrapper<TravelComment>();
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
				
			}else {
				List<TravelComment> selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
}
	 /**
	 * 评论单条查询
	 * @param requestMessage
	 * @return
	 */
	public  ResponseMessage getTravelComment(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelComment travelCommentVo = jsonObject.toJavaObject(TravelComment.class);
			QueryWrapper<TravelComment> queryWrapper = new QueryWrapper<TravelComment>();
			queryWrapper.eq(null!=travelCommentVo.getId(), "ID", travelCommentVo.getId());
			TravelComment selectOne = this.baseMapper.selectOne(queryWrapper);
			responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 修改评论信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage updateTravelComment(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelComment comment = jsonObject.getJSONObject("vo").toJavaObject(TravelComment.class);
			this.updateById(comment);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 删除评论信息
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage delelteTravelComment(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Integer id = jsonObject.getInteger("id");
			this.baseMapper.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
}
