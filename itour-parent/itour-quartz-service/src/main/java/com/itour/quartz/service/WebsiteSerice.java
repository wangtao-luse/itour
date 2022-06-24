package com.itour.quartz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itour.common.redis.RedisManager;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.RedisKey;
import com.itour.dictionary.api.DictionaryApi;
import com.itour.model.dictionary.WebsiteIpInfo;
import com.itour.model.dictionary.WebsiteVisitInfo;
import com.itour.util.DateUtil;
import com.itour.util.FastJsonUtil;
@Service
public class WebsiteSerice {
	private final static Logger logger=LoggerFactory.getLogger(WebsiteSerice.class);
	@Autowired
	DictionaryApi dictionaryApi;
	@Autowired
    RedisManager redisManager;
	public void insertWebsiteInfo() {
		try {
			Set<Object> smembers = redisManager.smembers(RedisKey.KEY_ITOUR_IP_LIST);
			String vv = redisManager.get(RedisKey.KEY_ITOUR_VV_COUNT);
			String uv = redisManager.get(RedisKey.KEY_ITOUR_UV_COUNT);
			String pv = redisManager.get(RedisKey.KEY_ITOUR_PV_COUNT);
			ResponseMessage selectRecentlyOne = dictionaryApi.selectRecentlyOne(null);
			WebsiteVisitInfo visitInfo = null;
			int size = smembers.size();
			Long today_ip = Long.getLong(String.valueOf(size));
			Long today_vv = Long.getLong(vv);
			Long today_uv = Long.getLong(uv);
			Long today_pv = Long.getLong(pv);
			if (ResponseMessage.isSuccessResult(selectRecentlyOne)) {
				 visitInfo = FastJsonUtil.mapToObject(selectRecentlyOne.getReturnResult(), WebsiteVisitInfo.class);
			}
			List<WebsiteVisitInfo> list = new ArrayList<WebsiteVisitInfo>();
			List<WebsiteIpInfo> ipList = new ArrayList<WebsiteIpInfo>();
			for (Object object : smembers) {
				//1.组装网站访问记录数据
				WebsiteVisitInfo websiteInfo = new WebsiteVisitInfo();
				websiteInfo.setIp(today_ip);
				if(visitInfo == null) {//网站访问记录表中无数据
					websiteInfo.setVv(today_vv);
					websiteInfo.setUv(today_uv);
					websiteInfo.setPv(today_pv);
				}else {
					websiteInfo.setVv(today_vv - visitInfo.getVv());
					websiteInfo.setUv(today_uv - visitInfo.getUv());
					websiteInfo.setPv(today_pv - visitInfo.getPv());
				}
				websiteInfo.setCountDate(DateUtil.currentLongDate());
				list.add(websiteInfo);
				WebsiteIpInfo info = new WebsiteIpInfo();
				String[] split = object.toString().split("::");
				String ip = split[0];	
				info.setIpAddr(ip);
				long ipVisitTime = DateUtil.getLongDate(split[1], DateUtil.FMT_DATETIME_SHORT);
				info.setIpVisitTime(ipVisitTime);
				ipList.add(info);
				
			}
			logger.info("网站统计执行成功！！！");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("网站统计执行失败！！！");
		}
		
	}
	
}
