package com.itour.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.dictionary.persist.ViewDDictionaryMapper;
import com.itour.model.dictionary.dto.ViewDDictionary;

import java.util.List;

import org.springframework.stereotype.Service;

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
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
