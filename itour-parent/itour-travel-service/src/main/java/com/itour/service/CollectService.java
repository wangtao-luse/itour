package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ConstantTravel;
import com.itour.exception.BaseException;
import com.itour.model.travel.Collect;
import com.itour.persist.CollectMapper;
import com.itour.service.CollectService;
import com.itour.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 旅行博客收藏表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-09-10
 */
@Service
public class CollectService extends ServiceImpl<CollectMapper, Collect> {
	/**
	 * 收藏记录列表
	 * @param requestMessage
	 * @return
	 */
public ResponseMessage queryCollectList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Collect collectVo = jsonObject.getJSONObject("vo").toJavaObject(Collect.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		QueryWrapper<Collect> queryWrapper = new QueryWrapper<Collect>();
		queryWrapper.eq(!StringUtils.isEmpty(collectVo.getUid()), "UID", collectVo.getUid());
		if(null!=pageVo) {
			Page page = pageVo.toJavaObject(Page.class);
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
			responseMessage.setReturnResult(selectPage);
		}else {
			List<Collect> selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
	
}
/**
 * 收藏
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage collectArticle(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Collect vo = jsonObject.getJSONObject(Constant.COMMON_KEY_VO).toJavaObject(Collect.class);
		//取消当前用户对当前文章的收藏
		Collect c = new Collect();
		c.setStatus(ConstantTravel.TRAVEL_COLLECT_CANCEL_STATUS);
		String getuId = requestMessage.getBody().getuId();
		QueryWrapper<Collect> updateWrapper = new QueryWrapper<Collect>();
		updateWrapper.eq("UID", getuId);
		updateWrapper.eq("TID",vo.getTid());
		this.update(c, updateWrapper );
		List<Collect> javaList = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(Collect.class);
		for (Collect collect : javaList) {
			collect.setUid(getuId);
			collect.setCtime(DateUtil.currentLongDate());
			collect.setStatus(ConstantTravel.TRAVEL_COLLECT_STATUS);
			//之前收藏过直接修改状态
			Collect selectOne = this.baseMapper.selectOne(new QueryWrapper<Collect>().eq("UID", collect.getUid()).eq("TID", collect.getTid()).eq("FID", collect.getFid()));
			if(null!=selectOne) {
		    collect.setId(selectOne.getId());
			}
		}
		if(javaList.size()>0) {
			this.saveOrUpdateBatch(javaList, javaList.size());
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
