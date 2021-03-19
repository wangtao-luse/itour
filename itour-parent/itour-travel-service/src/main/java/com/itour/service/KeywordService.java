package com.itour.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.Keyword;
import com.itour.persist.KeywordMapper;


/**
 * <p>
 * 旅行博客关键字表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class KeywordService extends ServiceImpl<KeywordMapper, Keyword>  {
//列表
public ResponseMessage queryKeywordList(RequestMessage requestMessage) {
	ResponseMessage response = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Keyword keywordVo = jsonObject.toJavaObject(Keyword.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		QueryWrapper<Keyword> queryWrapper = new QueryWrapper<Keyword>();
		queryWrapper.orderByDesc("ID");
		if(pageVo!=null) {
			Page page = pageVo.toJavaObject(Page.class);
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper );
			response.setReturnResult(selectPage);
		}else {
			this.baseMapper.selectList(queryWrapper);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return response;
}
//获取单条
public ResponseMessage selectKeywordById(RequestMessage requestMessage) {
	ResponseMessage response = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Integer id = jsonObject.getInteger("id");
	    Keyword selectById = this.baseMapper.selectById(id);
	    response.setReturnResult(selectById);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return response;
}
//修改
public ResponseMessage updateKeyword(RequestMessage requestMessage) {
	ResponseMessage response = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Keyword keywordVo = jsonObject.toJavaObject(Keyword.class);
	   this.baseMapper.updateById(keywordVo);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return response;
}
//删除
public ResponseMessage delKeyword(RequestMessage requestMessage) {
	ResponseMessage response = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Integer id = jsonObject.getInteger("id");
	   this.baseMapper.deleteById(id);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return response;
}
}
