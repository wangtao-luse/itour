package com.itour.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.account.dto.ViewAOauth;
import com.itour.persist.ViewAOauthMapper;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-10
 */
@Service
public class ViewAOauthService extends ServiceImpl<ViewAOauthMapper, ViewAOauth>   {
	public ResponseMessage getViewOAuthList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		JSONObject result = new JSONObject();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String needTotal = jsonObject.getString(Constant.COMMON_VO_NEEDTOTAL);
			ViewAOauth oauthVo = jsonObject.toJavaObject(ViewAOauth.class);
			Page pageVo = jsonObject.toJavaObject(Page.class);
			QueryWrapper<ViewAOauth> queryWrapper = new QueryWrapper<ViewAOauth>();
			queryWrapper.select("U_ID","OAUTH_ID","OAUTH_TYPE","NICKNAME","AVATAR");
			queryWrapper.eq(!StringUtils.isEmpty(oauthVo.getuId()),"U_ID", oauthVo.getuId());
			if(null!=pageVo) {
				Page selectMapsPage = this.baseMapper.selectMapsPage(pageVo, queryWrapper );
				result.put(Constant.COMMON_KEY_PAGE, selectMapsPage);
			}else {
				List<Map<String,Object>> selectMaps = this.baseMapper.selectMaps(queryWrapper);
				result.put(Constant.COMMON_KEY_LIST, selectMaps);
			}
			if("1".equals(needTotal)) {
				Map<String, Object> totalOauth = this.baseMapper.totalOauth(queryWrapper);
				result.put(Constant.COMMON_KEY_SUM, totalOauth);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return responseMessage;
	}
}
