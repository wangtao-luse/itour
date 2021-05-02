package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.dto.ViewTravelinfoOauth;
import com.itour.model.travel.dto.ViewTravelinfoWeekinfo;
import com.itour.persist.ViewTravelinfoWeekinfoMapper;
import com.itour.service.ViewTravelinfoWeekinfoService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-05-02
 */
@Service
public class ViewTravelinfoWeekinfoService extends ServiceImpl<ViewTravelinfoWeekinfoMapper, ViewTravelinfoWeekinfo> {
	/**
	 * 周末攻略信息视图
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryViewTravelinfoWeekinfoList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ViewTravelinfoWeekinfo weekinfo = jsonObject.getJSONObject("vo").toJavaObject(ViewTravelinfoWeekinfo.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<ViewTravelinfoWeekinfo> queryWrapper = new QueryWrapper<ViewTravelinfoWeekinfo>();
			queryWrapper.eq(!StringUtils.isEmpty(weekinfo.getStatus()), "STATUS", weekinfo.getStatus());
			queryWrapper.orderByDesc("PUBLISHTIME");
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
			}else {
				List<ViewTravelinfoWeekinfo> selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
			
		}
		
		return responseMessage;
}
}
