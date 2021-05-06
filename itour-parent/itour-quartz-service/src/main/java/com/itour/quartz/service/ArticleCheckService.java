package com.itour.quartz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.TravelApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.init.SensitiveWordFilter;
import com.itour.model.travel.TravelInfo;
import com.itour.model.travel.dto.ViewTravelinfoWeekinfo;
import com.itour.util.DateUtil;
import com.itour.util.FastJsonUtil;

public class ArticleCheckService {
	@Autowired
	private TravelApi travelApi;
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
			List<TravelInfo> list = new ArrayList<TravelInfo>();
			List<ViewTravelinfoWeekinfo> infoList = FastJsonUtil.mapToList(queryTravelInfoList.getReturnResult(), ViewTravelinfoWeekinfo.class);
			for (ViewTravelinfoWeekinfo travel : infoList) {
				TravelInfo t = new TravelInfo();
				boolean sensitive = sensitiveWordFilter.isSensitive(travel.getWeekContent());
				if(sensitive) {
					t.setStatus(Constant.COMMON_STATUS_CHECK);
				}else {
					t.setStatus(Constant.COMMON_STATUS_CHECKED);
					Long updatetime = t.getUpdatetime();
					if(null==updatetime) {
						t.setPublishtime(DateUtil.currentLongDate());
					}
				}
				t.setId(travel.getId());
				list.add(t);
				
			}
			//2.审核
			jsonObject.clear();
			jsonObject.put(Constant.COMMON_KEY_ARR, list);
			RequestMessage req = HttpDataUtil.postData(jsonObject, null);
			this.travelApi.updateTravelInfoBatch(requestMessage);
		}
		
		
	}

}
