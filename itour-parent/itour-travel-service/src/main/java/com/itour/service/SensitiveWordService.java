package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.entity.PageInfo1;
import com.itour.exception.BaseException;
import com.itour.model.travel.SensitiveWord;
import com.itour.persist.SensitiveWordMapper;
import com.itour.service.SensitiveWordService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 敏感字表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-04-21
 */
@Service
public class SensitiveWordService extends ServiceImpl<SensitiveWordMapper, SensitiveWord> {
public  ResponseMessage querySensitiveWordList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
		QueryWrapper queryWrapper = new QueryWrapper<SensitiveWord>();
		if(!StringUtils.isEmpty(pageVo)) {
			PageInfo1 page = pageVo.toJavaObject(PageInfo1.class);
			
			PageInfo1 selectPage = this.baseMapper.selectPage(page, queryWrapper);
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
