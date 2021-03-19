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
import com.itour.exception.BaseException;
import com.itour.model.travel.Location;
import com.itour.persist.LocationMapper;


/**
 * <p>
 * 城市信息表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class LocationService extends ServiceImpl<LocationMapper, Location>  {
	/**
	 * 城市列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryLocationList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Location locationVo = jsonObject.getJSONObject("vo").toJavaObject(Location.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<Location> queryWrapper = new QueryWrapper<Location>();
			queryWrapper.eq(!StringUtils.isEmpty(locationVo.getCode()), "CODE", locationVo.getCode());
			queryWrapper.like(!StringUtils.isEmpty(locationVo.getCity()), "CITY", locationVo.getCity());
			queryWrapper.orderByDesc("PUBLISHTIME");
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
				
			}else {
				List<Location> selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
}
	 /**
	 * 城市信息单条
	 * @param requestMessage
	 * @return
	 */
	public  ResponseMessage getLocation(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Location locationVo = jsonObject.toJavaObject(Location.class);
			QueryWrapper<Location> queryWrapper = new QueryWrapper<Location>();
			queryWrapper.eq(null!=locationVo.getId(), "ID", locationVo.getId());
			queryWrapper.eq(!StringUtils.isEmpty(locationVo.getCode()), "CODE", locationVo.getCode());
			Location selectOne = this.baseMapper.selectOne(queryWrapper);
			responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 修改城市信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage updateLocation(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Location location = jsonObject.getJSONObject("vo").toJavaObject(Location.class);
			this.updateById(location);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 删除旅位置信息
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage delelteLocation(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Integer id = jsonObject.getInteger("id");
			this.baseMapper.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
}
