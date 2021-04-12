package com.itour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.WeekInfo;
import com.itour.persist.WeekInfoMapper;
import com.netflix.discovery.converters.Auto;

/**
 * <p>
 * 周末攻略内容表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-02-12
 */
@Service
public class WeekInfoService extends ServiceImpl<WeekInfoMapper, WeekInfo> {
	@Autowired
	WeekInfoMapper weekInfoMapper;
public ResponseMessage selecWeekInfoOne(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
	JSONObject jsonObject = requestMessage.getBody().getContent();
	Long tid = jsonObject.getLong("tid");
	QueryWrapper<WeekInfo> queryWrapper = new QueryWrapper<WeekInfo>();
	queryWrapper.eq(null!=tid, "tid", tid);
	WeekInfo selectOne = this.weekInfoMapper.selectOne(queryWrapper );
	responseMessage.setReturnResult(selectOne);
	} catch (Exception e) {
		// TODO: handle exception
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
