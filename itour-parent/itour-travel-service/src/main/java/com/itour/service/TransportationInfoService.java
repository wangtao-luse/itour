package com.itour.service;

import java.util.List;

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
import com.itour.model.travel.TransportationInfo;
import com.itour.persist.TransportationInfoMapper;

/**
 * <p>
 * 交通信息表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class TransportationInfoService extends ServiceImpl<TransportationInfoMapper, TransportationInfo>   {
	/**
	 * 交通信息列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryTransportationInfoList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TransportationInfo infoVo = jsonObject.getJSONObject("vo").toJavaObject(TransportationInfo.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<TransportationInfo> queryWrapper = new QueryWrapper<TransportationInfo>();
			queryWrapper.eq(!StringUtils.isEmpty(infoVo.getCode()), "CODE", infoVo.getCode());
			queryWrapper.like(!StringUtils.isEmpty(infoVo.getCity()), "CITY", infoVo.getCity());
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
				
			}else {
				List<TransportationInfo> selectList = this.baseMapper.selectList(queryWrapper);
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
	 * 交通信息单条
	 * @param requestMessage
	 * @return
	 */
	public  ResponseMessage getTransportationInfo(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TransportationInfo infoVo = jsonObject.toJavaObject(TransportationInfo.class);
			QueryWrapper<TransportationInfo> queryWrapper = new QueryWrapper<TransportationInfo>();
			queryWrapper.eq(null!=infoVo.getId(), "ID", infoVo.getId());
			TransportationInfo selectOne = this.baseMapper.selectOne(queryWrapper);
			responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 修改交通信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage updateTransportationInfo(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TransportationInfo info = jsonObject.getJSONObject("vo").toJavaObject(TransportationInfo.class);
			this.updateById(info);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 删除交通信息
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage delelteTransportationInfo(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Integer id = jsonObject.getInteger("id");
			this.baseMapper.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
}
