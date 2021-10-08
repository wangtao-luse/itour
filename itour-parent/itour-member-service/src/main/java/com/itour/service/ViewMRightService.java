package com.itour.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.member.vo.ViewMRight;
import com.itour.persist.ViewMRightMapper;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-09
 */
@Service
public class ViewMRightService extends ServiceImpl<ViewMRightMapper, ViewMRight>  {
	/**
	 * 前台权限列表（视图）
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage selectViewRightList(RequestMessage requestMessage) {
		ResponseMessage resposeMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			
			ViewMRight rightVo = jsonObject.getJSONObject("view").toJavaObject(ViewMRight.class);
			Page pageVo = jsonObject.getJSONObject("page").toJavaObject(Page.class);
			QueryWrapper<ViewMRight> queryWrapper = new QueryWrapper<ViewMRight>();
			queryWrapper.eq(null!=rightVo.getId(), "ID", rightVo.getId());
			queryWrapper.eq(!StringUtils.isEmpty(rightVo.getMenuNo()), "MENU_NO", rightVo.getMenuNo());
			queryWrapper.eq(!StringUtils.isEmpty(rightVo.getMenuType()), "MENU_TYPE", rightVo.getMenuType());
			
			queryWrapper.like(!StringUtils.isEmpty(rightVo.getMenu()), "MENU", rightVo.getMenu());
			queryWrapper.like(!StringUtils.isEmpty(rightVo.getUrl()), "URL", rightVo.getUrl());
			if(null!=rightVo.getParentId()) {
				queryWrapper.eq( "PARENT_ID", rightVo.getParentId());
				queryWrapper.or().eq("MENU_NO", rightVo.getParentId());
			}
			if(null!=pageVo) {			
				Page selectPage = this.baseMapper.selectPage(pageVo, queryWrapper);
				resposeMessage.setReturnResult(selectPage);
			}else {
				List selectList = this.baseMapper.selectList(queryWrapper);
				resposeMessage.setReturnResult(selectList);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return resposeMessage;
	}
}
