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
import com.itour.exception.BaseException;
import com.itour.model.member.vo.ViewMOauth;
import com.itour.persist.ViewMOauthMapper;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-10
 */
@Service
public class ViewMOauthService extends ServiceImpl<ViewMOauthMapper, ViewMOauth> {
	public ResponseMessage getViewOAuthList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		JSONObject result = new JSONObject();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ViewMOauth oauthVo = jsonObject.getJSONObject("view").toJavaObject(ViewMOauth.class);
			String needTotal = jsonObject.getString(Constant.COMMON_VO_NEEDTOTAL);
			JSONObject pageVo = jsonObject.getJSONObject("page");
			QueryWrapper<ViewMOauth> queryWrapper = new QueryWrapper<ViewMOauth>();			
			queryWrapper.eq(!StringUtils.isEmpty(oauthVo.getuId()),"U_ID", oauthVo.getuId());
			queryWrapper.likeRight(!StringUtils.isEmpty(oauthVo.getOauthId()), "OAUTH_ID", oauthVo.getOauthId());
			queryWrapper.eq(!StringUtils.isEmpty(oauthVo.getOauthType()), "OAUTH_TYPE", oauthVo.getOauthType());
			if(null!=pageVo) {
				Page page = pageVo.toJavaObject(Page.class);
				Page selectMapsPage = this.baseMapper.selectPage(page, queryWrapper);
				result.put(Constant.COMMON_KEY_PAGE, selectMapsPage);
				
			}else {
				 List<ViewMOauth> selectList = this.baseMapper.selectList(queryWrapper);
				result.put(Constant.COMMON_KEY_LIST, selectList);
			}
			if("1".equals(needTotal)) {
				Map<String, Object> totalOauth = this.baseMapper.totalOauth(queryWrapper);
				result.put(Constant.COMMON_KEY_SUM, totalOauth);
			}
			responseMessage.setReturnResult(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}

}
