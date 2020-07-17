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
import com.itour.model.msg.ViewMMessageinfo;
import com.itour.persist.ViewMMessageinfoMapper;


/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-17
 */
@Service
public class ViewMMessageinfoService extends ServiceImpl<ViewMMessageinfoMapper, ViewMMessageinfo> {
	public ResponseMessage queryViewMessageList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ViewMMessageinfo msgVo = jsonObject.getJSONObject("msg").toJavaObject(ViewMMessageinfo.class);
			JSONObject pageVo = jsonObject.getJSONObject("page");
			QueryWrapper<ViewMMessageinfo> queryWrapper = new QueryWrapper<ViewMMessageinfo>();
			queryWrapper.eq(!StringUtils.isEmpty(msgVo.getType()),"TYPE", msgVo.getType());
			if(null!=pageVo) {	
				Page page = pageVo.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper );
				responseMessage.setReturnResult(selectPage);
			}else {
				List selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return responseMessage;
	}
}
