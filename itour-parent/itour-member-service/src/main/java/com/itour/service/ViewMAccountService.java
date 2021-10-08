package com.itour.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.member.vo.ViewMAccount;
import com.itour.persist.ViewMAccountMapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-08
 */
@Service
public class ViewMAccountService extends ServiceImpl<ViewMAccountMapper, ViewMAccount> {
	/**
	 *  后台管理员列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage selectViewAccountList(RequestMessage requestMessage) {
		ResponseMessage resposeMessage = ResponseMessage.getSucess();
		try {
			JSONObject result = new JSONObject();
			JSONObject jsonObject = requestMessage.getBody().getContent();
			//1.需要合计 ;0:不需要合计
			String needTotal = jsonObject.getString(Constant.COMMON_VO_NEEDTOTAL);
			
			ViewMAccount accountVo = jsonObject.getJSONObject("vo").toJavaObject(ViewMAccount.class);
			Page pageVo = jsonObject.getJSONObject("page").toJavaObject(Page.class);
			QueryWrapper queryWrapper = new QueryWrapper<ViewMAccount>();
			queryWrapper.orderByDesc("CREATEDATE");
			if(null!=pageVo) {			
				Page selectPage = this.baseMapper.selectPage(pageVo, queryWrapper);
				result.put(Constant.COMMON_KEY_PAGE, selectPage);
			}else {
				List selectList = this.baseMapper.selectList(queryWrapper);
				result.put(Constant.COMMON_KEY_LIST, selectList);
			}
			if("1".equals(needTotal)) {
				Map<String, Object> totalAccount = this.baseMapper.totalAccount(queryWrapper);
				result.put(Constant.COMMON_KEY_SUM, totalAccount);
			}
			
			resposeMessage.setReturnResult(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return resposeMessage;
	}
	/**
	 * 后台管理员单条
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage selectViewAccountOne(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ViewMAccount oauthVo = jsonObject.getJSONObject("view").toJavaObject(ViewMAccount.class);
			QueryWrapper<ViewMAccount> queryWrapper = new QueryWrapper<ViewMAccount>();			
			queryWrapper.eq(null!=oauthVo.getId(),"ID", oauthVo.getId());
			ViewMAccount selectOne = this.baseMapper.selectOne(queryWrapper);
			responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
