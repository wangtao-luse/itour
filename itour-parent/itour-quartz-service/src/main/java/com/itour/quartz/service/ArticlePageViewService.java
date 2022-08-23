package com.itour.quartz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.WorkApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestMessage;
import com.itour.constant.Constant;
import com.itour.constant.RedisKey;
import com.itour.help.DateHelper;
import com.itour.model.work.WorkIpInfo;
@Service
public class ArticlePageViewService {
	@Autowired
	WorkApi workApi;
	private final static Logger logger=LoggerFactory.getLogger(WebsiteSerice.class);
	@Autowired
    RedisManager redisManager;
	public void workInfoData() {
		try {
			//1.ip::yyyy-MM-dd HH:mm:ss::文章Id
			Set<Object> smembers = redisManager.smembers(RedisKey.KEY_WORKINFO_IP_LIST_DATE);
			List<WorkIpInfo> list = new ArrayList<WorkIpInfo>();
			for (Object object : smembers) {
				WorkIpInfo ipInfo = new WorkIpInfo();
				String[] split = object.toString().split("::");
				         String ip = split[0];
				         String time = split[1];
				         String tid = split[2];
				         ipInfo.setIpAddr(ip);
				         ipInfo.setIpVisitTime(DateHelper.getLongDate(time, DateHelper.FMT_DATETIME));
				         ipInfo.setCreateDate(DateHelper.currentLongDate());
				         ipInfo.setTid(Long.getLong(tid));
				         list.add(ipInfo);
				         
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(Constant.COMMON_KEY_ARR, list);
			RequestMessage postData = HttpDataUtil.postData(jsonObject, null);
			this.workApi.saveOrupdateWorkIpInfo(postData);
			this.redisManager.del(RedisKey.KEY_WORKINFO_IP_LIST);
			this.redisManager.del(RedisKey.KEY_WORKINFO_IP_LIST_DATE);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}
		
	}
}
