package com.itour.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.member.Account;
import com.itour.model.member.AccountGroup;
import com.itour.model.member.Oauth;
import com.itour.persist.AccountGroupMapper;
import com.itour.persist.AccountMapper;
import com.itour.persist.OauthMapper;
import com.itour.util.DateUtil;
import com.itour.util.SimpleHashUtil;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@Service
public class AccountService extends ServiceImpl<AccountMapper, Account> {
	@Autowired
private OauthMapper oauthMapper;
	@Autowired
private IpaddrService ipaddrService;
	@Autowired
private BaseService baseService;
	@Autowired
private AccountGroupMapper accountGroupMapper;

	/** 注册
	 * 1.插入用户表(t_a_account)
	 * 2.插入用户认证表(t_a_oauth)
	 * 3.插入用户-组表(t_a_account_group)
	 * 4.插入IP信息到(T_A_IPADDR)
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
		account.setCreateip(jsonObject.getString("ip"));
		//注册日期		
		account.setCreatedate(DateUtil.getlongDate(new Date()));
		this.baseMapper.insert(account);
		//2.插入用户认证表
		Oauth o = jsonObject.getJSONObject("vo").toJavaObject(Oauth.class);
		Oauth oauth = new Oauth();
		BeanUtils.copyProperties(oauth, o);		
		String salt = UUID.randomUUID().toString().replaceAll("-", "");
		
		oauth.setPwd(salt);
		String credential = oauth.getCredential();
		String result = SimpleHashUtil.simpleHashMd5(credential, salt);		
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
		//4.插入IP信息
		RequestMessage postData = HttpDataUtil.postData(jsonObject, null);
		ipaddrService.insertIPAddr(postData);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	   
	return responseMessage;
}

public static void main(String[] args) {
	System.out.println("414598c3a3f5f83061373e6b41b8663d".length());
	String result = SimpleHashUtil.simpleHashMd5("taotao141421", "4a350bd65b1148f193765d8f0a2c31f4");
	System.out.println(result);
	String simpleHashSHA_1 = SimpleHashUtil.simpleHashSHA_1("taotao141421", "4a350bd65b1148f193765d8f0a2c31f4");
	System.out.println("sha-1:"+simpleHashSHA_1);
	System.out.println(simpleHashSHA_1.length());
}
public ResponseMessage selectAccountList(RequestMessage requestMessage) {
	ResponseMessage resposeMessage = new ResponseMessage();
	try {
		JSONObject result = new JSONObject();
		JSONObject jsonObject = requestMessage.getBody().getContent();
		//1.需要合计 ;0:不需要合计
		String needTotal = jsonObject.getString(Constant.COMMON_VO_NEEDTOTAL);
		
		Account accountVo = jsonObject.getJSONObject("vo").toJavaObject(Account.class);
		Page pageVo = jsonObject.getJSONObject("page").toJavaObject(Page.class);
		QueryWrapper queryWrapper = new QueryWrapper<Account>();
		if(null!=pageVo) {			
			Page selectPage = this.baseMapper.selectPage(pageVo, queryWrapper);
			result.put(Constant.COMMON_KEY_PAGE, selectPage);
		}else {
			List selectList = this.baseMapper.selectList(queryWrapper);
			result.put(Constant.COMMON_KEY_LIST, selectList);
		}
		if("1".equals(needTotal)) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> totalAccount = this.baseMapper.totalAccount(map);
			jsonObject.put(Constant.COMMON_KEY_SUM, totalAccount);
		}
		resposeMessage.setReturnResult(result);
	} catch (Exception e) {
		// TODO: handle exception
	}
	return resposeMessage;
}
}
