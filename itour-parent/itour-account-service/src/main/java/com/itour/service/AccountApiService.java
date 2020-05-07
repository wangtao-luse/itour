package com.itour.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.account.Account;
import com.itour.model.account.AccountGroup;
import com.itour.model.account.LoginList;
import com.itour.model.account.Oauth;
import com.itour.model.test.Contract;
import com.itour.persist.AccountGroupMapper;
import com.itour.persist.AccountMapper;
import com.itour.persist.ContractMapper;
import com.itour.persist.LoginListMapper;
import com.itour.persist.OauthMapper;
import com.itour.util.Base64Util;
import com.itour.util.SimpleHashUtil;



@Service
public class AccountApiService extends ServiceImpl<AccountMapper, Account>{
	@Autowired
private OauthMapper oauthMapper;
	@Autowired
private BaseService baseService;
	@Autowired
private AccountGroupMapper accountGroupMapper;
	@Autowired
private LoginListMapper loginListMapper;
	/** 注册
	 * 1.插入用户表(t_a_account)
	 * 2.插入用户认证表(t_a_oauth)
	 * 3.插入用户-组表(t_a_account_group)
	 * @param requestMesage
	 * @return
	 */
@Transactional	
public 	ResponseMessage regiesterSub(RequestMessage requestMesage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMesage.getBody().getContent();
		//1.插入用户表
		Account account = jsonObject.getJSONObject("vo").toJavaObject(Account.class);
		//生成uid
		String uid = baseService.getUid();
		account.setUid(uid);
		account.setUtype("0");
		account.setCreatedate(System.currentTimeMillis());
		this.baseMapper.insert(account);
		//2.插入用户认证表
		Oauth oauth = jsonObject.getJSONObject("vo").toJavaObject(Oauth.class);
		String salt = UUID.randomUUID().toString();
		
		oauth.setPwd(uid+salt);
		String credential = oauth.getCredential();
		String result = SimpleHashUtil.SimpleHashMd5(credential, uid+salt);		
		String encodeToString = Base64Util.base64Str(oauth.getOauthId());
		oauth.setOauthId(encodeToString);
		oauth.setuId(uid);
		oauth.setOauthType("email");
		oauth.setCredential(result);
		oauth.setPwd(uid+salt);
		oauthMapper.insert(oauth);
		//3.插入用户组表
		AccountGroup accountGroup = new AccountGroup();
		accountGroup.setGroupId(1);
		accountGroup.setuId(uid);
		this.accountGroupMapper.insert(accountGroup);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	   
	return responseMessage;
}
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
		Oauth selectOne = this.oauthMapper.selectOne(queryWrapper);
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
	
	
	
	
	
	@Autowired
	ContractMapper contractMapper;
	@RequestMapping("/list")
	@ResponseBody
	public Contract getList() {
		Contract queryById = contractMapper.queryById(177L);
		return queryById;
		
	}

}
