package com.itour.realm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.itour.common.vo.ExUsernamePasswordToken;
import com.itour.connector.AccountConnector;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.account.Oauth;
import com.itour.model.account.RightDetail;
import com.itour.util.FastJsonUtil;
import com.itour.util.SimpleHashUtil;
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
		Object primaryPrincipal = principals.getPrimaryPrincipal();
		ResponseMessage queryAccountRight = accountConnector.queryAccountRight(null, null);
		Map<String, Object> map = queryAccountRight.getReturnResult();
		List<RightDetail> rightList = FastJsonUtil.mapToList(map, RightDetail.class, Constant.COMMON_KEY);
	
		return null;
	}
    /**
          * 验证当前登录的Subject 认证回调函数, 登录时调用
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		//1.把AuthenticationToken转换为ExUsernamePasswordToken
		ExUsernamePasswordToken upt = (ExUsernamePasswordToken)token;
		//2.从ExUsernamePasswordToken中获取Username
		String username = upt.getUsername();
		//3.调用数据库方法从校验用户名和密码
		String ip = upt.getIp();
		String cname=upt.getCname();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("regName", username);
		String salt="";
		//获取盐
		//4.若用户不存在则可以抛出UnknownAccountException异常
		ResponseMessage checkOauthId = this.accountConnector.checkOauthId(jsonObject, upt.getRequest());
		if(Constant.SUCCESS_CODE.equals(checkOauthId.getResultCode())&&null!=checkOauthId.getReturnResult()) {
			HashMap<String, Object> map = (HashMap<String, Object>)checkOauthId.getReturnResult();
			List<Oauth> list = FastJsonUtil.mapToList(map, Oauth.class, Constant.COMMON_KEY);				
			salt = list.get(0).getPwd();			
		}else {
			throw new UnknownAccountException();
		}
		jsonObject.clear();
		Oauth oauth = new Oauth();
		oauth.setOauthId(upt.getUsername());
		String pass = new String(upt.getPassword());
		String simpleHashMd5 = SimpleHashUtil.SimpleHashMd5(pass, salt);
		oauth.setCredential(simpleHashMd5);
		jsonObject.put("vo", oauth);
		jsonObject.put("ip", ip);		
		jsonObject.put("cname", cname);
		Oauth oauthObj = new Oauth();
		jsonObject.put("ipaddr", upt.getJsonObject());
		ResponseMessage loginSub = accountConnector.loginSub(jsonObject,upt.getRequest());
		if(Constant.SUCCESS_CODE.equals(loginSub.getResultCode())&&null!=loginSub.getReturnResult()) {
			HashMap<String, Object> map = (HashMap<String, Object>)loginSub.getReturnResult();
			oauthObj = FastJsonUtil.mapToObject(map, Oauth.class, Constant.COMMON_KEY);
		}
		//5.根据用户信息来构建AuthenticationInfo并返回，通常使用的是SimpleAuthenticationInfo
		//以下信息是从数据库中获取的
	    //认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对
		Object principal = oauthObj;
		//加密后的密码（从数据库中取的密码）
		Object hashedCredentials = oauthObj.getCredential();
		//Realm对象的name，调用父类的getName()方法即可
		String realmName= getName();
		//盐值使用MD5盐值加密
		//1.如何把一个字符串加密为MD5
		//2.shiro通过AuthenticatingRealm的credentialsMatcher属性来进行的密码比对;
		
		ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		return simpleAuthenticationInfo;
	}

}
