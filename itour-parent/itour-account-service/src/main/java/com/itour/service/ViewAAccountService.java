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
import com.itour.model.account.dto.ViewAAccount;
import com.itour.persist.ViewAAccountMapper;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-08
 */
@Service
public class ViewAAccountService extends ServiceImpl<ViewAAccountMapper, ViewAAccount> {
	public ResponseMessage selectViewAccountList(RequestMessage requestMessage) {
		ResponseMessage resposeMessage = ResponseMessage.getSucess();
		try {
			JSONObject result = new JSONObject();
			JSONObject jsonObject = requestMessage.getBody().getContent();
			//1.需要合计 ;0:不需要合计
			String needTotal = jsonObject.getString(Constant.COMMON_VO_NEEDTOTAL);			
			ViewAAccount accountVo = jsonObject.getJSONObject("vo").toJavaObject(ViewAAccount.class);
			Page pageVo = jsonObject.getJSONObject("page").toJavaObject(Page.class);
			QueryWrapper<ViewAAccount> queryWrapper = new QueryWrapper<ViewAAccount>();
			/**精确查询**/
			queryWrapper.eq(!StringUtils.isEmpty(accountVo.getSex()), "SEX", accountVo.getSex());
			queryWrapper.eq(!StringUtils.isEmpty(accountVo.getCreateip()), "CREATEIP", accountVo.getCreateip());
			queryWrapper.eq(!StringUtils.isEmpty(accountVo.getStatus()), "STATUS", accountVo.getStatus());
			queryWrapper.eq(!StringUtils.isEmpty(accountVo.getUtype()), "UTYPE", accountVo.getUtype());
			/**日期查询**/
			queryWrapper.ge(null!=accountVo.getCreatedateRange().getLowerLimit(), "CREATEDATE", accountVo.getCreatedateRange().getLowerLimit());
			queryWrapper.le(null!=accountVo.getCreatedateRange().getUpperLimit(), "CREATEDATE", accountVo.getCreatedateRange().getUpperLimit());
			
			queryWrapper.ge(null!=accountVo.getLasttimeRange().getLowerLimit(), "LASTTIME", accountVo.getCreatedateRange().getLowerLimit());
			queryWrapper.le(null!=accountVo.getLasttimeRange().getUpperLimit(), "LASTTIME", accountVo.getCreatedateRange().getUpperLimit());
			
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
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return resposeMessage;
	}
}
