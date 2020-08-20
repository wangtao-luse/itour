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
import com.itour.model.travel.TransportationType;
import com.itour.persist.TransportationTypeMapper;

/**
 * <p>
 * 交通类型表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class TransportationTypeService extends ServiceImpl<TransportationTypeMapper, TransportationType>  {
	/**
	 *  交通信息类型列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryTransportationTypeList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TransportationType infoVo = jsonObject.getJSONObject("vo").toJavaObject(TransportationType.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<TransportationType> queryWrapper = new QueryWrapper<TransportationType>();
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
				
			}else {
				List<TransportationType> selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
}
	 /**
	 * 交通信息类型单条
	 * @param requestMessage
	 * @return
	 */
	public  ResponseMessage getTransportationType(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TransportationType infoVo = jsonObject.toJavaObject(TransportationType.class);
			QueryWrapper<TransportationType> queryWrapper = new QueryWrapper<TransportationType>();
			queryWrapper.eq(null!=infoVo.getId(), "ID", infoVo.getId());
			TransportationType selectOne = this.baseMapper.selectOne(queryWrapper);
			responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	

	
}
