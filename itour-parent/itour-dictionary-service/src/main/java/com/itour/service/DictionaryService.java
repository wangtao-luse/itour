package com.itour.service;

import java.util.List;
import java.util.Map;

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
import com.itour.constant.ConstantV;
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
	/**
	 * 字典表列表
	 * @param requestMessage
	 * @return
	 */
public ResponseMessage getDictionaryList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Dictionary dictionaryVo = jsonObject.getJSONObject("vo").toJavaObject(Dictionary.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<Dictionary>();
		queryWrapper.eq(!StringUtils.isEmpty(dictionaryVo.getCodeSet()), "CODE_SET", dictionaryVo.getCodeSet());
		queryWrapper.eq(!StringUtils.isEmpty(dictionaryVo.getStatus()), "STATUS", dictionaryVo.getStatus());
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
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 字典表查询
 * @param requestMessage
 * @return
 */
public ResponseMessage getDictData(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		String code = requestMessage.getBody().getContent().getString("setCode");
		QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<Dictionary>();
		queryWrapper.eq("STATUS", ConstantV.DICT_STATUS);
		queryWrapper.eq(!StringUtils.isEmpty(code), "CODE_SET", code);
		queryWrapper.select("CODE","CNAME");
		List<Map<String, Object>> selectMaps = this.baseMapper.selectMaps(queryWrapper );
		responseMessage.setReturnResult(selectMaps);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	
	return responseMessage;
}
/**
 * 字典查看单条
 * @param requestMessage
 * @return
 */
public ResponseMessage getDictionary(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Integer id = jsonObject.getInteger("id");
		Dictionary selectById = this.baseMapper.selectById(id);
		responseMessage.setReturnResult(selectById);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 字典修改
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage updateDictionary(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Dictionary enity = jsonObject.getJSONObject("dict").toJavaObject(Dictionary.class);
		this.baseMapper.updateById(enity);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
