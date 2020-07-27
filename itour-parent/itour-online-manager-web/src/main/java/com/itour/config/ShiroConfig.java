package com.itour.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itour.realm.LoginRealm;

@Configuration
public class ShiroConfig {
/**
 * 配置SecurityManager
 * @return
 */
@Bean
public SecurityManager securityManager() {//可配置缓存和Realm
    DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
    defaultSecurityManager.setRealm(LoginRealm());
    return defaultSecurityManager;
}
/**
 * 配置Realm
 * @return
 */
@Bean
public LoginRealm LoginRealm() {
	LoginRealm customRealm = new LoginRealm();
    customRealm.setCredentialsMatcher(credentialsMatcher());
    return customRealm;
}
//配置LifecycleBeanPostProcessor,可以自动调用配置在Spring IOC容器中的shiro bean的生命周期的方法;
@Bean	
public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
	LifecycleBeanPostProcessor lifecycleBeanPostProcessor = new LifecycleBeanPostProcessor();
	return lifecycleBeanPostProcessor;
}
@Bean //启用IOC 容器中使用shiro注解，但是必须在配置类LifecycleBeanPostProcessor之后才可以使用。
public DefaultAdvisorAutoProxyCreator  defaultAdvisorAutoProxyCreator() {
	DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
	defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
	return defaultAdvisorAutoProxyCreator;
}
@Bean //使其支持注解
public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
}
	/**
	 * shiro的过滤器
	 * @param securityManager
	 * @return
	 */
@Bean("shiroFilter")
public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	    shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");// 未授权界面(没有权限);
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/member/login");
		/* <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/css/**","anon");
		filterChainDefinitionMap.put("/js/**","anon");
		filterChainDefinitionMap.put("/img/**","anon");	
		filterChainDefinitionMap.put("/easyui/**","anon");	
		filterChainDefinitionMap.put("/member/login", "anon");
		filterChainDefinitionMap.put("/member/loginSub", "anon");
		filterChainDefinitionMap.put("http://pv.sohu.com/**", "anon");
		filterChainDefinitionMap.put("http://ip-api.com/**", "anon");
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/shiro/logout", "logout");		
		/* /主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证 */
		filterChainDefinitionMap.put("/**", "authc");//不能访问的情况下shiro会自动跳转到setLoginUrl()的页面;
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
}



@Bean
public HashedCredentialsMatcher credentialsMatcher(){
    HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
    //加密算法的名字，也可以设置SHA-1等其他加密算法名字
    credentialsMatcher.setHashAlgorithmName("SHA-1");
    //加密次数
    credentialsMatcher.setHashIterations(1024);
    return credentialsMatcher;
}




}
