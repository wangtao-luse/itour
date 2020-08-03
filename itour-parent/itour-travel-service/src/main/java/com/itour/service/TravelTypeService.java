package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.travel.TravelType;
import com.itour.persist.TravelTypeMapper;

/**
 * <p>
 * 旅行博客类型表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class TravelTypeService extends ServiceImpl<TravelTypeMapper, TravelType>  {
	/**
	 * 旅行信息类型类型列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryTravelTypeList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelType travelTypeVo = jsonObject.getJSONObject("vo").toJavaObject(TravelType.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<TravelType> queryWrapper = new QueryWrapper<TravelType>();
			queryWrapper.orderByAsc("ID");
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
				
			}else {
				List<TravelType> selectList = this.baseMapper.selectList(queryWrapper);
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
	 * 获取旅行信息类型
	 * @param requestMessage
	 * @return
	 */
	public  ResponseMessage getTravelType(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelType travleTypeVo = jsonObject.toJavaObject(TravelType.class);
			QueryWrapper<TravelType> queryWrapper = new QueryWrapper<TravelType>();
			queryWrapper.eq(null!=travleTypeVo.getId(),"ID", travleTypeVo.getId());
			TravelType selectById = this.baseMapper.selectOne(queryWrapper );
			responseMessage.setReturnResult(selectById);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 修改旅行信息类型
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage updateTravelType(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelType travelType = jsonObject.getJSONObject("vo").toJavaObject(TravelType.class);
			this.updateById(travelType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 删除旅行信息类型
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage delTravelType(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelType travelType = jsonObject.toJavaObject(TravelType.class);
			this.updateById(travelType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 新增旅行信息类型
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage insertTravelType(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelType travelType = jsonObject.toJavaObject(TravelType.class);
			this.baseMapper.insert(travelType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
}
