package com.itour.quartz.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;



@Service
public class JobService {
	private static Logger logger = LoggerFactory.getLogger(JobService.class);

	@Autowired
	private Scheduler scheduler;

	
	
	
	private String getTime(Object obj){
		if(obj == null)
			return "";
		String time = obj.toString();
		if("0".equals(time))
			return time;
		if(time.length() != 13)
			return time;
		
		return fmtLongTimeToString(time);
	}
	
	
	private  Date paraseToDate(String time){
		SimpleDateFormat sf = new SimpleDateFormat(DATE_PATTERN_FORMAT);
		try {
			Date date = sf.parse(time);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private final static String DATE_PATTERN_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String fmtLongTimeToString(String times){
		SimpleDateFormat sf = new SimpleDateFormat(DATE_PATTERN_FORMAT);
		Date date = new Date();
		date.setTime(Long.valueOf(times));
		return sf.format(date);
	}
	
	
	
	/**
	 * 添加触发器
	 * @param jobName
	 * @param jobGroup
	 * @param triggerName
	 * @param triggerGroup
	 * @param JobClassName
	 * @param jobDescription
	 * @param cronExpression
	 * @param startTime
	 * @param endTime
	 */
	public void schedule(String jobName, String jobGroup, String triggerName,String triggerGroup,String jobClassName,String jobDescription,String cronExpression,String startTime,String endTime) {

		CronTriggerImpl trigger = new CronTriggerImpl();
		
		TriggerKey triggerKey = new TriggerKey(triggerName.trim(), triggerGroup.trim());
		
		try {
			trigger.setCronExpression(new CronExpression(cronExpression));
		} catch (ParseException e2) {
			e2.printStackTrace();
			throw new BaseException("cron表达式格式错误");
		}
		
		if(startTime!=null && !"".equals(startTime)){
			Date date = paraseToDate(startTime);
			trigger.setStartTime(date);
		}
		if(endTime!=null && !"".equals(endTime)){
			Date date = paraseToDate(endTime);
			trigger.setEndTime(date);
		}
		
		trigger.setJobName(jobName);
		trigger.setJobGroup(jobGroup);
		trigger.setKey(triggerKey);
		trigger.setDescription(jobDescription);
		
		
		
		Class clz = null;
		try {
			clz = Class.forName(jobClassName.trim());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		JobDetail jobDetail = JobBuilder.newJob(clz).storeDurably(true).withIdentity(jobName, jobGroup).withDescription(jobDescription).build();  
		

		try {
			scheduler.addJob(jobDetail, true);
			if (scheduler.checkExists(triggerKey)) {
				scheduler.rescheduleJob(triggerKey, trigger);
			} else {
				scheduler.scheduleJob(trigger);
			}
		} catch (SchedulerException e) {
			throw new IllegalArgumentException(e);
		}
		
	}
	
	public ResponseMessage schedule(RequestMessage requestMessage){
		JSONObject jsonObject = requestMessage.getBody().getContent();
		
		ResponseMessage responseMessage = ResponseMessage.getFailed("接受失败");
		
		try {
			String jobName = jsonObject.getString("jobName");
			String jobGroup = "DEFAULT";
			String triggerName = jsonObject.getString("triggerName");
			String triggerGroup = "DEFAULT";
			String jobClassName = jsonObject.getString("jobClassName");
			String jobDescription = jsonObject.getString("jobDescription");
			String cronExpression = jsonObject.getString("cronExpression");
			String startTime = jsonObject.getString("startTime");
			String endTime = jsonObject.getString("endTime");
			
			this.schedule(jobName, jobGroup, triggerName, triggerGroup, jobClassName, jobDescription, cronExpression, startTime, endTime);
			responseMessage = ResponseMessage.getSucess();
		} catch (BaseException e) {
			logger.error(getStackMsg(e));
			e.printStackTrace();
			throw new BaseException(e.getMessage());
		}catch (Exception e) {
			logger.error(getStackMsg(e));
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
	}
	
	private static String getStackMsg(Exception e) {

		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stackArray = e.getStackTrace();
		for (int i = 0; i < stackArray.length; i++) {
			StackTraceElement element = stackArray[i];
			sb.append(element.toString() + "\n");
		}
		return sb.toString();
	}
	
	/**
	 * 获取所有的job
	 * @return
	 */
	public List<JobDetail> getJobs(){
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			
			List<JobDetail> jobDetails = new ArrayList<JobDetail>();
			
			for (JobKey key : jobKeys) {
				jobDetails.add(scheduler.getJobDetail(key));
			}
			return jobDetails;
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		 
		 return null;
	}
	
	/*
	 * //获取所有的触发器 public List<PageTrigger> getTriggersInfo(){ try {
	 * GroupMatcher<TriggerKey> matcher = GroupMatcher.anyTriggerGroup();
	 * Set<TriggerKey> Keys = scheduler.getTriggerKeys(matcher); List<PageTrigger>
	 * triggers = new ArrayList<PageTrigger>();
	 * 
	 * for (TriggerKey key : Keys) { Trigger trigger = scheduler.getTrigger(key);
	 * PageTrigger pageTrigger = new PageTrigger();
	 * pageTrigger.setName(trigger.getJobKey().getName());
	 * pageTrigger.setGroup(trigger.getJobKey().getGroup());
	 * pageTrigger.setStatus(scheduler.getTriggerState(key)+""); if (trigger
	 * instanceof SimpleTrigger) { SimpleTrigger simple = (SimpleTrigger) trigger;
	 * pageTrigger.setExpression("重复次数:"+ (simple.getRepeatCount() == -1 ? "无限" :
	 * simple.getRepeatCount()) +",重复间隔:"+(simple.getRepeatInterval()/1000L));
	 * pageTrigger.setDesc(simple.getDescription()); } if (trigger instanceof
	 * CronTrigger) { CronTrigger cron = (CronTrigger) trigger;
	 * pageTrigger.setExpression(cron.getCronExpression());
	 * pageTrigger.setDesc(cron.getDescription()); } triggers.add(pageTrigger); }
	 * return triggers; } catch (SchedulerException e) { e.printStackTrace(); }
	 * return null; }
	 */
	
	//暂停任务
	public void stopJob(String name, String group){
		JobKey key = new JobKey(name, group);
		try {
			scheduler.pauseJob(key);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	//暂停任务
	public ResponseMessage stopJob(RequestMessage requestMessage){
		
		JSONObject jsonObject = requestMessage.getBody().getContent();

		ResponseMessage responseMessage = ResponseMessage.getSucess();

		try {
			
			String jobName = (String)jsonObject.get("jobName");
			String jobGroup = (String)jsonObject.get("jobGroup");
			this.stopJob(jobName, jobGroup);
			
		} catch (Exception e) {
			logger.error("定时任务管理暂停任务报错：", e);
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
	//恢复任务
	public void restartJob(String name, String group){
		JobKey key = new JobKey(name,group);
		try {
			scheduler.resumeJob(key);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	//恢复任务
	public ResponseMessage restartJob(RequestMessage requestMessage) {

		JSONObject jsonObject = requestMessage.getBody().getContent();

		ResponseMessage responseMessage = ResponseMessage.getSucess();

		try {

			String jobName = (String) jsonObject.get("jobName");
			String jobGroup = (String) jsonObject.get("jobGroup");
			this.restartJob(jobName, jobGroup);

		} catch (Exception e) {
			logger.error("定时任务管理恢复任务报错：", e);
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
	//立马执行一次任务
	public void startNowJob(String name, String group){
		try {
			JobKey key = new JobKey(name, group);
			JobDetail job = JobBuilder.newJob(
						scheduler.getJobDetail(key).getJobClass())
					.storeDurably()
					.build();
			scheduler.addJob(job, false);
			scheduler.triggerJob(job.getKey());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	//立马执行一次任务
	public ResponseMessage startNowJob(RequestMessage requestMessage) {

		JSONObject jsonObject = requestMessage.getBody().getContent();

		ResponseMessage responseMessage = ResponseMessage.getSucess();

		try {

			String jobName = (String) jsonObject.get("jobName");
			String jobGroup = (String) jsonObject.get("jobGroup");
			this.startNowJob(jobName, jobGroup);

		} catch (Exception e) {
			logger.error("定时任务管理立马执行一次任务报错：", e);
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
	//删除任务
	public void delJob(String name, String group){
		JobKey key = new JobKey(name,group);
		try {
			scheduler.deleteJob(key);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	//删除任务
	public ResponseMessage delJob(RequestMessage requestMessage) {

		JSONObject jsonObject = requestMessage.getBody().getContent();

		ResponseMessage responseMessage = ResponseMessage.getSucess();

		try {

			String jobName = (String) jsonObject.get("jobName");
			String jobGroup = (String) jsonObject.get("jobGroup");
			this.delJob(jobName, jobGroup);

		} catch (Exception e) {
			logger.error("定时任务管理立马执行一次任务报错：", e);
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
	//修改触发器时间
	public void modifyTrigger(String name,String group,String cron){
		try {
			TriggerKey key = TriggerKey.triggerKey(name, group);
			Trigger trigger = scheduler.getTrigger(key);
			
			CronTrigger newTrigger = (CronTrigger) TriggerBuilder.newTrigger()
					.withIdentity(key)
					.withSchedule(CronScheduleBuilder.cronSchedule(cron))
					.build();
			scheduler.rescheduleJob(key, newTrigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
	
	//暂停调度器
	public void stopScheduler(){
		 try {
			if (!scheduler.isInStandbyMode()) {
				scheduler.standby();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}



	
}
