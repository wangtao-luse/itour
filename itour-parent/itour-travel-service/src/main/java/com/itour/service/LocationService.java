package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
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
	 * 旅行信息列表
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
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
}
	 /**
	 * 根据编号获取旅行信息
	 * @param requestMessage
	 * @return
	 */
	public  ResponseMessage selectLocationById(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Integer id = jsonObject.getInteger("id");
			Location selectById = this.baseMapper.selectById(id);
			responseMessage.setReturnResult(selectById);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 修改旅行信息
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
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 删除旅行信息
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage delLocation(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Location location = jsonObject.toJavaObject(Location.class);
			this.updateById(location);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
}
