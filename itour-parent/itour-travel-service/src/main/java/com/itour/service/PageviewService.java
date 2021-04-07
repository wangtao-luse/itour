package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.Pageview;
import com.itour.persist.PageviewMapper;

/**
 * <p>
 * 旅行信息浏览数表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-09-14
 */
@Service
public class PageviewService extends ServiceImpl<PageviewMapper, Pageview> {
//浏览量列表
public ResponseMessage queryPageviewList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Pageview pageviewVo= jsonObject.getJSONObject("vo").toJavaObject(Pageview.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		QueryWrapper<Pageview> queryWrapper = new QueryWrapper<Pageview>();
		if(null!=pageVo) {
			Page page = pageVo.toJavaObject(Page.class);
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
			responseMessage.setReturnResult(selectPage);
		}else {
			List<Pageview> selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return responseMessage;
}
//浏览量批量新增或修改
public ResponseMessage saveOrUpdateBatchPageview(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		List<Pageview> pageviewList = jsonObject.getJSONArray("arr").toJavaList(Pageview.class);
		boolean saveOrUpdateBatch = this.saveOrUpdateBatch(pageviewList);
		if(saveOrUpdateBatch) {
			return responseMessage;
		}else {
			throw new BaseException(Constant.FAILED_MESSAGE);
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
}
}
