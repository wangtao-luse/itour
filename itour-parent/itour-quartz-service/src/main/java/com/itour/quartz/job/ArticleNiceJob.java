package com.itour.quartz.job;


import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.itour.quartz.service.TravelCommentNiceService;
import com.itour.quartz.service.TravelCommentReplyService;
import com.itour.quartz.service.TravelNiceService;
import com.itour.quartz.service.WorkCommentLikeService;
import com.itour.quartz.service.WorkCommentReplyService;
import com.itour.quartz.service.WorkLikeService;
/**
 * 文章点赞Job
 * @author wwang
 *
 */
public class ArticleNiceJob extends QuartzJobBean {
private final static Logger logger=LoggerFactory.getLogger(ArticleNiceJob.class);
@Autowired
TravelNiceService travelNiceService;
@Autowired
TravelCommentNiceService travelCommentNiceService;
@Autowired
TravelCommentReplyService travelCommentReplyService;
@Autowired
WorkLikeService workLikeService;
@Autowired
WorkCommentLikeService workCommentLikeService;
@Autowired
WorkCommentReplyService workCommentReplyService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		//将点赞记录放入Redis中,然后定时同步到Mysql中;
		//1.从Redis缓存中取出点赞的数据;
		//2.查看该用户是否已经点赞
		//3.同步数据到数据库
		//5.每5分钟执行一次
		/**
		 * 攻略模块
		 */
		travelNice();
		
		/**
		 * 日志模块
		 */
		workLike();
		
	}

	private void workLike() {
		/***
		 * 工作日志文章点赞数刷新
		 * 
		 */
		workLikeService.insertLike();
		workCommentLikeService.insertCommentLike();
		workCommentReplyService.insertCommentReplyLike();
	}

	private void travelNice() {
		/**
		 * 旅行攻略文章点赞数刷新
		 */
		travelNiceService.insertNice();
		/**
		 * 旅行攻略文章评论点赞数刷新
		 */
		travelCommentNiceService.insertCommentNice();
		/**
		 * 旅行攻略文章评论回复点赞数刷新
		 */
		travelCommentReplyService.insertCommentReplyNice();
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
