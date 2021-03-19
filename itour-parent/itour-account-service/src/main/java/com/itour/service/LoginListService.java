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
import com.itour.model.account.LoginList;
import com.itour.persist.LoginListMapper;

/**
 * <p>
 * 用户认证表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-05
 */
@Service
public class LoginListService extends ServiceImpl<LoginListMapper, LoginList>  {
/**
 * 登录记录列表查询
 * @param requestMessage
 * @return
 */
public ResponseMessage queryLoginList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {		
		JSONObject jsonObject = requestMessage.getBody().getContent();
		LoginList loginList = jsonObject.getJSONObject("vo").toJavaObject(LoginList.class);
		JSONObject pageJson = jsonObject.getJSONObject("page");
		
		QueryWrapper<LoginList> queryWrapper = new QueryWrapper<LoginList>();
		/**模糊查询**/
		queryWrapper.likeRight(!StringUtils.isEmpty(loginList.getOauthId()), "OAUTH_ID", loginList.getOauthId());
		/**精确查询**/
		queryWrapper.eq(!StringUtils.isEmpty(loginList.getOauthType()), "OAUTH_TYPE", loginList.getOauthType());		
		queryWrapper.eq(!StringUtils.isEmpty(loginList.getLoginIp()), "LOGIN_IP", loginList.getLoginIp());	
		/**区间段查询**/
	//	queryWrapper.ge(null!=loginList.getLoginDate()&&null!=loginList.getLoginDate().getLowerLimit(), "LOGIN_TIME", loginList.getLoginDate().getLowerLimit());
	//	queryWrapper.le(null!=loginList.getLoginDate()&&null!=loginList.getLoginDate().getUpperLimit(), "LOGIN_TIME", loginList.getLoginDate().getUpperLimit());
		
		if(pageJson!=null) {
			Page page = pageJson.toJavaObject(Page.class);
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
			responseMessage.setReturnResult(selectPage);
			
		}else {
			List<LoginList> selectList = this.baseMapper.selectList(queryWrapper);
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
