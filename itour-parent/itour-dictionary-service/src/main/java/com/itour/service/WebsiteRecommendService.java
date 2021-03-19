package com.itour.service;

import java.util.List;

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
import com.itour.model.dictionary.WebsiteRecommend;
import com.itour.persist.WebsiteRecommendMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-08-05
 */
@Service
public class WebsiteRecommendService extends ServiceImpl<WebsiteRecommendMapper, WebsiteRecommend> {
	/**
	 * 网站列表
	 * @param requestMessage
	 * @return
	 */
public ResponseMessage queryWebsiteList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		WebsiteRecommend websiteVo = jsonObject.getJSONObject("vo").toJavaObject(WebsiteRecommend.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		QueryWrapper<WebsiteRecommend> queryWrapper = new QueryWrapper<WebsiteRecommend>();
		queryWrapper.eq(!StringUtils.isEmpty(websiteVo.getType()), "TYPE", websiteVo.getType());
		queryWrapper.eq(!StringUtils.isEmpty(websiteVo.getFee()), "STATUS", websiteVo.getFee());
		queryWrapper.eq(!StringUtils.isEmpty(websiteVo.getProfession()), "PROFESSION", websiteVo.getProfession());
		queryWrapper.eq(!StringUtils.isEmpty(websiteVo.getJob()), "JOB", websiteVo.getJob());
		if(null!=pageVo) {
			Page page = pageVo.toJavaObject(Page.class);			
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
			responseMessage.setReturnResult(selectPage);
		}else {
			List selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 网站信息查询单条
 * @param requestMessage
 * @return
 */
public ResponseMessage getWebsite(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Integer id = jsonObject.getInteger("id");
		WebsiteRecommend selectById = this.baseMapper.selectById(id);
		responseMessage.setReturnResult(selectById);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 网站推荐修改
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage updateWebsite(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		WebsiteRecommend enity = jsonObject.getJSONObject("website").toJavaObject(WebsiteRecommend.class);
		this.baseMapper.updateById(enity);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
//删除
public ResponseMessage delWebSite(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		WebsiteRecommend entity = jsonObject.getJSONObject("website").toJavaObject(WebsiteRecommend.class);
		
		this.baseMapper.updateById(entity);
	} catch (Exception e) {
		// TODO: handle exception
	}
	return responseMessage;
	
}
}
