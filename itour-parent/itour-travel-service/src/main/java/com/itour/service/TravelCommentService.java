package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.model.travel.TravelComment;
import com.itour.persist.TravelCommentMapper;
import com.itour.service.TravelCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
