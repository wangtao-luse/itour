package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.dto.ViewTravelinfoOauth;
import com.itour.persist.ViewTravelinfoOauthMapper;
import com.itour.service.ViewTravelinfoOauthService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-08-03
 */
@Service
public class ViewTravelinfoOauthService extends ServiceImpl<ViewTravelinfoOauthMapper, ViewTravelinfoOauth>  {
	
	/**
	 * 旅行信息列表视图
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryViewTravelinfoOauthList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ViewTravelinfoOauth viewTravelinfoOauthVo = jsonObject.getJSONObject("vo").toJavaObject(ViewTravelinfoOauth.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<ViewTravelinfoOauth> queryWrapper = new QueryWrapper<ViewTravelinfoOauth>();
			queryWrapper.orderByDesc("PUBLISHTIME");
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
			}else {
				List<ViewTravelinfoOauth> selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
			System.out.println(1/0);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
			
		}
		
		return responseMessage;
}
	 /**
	 * 根据编号获取旅行信息视图
	 * @param requestMessage
	 * @return
	 */
	public  ResponseMessage selectViewTravelinfoOauthById(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Integer id = jsonObject.getInteger("id");
			ViewTravelinfoOauth selectById = this.baseMapper.selectById(id);
			responseMessage.setReturnResult(selectById);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return responseMessage;
	}
	
}