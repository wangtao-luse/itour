package com.itour.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

@Component  //https://www.imooc.com/article/25585
//解决SpringBoot不能再Quartz中注入Bean的问题
public class SpringJobFactory extends AdaptableJobFactory  {  
	/**
     * AutowireCapableBeanFactory接口是BeanFactory的子类
     * 可以连接和填充那些生命周期不被Spring管理的已存在的bean实例
     */
    @Autowired    
    private AutowireCapableBeanFactory capableBeanFactory;    
    /**
     * 创建Job实例
     */
    @Override    
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {    
        // 调用父类的方法 ,实例化对象  
        Object jobInstance = super.createJobInstance(bundle);    
         // 进行注入（Spring管理该Bean）  
        capableBeanFactory.autowireBean(jobInstance);  
        //返回对象
        return jobInstance;    
    }    
}  

