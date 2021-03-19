package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.Collect;
import com.itour.persist.CollectMapper;
import com.itour.service.CollectService;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 旅行博客收藏表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-09-10
 */
@Service
public class CollectService extends ServiceImpl<CollectMapper, Collect> {
	/**
	 * 收藏记录列表
	 * @param requestMessage
	 * @return
	 */
public ResponseMessage queryCollectList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Collect collectVo = jsonObject.getJSONObject("vo").toJavaObject(Collect.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		QueryWrapper<Collect> queryWrapper = new QueryWrapper<Collect>();
		queryWrapper.eq(!StringUtils.isEmpty(collectVo.getUid()), "UID", collectVo.getUid());
		if(null!=pageVo) {
			Page page = pageVo.toJavaObject(Page.class);
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
			responseMessage.setReturnResult(selectPage);
		}else {
			List<Collect> selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
	
}

}
