package com.itour.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ConstantTravel;
import com.itour.entity.PageInfo;
import com.itour.exception.BaseException;
import com.itour.model.travel.dto.ViewCommentReply;
import com.itour.model.travel.dto.ViewTravelComment;
import com.itour.model.vo.Orderby;
import com.itour.persist.ViewCommentReplyMapper;
import com.itour.persist.ViewTravelCommentMapper;
import com.itour.util.FastJsonUtil;


/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-04-12
 */
@Service
public class ViewTravelCommentService extends ServiceImpl<ViewTravelCommentMapper, ViewTravelComment> {
	@Autowired
	private ViewCommentReplyMapper viewCommentReplyMapper;
	/**
	 * 评论列表
	 * @param requestMessage
	 * @return
	 */
public ResponseMessage queryCommentList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		//1.获取参数
		JSONObject jsonObject = requestMessage.getBody().getContent();
		ViewTravelComment comment = jsonObject.toJavaObject(ViewTravelComment.class);
		JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
		//2.获取对应文章评论
		QueryWrapper<ViewTravelComment> queryWrapper = new QueryWrapper<ViewTravelComment>();
		queryWrapper.eq(!StringUtils.isEmpty(comment.getTid()), "TID", comment.getTid());
		String getuId = requestMessage.getBody().getuId();
		queryWrapper.eq("STATUS", Constant.COMMON_STATUS_CHECKED);
		List<Orderby> orderbyList = comment.getOrderbyList();
		if(!StringUtils.isEmpty(getuId)) {
			queryWrapper.or();
			queryWrapper.eq("STATUS", Constant.COMMON_STATUS_CHECKING);
			queryWrapper.eq("UID", getuId);
			queryWrapper.eq("TID", comment.getTid());
		}
		if(null!=orderbyList&&orderbyList.size()>0) {
			for (Orderby orderby : orderbyList) {
				String sortRule = orderby.getSortRule();
				String sortType = orderby.getSortType();
				if("1".equals(sortRule)){
					queryWrapper.orderByAsc(sortType);
				}else{
					queryWrapper.orderByDesc(sortType);
				} 
			}
		}
		if(StringUtils.isEmpty(pageVo)) {//获取文章下的评论及回复
			//获取文章下的评论
			List<ViewTravelComment> commentList = this.baseMapper.selectList(queryWrapper);
			//3.获取对应文章评论下的回复
			Map<String, Object> cList = getCommentList(commentList,getuId);
			responseMessage.setReturnResult(cList);
		}else {
			PageInfo page = pageVo.toJavaObject(PageInfo.class);
			PageInfo selectPage = this.baseMapper.selectPage(page, queryWrapper);
			//3.获取对应文章评论下的回复
			Map<String, Object> commentList = getCommentList(selectPage.getRecords(),getuId);
			List<ViewTravelComment> resultList = FastJsonUtil.mapToList(commentList, ViewTravelComment.class);
			Page resultPage = selectPage.setRecords(resultList);
			responseMessage.setReturnResult(resultPage);
			long total = selectPage.getTotal();
			if(total>0) {
				Integer  replaysize =(Integer)commentList.get(ConstantTravel.TRAVEL_REPLYSIZE);
				if(!StringUtils.isEmpty(replaysize)) {
					responseMessage.add(ConstantTravel.TRAVEL_COMMENTSIZE, total+replaysize);
				}
			}else {
				responseMessage.add(ConstantTravel.TRAVEL_COMMENTSIZE, total);
			}
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 组装评论回复
 * @param commentList
 * @param uid
 * @return
 */
public Map<String,Object> getCommentList(List<ViewTravelComment> commentList,String uid) {
	Map<String,Object> result = new HashMap<String, Object>();
	String collect = commentList.stream().map(ViewTravelComment::getId).map(String::valueOf).collect(Collectors.joining(","));
	QueryWrapper<ViewCommentReply> wrapper = new QueryWrapper<ViewCommentReply>();
	wrapper.in(!StringUtils.isEmpty(collect),"COMMENT_ID", collect.split(","));	
	wrapper.eq("STATUS", Constant.COMMON_STATUS_CHECKED);
	if(!StringUtils.isEmpty(uid)) {
		wrapper.or();
		wrapper.eq("STATUS", Constant.COMMON_STATUS_CHECKING);
		wrapper.eq("FROM_UID", uid);
		wrapper.in(!StringUtils.isEmpty(collect),"COMMENT_ID", collect.split(","));
	}
	wrapper.orderByDesc("RTIME");
	List<ViewCommentReply> replyList = viewCommentReplyMapper.selectList(wrapper);		
	//4.组装评论下的回复信息
	for (ViewTravelComment vComment : commentList) {
		List<ViewCommentReply> rList = new ArrayList<ViewCommentReply>();
		for (ViewCommentReply vReply : replyList) {
			Long id = vComment.getId();
			Long rid = vReply.getCommentId();
			if(id==rid) {
				rList.add(vReply);
			}
		}
		vComment.setvCommentReplyList(rList);
	}
	result.put(ConstantTravel.TRAVEL_REPLYSIZE, replyList.size());
	result.put(Constant.COMMON_KEY_RESULT, commentList);
	return result;
}
}
