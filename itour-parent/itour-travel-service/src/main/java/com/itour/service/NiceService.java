package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.travel.Nice;
import com.itour.persist.NiceMapper;
import com.itour.service.NiceService;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
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
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return response;
	
}
}
