package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.quartz.QrtzTriggers;
import com.itour.persist.QrtzTriggersMapper;
import com.itour.quartz.api.QuartzApi;
import com.itour.service.QrtzTriggersService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-08-27
 */
@Service
public class QrtzTriggersService extends ServiceImpl<QrtzTriggersMapper, QrtzTriggers> {
	/**
	 * 定时任务列表
	 */
	public ResponseMessage queryTriggersList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String jsonString = jsonObject.toJSONString();
			Map map = (Map)JSON.parse(jsonString);
			List<Map<String, Object>> queryTriggersList = this.baseMapper.queryTriggersList(map);
			responseMessage.setReturnResult(queryTriggersList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
	}
}
