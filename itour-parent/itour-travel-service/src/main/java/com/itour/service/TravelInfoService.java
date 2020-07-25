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
import com.itour.model.travel.TravelInfo;
import com.itour.persist.TravelInfoMapper;

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
	/**
	 * 旅行信息列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryGroupList(RequestMessage requestMessage) {
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
}
