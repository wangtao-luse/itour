package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.entity.PageInfo;
import com.itour.exception.BaseException;
import com.itour.model.travel.dto.ViewTravelColumn;
import com.itour.persist.ViewTravelColumnMapper;
import com.itour.service.ViewTravelColumnService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-05-06
 */
@Service
public class ViewTravelColumnService extends ServiceImpl<ViewTravelColumnMapper, ViewTravelColumn> {
public ResponseMessage queryViewTravelColumnList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
		QueryWrapper queryWrapper = new QueryWrapper<ViewTravelColumn>();
		if(!StringUtils.isEmpty(pageVo)) {
			PageInfo page = pageVo.toJavaObject(PageInfo.class);			
			this.baseMapper.selectPage(page, queryWrapper );
			responseMessage.setReturnResult(page);
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
}
