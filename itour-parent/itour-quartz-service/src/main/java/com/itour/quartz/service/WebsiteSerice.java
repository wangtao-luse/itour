package com.itour.quartz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.HttpDataUtil;
import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.RedisKey;
import com.itour.dictionary.api.DictionaryApi;
import com.itour.help.DateHelper;
import com.itour.model.dictionary.WebsiteIpInfo;
import com.itour.model.dictionary.WebsiteVisitInfo;
@Service
public class WebsiteSerice {
	private final static Logger logger=LoggerFactory.getLogger(WebsiteSerice.class);
	@Autowired
	DictionaryApi dictionaryApi;
	@Autowired
    RedisManager redisManager;
	public void insertWebsiteInfo() {
		try {
			//获取独立IP列表
			Set<Object> smembers = redisManager.smembers(RedisKey.KEY_ITOUR_IP_LIST_DATE);
			//站点访问次数
			String vv = redisManager.get(RedisKey.KEY_ITOUR_VV_COUNT);
			//站点独立访客
			String uv = redisManager.get(RedisKey.KEY_ITOUR_UV_COUNT);
			//站点访问量
			String pv = redisManager.get(RedisKey.KEY_ITOUR_PV_COUNT);
			
			int size = smembers.size();
			//每日独立IP数量
			Long today_ip = Long.getLong(String.valueOf(size));
			//			
			Long today_vv = Long.getLong(vv);
			//
			Long today_uv = Long.getLong(uv);
			//网站访问量
			Long today_pv = Long.getLong(pv);
			
			List<WebsiteVisitInfo> list = new ArrayList<WebsiteVisitInfo>();
			List<WebsiteIpInfo> ipList = new ArrayList<WebsiteIpInfo>();
			for (Object object : smembers) {
				//1.组装网站访问记录数据
				WebsiteVisitInfo websiteInfo = new WebsiteVisitInfo();
				websiteInfo.setIp(today_ip);
				websiteInfo.setVv(today_vv);
				websiteInfo.setUv(today_uv);
				websiteInfo.setPv(today_pv);				
				websiteInfo.setCountDate(DateHelper.currentLongDate());
				list.add(websiteInfo);
				//2.组装独立IP记录
				WebsiteIpInfo info = new WebsiteIpInfo();
				String[] split = object.toString().split("::");
				String ip =split[0];
				String date =split[1];
				info.setIpAddr(ip);				
				info.setIpVisitTime(DateHelper.getLongDate(date, DateHelper.FMT_DATETIME));
				info.setCreateDate(DateHelper.currentLongDate());
				ipList.add(info);
				
			}
			//批量插入网站访问数据
			JSONObject jsonObject = new JSONObject();
			jsonObject.put(Constant.COMMON_KEY_ARR, list);
			RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
			ResponseMessage visizt = dictionaryApi.saveOrUpdateVisitInfoBatch(requestMessage);
			//批量插入IP记录
			jsonObject.clear();
			jsonObject.put(Constant.COMMON_KEY_ARR, ipList);
			RequestMessage postData = HttpDataUtil.postData(jsonObject, null);
			ResponseMessage ipInfo = dictionaryApi.saveOrUpdateIpInfoBatch(postData);
			//清除缓存
			redisManager.del(RedisKey.KEY_ITOUR_IP_LIST);
			redisManager.del(RedisKey.KEY_ITOUR_IP_LIST_DATE);
			redisManager.del(RedisKey.KEY_ITOUR_VV_COUNT);
			redisManager.del(RedisKey.KEY_ITOUR_UV_COUNT);
			redisManager.del(RedisKey.KEY_ITOUR_PV_COUNT);
			logger.info("网站统计执行成功！！！");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("网站统计执行失败！！！");
		}
		
	}
	
}
