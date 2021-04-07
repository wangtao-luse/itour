package com.itour.quartz.job;


import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.itour.quartz.service.TravelNiceService;
public class TravelNiceJob extends QuartzJobBean {
private final static Logger logger=LoggerFactory.getLogger(TravelNiceJob.class);
@Autowired
TravelNiceService travelNiceService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		//将点赞记录放入Redis中,然后定时同步到Mysql中;
		//1.从Redis缓存中取出点赞的数据;
		//2.查看该用户是否已经点赞
		//3.同步数据到数据库
		//5.每5分钟执行一次
		travelNiceService.insertNice();
		
	}

public static void main(String[] args) {
	//join();
	 String [] s = { "a", "b", "c", "d", "e" };
	 String join2 = String.join(",", s);
	 System.out.println(join2);
	 
	 
	List<String> tidList = new ArrayList<String>();
	tidList.add("1");
	tidList.add("2");
	tidList.add("3");
	tidList.add("4");
	String [] arr = new String[tidList.size()];
	String[] array = tidList.toArray(arr);
	System.out.println(String.join(",", array));
	
}
}
