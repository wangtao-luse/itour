package com.itour.quartz.study;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
/**
 * 1.springboot简单定时任务https://www.cnblogs.com/wadmwz/p/10315481.html
 *   1.启动项使用@EnableScheduling注解
 *   2.创建定时任务类,使用@Scheduled标记方法
 *    缺点：实际开发中定时任务比较多,管理困难;
 * 2.springboot集成quartz
 *   https://www.cnblogs.com/imyanger/p/11828301.html
 *   1.定义工作任务的Job
 *   2.构建JobDetail关联工作任务的Job
 *   3.定义触发器Trigger,将Trigger注册到Scheduler;
 * **/
public class SchedulerTask {
	private int count =0;
	private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("HH:mm:ss");
	//设置没6秒执行一次
    @Scheduled(cron = "*/6 * * * * ?")
    private void proces(){
    	System.out.println("now time is " + dateFormat.format(new Date())+"----"+Thread.currentThread().getName());
        System.out.println(dateFormat.format(new Date())+"this is scheduler task running "+count+++"----"+Thread.currentThread().getName());
    }
}
