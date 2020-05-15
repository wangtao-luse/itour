package com.itour.service;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.itour.persist.AccountGroupMapper;
import com.itour.persist.AccountMapper;
import com.itour.persist.LoginListMapper;
import com.itour.persist.OauthMapper;
import com.itour.util.Base64Util;
import com.itour.util.DateUtil;
import com.itour.util.SimpleHashUtil;



@Service
public class AccountApiService extends ServiceImpl<AccountMapper, Account>{
	@Autowired
private OauthMapper oauthMapper;
	@Autowired
private BaseService baseService;
	@Autowired
private AccountGroupMapper accountGroupMapper;

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
		account.setCreatedate(DateUtil.getlongDate(new Date()));
		this.baseMapper.insert(account);
		//2.插入用户认证表
		Oauth o = jsonObject.getJSONObject("vo").toJavaObject(Oauth.class);
		Oauth oauth = new Oauth();
		BeanUtils.copyProperties(oauth, o);		
		String salt = UUID.randomUUID().toString();
		
		oauth.setPwd(salt);
		String credential = oauth.getCredential();
		String result = SimpleHashUtil.SimpleHashMd5(credential, salt);		
		oauth.setOauthId(oauth.getOauthId());
		oauth.setuId(uid);
		oauth.setOauthType("email");
		oauth.setCredential(result);
		oauth.setNickname(oauth.getNickname());
		oauthMapper.insert(oauth);
		oauth.setOauthType("uname");
		oauth.setOauthId(oauth.getNickname());
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
public static void main(String[] args) {
	System.out.println("414598c3a3f5f83061373e6b41b8663d".length());
}
}
