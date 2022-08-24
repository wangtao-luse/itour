package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.dictionary.SensitiveWord;
import com.itour.model.dto.PageInfo;
import com.itour.persist.SensitiveWordMapper;

import cn.hutool.core.util.StrUtil;

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
/**
 * 获取敏感字列表
 * @param requestMessage
 * @return
 */
public  ResponseMessage querySensitiveWordList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
		QueryWrapper queryWrapper = new QueryWrapper<SensitiveWord>();
		if(!StrUtil.isEmptyIfStr(pageVo)) {
			PageInfo page = pageVo.toJavaObject(PageInfo.class);
			
			PageInfo selectPage = this.baseMapper.selectPage(page, queryWrapper);
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