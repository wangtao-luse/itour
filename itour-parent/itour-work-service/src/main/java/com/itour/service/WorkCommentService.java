package com.itour.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ConstantTravel;
import com.itour.exception.BaseException;
import com.itour.model.dto.PageInfo;
import com.itour.model.work.WorkComment;
import com.itour.model.work.WorkCommentReply;
import com.itour.model.work.vo.WorkCommentReplyVo;
import com.itour.model.work.vo.WorkCommentVo;
import com.itour.persist.WorkCommentMapper;
import com.itour.persist.WorkCommentReplyMapper;
import com.itour.util.DateUtil;
import com.itour.util.FastJsonUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@Service
public class WorkCommentService extends ServiceImpl<WorkCommentMapper, WorkComment> {
	@Autowired
	WorkCommentReplyMapper workCommentReplyMapper;
	/**
	 * 评论列表
	 * @param requestMessage
	 * @return
	 */
public ResponseMessage queryWorkCommentList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		//1.获取参数
		JSONObject jsonObject = requestMessage.getBody().getContent();
		WorkCommentVo vo = jsonObject.toJavaObject(WorkCommentVo.class);
		JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
		String getuId = requestMessage.getBody().getuId();
		vo.setUid(getuId);
		//2.获取对应文章评论		
		if(StringUtils.isEmpty(pageVo)) {//获取文章下的评论及回复
			//获取文章下的评论
			 List<WorkCommentVo> commentList = this.baseMapper.commentList(vo);
			//3.获取对应文章评论下的回复
			Map<String, Object> cList = getCommentList(commentList,getuId);
			responseMessage.setReturnResult(cList);
		}else {
			 PageInfo page = pageVo.toJavaObject(PageInfo.class);
			 List<WorkCommentVo> cList = this.baseMapper.commentList(page, vo);
			 if(cList.size()>0) {
				//3.获取对应文章评论下的回复
					Map<String, Object> commentList = getCommentList(cList,getuId);
					List<WorkCommentVo> resultList = FastJsonUtil.mapToList(commentList, WorkCommentVo.class);
					Page resultPage = page.setRecords(resultList);
					responseMessage.setReturnResult(resultPage);
					long total = page.getTotal();
					if(total>0) {
						Integer  replaysize =(Integer)commentList.get(ConstantTravel.TRAVEL_REPLYSIZE);
						if(!StringUtils.isEmpty(replaysize)) {
							responseMessage.add(ConstantTravel.TRAVEL_COMMENTSIZE, total+replaysize);
						}
					}else {
						responseMessage.add(ConstantTravel.TRAVEL_COMMENTSIZE, total);
					} 
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
public Map<String,Object> getCommentList(List<WorkCommentVo> commentList,String uid) {
	Map<String,Object> result = new HashMap<String, Object>();
	List<Long> collect = commentList.stream().map(WorkCommentVo::getId).collect(Collectors.toList());
	WorkCommentReplyVo vo = new WorkCommentReplyVo();
    vo.setIdList(collect);
    vo.setFromUid(uid);
	List<WorkCommentReplyVo> replyList = workCommentReplyMapper.queryCommentReplyList(vo);
	//4.组装评论下的回复信息
	for (WorkCommentVo vComment : commentList) {
		List<WorkCommentReplyVo> rList = new ArrayList<WorkCommentReplyVo>();
		for (WorkCommentReplyVo vReply : replyList) {
			Long id = vComment.getId();
			Long rid = vReply.getCommentId();
			if(id==rid) {
				rList.add(vReply);
			}
		}
		vComment.setCommentReplyList(rList);
	}
	result.put(ConstantTravel.TRAVEL_REPLYSIZE, replyList.size());
	result.put(Constant.COMMON_KEY_RESULT, commentList);
	return result;
}
/**
 * 添加评论
 * @param requestMessage
 * @return
 */
public ResponseMessage insertComment(RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		WorkComment commentVo = jsonObject.toJavaObject(WorkComment.class);
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
/**
 * 逻辑删除评论
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage delComment(RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		WorkComment commentVo = jsonObject.toJavaObject(WorkComment.class);
		commentVo.setStatus(Constant.COMMON_STATUS_DELETED);
		this.updateById(commentVo);
		QueryWrapper<WorkCommentReply> updateWrapper = new QueryWrapper<WorkCommentReply>();
		updateWrapper.eq("COMMENT_ID", commentVo.getId());
		WorkCommentReply entity = new WorkCommentReply();
		entity.setStatus(Constant.COMMON_STATUS_DELETED);
		this.workCommentReplyMapper.update(entity , updateWrapper);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
* 批量修改评论信息
* @param requestMessage
* @return
*/
@Transactional
public ResponseMessage updateWorkCommentBatch(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		List<WorkComment> clist = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(WorkComment.class);
		if(clist.size()>0) {
			this.updateBatchById(clist, clist.size());
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
