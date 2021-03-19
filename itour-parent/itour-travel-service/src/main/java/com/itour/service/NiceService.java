package com.itour.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.itour.model.travel.Nice;
import com.itour.persist.NiceMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-08-03
 */
@Service
public class NiceService extends ServiceImpl<NiceMapper, Nice> {
//点赞列表
public ResponseMessage queryNiceList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Nice niceVo = jsonObject.toJavaObject(Nice.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		QueryWrapper queryWrapper = new QueryWrapper<Nice>();
		if(pageVo!=null) {
			Page page = pageVo.toJavaObject(Page.class);
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper );
			responseMessage.setReturnResult(selectPage);
		}else {
			List<Nice> selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	
	return responseMessage;
	
}
public ResponseMessage getNice(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Nice niceVo = jsonObject.toJavaObject(Nice.class);
		QueryWrapper<Nice> queryWrapper = new QueryWrapper<Nice>();
		queryWrapper.eq(null!=niceVo.getTid(), "TID", niceVo.getTid());
		queryWrapper.eq(null!=niceVo.getId(), "ID", niceVo.getId());
		queryWrapper.eq(!StringUtils.isEmpty(niceVo.getUid()), "UID", niceVo.getUid());
		Nice selectOne = this.baseMapper.selectOne(queryWrapper);
		responseMessage.setReturnResult(selectOne);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
@Transactional
public ResponseMessage insertNice(RequestMessage requestMessage) {
	ResponseMessage response = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Nice niceVo = jsonObject.toJavaObject(Nice.class);
		this.baseMapper.insert(niceVo);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return response;
	
}
@Transactional
public ResponseMessage updateNice(RequestMessage requestMessage) {
	ResponseMessage response = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Nice niceVo = jsonObject.toJavaObject(Nice.class);
		this.baseMapper.updateById(niceVo);
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
public ResponseMessage saveOrUpdateBatchNice(RequestMessage requestMessage) {
	ResponseMessage response = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		List<Nice> javaList = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(Nice.class);
		this.saveOrUpdateBatch(javaList, javaList.size());
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return response;
	
}
/**
 * 统计指定文章点赞量
 * @param requestMessage
 * @return
 */
public ResponseMessage countNiceList(RequestMessage requestMessage) {
	ResponseMessage response = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		String tids = jsonObject.getString("tids");
		List<Map<String, Object>> countNice = this.baseMapper.countNice(tids);
		response.setReturnResult(countNice);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return response;
}
}
