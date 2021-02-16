package com.itour.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.travel.Tag;
import com.itour.model.travel.TravelInfo;
import com.itour.persist.TagMapper;
import com.itour.util.DateUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-02-13
 */
@Service
public class TagService extends ServiceImpl<TagMapper, Tag> {
	/**
	 * 标签list
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryTravelTagList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			TravelInfo travelInfoVo = jsonObject.getJSONObject("vo").toJavaObject(TravelInfo.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<Tag> queryWrapper = new QueryWrapper<Tag>();
			queryWrapper.orderByDesc("CREATEDATE");
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
				
			}else {
				List<Tag> selectList = this.baseMapper.selectList(queryWrapper);
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
	 * 添加攻略话题标题
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage insertTravelTag(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String uid = requestMessage.getBody().getuId();
			List<Tag> javaList = jsonObject.getJSONArray("arr").toJavaList(Tag.class);
			List<Tag> reqList = new ArrayList<Tag>();
			//1.查询当前账号下创建的所以的标签
			QueryWrapper<Tag> wrapper = new QueryWrapper<Tag>();
			wrapper.eq("UID", uid);
			List<Tag> selectList = this.baseMapper.selectList(wrapper);
			
			//2.该用户下是否已经创建了该标签
			javaList.stream().forEach(item->{
				item.setUid(uid);
				item.setCreatedate(DateUtil.currentLongDate());
				String tag = item.getTag();
				//判断该用户下是否已经创建了该标签，如果已经创建了返回true;
				boolean present = selectList.stream().filter(t->t.getTag().equals(tag)).findAny().isPresent();
				if(!present) {
					reqList.add(item);
				}
			});
			//3.批量插入
			if(reqList.size()>0) {
				boolean saveBatch = this.saveBatch(reqList);
			}
			  
		}catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	@Transactional
	public ResponseMessage updateTravelTag(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Tag tag = jsonObject.toJavaObject(Tag.class);
			 String uid = requestMessage.getBody().getuId();
			    tag.setUid(uid);
			    QueryWrapper<Tag> queryWrapper = new QueryWrapper<Tag>();
			    queryWrapper.eq("UID", tag.getUid());
			    queryWrapper.eq("TAG", tag.getTag());
			    queryWrapper.ne("ID", tag.getId());
			    Tag selectOne = this.baseMapper.selectOne(queryWrapper);
			    if(null!=selectOne) {
			    	throw new BaseException(ExceptionInfo.EXCEPTION_TAG);
			    }
			    this.updateById(tag);
			  
		}catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	@Transactional
	public ResponseMessage delTravelTag(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Tag tag = jsonObject.toJavaObject(Tag.class);
			if(tag.getId()==null) {
				throw new BaseException(ExceptionInfo.EXCEPTION_DEL_FAIL);
			}
			 this.baseMapper.deleteById(tag.getId());
			
		}catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
}
