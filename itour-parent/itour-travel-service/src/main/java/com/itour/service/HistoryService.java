package com.itour.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.lambda.Student;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ConstantV;
import com.itour.model.travel.History;
import com.itour.persist.HistoryMapper;
import com.itour.util.DateUtil;

/**
 * <p>
 * 旅行信息浏览记录表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-09-22
 */
@Service
public class HistoryService extends ServiceImpl<HistoryMapper, History> {
//浏览记录列表
public ResponseMessage queryHistoryList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		History historyVo = jsonObject.getJSONObject("vo").toJavaObject(History.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		String admin = jsonObject.getString("isAdmin");
		QueryWrapper<History> queryWrapper = new QueryWrapper<History>();
		queryWrapper.eq(StringUtils.isEmpty(admin)||"0".equals(admin), "U_ID", historyVo.getuId());
		queryWrapper.eq(StringUtils.isEmpty(admin)||"0".equals(admin), "STATUS",ConstantV.HISTORY_NORMAL);
		if(pageVo!=null) {
			Page page = pageVo.toJavaObject(Page.class);
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
			responseMessage.setReturnResult(selectPage);
		}else {
			List<History> selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 浏览记录新增
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage insertHistory(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		History history = jsonObject.toJavaObject(History.class);
		if(!StringUtils.isEmpty(history.getuId())) {
			QueryWrapper<History> queryWrapper = new QueryWrapper<History>();
			queryWrapper.eq("U_ID", history.getuId());
			queryWrapper.eq("TITLE", history.getTitle());
			queryWrapper.ge("CREATEDATE", DateUtil.getlongDate(DateUtil.getMidnight()));
			History selectOne = this.baseMapper.selectOne(queryWrapper );
			if(selectOne!=null) {
				selectOne.setCreatedate(DateUtil.getlongDate(new Date()));
				this.baseMapper.updateById(selectOne);
			}else {
				this.baseMapper.insert(history);
			}
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 清除浏览记录
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage clearHistory(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		String uId = jsonObject.getString("uId");
		JSONArray jsonArray = jsonObject.getJSONArray("ids");
		if(null!=jsonArray) {//清除部分浏览记录
			QueryWrapper<History> queryWrapper = new QueryWrapper<History>();
			queryWrapper.in("ID",jsonArray);
			List<History> selectList = this.baseMapper.selectList(queryWrapper);
			selectList.forEach(f->f.setStatus("2"));
			this.updateBatchById(selectList);
		}else {//清除全部浏览记录
			QueryWrapper<History> queryWrapper = new QueryWrapper<History>();
			queryWrapper.eq("U_ID", uId);
			List<History> list = this.baseMapper.selectList(queryWrapper);
			list.forEach(f->f.setStatus("2"));
			this.updateBatchById(list);
		}
	} catch (Exception e) {
		// TODO: handle finally clause
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}

}
