package com.itour.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.itour.model.dto.PageInfo;
import com.itour.model.travel.TravelInfo;
import com.itour.model.work.InfoColumn;
import com.itour.model.work.InfoLabel;
import com.itour.model.work.Label;
import com.itour.model.work.Like;
import com.itour.model.work.WorkColumn;
import com.itour.model.work.WorkInfo;
import com.itour.model.work.Worktext;
import com.itour.model.work.vo.WorkInfoVo;
import com.itour.persist.InfoColumnMapper;
import com.itour.persist.InfoLabelMapper;
import com.itour.persist.LabelMapper;
import com.itour.persist.WorkColumnMapper;
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
	@Autowired
	LabelService labelService;
	@Autowired
	WorkColumnService workColumnService;
	@Autowired
	WorkColumnMapper workColumnMapper;
	@Autowired
	RedisManager redisManager;
	
	/**
	 * 前台使用（包含了用户图像，昵称等信息）
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage selectWorkInfoList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkInfoVo vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfoVo.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				List<WorkInfoVo> sList = this.baseMapper.selectWorkInfoList(page, vo);
				page.setRecords(sList);
				responseMessage.setReturnResult(page);
			}else {
				List<WorkInfoVo> sList = this.baseMapper.selectWorkInfoList(vo);
				responseMessage.setReturnResult(sList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 *   查询工作日志单条
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage selectWorkInfoOne(RequestMessage requestMessage) {
		   ResponseMessage responseMessage = ResponseMessage.getSucess();
		   try {
			   JSONObject jsonObject = requestMessage.getBody().getContent();
			   WorkInfo vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfo.class);
			   QueryWrapper<WorkInfo> queryWrapper = new QueryWrapper<WorkInfo>();
			   queryWrapper.eq(null!=vo.getId(), "ID", vo.getId());
			   queryWrapper.eq(!StringUtils.isEmpty(vo.getUid()), "UID", vo.getUid());
			   WorkInfo selectTraveInfo = this.baseMapper.selectOne(queryWrapper );
			   responseMessage.setReturnResult(selectTraveInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		   return responseMessage;
	   }
	/**
	 * 工作日志单条查询(保护用户昵称，图像等信息)
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage selectWorkInfo(RequestMessage requestMessage) {
		   ResponseMessage responseMessage = ResponseMessage.getSucess();
		   try {
			   JSONObject jsonObject = requestMessage.getBody().getContent();
			   WorkInfoVo vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfoVo.class);
			   WorkInfoVo selectTraveInfo = this.baseMapper.selectWorkInfo(vo);
			   responseMessage.setReturnResult(selectTraveInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		   return responseMessage;
	   }
	/**
	 * 工作日志查询
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage searchTextList(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkInfoVo vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfoVo.class);
			PageInfo pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE).toJavaObject(PageInfo.class);
			List<WorkInfoVo> infoData = this.baseMapper.searchTextList(pageVo,vo);
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
			WorkInfoVo vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfoVo.class);
		    List<WorkInfoVo> selectDynamicList = this.baseMapper.selectDynamicList(page,vo);
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
			WorkInfoVo vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfoVo.class);
			WorkInfoVo infoData = this.baseMapper.getInfoData(vo);
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
	 * 1.插入工作日志表
	 * 2.插入工作日志内容表
	 * 3.插入标签表
	 * 4.插入工作日志-标签表
	 * 5.插入分类专栏表
	 * 6.插入工作日志-分类专栏表
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
			LinkedHashSet<String> hs_col = new LinkedHashSet<String>();
			LinkedHashSet<String> hs_tag = new LinkedHashSet<String>();
			for (Object object : colArr) {
				hs_col.add(String.valueOf(object));
			}
			for (Object object : tagArr) {
				hs_tag.add(String.valueOf(object));
			}
			
			
			String function = jsonObject.getString(Constant.COMMOM_FUNCTION);
			//1.插入工作日志表
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
				
			}else {//新增
				workInfo.setUid(body.getuId());
				if(Constant.COMMON_FUNCTION_SAVE.equals(function)) {
					workInfo.setStatus(Constant.COMMON_STATUS_CHECKING);
					workInfo.setPublishtime(DateUtil.currentLongDate());
				}else if(Constant.COMMON_FUNCTION_DRAFT.equals(function)) {
					workInfo.setStatus(Constant.COMMON_STATUS_DRAFT);
				}
			}
             
			 this.saveOrUpdate(workInfo);
			//2.插入工作日志内容表
			 WorkInfo info = jsonObject.getJSONObject("vo").toJavaObject(WorkInfo.class);
				String text = jsonObject.getString("markdown");
				if(StringUtils.isEmpty(info.getId())) {//新增
					Worktext entity = new Worktext();
					entity.setWid(workInfo.getId());
					entity.setWcontent(text);
					worktextMapper.insert(entity);
				}else {//修改
					QueryWrapper<Worktext> queryWrapper = new QueryWrapper<Worktext>();
					queryWrapper.eq("WID", workInfo.getId());
					Worktext selectOne = worktextMapper.selectOne(queryWrapper );
					selectOne.setWcontent(text);
					this.worktextMapper.updateById(selectOne);
				}
		     //3.插入标签表
				if(tagArr.size()>0) {
					//1.组装数据
					List<Label> lList = new ArrayList<Label>();
					for (Object object : tagArr) {
						Label label = new Label();
						label.setTag(String.valueOf(object));
						label.setUid(body.getuId());
						label.setCreatedate(DateUtil.currentLongDate());
						lList.add(label);
					}
					QueryWrapper<Label> queryWrapper = new QueryWrapper<Label>();
					queryWrapper.in("TAG", hs_tag);
					queryWrapper.eq("UID",body.getuId());
					//2.查询是否已经存在该标签
					List<Label> labelList = this.labelMapper.selectList(queryWrapper );
				    //3.如果已经存在组装id
					for (Label label : labelList) {
						for (Label label2 : lList) {
							if(label.getTag().equals(label2.getTag())) {
								label2.setId(label.getId());
							}
						}
					}
					labelService.saveOrUpdateBatch(lList, lList.size());
					
				}
				
			//4.插入标签中间表				  
		     if(null!=workInfo.getId()) {
		    	 //4.1删除该文章的中间关系
		    	 QueryWrapper<InfoLabel> wrapper = new QueryWrapper<InfoLabel>();
					wrapper.eq("WID", workInfo.getId());
					this.infoLabelMapper.delete(wrapper);
		     }			
			//4.2将该文章和标签关系批量插入中间表
			List<InfoLabel> tagList = new ArrayList<InfoLabel>(); 
			if(tagArr.size()>0) {
				//4.3检查该标签
				QueryWrapper<Label> queryWrapper = new QueryWrapper<Label>();
				queryWrapper.in("TAG", tagArr);
				queryWrapper.eq("UID", body.getuId());
				List<Label> selectList = this.labelMapper.selectList(queryWrapper);			
				
				for (Label t : selectList) {
					InfoLabel tag = new InfoLabel();
					tag.setWid(workInfo.getId());
					tag.setLabelId(t.getId());
					tagList.add(tag);				  
				}
			}
			//4.3批量插入工作日志-标签表关系表
			if(tagList.size()>0) {
				infoLabelService.saveBatch(tagList, tagList.size());
			}
			 //5.插入分类专栏表
			if(colArr.size()>0) {
				//1.组装数据
				List<WorkColumn> cList = new ArrayList<WorkColumn>();
				for (Object object : colArr) {
					WorkColumn col = new WorkColumn();
					col.setColumn(String.valueOf(object));
					col.setUid(body.getuId());
					col.setCreatedate(DateUtil.currentLongDate());
					cList.add(col);
				}
				QueryWrapper<WorkColumn> queryWrapper = new QueryWrapper<WorkColumn>();
				queryWrapper.in("`COLUMN`", hs_col);
				queryWrapper.eq("UID",body.getuId());
				//2.查询是否已经存在该标签
				List<WorkColumn> columnList = this.workColumnMapper.selectList(queryWrapper );
			    //3.如果已经存在组装id
				for (WorkColumn col : columnList) {
					for (WorkColumn col2 : cList) {
						if(col.getColumn().equals(col2.getColumn())) {
							col2.setId(col.getId());
						}
					}
				}
				workColumnService.saveOrUpdateBatch(cList, cList.size());
				
			}
			
			
			//6.插入分类专栏表中间表
			 //6.1 删除该文章下的中间表关系
			QueryWrapper<InfoColumn> ew = new QueryWrapper<InfoColumn>();
			ew.eq("WID", workInfo.getId());
			this.infoColumnMapper.delete(ew);
			//6.2将分类专栏插入中间表
			List<InfoColumn> colList = new ArrayList<InfoColumn>();
			if(colArr.size()>0) {
				QueryWrapper<WorkColumn> qw = new QueryWrapper<WorkColumn>();
				qw.in("`COLUMN`", colArr);
				qw.eq("UID",body.getuId());
				List<WorkColumn> selectColList = this.workColumnMapper.selectList(qw);
				
				for (WorkColumn c : selectColList) {
					InfoColumn col = new InfoColumn();	
					col.setWid(workInfo.getId());
					col.setCid(c.getId());
					colList.add(col);
				}
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
			List<WorkInfo> workinfo = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(WorkInfo.class);
			if(workinfo.size()>0) {
				this.updateBatchById(workinfo, workinfo.size());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 博客的标签
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage workTagList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Long id = jsonObject.getLong("id");
			List<Label> workTagList = this.baseMapper.workTagList(id);
			responseMessage.setReturnResult(workTagList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 博客的专栏
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage workColList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Long id = jsonObject.getLong("id");
			List<WorkColumn> workColList = this.baseMapper.workColList(id);
			responseMessage.setReturnResult(workColList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	   * 工作日志点赞功能
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage likeSub(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Long tid = jsonObject.getLong("tid");
			String status = jsonObject.getString("status");
			String uid = jsonObject.getString("uid");
			Like n = new Like();
			 n.setStatus(status);
			 n.setWid(tid);
			 n.setUid(uid);
			 n.setCreatedate(DateUtil.currentLongDate());
			//将点赞对象放入缓存并设置缓存超时时间
			//key 不存在直接放入缓存
			HashMap<String, Object> m = new HashMap<String, Object>();
			 m.put(uid+"::"+tid, n);
			 redisManager.hmSset(RedisKey.KEY_WORK_NICE, m);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	/**
	 * 根据专栏查询个人专栏列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryWorkByColList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkInfoVo vo = jsonObject.getJSONObject("vo").toJavaObject(WorkInfoVo.class);
			WorkColumn col = workColumnMapper.selectById(vo.getColId());
			String uid = col.getUid();
			Long cid = col.getId();
			vo.setQueryUid(uid);
			vo.setColId(cid);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				List<WorkInfoVo> sList = this.baseMapper.queryWorkByColList(page, vo);
				page.setRecords(sList);
				responseMessage.setReturnResult(page);
			}else {
				List<WorkInfoVo> sList = this.baseMapper.queryWorkByColList(vo);
				responseMessage.setReturnResult(sList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}

}
