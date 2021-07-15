package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.vo.PageInfo;
import com.itour.model.work.WorkInfo;
import com.itour.model.work.dto.WorkInfoDto;
import com.itour.persist.WorkInfoMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@Service
public class WorkInfoService extends ServiceImpl<WorkInfoMapper, WorkInfo> {
	
	/**
	 * 前台使用
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage selectWorkInfoList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkInfoDto vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfoDto.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				List<WorkInfoDto> sList = this.baseMapper.selectWorkInfoList(page, vo);
				page.setRecords(sList);
				responseMessage.setReturnResult(page);
			}else {
				List<WorkInfoDto> sList = this.baseMapper.selectWorkInfoList(vo);
				responseMessage.setReturnResult(sList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	public ResponseMessage selectWorkInfo(RequestMessage requestMessage) {
		   ResponseMessage responseMessage = ResponseMessage.getSucess();
		   try {
			   JSONObject jsonObject = requestMessage.getBody().getContent();
			   WorkInfoDto vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfoDto.class);
			   WorkInfoDto selectTraveInfo = this.baseMapper.selectWorkInfo(vo);
			   responseMessage.setReturnResult(selectTraveInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		   return responseMessage;
	   }
	public ResponseMessage searchTextList(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkInfoDto vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfoDto.class);
			PageInfo pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE).toJavaObject(PageInfo.class);
			List<WorkInfoDto> infoData = this.baseMapper.searchTextList(pageVo,vo);
			Page setRecords = pageVo.setRecords(infoData);
			response.setReturnResult(setRecords);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	/**
	 * 个人中心
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryPersonCenterList(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			PageInfo page = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE).toJavaObject(PageInfo.class);
			WorkInfoDto vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfoDto.class);
		    List<WorkInfoDto> selectDynamicList = this.baseMapper.selectDynamicList(page,vo);
		    page.setRecords(selectDynamicList);
			response.setReturnResult(page);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	public ResponseMessage getInfoData(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkInfoDto vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfoDto.class);
			WorkInfoDto infoData = this.baseMapper.getInfoData(vo);
			response.setReturnResult(infoData);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}

	/**
	 * 批量修改个人博客信息的浏览量
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage updatePvBatch(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			List<WorkInfo> workInfo = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(WorkInfo.class);
			if(workInfo.size()>0) {
				this.baseMapper.updatePvBatch(workInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
