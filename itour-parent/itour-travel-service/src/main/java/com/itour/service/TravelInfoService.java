package com.itour.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ConstantTravel;
import com.itour.model.travel.Tag;
import com.itour.model.travel.TravelColumn;
import com.itour.model.travel.TravelInfo;
import com.itour.model.travel.WeekInfo;
import com.itour.persist.TagMapper;
import com.itour.persist.TravelColumnMapper;
import com.itour.persist.TravelInfoMapper;
import com.itour.persist.TravelTagMapper;
import com.itour.persist.WeekInfoMapper;

/**
 * <p>
 * 旅行信息表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class TravelInfoService extends ServiceImpl<TravelInfoMapper, TravelInfo>  {
	@Autowired
	WeekInfoMapper weekInfoMapper;
	@Autowired
	TravelTagMapper travelTagMapper;
	@Autowired
	TagMapper tagMapper;
	@Autowired
	TagService tagService;
	@Autowired
	TravelColumnMapper travelColumnMapper;
	@Autowired
	TravelColumnService travelColumnService;
	/**
	 * 旅行信息列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryTravelInfoList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelInfo travelInfoVo = jsonObject.getJSONObject("vo").toJavaObject(TravelInfo.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<TravelInfo> queryWrapper = new QueryWrapper<TravelInfo>();
			queryWrapper.orderByDesc("PUBLISHTIME");
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
				
			}else {
				List<TravelInfo> selectList = this.baseMapper.selectList(queryWrapper);
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
	public  ResponseMessage selectTravelInfoById(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Integer id = jsonObject.getInteger("id");
			TravelInfo selectById = this.baseMapper.selectById(id);
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
	public ResponseMessage updateTravelInfo(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelInfo travelInfo = jsonObject.getJSONObject("vo").toJavaObject(TravelInfo.class);
			this.updateById(travelInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 批量修改旅行信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage updateTravelInfoBatch(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			List<TravelInfo> travelInfoVo = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(TravelInfo.class);
			this.updateBatchById(travelInfoVo, travelInfoVo.size());
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
	@Transactional
	public ResponseMessage delTravelInfo(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelInfo travelInfo = jsonObject.toJavaObject(TravelInfo.class);
			this.updateById(travelInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 新增旅行信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage insertTravelInfo(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			//获取参数
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String travel_type = jsonObject.getString(ConstantTravel.TRAVEL_INFO_TYPE);
			JSONArray tagArr = jsonObject.getJSONArray("tag_arr");
			JSONArray colArr = jsonObject.getJSONArray("col_arr");
			//1.插入旅行旅行信息表
			TravelInfo travelInfo = jsonObject.toJavaObject(TravelInfo.class);
			this.baseMapper.insert(travelInfo);
			//2.插入周末旅行信息表
			if(ConstantTravel.TRAVEL_INFO_WEEK.equals(travel_type)) {
				WeekInfo entity = new WeekInfo();
				entity.setTid(travelInfo.getId());
				entity.setWeekContent(jsonObject.getString("markdown"));
				weekInfoMapper.insert(entity);
			}
			//3.插入标签中间表
			
			//4.插入分类专栏表中间表
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
}
