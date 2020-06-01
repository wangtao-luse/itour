package com.itour.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.AccountVo;
import com.itour.common.vo.ExUsernamePasswordToken;
import com.itour.connector.AccountConnector;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.model.account.Oauth;
/**
 * 自定义的指定Shiro验证用户登录的类
 * @author wwang
 *
 */

public class LoginRealm extends AuthorizingRealm {
	@Autowired
	private AccountConnector accountConnector;
   /**
        * 为当前登录的Subject授予角色和权限
    */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
    /**
          * 验证当前登录的Subject 认证回调函数, 登录时调用
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		ExUsernamePasswordToken upt = (ExUsernamePasswordToken)token;
		String username = upt.getUsername();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("regName", username);
		String salt="";
		//获取盐
		ResponseMessage checkOauthId = this.accountConnector.checkOauthId(jsonObject, upt.getRequest());
		if(Constant.SUCCESS_CODE.equals(checkOauthId.getResultCode())) {
			if(null==checkOauthId) {
				throw new UnknownAccountException(ExceptionInfo.EXCEPTION_ACCOUNTINFO);
			}else {
				List<Oauth> oauth = (List<Oauth>)checkOauthId.getReturnResult();
				salt = oauth.get(0).getPwd();
			}			
		}
		jsonObject.clear();
		AccountVo account = new AccountVo();
		account.setOauthId(upt.getUsername());
		jsonObject.put("vo", account);
		ResponseMessage loginSub = accountConnector.loginSub(jsonObject,upt.getRequest());
		Oauth oauth = new Oauth();
		if(Constant.FAILED_CODE.equals(loginSub.getResultCode())&&null!=loginSub.getReturnResult()) {
			 oauth = (Oauth)loginSub.getReturnResult();
			oauth.getOauthId();
		}
		//以下信息是从数据库中获取的
	    //认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对
		Object principal = oauth;
		//加密后的密码
		Object hashedCredentials = oauth.getCredential();
		//ealm对象的name，调用父类的getName()方法即可
		String realmName= getName();
		ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		return simpleAuthenticationInfo;
	}

}