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
import com.itour.dictionary.persist.DictionaryMapper;
import com.itour.model.dictionary.Dictionary;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-08
 */
@Service
public class DictionaryService extends ServiceImpl<DictionaryMapper, Dictionary> {
public ResponseMessage getDictionaryList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Dictionary dictionaryVo = jsonObject.getJSONObject("vo").toJavaObject(Dictionary.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		QueryWrapper queryWrapper = new QueryWrapper<Dictionary>();
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
