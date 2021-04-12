package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.TravelComment;
import com.itour.persist.TravelCommentMapper;
import com.itour.service.TravelCommentService;
import com.itour.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 旅行信息评论表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-04-09
 */
@Service
public class TravelCommentService extends ServiceImpl<TravelCommentMapper, TravelComment>   {

	public ResponseMessage queryTravelCommentList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseMessage getTravelComment(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseMessage updateTravelComment(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseMessage delelteTravelComment(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	public ResponseMessage insertComment(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelComment commentVo = jsonObject.getJSONObject("vo").toJavaObject(TravelComment.class);
			commentVo.setCtime(DateUtil.currentLongDate());
			commentVo.setStatus(Constant.COMMON_STATUS_CHECKING);
			this.baseMapper.insert(commentVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
    
}
