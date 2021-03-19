package com.itour.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.TravelSchedule;
import com.itour.persist.TravelScheduleMapper;

/**
 * <p>
 * 旅行行程安排表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class TravelScheduleService extends ServiceImpl<TravelScheduleMapper, TravelSchedule>{
	//列表
	public ResponseMessage queryTravelScheduleList(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelSchedule travelAnalysis = jsonObject.toJavaObject(TravelSchedule.class);
			JSONObject pageVo = jsonObject.getJSONObject("page");
			QueryWrapper<TravelSchedule> queryWrapper = new QueryWrapper<TravelSchedule>();
			if(pageVo!=null) {
				Page page = pageVo.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper );
				response.setReturnResult(selectPage);
			}else {
				this.baseMapper.selectList(queryWrapper);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	//单条
	public ResponseMessage getTravelSchedule(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelSchedule analysis = jsonObject.toJavaObject(TravelSchedule.class);
			QueryWrapper<TravelSchedule> queryWrapper = new QueryWrapper<TravelSchedule>();
			TravelSchedule selectOne = this.baseMapper.selectOne(queryWrapper);
		    response.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	//修改
	public ResponseMessage updateTravelSchedule(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelSchedule analysis = jsonObject.toJavaObject(TravelSchedule.class);
			this.baseMapper.updateById(analysis);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	//删除
	public ResponseMessage delTravelSchedule(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Integer id = jsonObject.getInteger("id");
			int deleteById = this.baseMapper.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
}
