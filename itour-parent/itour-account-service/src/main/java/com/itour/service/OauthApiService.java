package com.itour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.account.LoginList;
import com.itour.model.account.Oauth;
import com.itour.persist.LoginListMapper;
import com.itour.persist.OauthMapper;

/**
 * <p>
 * 用户认证表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-03
 */
@Service
public class OauthApiService extends ServiceImpl<OauthMapper, Oauth> {
	@Autowired
private LoginListMapper loginListMapper;
	/**
	 * 登录
	 * 1.校验用户名和密码(t_a_oauth)
	 * 2.插入登录记录(t_a_login_list)
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage loginSub(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			Oauth oauth = requestMessage.getBody().getContent().getJSONObject("vo").toJavaObject(Oauth.class);
			QueryWrapper<Oauth> queryWrapper = new QueryWrapper<Oauth>();
			queryWrapper.eq("oauthId", oauth.getOauthId());
			queryWrapper.eq("credential", oauth.getCredential());
			Oauth selectOne = this.baseMapper.selectOne(queryWrapper);
	        if(null==selectOne) {
	        	throw new BaseException(ExceptionInfo.EXCEPTION_ACCOUNTINFO);
	        }        
	        responseMessage.setReturnResult(selectOne);
	        LoginList loginList = new LoginList();
	        loginList.setLoginTime(System.currentTimeMillis());
	        loginList.setOauthId(oauth.getOauthId());
	        loginList.setOauthType(oauth.getOauthType());
	        loginList.setuId(selectOne.getuId());
	        this.loginListMapper.insert(loginList);
		}catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(ExceptionInfo.EXCEPTION_ACCOUNTINFO);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 检查用户名是否可用
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage checkRegName(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			 String regName = requestMessage.getBody().getContent().getString("regName");
			QueryWrapper<Oauth> queryWrapper = new QueryWrapper<Oauth>();
			queryWrapper.eq("OAUTH_ID", regName);
			List<Oauth> selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(ExceptionInfo.EXCEPTION_ACCOUNTINFO);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
