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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestBody;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ConstantTravel;
import com.itour.exception.BaseException;
import com.itour.model.vo.PageInfo;
import com.itour.model.work.InfoColumn;
import com.itour.model.work.InfoLabel;
import com.itour.model.work.Label;
import com.itour.model.work.WorkInfo;
import com.itour.model.work.Worktext;
import com.itour.model.work.dto.WorkInfoDto;
import com.itour.persist.InfoColumnMapper;
import com.itour.persist.InfoLabelMapper;
import com.itour.persist.LabelMapper;
import com.itour.persist.WorkInfoMapper;
import com.itour.persist.WorktextMapper;
import com.itour.util.DateUtil;

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
	@Autowired
	WorktextMapper worktextMapper;
	@Autowired
	InfoLabelMapper infoLabelMapper;
	@Autowired
	LabelMapper labelMapper;
	@Autowired
	InfoColumnMapper infoColumnMapper;
	@Autowired
	InfoLabelService infoLabelService;
	@Autowired
	InfoColumnService infoColumnService;
	
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
	/**
	 * 新增或修改旅行信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage saveOrUpdateWorkInfo(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		 HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			//获取参数
			RequestBody body = requestMessage.getBody();
			JSONObject jsonObject = requestMessage.getBody().getContent();
			JSONArray tagArr = jsonObject.getJSONArray("tag_arr");
			JSONArray colArr = jsonObject.getJSONArray("col_arr");
			String function = jsonObject.getString(Constant.COMMOM_FUNCTION);
			//1.插入旅行旅行信息表
			WorkInfo workInfo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfo.class);
			if(!StringUtils.isEmpty(workInfo.getId())) {//修改
				//1.检查该用户是否有权限修改此攻略
				QueryWrapper<WorkInfo> queryWrapper = new QueryWrapper<WorkInfo>();
				queryWrapper.eq("ID", workInfo.getId());
				queryWrapper.eq("UID", body.getuId());
				WorkInfo selectOne = this.baseMapper.selectOne(queryWrapper );
				if(StringUtils.isEmpty(selectOne)) {
					throw new BaseException(ConstantTravel.EXCEPTION_INFO_NOAUTHOR);
				}
				if(Constant.COMMON_FUNCTION_SAVE.equals(function)) {
					workInfo.setStatus(Constant.COMMON_STATUS_CHECKING);
					workInfo.setUpdatetime(DateUtil.currentLongDate());
				}else if(Constant.COMMON_FUNCTION_DRAFT.equals(function)) {
					workInfo.setStatus(Constant.COMMON_STATUS_DRAFT);
				}
				
			}else {
				workInfo.setPublishtime(DateUtil.currentLongDate());	
				workInfo.setStatus(Constant.COMMON_STATUS_DRAFT);
			}
             
			 this.saveOrUpdate(workInfo);
			//2.插入工作博客内容信息表
			 WorkInfo info = jsonObject.getJSONObject("vo").toJavaObject(WorkInfo.class);
				String text = jsonObject.getString("markdown");
				if(StringUtils.isEmpty(info.getId())) {
					Worktext entity = new Worktext();
					entity.setWid(workInfo.getId());
					entity.setWcontent(text);
					worktextMapper.insert(entity);
				}else {
					QueryWrapper<Worktext> queryWrapper = new QueryWrapper<Worktext>();
					queryWrapper.eq("TID", workInfo.getId());
					Worktext selectOne = worktextMapper.selectOne(queryWrapper );
					selectOne.setWcontent(text);
					this.worktextMapper.updateById(selectOne);
				}
				
			//3.插入标签中间表	
			QueryWrapper<InfoLabel> wrapper = new QueryWrapper<InfoLabel>();
			wrapper.eq("WID", workInfo.getId());
			this.infoLabelMapper.delete(wrapper);
			String join = String.join(",",tagArr.stream().map(String::valueOf).collect(Collectors.toList()));
			QueryWrapper<Label> queryWrapper = new QueryWrapper<Label>();
			queryWrapper.in("TAG", tagArr);
			queryWrapper.eq("UID", body.getuId());
			
			List<Label> selectList = this.labelMapper.selectList(queryWrapper);			
			List<InfoLabel> tagList = new ArrayList<InfoLabel>(); 
			for (Label t : selectList) {
				InfoLabel tag = new InfoLabel();
				tag.setWid(workInfo.getId());
				tag.setTid(t.getId());
				tagList.add(tag);				  
			}
			if(tagList.size()>0) {
				infoLabelService.saveBatch(tagList, tagList.size());
			}
			
			
			//4.插入分类专栏表中间表
			 //4.1 删除该文章下的中间表关系
			QueryWrapper<InfoColumn> ew = new QueryWrapper<InfoColumn>();
			ew.eq("WID", workInfo.getId());
			this.infoColumnMapper.delete(ew);
			QueryWrapper<InfoColumn> qw = new QueryWrapper<InfoColumn>();
			String colStr = String.join(",", colArr.stream().map(String::valueOf).collect(Collectors.toList()));
			qw.in("`COLUMN`", colArr);
			qw.eq("UID",body.getuId());
			List<InfoColumn> selectColList = this.infoColumnMapper.selectList(qw);
			List<InfoColumn> colList = new ArrayList<InfoColumn>();
			for (InfoColumn c : selectColList) {
				InfoColumn col = new InfoColumn();	
				col.setWid(workInfo.getId());
				col.setCid(c.getId());
				colList.add(col);
			}
			if(colList.size()>0) {
				infoColumnService.saveBatch(colList, colList.size());
			}
			result.put("id", workInfo.getId());
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
}
