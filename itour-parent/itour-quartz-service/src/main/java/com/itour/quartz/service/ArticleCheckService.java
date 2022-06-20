package com.itour.quartz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.TravelApi;
import com.itour.api.WorkApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.init.SensitiveWordFilter;
import com.itour.model.travel.TravelInfo;
import com.itour.model.travel.vo.ViewTravelinfoWeekinfo;
import com.itour.model.work.WorkInfo;
import com.itour.model.work.vo.ViewWorkinfoWorktext;
import com.itour.util.DateUtil;
import com.itour.util.FastJsonUtil;
@Service
public class ArticleCheckService {
	private static final Logger logger = LoggerFactory.getLogger(ArticleCheckService.class);
	@Autowired
	private TravelApi travelApi;
	@Autowired
	private WorkApi workApi;
	@Autowired
    private SensitiveWordFilter sensitiveWordFilter;
	//周末攻略审核
	public void checkTravel() {		
		// TODO Auto-generated method stub
		//1.查询待审核的攻略记录;
		JSONObject jsonObject = new JSONObject();
		TravelInfo info = new TravelInfo();
		info.setStatus(Constant.COMMON_STATUS_CHECKING);
		jsonObject.put(Constant.COMMON_KEY_VO, info);
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
		ResponseMessage queryTravelInfoList = this.travelApi.queryViewTravelinfoWeekinfoList(requestMessage);
		if(ResponseMessage.isSuccessResult(queryTravelInfoList)) {
			//2.检查是否含有敏感字
			List<TravelInfo> list = new ArrayList<TravelInfo>();
			List<ViewTravelinfoWeekinfo> infoList = FastJsonUtil.mapToList(queryTravelInfoList.getReturnResult(), ViewTravelinfoWeekinfo.class);
			for (ViewTravelinfoWeekinfo travel : infoList) {
				TravelInfo t = new TravelInfo();
				boolean sensitive = sensitiveWordFilter.isSensitive(travel.getWeekContent());
				if(sensitive) {
					t.setStatus(Constant.COMMON_STATUS_CHECK);
					Set<String> sensitiveWord = sensitiveWordFilter.getSensitiveWord(travel.getWeekContent());
					logger.error("包含敏感字符： "+sensitiveWord);
				}else {
					t.setStatus(Constant.COMMON_STATUS_CHECKED);
					Long updatetime = t.getUpdatetime();
					if(null==updatetime) {
						t.setUpdatetime(DateUtil.currentLongDate());
					}
				}
				t.setId(travel.getId());
				list.add(t);
				
			}
			//2.修改文章的状态
			jsonObject.clear();
			jsonObject.put(Constant.COMMON_KEY_ARR, list);
			RequestMessage req = HttpDataUtil.postData(jsonObject, null);
			this.travelApi.updateTravelInfoBatch(req);
		}
		
		
	}
	public void checkWorkInfo() {		
		// TODO Auto-generated method stub
		//1.查询待审核的工作日志;
		JSONObject jsonObject = new JSONObject();
		WorkInfo info = new WorkInfo();
		info.setStatus(Constant.COMMON_STATUS_CHECKING);
		jsonObject.put(Constant.COMMON_KEY_VO, info);		
		RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, null);
		ResponseMessage queryTravelInfoList = this.workApi.queryViewWorkInfoList(requestMessage);
		if(ResponseMessage.isSuccessResult(queryTravelInfoList)) {
			List<WorkInfo> list = new ArrayList<WorkInfo>();
			List<ViewWorkinfoWorktext> infoList = FastJsonUtil.mapToList(queryTravelInfoList.getReturnResult(), ViewWorkinfoWorktext.class);
			for (ViewWorkinfoWorktext work : infoList) {
				WorkInfo t = new WorkInfo();
				boolean sensitive = sensitiveWordFilter.isSensitive(work.getWcontent());
				if(sensitive) {
					t.setStatus(Constant.COMMON_STATUS_CHECK);
					Set<String> sensitiveWord = sensitiveWordFilter.getSensitiveWord(work.getWcontent());
					logger.error("包含敏感字符： "+sensitiveWord);
				}else {
					t.setStatus(Constant.COMMON_STATUS_CHECKED);
					Long updatetime = t.getUpdatetime();
					if(null==updatetime) {
						t.setUpdatetime(DateUtil.currentLongDate());
					}
				}
				t.setId(work.getId());
				list.add(t);
				
			}
			//2.修改日志状态
			jsonObject.clear();
			jsonObject.put(Constant.COMMON_KEY_ARR, list);
			RequestMessage req = HttpDataUtil.postData(jsonObject, null);
			this.workApi.updateWorkInfoBatch(req);
		}
		
		
	}

}
