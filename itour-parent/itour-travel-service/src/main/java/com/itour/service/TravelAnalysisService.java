package com.itour.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.travel.TravelAnalysis;
import com.itour.persist.TravelAnalysisMapper;

/**
 * <p>
 * 旅行行程剖析表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class TravelAnalysisService extends ServiceImpl<TravelAnalysisMapper, TravelAnalysis>{
	//列表
	public ResponseMessage queryTravelAnalysisList(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelAnalysis travelAnalysis = jsonObject.toJavaObject(TravelAnalysis.class);
			JSONObject pageVo = jsonObject.getJSONObject("page");
			QueryWrapper<TravelAnalysis> queryWrapper = new QueryWrapper<TravelAnalysis>();
			queryWrapper.eq(!StringUtils.isEmpty(travelAnalysis.getCode()), "CODE", travelAnalysis.getCode());
			queryWrapper.eq(!StringUtils.isEmpty(travelAnalysis.getType()), "TYPE", travelAnalysis.getType());
			queryWrapper.likeRight(!StringUtils.isEmpty(travelAnalysis.getLocation()), "LOCATION", travelAnalysis.getLocation());
			queryWrapper.orderByDesc("ID");
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
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	//单条
	public ResponseMessage getTravelAnalysis(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelAnalysis analysis = jsonObject.toJavaObject(TravelAnalysis.class);
			QueryWrapper<TravelAnalysis> queryWrapper = new QueryWrapper<TravelAnalysis>();
		    TravelAnalysis selectOne = this.baseMapper.selectOne(queryWrapper);
		    response.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	//修改
	public ResponseMessage updateTravelAnalysis(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelAnalysis analysis = jsonObject.toJavaObject(TravelAnalysis.class);
			this.baseMapper.updateById(analysis);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	//删除
	public ResponseMessage delTravelAnalysis(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Integer id = jsonObject.getInteger("id");
			int deleteById = this.baseMapper.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
}
