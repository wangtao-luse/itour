package com.itour.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.vo.AccountVo;



public class SessionUtil {
	private final static Logger logger=LoggerFactory.getLogger(SessionUtil.class);
	//权限key
    public static final String SESSION_PERMISSION="session_permission";
    
	public static Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			return subject.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static AccountVo getSessionUser(){
		AccountVo currentUser = null;
		try {
			Subject subject = SecurityUtils.getSubject();
			System.out.println("SessionUtil getSessionUser---------------------------------------------------------------------");
			System.out.println("SessionUtil getSessionUser subject ");
			if (null!=subject ) {
				PrincipalCollection principalCollection = subject.getPrincipals();
				if ( null!=principalCollection && !principalCollection.isEmpty()) {
					 Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
					 System.out.println("SessionUtil primaryPrincipal"+primaryPrincipal);
					 String jsonString = JSONObject.toJSONString(primaryPrincipal);
					 System.out.println("jsonString "+jsonString);
					 currentUser = JSONObject.parseObject(jsonString, AccountVo.class);
					 System.out.println("SessionUtil currentUser");
				}
			}
		} catch (Exception e) {
			logger.info("获取登录对象的时候出现异常:"+e.getMessage());
		}
		return currentUser;
	}

	
	/**
	 * 获取sessionId
	 * @return
	 */
	public static String getSessionId() {
		String sessionId="";
		try {
			Subject subject = SecurityUtils.getSubject();
			sessionId=subject.getSession().getId().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionId;
	}
	
}
