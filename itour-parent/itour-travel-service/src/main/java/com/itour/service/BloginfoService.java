package com.itour.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.itour.model.travel.Bloginfo;
import com.itour.persist.BloginfoMapper;

/**
 * <p>
 * 旅行博客信息表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-06
 */
@Service
public class BloginfoService extends ServiceImpl<BloginfoMapper, Bloginfo> {
	@Autowired
	BloginfoMapper bloginfoMapper;
/**
 * 旅行博客列表
 * @param requestMessage
 * @return
 */
public ResponseMessage queryblogInfoList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Bloginfo bloginfo = jsonObject.getJSONObject("vo").toJavaObject(Bloginfo.class);
		JSONObject pageJson = jsonObject.getJSONObject("page");
		QueryWrapper<Bloginfo> queryWrapper = new QueryWrapper<Bloginfo>();
		queryWrapper.like(!StringUtils.isEmpty(bloginfo.getBlogtitle()), "BLOGTITLE", bloginfo.getBlogtitle());
		queryWrapper.eq(null!=bloginfo.getBlogcolumn(), "BLOGCOLUMN", bloginfo.getBlogcolumn());
		queryWrapper.orderByDesc("ID");
		if(pageJson!=null) {
			Page page = pageJson.toJavaObject(Page.class);
			Page result = this.baseMapper.selectPage(page, queryWrapper);
			responseMessage.setReturnResult(result);
		}
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 旅行博客新增
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage insertblogInfo(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Bloginfo bloginfo = jsonObject.getJSONObject("vo").toJavaObject(Bloginfo.class);
		this.baseMapper.insert(bloginfo);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 旅行博客新增
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage updateblogInfo(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Bloginfo bloginfo = jsonObject.getJSONObject("vo").toJavaObject(Bloginfo.class);
		this.baseMapper.updateById(bloginfo);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 旅行博客删除
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage deleteblogInfo(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		Long id = requestMessage.getBody().getContent().getLong("id");
		this.baseMapper.deleteById(id);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
