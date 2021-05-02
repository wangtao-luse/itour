package com.itour.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestBody;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ConstantTravel;
import com.itour.constant.RedisKey;
import com.itour.exception.BaseException;
import com.itour.model.travel.Nice;
import com.itour.model.travel.Tag;
import com.itour.model.travel.TravelColumn;
import com.itour.model.travel.TravelInfo;
import com.itour.model.travel.TravelTag;
import com.itour.model.travel.TravelinfoColumn;
import com.itour.model.travel.WeekInfo;
import com.itour.persist.TagMapper;
import com.itour.persist.TravelColumnMapper;
import com.itour.persist.TravelInfoMapper;
import com.itour.persist.TravelTagMapper;
import com.itour.persist.TravelinfoColumnMapper;
import com.itour.persist.WeekInfoMapper;
import com.itour.util.DateUtil;

/**
 * <p>
 * 旅行信息表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class TravelInfoService extends ServiceImpl<TravelInfoMapper, TravelInfo>  {
	@Autowired
	WeekInfoMapper weekInfoMapper;
	@Autowired
	TravelinfoColumnService travelinfoColumnService;
	@Autowired
	TravelTagService travelTagService;
	@Autowired
	TagMapper tagMapper;
	@Autowired
	TravelColumnMapper travelColumnMapper;
	@Autowired
	TravelinfoColumnMapper travelinfoColumnMapper;//中间表
	@Autowired
	RedisManager redisManager;
	@Autowired
	TravelInfoMapper travelInfoMapper;
	@Autowired
	TravelTagMapper travelTagMapper;
	/**
	 * 旅行信息列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryTravelInfoList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelInfo travelInfoVo = jsonObject.getJSONObject("vo").toJavaObject(TravelInfo.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<TravelInfo> queryWrapper = new QueryWrapper<TravelInfo>();
			queryWrapper.orderByDesc("PUBLISHTIME");
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
				
			}else {
				List<TravelInfo> selectList = this.baseMapper.selectList(queryWrapper);
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
	 * 获取旅行信息单条
	 * @param requestMessage
	 * @return
	 */
	public  ResponseMessage selectTravelInfoOne(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Long id = jsonObject.getLong("id");
			String uid = jsonObject.getString("uid");
			QueryWrapper<TravelInfo> queryWrapper = new QueryWrapper<TravelInfo>();
			queryWrapper.eq(!StringUtils.isEmpty(id), "ID", id);
			queryWrapper.eq(!StringUtils.isEmpty(uid), "UID", uid);
			TravelInfo selectOne = this.baseMapper.selectOne(queryWrapper );
			responseMessage.setReturnResult(selectOne);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
	public ResponseMessage updateTravelInfo(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			//1.修改攻略信息
			TravelInfo travelInfo = jsonObject.getJSONObject("vo").toJavaObject(TravelInfo.class);
			this.updateById(travelInfo);
			//2.修改周末旅行信息表
			if(ConstantTravel.TRAVEL_INFO_WEEK.equals(travelInfo.getType())) {
				WeekInfo weekinfo = jsonObject.getJSONObject("weekInfo").toJavaObject(WeekInfo.class);
				this.weekInfoMapper.updateById(weekinfo);
			}
			//3.修改标签中间表	
			//4.修改分类专栏表中间表
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
	public ResponseMessage updateTravelInfoBatch(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			List<TravelInfo> travelInfoVo = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(TravelInfo.class);
			if(travelInfoVo.size()>0) {
				this.updateBatchById(travelInfoVo, travelInfoVo.size());
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
	public ResponseMessage updatePvBatch(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			List<TravelInfo> travelInfoVo = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(TravelInfo.class);
			if(travelInfoVo.size()>0) {
				this.travelInfoMapper.updatePvBatch(travelInfoVo);				
			}
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
	public ResponseMessage delTravelInfo(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelInfo travelInfo = jsonObject.toJavaObject(TravelInfo.class);
			this.updateById(travelInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 新增旅行信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage insertTravelInfo(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		 HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			//获取参数
			RequestBody body = requestMessage.getBody();
			JSONObject jsonObject = requestMessage.getBody().getContent();
			JSONArray tagArr = jsonObject.getJSONArray("tag_arr");
			JSONArray colArr = jsonObject.getJSONArray("col_arr");
			//1.插入旅行旅行信息表
			TravelInfo travelInfo = jsonObject.getJSONObject("vo").toJavaObject(TravelInfo.class);
             travelInfo.setPublishtime(DateUtil.currentLongDate());
			 this.saveOrUpdate(travelInfo);
			//2.插入周末旅行信息表
			if(ConstantTravel.TRAVEL_INFO_WEEK.equals(travelInfo.getType())) {
				TravelInfo info = jsonObject.getJSONObject("vo").toJavaObject(TravelInfo.class);
				String text = jsonObject.getString("markdown");
				if(StringUtils.isEmpty(info.getId())) {
					WeekInfo entity = new WeekInfo();
					entity.setTid(travelInfo.getId());
					entity.setWeekContent(text);
					weekInfoMapper.insert(entity);
				}else {
					QueryWrapper<WeekInfo> queryWrapper = new QueryWrapper<WeekInfo>();
					queryWrapper.eq("TID", travelInfo.getId());
					WeekInfo selectOne = weekInfoMapper.selectOne(queryWrapper );
					selectOne.setWeekContent(text);
					this.weekInfoMapper.updateById(selectOne);
				}
				
				
			}
			
			//3.插入标签中间表	
			QueryWrapper<TravelTag> wrapper = new QueryWrapper<TravelTag>();
			wrapper.eq("TID", travelInfo.getId());
			this.travelTagMapper.delete(wrapper);
			String join = String.join(",",tagArr.stream().map(String::valueOf).collect(Collectors.toList()));
			QueryWrapper<Tag> queryWrapper = new QueryWrapper<Tag>();
			queryWrapper.in("TAG", join);
			queryWrapper.eq("UID", body.getuId());
			
			List<Tag> selectList = this.tagMapper.selectList(queryWrapper);			
			List<TravelTag> tagList = new ArrayList<TravelTag>(); 
			for (Tag t : selectList) {
				TravelTag tag = new TravelTag();
				tag.setTid(travelInfo.getId());
				tag.setTagId(t.getId());
				tagList.add(tag);				  
			}
			if(tagList.size()>0) {
				travelTagService.saveBatch(tagList, tagList.size());
			}
			
			
			//4.插入分类专栏表中间表
			QueryWrapper<TravelinfoColumn> ew = new QueryWrapper<TravelinfoColumn>();
			ew.eq("TID", travelInfo.getId());
			this.travelinfoColumnMapper.delete(ew  );
			QueryWrapper<TravelColumn> qw = new QueryWrapper<TravelColumn>();
			String colStr = String.join(",", colArr.stream().map(String::valueOf).collect(Collectors.toList()));
			qw.in("`COLUMN`", colStr);
			qw.eq("UID",body.getuId());
			List<TravelColumn> selectColList = this.travelColumnMapper.selectList(qw);
			List<TravelinfoColumn> colList = new ArrayList<TravelinfoColumn>();
			for (TravelColumn c : selectColList) {
				TravelinfoColumn col = new TravelinfoColumn();	
				col.setTid(travelInfo.getId());
				col.setCid(c.getId());
				colList.add(col);
			}
			if(colList.size()>0) {
				travelinfoColumnService.saveBatch(colList, colList.size());
			}
			result.put("id", travelInfo.getId());
			responseMessage.setReturnResult(result);
		} catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	   * 旅行攻略点赞功能
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage niceSub(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Long tid = jsonObject.getLong("tid");
			String status = jsonObject.getString("status");
			String uid = jsonObject.getString("uid");
			Nice n = new Nice();
			 n.setStatus(status);
			 n.setTid(tid);
			 n.setUid(uid);
			 n.setCreatedate(DateUtil.currentLongDate());
			//将点赞对象放入缓存并设置缓存超时时间
			//key 不存在直接放入缓存
			HashMap<String, Object> m = new HashMap<String, Object>();
			 m.put(uid+"::"+tid, n);
			 redisManager.hmSset(RedisKey.KEY_NICE, m);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
}
