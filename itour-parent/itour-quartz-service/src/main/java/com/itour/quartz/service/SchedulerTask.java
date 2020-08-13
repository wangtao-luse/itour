package com.itour.quartz.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//https://www.cnblogs.com/wadmwz/p/10315481.html
@Component
/**
 * 1.springboot简单定时任务
 *   1.启动项使用@EnableScheduling注解
 *   2.创建定时任务类,使用@Scheduled标记方法
 *   缺点：实际开发中定时任务比较多,管理困难;
 * 
 * **/
public class SchedulerTask {
	private int count =0;
	private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("HH:mm:ss");
	//设置没6秒执行一次
    @Scheduled(cron = "*/6 * * * * ?")
    private void proces(){
    	System.out.println("this is scheduler task running " + dateFormat.format(new Date())+"----"+Thread.currentThread().getName());
       // System.out.println(dateFormat.format(new Date())+"this is scheduler task running "+count+++"----"+Thread.currentThread().getName());
    }
}
