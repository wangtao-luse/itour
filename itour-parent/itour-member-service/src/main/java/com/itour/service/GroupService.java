package com.itour.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.member.Group;
import com.itour.persist.GroupMapper;

/**
 * <p>
 * 管理用户组表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@Service
public class GroupService extends ServiceImpl<GroupMapper, Group> {
	/**
	 * 用户组表查询
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryGroupList(RequestMessage requestMessage) {
			ResponseMessage responseMessage = ResponseMessage.getSucess();
			try {		
				JSONObject jsonObject = requestMessage.getBody().getContent();
				Group group = jsonObject.getJSONObject("vo").toJavaObject(Group.class);
				JSONObject pageJson = jsonObject.getJSONObject("page");
				
				QueryWrapper<Group> queryWrapper = new QueryWrapper<Group>();
				/**模糊查询**/
				queryWrapper.likeRight(!StringUtils.isEmpty(group.getgName()), "G_NAME", group.getgName());
				
				if(pageJson!=null) {
					Page page = pageJson.toJavaObject(Page.class);
					Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
					responseMessage.setReturnResult(selectPage);
					
				}else {
					List<Group> selectList = this.baseMapper.selectList(queryWrapper);
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
	 * 用户组插入
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage insertGroup(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Group group = jsonObject.getJSONObject("vo").toJavaObject(Group.class);
		    this.baseMapper.insert(group);	    
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
	}
	/**
	 * 用户组修改
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage updateGroup(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Group group = jsonObject.getJSONObject("vo").toJavaObject(Group.class);
		    this.baseMapper.updateById(group);   
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
	}
	/**
	 * 用户组删除
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage deleteGroup(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Group group = jsonObject.getJSONObject("vo").toJavaObject(Group.class);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
	}
}
