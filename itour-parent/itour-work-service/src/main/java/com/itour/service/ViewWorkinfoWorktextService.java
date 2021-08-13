package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.work.dto.ViewWorkinfoWorktext;
import com.itour.persist.ViewWorkinfoWorktextMapper;
import com.itour.service.ViewWorkinfoWorktextService;
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
 * @since 2021-08-13
 */
@Service
public class ViewWorkinfoWorktextService extends ServiceImpl<ViewWorkinfoWorktextMapper, ViewWorkinfoWorktext> {
	//审核列表
	public ResponseMessage queryViewWorkInfoList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ViewWorkinfoWorktext weekinfo = jsonObject.getJSONObject("vo").toJavaObject(ViewWorkinfoWorktext.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<ViewWorkinfoWorktext> queryWrapper = new QueryWrapper<ViewWorkinfoWorktext>();
			queryWrapper.eq(!StringUtils.isEmpty(weekinfo.getStatus()), "STATUS", weekinfo.getStatus());
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
			}else {
				List<ViewWorkinfoWorktext> selectList = this.baseMapper.selectList(queryWrapper);
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
