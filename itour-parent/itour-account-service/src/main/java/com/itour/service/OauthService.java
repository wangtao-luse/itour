
package com.itour.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.ConstAccount;
import com.itour.constant.Constant;
import com.itour.constant.ConstantMessage;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.account.Account;
import com.itour.model.account.LoginList;
import com.itour.model.account.Oauth;
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
 * @since 2020-05-03
 */
@Service
public class OauthService extends ServiceImpl<OauthMapper, Oauth> {
	@Autowired
private LoginListMapper loginListMapper;
	@Autowired
private AccountMapper accountMapper;
	/**
	 * 登录
	 * 1.校验用户名和密码(t_a_oauth)
	 * 1.1 校验用户状态是否正常(t_a_account)
	 * 2.插入登录记录(t_a_login_list)
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage loginSub(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Oauth oauth = jsonObject.getJSONObject("vo").toJavaObject(Oauth.class);
			//1.校验用户名和密码(t_a_oauth)
			QueryWrapper<Oauth> queryWrapper = new QueryWrapper<Oauth>();
			queryWrapper.eq("OAUTH_ID", oauth.getOauthId());
			queryWrapper.eq("CREDENTIAL", oauth.getCredential());
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
	        responseMessage.setReturnResult(selectOne);
	        //插入登录记录
	        LoginList loginList = new LoginList();
	        loginList.setLoginTime(DateUtil.getlongDate(new Date()));
	        loginList.setOauthId(oauth.getOauthId());
	        loginList.setOauthType(selectOne.getOauthType());
	        loginList.setuId(selectOne.getuId());
	        loginList.setLoginIp(jsonObject.getString("ip"));
	        loginList.setLoginIpLookup(jsonObject.getString("cname"));
	        this.loginListMapper.insert(loginList);
		}catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
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
				
			}
			responseMessage.setReturnResult(selectList);
		}catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
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
			String regName = jsonObject.getString("regName");
			String credential=jsonObject.getString("kl");
			QueryWrapper<Oauth> queryWrapper = new QueryWrapper<Oauth>();
			queryWrapper.eq("OAUTH_ID", regName);
			Oauth selectOne = this.baseMapper.selectOne(queryWrapper);
			String getuId = selectOne.getuId();
			List<Oauth> selectList = this.baseMapper.selectList(new QueryWrapper<Oauth>().eq("U_ID", getuId));
			String salt = UUID.randomUUID().toString().replaceAll("-", "");
			String result = SimpleHashUtil.SimpleHashMd5(credential, salt);	
			for (Oauth oauth : selectList) {
				oauth.setPwd(salt);
				oauth.setCredential(result);
			}
	        boolean updateBatchById = this.updateBatchById(selectList);
		} catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);

		}
		
		
		return ResponseMessage.getSucess();
	}
	
	
}
