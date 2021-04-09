package com.itour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public ResponseMessage selecWeekInfotById(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		Long id = requestMessage.getBody().getContent().getLong("id");
		WeekInfo selectById = this.weekInfoMapper.selectById(id);
		responseMessage.setReturnResult(selectById);
	} catch (Exception e) {
		// TODO: handle exception
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
