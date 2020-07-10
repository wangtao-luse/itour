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
import com.itour.model.member.dto.ViewMRight;
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
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return resposeMessage;
	}
}
