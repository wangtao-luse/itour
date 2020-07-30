package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.travel.TravelComment;
import com.itour.persist.TravelCommentMapper;
import com.itour.service.TravelCommentService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public ResponseMessage queryLocationList(RequestMessage requestMessage) {
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
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
}
	 /**
	 * 评论单条查询
	 * @param requestMessage
	 * @return
	 */
	public  ResponseMessage getLocation(RequestMessage requestMessage) {
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
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 修改评论信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage updateLocation(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelComment location = jsonObject.getJSONObject("vo").toJavaObject(TravelComment.class);
			this.updateById(location);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 删除评论信息
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage delelteLocation(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Integer id = jsonObject.getInteger("id");
			this.baseMapper.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
}
