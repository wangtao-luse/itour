package com.itour.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.account.dto.ViewARight;
import com.itour.persist.ViewARightMapper;


/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-09
 */
@Service
public class ViewARightService extends ServiceImpl<ViewARightMapper, ViewARight> {
	/**
	 * 会员权限列表查询
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage selectViewRightList(RequestMessage requestMessage) {
		ResponseMessage resposeMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			
			ViewARight rightVo = jsonObject.getJSONObject("view").toJavaObject(ViewARight.class);
			Page pageVo = jsonObject.getJSONObject("page").toJavaObject(Page.class);
			QueryWrapper<ViewARight> queryWrapper = new QueryWrapper<ViewARight>();
			/**精确查询**/
			queryWrapper.eq(null!=rightVo.getId(), "ID", rightVo.getId());
			queryWrapper.eq(!StringUtils.isEmpty(rightVo.getMenuNo()), "MENU_NO", rightVo.getMenuNo());
			queryWrapper.eq(!StringUtils.isEmpty(rightVo.getParentId()), "PARENT_ID", rightVo.getParentId());
			queryWrapper.eq(!StringUtils.isEmpty(rightVo.getMenuType()), "MENU_TYPE", rightVo.getMenuType());
			/**模糊查询**/
			queryWrapper.likeRight(!StringUtils.isEmpty(rightVo.getMenu()), "MENU", rightVo.getMenu());
			queryWrapper.like(!StringUtils.isEmpty(rightVo.getUrl()), "URL", rightVo.getUrl());
		
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
