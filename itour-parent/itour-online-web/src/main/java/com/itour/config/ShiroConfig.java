package com.itour.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itour.cache.MyRedisCacheManager;
import com.itour.common.resp.ResponseMessage;
import com.itour.connector.AccountConnector;
import com.itour.constant.Constant;
import com.itour.realm.MyRealm;

@Configuration
public class ShiroConfig {
	private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
	@Autowired
	AccountConnector accountConnector;
//1.Realm 代表系统资源
@Bean
public Realm myRealm(HashedCredentialsMatcher credentialsMatcher) {
	MyRealm myRealm = new MyRealm();
	myRealm.setCredentialsMatcher(credentialsMatcher);
	return myRealm;
}
//2.SecurityManager 流程控制
@Bean
public DefaultWebSecurityManager mySecurityManager(Realm myRealm) {
	 DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	 securityManager.setRealm(myRealm);
	 securityManager.setCacheManager(memoryConstrainedCacheManager());	 
	 return securityManager;
}
//3.ShiroFilterFactoryBean	请求过滤
@Bean
public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager mySecurityManager) {
	ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
	factoryBean.setSecurityManager(mySecurityManager);
	//设置登录页面,如果不设置会默认去找项目根目录下的login.jsp
	factoryBean.setLoginUrl("/account/login");
	//配置路径过滤key：是ant路径,支持*,**,?;value配置shiro的默认过滤器, anon:匿名访问;authc:需要认证(登录)才能访问; 
	//实际开发中会从数据库中读取对应的权限
	Map<String,String> filterMap = new LinkedHashMap<String, String>();
	filterMap.put("/css/**","anon");
	filterMap.put("/md/**","anon");
	filterMap.put("/js/**","anon");
	filterMap.put("/img/**","anon");
	filterMap.put("/uploaded/**","anon");
	filterMap.put("/test/**","anon");
	filterMap.put("/travel/hello","anon");
	ResponseMessage accountRightAnon = accountConnector.getAccountRightAnon(null, null);
	Map<String, Object> returnResult = accountRightAnon.getReturnResult();
	Object result = returnResult.get(Constant.COMMON_KEY_RESULT);
	if(null!=result) {
		List<Map<String, Object>> object = (List<Map<String, Object>>) returnResult.get(Constant.COMMON_KEY_RESULT);
		for (Map<String, Object> map : object) {
			Object islogin = map.get("ISLOGIN");
			Object url = map.get("URL");
			filterMap.put(url.toString(), islogin.toString());
			logger.info(url.toString()+"<<<-------------------->>>"+islogin.toString());
		}
	}
	//不能访问的情况下shiro会自动跳转到setLoginUrl()的页面;
	filterMap.put("/**", "authc");
	factoryBean.setFilterChainDefinitionMap(filterMap);
	return factoryBean;
}

//4.HashedCredentialsMatcher 告诉Shiro使用什么方式加密
@Bean
public HashedCredentialsMatcher credentialsMatcher(){
    HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
    //加密算法的名字，也可以设置MD5等其他加密算法名字
    credentialsMatcher.setHashAlgorithmName("MD5");
    //加密次数
    credentialsMatcher.setHashIterations(1024);
    return credentialsMatcher;
}

//Shiro自带的缓存 (缓存只能用于本机，那么在集群时就无法使)
@Bean
public CacheManager memoryConstrainedCacheManager() {
	MemoryConstrainedCacheManager  cacheManager = new MemoryConstrainedCacheManager();
	return cacheManager;
}
//Shiro集成Redis缓存
//@Bean
public MyRedisCacheManager redisCacheManager() {
	MyRedisCacheManager redisManager = new MyRedisCacheManager();
	return redisManager;
			
}
/**
 * 开启Shiro的注解，
 * (如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,
 * 并在必要时进行安全逻辑验证 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)
 * 和AuthorizationAttributeSourceAdvisor)即可实现此功能
 * 与springboot集成时，如果不配置，且使用了相关注解会导致页面404无法访问;
 * @return
 */
@Bean
public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
    proxyCreator.setProxyTargetClass(true);
    return proxyCreator;
}

/**
 * 开启 shiro aop注解支持.
 *
 * @param securityManager
 * @return
 */
@Bean
public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
}

}
