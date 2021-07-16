package com.itour.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.account.api.AccountApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.ConstAccount;
import com.itour.constant.Constant;
import com.itour.constant.ConstantMessage;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.member.Account;
import com.itour.model.member.LoginList;
import com.itour.model.member.Oauth;
import com.itour.persist.AccountMapper;
import com.itour.persist.LoginListMapper;
import com.itour.persist.OauthMapper;
import com.itour.util.DateUtil;
import com.itour.util.SimpleHashUtil;

/**
 * <p>
 * 用户认证表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@Service
public class OauthService extends ServiceImpl<OauthMapper, Oauth> {
	@Autowired
private LoginListMapper loginListMapper;
	@Autowired
private AccountMapper accountMapper;
	@Autowired
private IpaddrService ipaddrService;
	@Autowired
private AccountApi accountApi;
	/**
	 * 登录
	 * 1.校验用户名和密码(t_m_oauth)
	 * 1.1 校验用户状态是否正常(t_m_account)
	 * 1.2修改最近登录时间
	 * 2.插入登录记录(t_m_login_list)
	 * 3.插入IP信息(t_m_ipaddr)
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage loginSub(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Oauth oauth = jsonObject.getJSONObject("vo").toJavaObject(Oauth.class);
			//1.校验用户名
			QueryWrapper<Oauth> queryWrapper = new QueryWrapper<Oauth>();
			queryWrapper.eq("OAUTH_ID", oauth.getOauthId());
			Oauth selectOne = this.baseMapper.selectOne(queryWrapper);
	        if(null==selectOne) {
	        	throw new BaseException(ExceptionInfo.EXCEPTION_ACCOUNTINFO);
	        }
	        //1.1校验用户状态是否正常
	        QueryWrapper<Account> queryAccount = new QueryWrapper<Account>();
	        queryAccount.eq("UID", selectOne.getuId());
	        Account account = this.accountMapper.selectOne(queryAccount);
	        if("0".equals(account.getStatus())) {
	        	throw new BaseException(ExceptionInfo.EXCEPTION_STATUS);
	        }
	        //1.2修改最近登录时间
	        account.setLasttime(DateUtil.currentLongDate());
	        this.accountMapper.updateById(account);
	        responseMessage.setReturnResult(selectOne);
	        //插入登录记录
	        LoginList loginList = new LoginList();
	        loginList.setLoginTime(DateUtil.currentLongDate());
	        loginList.setOauthId(oauth.getOauthId());
	        loginList.setOauthType(selectOne.getOauthType());
	        loginList.setuId(selectOne.getuId());
	        loginList.setLoginIp(jsonObject.getString("ip"));
	        loginList.setLoginIpLookup(jsonObject.getString("cname"));
	        this.loginListMapper.insert(loginList);
	      //4.插入IP信息
			RequestMessage postData = HttpDataUtil.postData(jsonObject, null);
			ipaddrService.insertIPAddr(postData);
		}catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	   * 检查用户名、邮箱是否可用
	 * @param requestMessage
	 * @return 不可用：返回错误信息
	 */
	public ResponseMessage checkOauthId(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			 String regName = requestMessage.getBody().getContent().getString("regName");
			 String type = requestMessage.getBody().getContent().getString("type");
			 if(StringUtils.isEmpty(regName)) {				 
				 throw new BaseException(ExceptionInfo.EXCEPTION_ISEMPTY);
			 }			 
			QueryWrapper<Oauth> queryWrapper = new QueryWrapper<Oauth>();
			if(ConstAccount.EMAIL.equals(type)) {
				queryWrapper.eq("OAUTH_TYPE", type);
			}else if(ConstAccount.UNAME.equals(type)) {
				queryWrapper.eq("OAUTH_TYPE", type);
			}
			queryWrapper.eq("OAUTH_ID", regName);			
			List<Oauth> selectList = this.baseMapper.selectList(queryWrapper);			
			if(null!=selectList&&selectList.size()>0) {
				if(ConstAccount.EMAIL.equals(type)) {
					throw new BaseException(ConstantMessage.REGEMAIL_ERROR);
				}else if(ConstAccount.UNAME.equals(type)) {
					throw new BaseException(ConstantMessage.REGNAME_ERROR);
				}
				
			}else {
				//找回密码时使用
				if(ConstAccount.FINPWD.equals(type)) {
					throw new BaseException(ConstantMessage.UNKNOWUNAME);
				}
			}
			responseMessage.setReturnResult(selectList);
		}catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 找回(修改)密码
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage updateCredential(RequestMessage requestMessage) {
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String regName = jsonObject.getString("email");
			String credential=jsonObject.getString("pwd");
			QueryWrapper<Oauth> queryWrapper = new QueryWrapper<Oauth>();
			queryWrapper.eq("OAUTH_ID", regName);
			Oauth selectOne = this.baseMapper.selectOne(queryWrapper);
			String getuId = selectOne.getuId();
			List<Oauth> selectList = this.baseMapper.selectList(new QueryWrapper<Oauth>().eq("U_ID", getuId));
			String salt = UUID.randomUUID().toString().replaceAll("-", "");
			String result = SimpleHashUtil.simpleHashSHA_1(credential, salt);	
			for (Oauth oauth : selectList) {
				oauth.setPwd(salt);
				oauth.setCredential(result);
			}
	        boolean updateBatchById = this.updateBatchById(selectList);
		} catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);

		}
		
		
		return ResponseMessage.getSucess();
	}
public ResponseMessage getOAuthList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	JSONObject result = new JSONObject();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		String needTotal = jsonObject.getString(Constant.COMMON_VO_NEEDTOTAL);
		Oauth oauthVo = jsonObject.toJavaObject(Oauth.class);
		Page pageVo = jsonObject.toJavaObject(Page.class);
		QueryWrapper<Oauth> queryWrapper = new QueryWrapper<Oauth>();
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
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
