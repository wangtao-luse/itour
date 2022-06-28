package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.work.WorkIpInfo;
import com.itour.persist.WorkIpInfoMapper;
import com.itour.service.WorkIpInfoService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 日志独立IP访问记录 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2022-06-28
 */
@Service
public class WorkIpInfoService extends ServiceImpl<WorkIpInfoMapper, WorkIpInfo> {
	@Transactional
public ResponseMessage saveOrupdateWorkIpInfo(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		List<WorkIpInfo> entityList = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(WorkIpInfo.class);
		if(entityList.size()>0) {
			this.saveOrUpdateBatch(entityList, entityList.size());
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
	
}
public ResponseMessage getIPCount(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Long tid = jsonObject.getLong("tid");
		QueryWrapper<WorkIpInfo> queryWrapper = new QueryWrapper<WorkIpInfo>();
		queryWrapper.eq("TID", tid);
		Integer selectCount = this.baseMapper.selectCount(queryWrapper );
	} catch (Exception e) {
		// TODO: handle exception
	}
	return responseMessage;
}
}
