package com.itour.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.quartz.QrtzJobDetails;
import com.itour.persist.QrtzJobDetailsMapper;
import com.itour.quartz.service.QuartzService;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-08-28
 */
@Service
public class QrtzJobDetailsService extends ServiceImpl<QrtzJobDetailsMapper, QrtzJobDetails> {
	@Autowired
	QuartzService quartzService;
	@Autowired
    private Scheduler scheduler;

	/**
	 * 定时任务列表
	 */
	public ResponseMessage queryJobList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			JSONObject jsonVo = jsonObject.getJSONObject("vo");
		    JSONObject pageVo = jsonObject.getJSONObject("page"); 
			Page page = null;
			if(pageVo!=null) {
				page = pageVo.toJavaObject(Page.class);
			}
			String jsonString = jsonVo.toJSONString();
			Map map =null;
			if(!StringUtils.isEmpty(jsonString)) {
				 map = (Map)JSON.parse(jsonString);
			}
			
			List<Map<String, Object>> queryTriggersList = this.baseMapper.queryTriggersList(page,map);
			responseMessage.setReturnResult(page.setRecords(queryTriggersList));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
	}
	/**
	 * 定时任务单条查询
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage getTrigger(RequestMessage requestMessage) {
		ResponseMessage responseMessage =ResponseMessage.getSucess();
		JSONObject jsonObject = requestMessage.getBody().getContent();
		String jsonString = jsonObject.toJSONString();
		Map map =null;
		if(!StringUtils.isEmpty(jsonString)) {
			 map = (Map)JSON.parse(jsonString);
		}
		Map<String, Object> trigger = this.baseMapper.getTrigger(map);
		responseMessage.setReturnResult(trigger);
		return responseMessage;
	}
/**
 * 新增定时任务
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage insertJob(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Map map = (Map)JSON.parse(jsonObject.toJSONString());
		Map<String, Object> trigger = this.baseMapper.getTrigger(map);
		if(trigger!=null) {
			Object object = trigger.get("jobClassName");
			throw new BaseException(ExceptionInfo.EXCEPTION_QUARTZ);
		}
		
		Class jobClass = Class.forName(jsonObject.getString("jobClassName"));
		String name = UUID.randomUUID().toString();
		String group = jsonObject.getString("jobGroup");
		String jobDescription = jsonObject.getString("jobDescription");
		String cronExpression = jsonObject.getString("cronExpression").trim();
		String triggerName = UUID.randomUUID().toString();
		String triggerGroup = jsonObject.getString("triggerGroup");
		String triggerDescription = jsonObject.getString("triggerDescription");
		this.addJob(jobClass, name,  group, jobDescription, cronExpression, triggerName, triggerGroup,triggerDescription);
	}catch (BaseException e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(ExceptionInfo.EXCEPTION_QUARTZ);
	}catch (ClassNotFoundException e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(ExceptionInfo.CLASSNOTFOUNDEXCEPTION+":"+e.getMessage());
	}catch (SchedulerException e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 修改定时任务
 * @param requestMessage
 * @return
 */
public ResponseMessage updateJob(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		String jobName = jsonObject.getString("jobName");
		String jobGroup = jsonObject.getString("jobGroup");
		String triggerName = jsonObject.getString("triggerName");
		String triggerGroup = jsonObject.getString("triggerGroup");
		String cron = jsonObject.getString("cronExpression");
		String triggerDescription = jsonObject.getString("triggerDescription");
		String jobDescription = jsonObject.getString("jobDescription");
		Class jobClass = Class.forName(jsonObject.getString("jobClassName"));
		modifyJob(jobClass, jobName, jobGroup, jobDescription, cron, triggerName, triggerGroup, triggerDescription);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}

/**
 * 删除定时任务
 * @param requestMessage
 * @return
 */
public ResponseMessage delJob(RequestMessage requestMessage) {
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		String jobName = jsonObject.getString("jobName");
		String jobgroup = jsonObject.getString("jobGroup");
		boolean delJob = this.delJob(jobName, jobgroup);
		if(delJob) {
			return ResponseMessage.getSucess();
		}else {
			return ResponseMessage.getFailed();
		}
	} catch (Exception e) {
		// TODO: handle exception
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	
}
/**
 * 暂停定时任务
 * @param requestMessage
 * @return
 */
public ResponseMessage pauseJob(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		String jobName = jsonObject.getString("jobName");
		String jobGroupName = jsonObject.getString("jobGroup");
		this.pauseJob(jobName, jobGroupName);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 恢复定时任务
 * @param requestMessage
 * @return
 */
public ResponseMessage resumeJob(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		String jobName = jsonObject.getString("jobName");
		String jobGroupName = jsonObject.getString("jobGroup");
		this.resumeJob(jobName, jobGroupName);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 执行定时任务
 * @param requestMessage
 * @return
 */
public ResponseMessage startNowJob(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		String jobName = jsonObject.getString("jobName");
		String jobGroupName = jsonObject.getString("jobGroup");
		this.startNowJob(jobName, jobGroupName);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 
 * @param jobClass JOb全路径名
 * @param name  JOb名称
 * @param group JOb组名称
 * @param jobDescription JOB描述
 * @param cronExpression cron表达式
 * @param triggerName  trigger名称
 * @param triggerGroup trigger组名称
 * @param triggerDescription trigger描述
 * @throws SchedulerException
 */
private void addJob(Class <? extends Job> jobClass,String name,String group,String jobDescription,String cronExpression,String triggerName,String triggerGroup,String triggerDescription) throws SchedulerException {
	//1.构建JobDetail关联工作任务的Job
	JobDetail jobDetail = JobBuilder.newJob(jobClass)
			.storeDurably()
			.withIdentity(name, group)
			.withDescription(jobDescription)
			.build();
	//2.定义触发器Trigger,将Trigger注册到Scheduler;
	CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(cronExpression);
	CronTrigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withSchedule(cronSchedule)
				.withIdentity(triggerName, triggerGroup)
				.withDescription(triggerDescription)
				.build();
	//3.添加JOb到调度程序
	scheduler.scheduleJob(jobDetail, trigger);
}
/**
 * 修改定时任务
 * @param jobClass
 * @param name
 * @param group
 * @param jobDescription
 * @param cronExpression
 * @param triggerName
 * @param triggerGroup
 * @param triggerDescription
 * @throws SchedulerException
 */
private void modifyJob(Class <? extends Job> jobClass,String name,String group,String jobDescription,String cronExpression,String triggerName,String triggerGroup,String triggerDescription) throws SchedulerException {
	//1.构建JobDetail关联工作任务的Job
	JobDetail jobDetail = JobBuilder.newJob(jobClass)
			.storeDurably()
			.withIdentity(name, group)
			.withDescription(jobDescription)
			.build();
	//2.定义触发器Trigger,将Trigger注册到Scheduler;
	CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(cronExpression);
	CronTrigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withSchedule(cronSchedule)
				.withIdentity(triggerName, triggerGroup)
				.withDescription(triggerDescription)
				.build();
	//3.添加JOb到调度程序
	TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
	scheduler.addJob(jobDetail, true);
	scheduler.rescheduleJob(triggerKey, trigger);
}
private void insertOrupdateJOb(Class <? extends Job> jobClass,String name,String group,String jobDescription,String cronExpression,String triggerName,String triggerGroup,String triggerDescription) throws SchedulerException {
	//1.构建JobDetail关联工作任务的Job
		JobDetail jobDetail = JobBuilder.newJob(jobClass)
				.storeDurably()
				.withIdentity(name, group)
				.withDescription(jobDescription)
				.build();
		//2.定义触发器Trigger,将Trigger注册到Scheduler;
		CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(cronExpression);
		CronTrigger trigger = TriggerBuilder.newTrigger()
					.forJob(jobDetail)
					.withSchedule(cronSchedule)
					.withIdentity(triggerName, triggerGroup)
					.withDescription(triggerDescription)
					.build();
		TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroup);
		boolean checkExists = scheduler.checkExists(triggerKey );
		if(checkExists) {
			//3.重新添加到调度程序
			scheduler.rescheduleJob(triggerKey, trigger);
		}else {
			//3.添加JOb到调度程序
			scheduler.scheduleJob(jobDetail, trigger);
		}
		
}
/**
 * 修改cron表达式
 * @param triggerName
 * @param triggerGroup
 * @param cron
 * @throws SchedulerException
 */
private void modifyTrigger(String triggerName,String triggerGroup,String cron) throws SchedulerException {
	TriggerKey key = TriggerKey.triggerKey(triggerName, triggerGroup);
	CronTrigger trigger = (CronTrigger)scheduler.getTrigger(key);
	trigger = trigger.getTriggerBuilder()
			.withIdentity(key)
            .withSchedule(CronScheduleBuilder.cronSchedule(cron))
            .build();
	scheduler.rescheduleJob(key, trigger);
	
}
/**
 * 修改trigger描述
 * @param triggerName
 * @param triggerGroup
 * @param cron
 * @throws SchedulerException
 */
private void modifyTriggerDesc(String triggerName,String triggerGroup,String triggerDescription) throws SchedulerException {
	TriggerKey key = TriggerKey.triggerKey(triggerName, triggerGroup);
	CronTrigger trigger = (CronTrigger)scheduler.getTrigger(key);
	trigger = trigger.getTriggerBuilder()
			.withIdentity(key).withDescription(triggerDescription)			
			.build();
	scheduler.rescheduleJob(key, trigger);
	
}
/**
 * 修改job描述
 * @param jobName
 * @param jobGroup
 * @param jobDescription
 * @throws SchedulerException 
 */

private void modifyJobDesc(String jobName, String jobGroup, String triggerName,String triggerGroup,String jobDescription) throws SchedulerException {
	// TODO Auto-generated method stub
	JobKey jobKey = new JobKey(jobName, jobGroup);
    JobDetail jobDetail = scheduler.getJobDetail(jobKey);
    jobDetail.getJobDataMap().put("desciption", jobDescription);
    TriggerKey key = TriggerKey.triggerKey(triggerName, triggerGroup);
    Trigger trigger = scheduler.getTrigger(key);
	scheduler.scheduleJob(jobDetail, trigger);
}
/**
 * 删除定时任务
 * @param name
 * @param group
 * @return
 */
private boolean delJob(String name, String group){
	boolean bl=true;
	try {
		 bl = scheduler.deleteJob(new JobKey(name,group));
	} catch (SchedulerException e) {
		e.printStackTrace();
		bl=false;
	}
	return bl;
}
/**
 * 暂停一个job
 * 
 * @param jobName
 * @param jobGroupName
 */
private void pauseJob(String jobName, String jobGroupName) {
    try {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        scheduler.pauseJob(jobKey);
    } catch (SchedulerException e) {
        e.printStackTrace();
    }
}
/**
 * 恢复一个job
 * 
 * @param jobName
 * @param jobGroupName
 */
public void resumeJob(String jobName, String jobGroupName) {
    try {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        scheduler.resumeJob(jobKey);
    } catch (SchedulerException e) {
        e.printStackTrace();
    }
}
/**
 * 立马执行一次任务
 * @param name
 * @param group
 */
	private void startNowJob(String name, String group){
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
}
