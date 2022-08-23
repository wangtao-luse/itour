package com.itour.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctc.wstx.util.StringUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.help.DateHelper;
import com.itour.help.StringHelper;
import com.itour.model.travel.TravelColumn;
import com.itour.persist.TravelColumnMapper;

import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * 旅行博客专栏表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class TravelColumnService extends ServiceImpl<TravelColumnMapper, TravelColumn> {
	/**
	 * 旅行信息专栏列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryTravelColumnList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelColumn travelColumnVo = jsonObject.getJSONObject("vo").toJavaObject(TravelColumn.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<TravelColumn> queryWrapper = new QueryWrapper<TravelColumn>();
			queryWrapper.likeRight(!StringUtils.isEmpty(travelColumnVo.getColumn()), "COLUMN", travelColumnVo.getColumn());
			queryWrapper.eq(null!=travelColumnVo.getUid(), "UID", travelColumnVo.getUid());
			//日期
			if(!StringHelper.isEmpty(travelColumnVo.getCreatedateRange())&&!StringHelper.isEmpty(travelColumnVo.getCreatedateRange().getLowerLimit())) {
				queryWrapper.ge("CREATEDATE", travelColumnVo.getCreatedateRange().getLowerLimit());
			}
			if(!StringHelper.isEmpty(travelColumnVo.getCreatedateRange())&&!StringHelper.isEmpty(travelColumnVo.getCreatedateRange().getUpperLimit())) {
				queryWrapper.le("CREATEDATE", travelColumnVo.getCreatedateRange().getUpperLimit());
			}
			queryWrapper.orderByDesc("ID");
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
				
			}else {
				List<TravelColumn> selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
}
	 /**
	 * 获取旅行信息专栏单条
	 * @param requestMessage
	 * @return
	 */
	public  ResponseMessage getTravelColumn(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelColumn travelColumnVo = jsonObject.toJavaObject(TravelColumn.class);
			QueryWrapper<TravelColumn> queryWrapper = new QueryWrapper<TravelColumn>();
			queryWrapper.eq(null!=travelColumnVo.getId(),"ID", travelColumnVo.getId());
			TravelColumn selectById = this.baseMapper.selectOne(queryWrapper );
			responseMessage.setReturnResult(selectById);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 修改旅行信息专栏
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage updateTravelColumn(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelColumn travelColumn = jsonObject.getJSONObject("vo").toJavaObject(TravelColumn.class);
			this.updateById(travelColumn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 删除旅行信息专栏
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage delTravelColumn(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelColumn travelColumn = jsonObject.toJavaObject(TravelColumn.class);
			this.updateById(travelColumn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 新增旅行信息专栏
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage insertTravelColumn(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String getuId = requestMessage.getBody().getuId();			
			List<TravelColumn> javaList = jsonObject.getJSONArray("arr").toJavaList(TravelColumn.class);
			List<TravelColumn>  reqList = new ArrayList<TravelColumn>();
			QueryWrapper<TravelColumn> queryWrapper = new QueryWrapper<TravelColumn>();
			queryWrapper.eq("UID", getuId);
			List<TravelColumn> selectList = this.baseMapper.selectList(queryWrapper);
			//设置属性且判重
			javaList.stream().forEach(t->{
				t.setUid(getuId);
				t.setCreatedate(DateHelper.currentLongDate());
				boolean present = selectList.stream().filter(p->p.getColumn().equals(t.getColumn())).findAny().isPresent();
				if(!present) {
					reqList.add(t);
				}
			});
			if(null!=reqList && reqList.size()>0) {
				this.saveBatch(reqList);
			}
			
		} catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
}
