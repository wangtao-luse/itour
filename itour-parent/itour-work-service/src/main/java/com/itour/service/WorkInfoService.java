package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.vo.PageInfo;
import com.itour.model.work.WorkInfo;
import com.itour.persist.WorkInfoMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-09
 */
@Service
public class WorkInfoService extends ServiceImpl<WorkInfoMapper, WorkInfo> {

public ResponseMessage queryWorkInfoList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		WorkInfo vo = jsonObject.getJSONObject(Constant.COMMON_VO_NEEDTOTAL).toJavaObject(WorkInfo.class);
		JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
		QueryWrapper queryWrapper = new QueryWrapper<WorkInfo>();
		if(null!=pageVo) {
			PageInfo page = pageVo.toJavaObject(PageInfo.class);
			PageInfo selectPage = this.baseMapper.selectPage(page, queryWrapper);
			responseMessage.setReturnResult(selectPage);
		}else {
			List selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}
	} catch (Exception e) {
		// TODO: handle exception
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
	
}
public ResponseMessage selectOneWorkInfo(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		WorkInfo vo = jsonObject.getJSONObject(Constant.COMMON_VO_NEEDTOTAL).toJavaObject(WorkInfo.class);
		QueryWrapper queryWrapper = new QueryWrapper<WorkInfo>();
		queryWrapper.eq(null!=vo.getId(), "id", vo.getId());
		WorkInfo selectOne = this.baseMapper.selectOne(queryWrapper);
	} catch (Exception e) {
		// TODO: handle exception
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
	
}
/**
 * 修改旅行信息
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage updateWorkInfo(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		//1.修改攻略信息
		WorkInfo vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfo.class);
		this.updateById(vo);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 批量修改旅行信息
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage updateWorkInfoBatch(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		List<WorkInfo> vo = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(WorkInfo.class);
		if(vo.size()>0) {
			this.updateBatchById(vo, vo.size());
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 批量修改旅行信息的浏览量
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage updateWorkInfoPvBatch(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		List<WorkInfo> travelInfoVo = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(WorkInfo.class);
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 删除旅行信息
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage delWorkInfo(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		WorkInfo workInfo = jsonObject.toJavaObject(WorkInfo.class);
		this.updateById(workInfo);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
@Transactional
public ResponseMessage insertWorkInfo(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
