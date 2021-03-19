package com.itour.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.dictionary.dto.ViewDDictionary;
import com.itour.persist.ViewDDictionaryMapper;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-09
 */
@Service
public class ViewDDictionaryService extends ServiceImpl<ViewDDictionaryMapper, ViewDDictionary>   {
	
	public ResponseMessage getViewDictionaryList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ViewDDictionary dictionaryVo = jsonObject.getJSONObject("vo").toJavaObject(ViewDDictionary.class);
			JSONObject pageVo = jsonObject.getJSONObject("page");
			QueryWrapper<ViewDDictionary> queryWrapper = new QueryWrapper<ViewDDictionary>();
			queryWrapper.eq(null!=dictionaryVo.getId(), "ID", dictionaryVo.getId());
			queryWrapper.eq(!StringUtils.isEmpty(dictionaryVo.getCodeSet()), "CODE_SET", dictionaryVo.getCodeSet());
			queryWrapper.eq(!StringUtils.isEmpty(dictionaryVo.getCode()), "CODE", dictionaryVo.getCode());
			queryWrapper.eq(!StringUtils.isEmpty(dictionaryVo.getCname()), "CNAME", dictionaryVo.getCname());
			if(null!=pageVo) {
				Page page = pageVo.toJavaObject(Page.class);			
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper );
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
}
