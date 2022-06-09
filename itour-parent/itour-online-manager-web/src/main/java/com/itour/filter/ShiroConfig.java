package com.itour.filter;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;

public class ShiroConfig {
	/**
	 * 1.配置shiro拦截器ShiroFilter
	 * @param securityManager 安全管理器
	 * @return  ShiroFilterFactoryBean
	 */
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		    //1.构建ShiroFilterFactoryBean
			ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
			//2.获取Filters
			Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();			
            //4.设置SecurityManager
		    shiroFilterFactoryBean.setSecurityManager(securityManager);
		    //5.设置未授权界面(没有权限);
			shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");
			//6.设置登录成功后要跳转的链接
			shiroFilterFactoryBean.setSuccessUrl("/index");
			//7.设置登录页面,如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
			shiroFilterFactoryBean.setLoginUrl("/member/login");
			/*8. authc:url必须认证(登录)通过才可以访问; anon:url可以匿名访问 (无需认证)*/
			Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
			filterChainDefinitionMap.put("/css/**","anon");
			filterChainDefinitionMap.put("/js/**","anon");
			filterChainDefinitionMap.put("/img/**","anon");	
			filterChainDefinitionMap.put("/easyui/**","anon");	
			filterChainDefinitionMap.put("/member/login", "anon");
			filterChainDefinitionMap.put("/member/loginSub", "anon");
			filterChainDefinitionMap.put("/verify/**", "anon");	
			//8.0实际开发中,需要从数据库获取需要登录url,设置的到filterChainDefinitionMap中;
			//8.1配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
			filterChainDefinitionMap.put("/shiro/logout", "logout");		
			/**
			 * 8.2 put("/**", "authc");必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证 ;
			 *    不能访问的情况下shiro会自动跳转到setLoginUrl()的页面;
			 */
			filterChainDefinitionMap.put("/**", "authc");
			shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
			return shiroFilterFactoryBean;
	}
}
