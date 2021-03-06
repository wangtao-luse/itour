package com.itour.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.resp.ResponseMessage;
import com.itour.common.vo.ExUsernamePasswordToken;
import com.itour.connector.MemberConnector;
import com.itour.constant.Constant;
import com.itour.model.account.Oauth;
import com.itour.util.FastJsonUtil;
/**
 * 自定义的指定Shiro验证用户登录的类
 * @author wwang
 *
 */

public class LoginRealm extends AuthorizingRealm {
	@Autowired
	private MemberConnector memberConnector;
   /**
        * 为当前登录的Subject授予角色和权限
        //1.从PrincipalCollection获取登录用户的信息
		//2.利用登录的用户信息来获取当前用户的角色或权限(可能需要查询数据库)
		//3.创建SimpleAuthorizationInfo并设置roles属性		 
    */
	//用户访问的目标资源或者方法需要权限的时候才会调用doGetAuthorizationInfo进行授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		//1.从PrincipalCollection获取登录用户的信息
		//2.利用登录的用户信息来获取当前用户的角色或权限(可能需要查询数据库)
		//3.创建SimpleAuthorizationInfo并设置roles属性
		//1.1获取登录用户信息
		Oauth primaryPrincipal = (Oauth)principals.getPrimaryPrincipal();
		Set<String> roles= new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		//2.1查询该用户下的组
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("uid", primaryPrincipal.getuId());
		ResponseMessage groupMsg = memberConnector.getAccountGroupName(jsonObject, null);
		Map<String, Object> groupResult =(Map<String, Object>) groupMsg.getReturnResult();
		List<Map<String,Object>>  groupResultList =(List<Map<String,Object>>) groupResult.get(Constant.COMMON_KEY_RESULT);
		  for (Map<String, Object> map : groupResultList) {
		  roles.add(String.valueOf(map.get("G_NAME"))); 
		  }
		 
		//2.2查询该用户下的角色
		
		ResponseMessage roleMsg = memberConnector.getAccountRoleName(jsonObject, null);
		Map<String, Object> roleResult=(Map<String, Object>)roleMsg.getReturnResult();
		List<Map<String,Object>> roleResultList=(List<Map<String,Object>>)roleResult.get(Constant.COMMON_KEY_RESULT);
		for (Map<String, Object> map : roleResultList) {
			roles.add(String.valueOf(map.get("ROLE_NAME")));
		}
		//2.3查询该用户下的权限
		ResponseMessage queryAccountRight = memberConnector.getAccountRightDetial(jsonObject, null);
		Map<String, Object> rightResult =(Map<String, Object>)queryAccountRight.getReturnResult();
		List<Map<String,Object>> rightResultList =(List<Map<String,Object>>) rightResult.get(Constant.COMMON_KEY_RESULT);
		for (Map<String, Object> map : rightResultList) {
			permissions.add(String.valueOf(map.get("URL")));
		}
		//3.1创建SimpleAuthorizationInfo对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roles);
		info.setStringPermissions(permissions);
		return info;
	}
    /**
     * 基本步骤：
          1.把AuthenticationToken转换为ExUsernamePasswordToken
          2.从ExUsernamePasswordToken中获取Username
          3.调用数据库方法从数据库中查询Username对应的记录
          4.若用户不存在则可以抛出UnknownAccountException异常
          5.根据用户情况,决定是否抛出其他的AuthenticationException异常
          6.根据用户信息来构建AuthenticationInfo并返回，通常使用的是SimpleAuthenticationInfo
                     盐值使用MD5盐值加密：
            1.如何把一个字符串加密为MD5
            2.shiro通过AuthenticatingRealm的credentialsMatcher属性来进行的密码比对         
     */
	//登录认证的时候调用
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.把AuthenticationToken转换为ExUsernamePasswordToken
		ExUsernamePasswordToken upt = (ExUsernamePasswordToken)token;
		//2.从ExUsernamePasswordToken中获取Username
		String username = upt.getUsername();
		String ip = upt.getIp();
		String cname=upt.getCname();
		//3.调用数据库方法从校验用户名和密码，若用户不存在则可以抛出UnknownAccountException异常		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("regName", username);
		String salt="";
		//获取盐		
		jsonObject.put("ip", ip);		
		jsonObject.put("cname", cname);
		Oauth oauthObj = new Oauth();
		oauthObj.setOauthId(username);
		jsonObject.put("vo", oauthObj);
		jsonObject.put("ipaddr", upt.getJsonObject());
		ResponseMessage loginSub = memberConnector.loginSub(jsonObject,upt.getRequest());
		if(Constant.SUCCESS_CODE.equals(loginSub.getResultCode())&&null!=loginSub.getReturnResult()) {
			HashMap<String, Object> map = (HashMap<String, Object>)loginSub.getReturnResult();
			oauthObj = FastJsonUtil.mapToObject(map, Oauth.class, Constant.COMMON_KEY_RESULT);
			salt = oauthObj.getPwd();
		}else {
			//https://blog.csdn.net/qq_42826413/article/details/103921055
			//解决shiro
			throw new IncorrectCredentialsException(loginSub.getResultMessage());
		}
		//4.根据用户信息来构建AuthenticationInfo并返回，通常使用的是SimpleAuthenticationInfo
		//以下信息是从数据库中获取的
	    //认证的实体信息，可以是username，也可以是数据库表对应的用户的实体对
		Object principal = oauthObj;
		//加密后的密码（从数据库中取的密码）
		Object hashedCredentials = oauthObj.getCredential();
		//Realm对象的name，调用父类的getName()方法即可
		String realmName= getName();
		ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		return simpleAuthenticationInfo;
	}

}
