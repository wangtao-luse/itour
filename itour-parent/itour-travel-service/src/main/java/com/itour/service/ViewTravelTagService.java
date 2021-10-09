package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.vo.ViewTravelTag;
import com.itour.persist.ViewTravelTagMapper;
import com.itour.service.ViewTravelTagService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctc.wstx.util.StringUtil;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-04-12
 */
@Service
public class ViewTravelTagService extends ServiceImpl<ViewTravelTagMapper, ViewTravelTag>{

	public ResponseMessage queryViewTravelTagList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ViewTravelTag vo = jsonObject.toJavaObject(ViewTravelTag.class);
			QueryWrapper<ViewTravelTag> queryWrapper = new QueryWrapper<ViewTravelTag>();
			queryWrapper.eq(null!=vo.getTid(), "tid", vo.getTid());
			queryWrapper.eq(!StringUtils.isEmpty(vo.getUid()), "uid", vo.getUid());
			List<ViewTravelTag> selectList = this.baseMapper.selectList(queryWrapper );
			responseMessage.setReturnResult(selectList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
		
	}
}
