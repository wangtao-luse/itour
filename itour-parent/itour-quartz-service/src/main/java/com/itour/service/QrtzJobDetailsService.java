package com.itour.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.model.quartz.QrtzJobDetails;
import com.itour.persist.QrtzJobDetailsMapper;
import com.itour.quartz.service.QuartzService;

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
 * 新增定时任务
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage insertJob(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Class jobClass = Class.forName(jsonObject.getString("jobClassName"));
		String name = UUID.randomUUID().toString();
		String group = jsonObject.getString("jobGroup");
		String jobDescription = jsonObject.getString("jobDescription");
		String cronExpression = jsonObject.getString("cronExpression").trim();
		String triggerName = UUID.randomUUID().toString();
		String triggerGroup = jsonObject.getString("triggerGroup");
		String triggerDescription = jsonObject.getString("triggerDescription");
		this.addJob(jobClass, name,  group, jobDescription, cronExpression, triggerName, triggerGroup,triggerDescription);
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
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
public ResponseMessage updateJob(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		
	} catch (Exception e) {
		// TODO: handle exception
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
//修改cron表达式
private void modifyJob(String name,String group,String cron) throws SchedulerException {
	TriggerKey key = TriggerKey.triggerKey(name, group);
	CronTrigger trigger = (CronTrigger)scheduler.getTrigger(key);
	trigger = trigger.getTriggerBuilder()
			.withIdentity(key)
            .withSchedule(CronScheduleBuilder.cronSchedule(cron))
            .build();
	scheduler.rescheduleJob(key, trigger);
}
private void stopJob() {
	
}
}
