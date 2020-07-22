package com.itour.config;

import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itour.entity.ShiroExt;
//https://www.cnblogs.com/upuptop/p/11154572.html
@Configuration
public class BeetlConf {

        @Value("${beetl.templatesPath}") String templatesPath;//模板根目录 ，比如 "templates"
        @Bean(name = "beetlConfig")
        public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
                BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
          //获取Spring Boot 的ClassLoader
           ClassLoader loader = Thread.currentThread().getContextClassLoader();
	        if(loader==null){
	            loader = BeetlConf.class.getClassLoader();
	        }
        //设置SpringBoot模板路径路径
	        ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader,
                templatesPath);
        beetlGroupUtilConfiguration.setResourceLoader(cploder);
        beetlGroupUtilConfiguration.init();
        //设置Springboot的shiro标签
        GroupTemplate groupTemplate = beetlGroupUtilConfiguration.getGroupTemplate();
        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
        groupTemplate.setClassLoader(loader);
        //如果使用了优化编译器，涉及到字节码操作，需要添加ClassLoader
        beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(loader);
        return beetlGroupUtilConfiguration;

        }

        @Bean(name = "beetlViewResolver")
        public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
                BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
                beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
                beetlSpringViewResolver.setOrder(0);
                beetlSpringViewResolver.setSuffix(".html");
                beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
                return beetlSpringViewResolver;
        }

 }
