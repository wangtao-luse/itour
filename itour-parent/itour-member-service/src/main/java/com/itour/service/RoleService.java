package com.itour.service;

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
import com.itour.exception.BaseException;
import com.itour.model.member.Role;
import com.itour.persist.RoleMapper;

/**
 * <p>
 * 角色主表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
	/**
	 * 角色查询
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryRoleList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();	
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Page page = jsonObject.getJSONObject("page").toJavaObject(Page.class);
			
			
			QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
			if(page!=null) {
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper );	
				responseMessage.setReturnResult(selectPage);
			}else {
				List<Role> selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
		}catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}	
		
		return responseMessage;
	}
	/**
	 * 修改角色
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage updateRole(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Role role = jsonObject.getJSONObject("vo").toJavaObject(Role.class);
			int updateById = this.baseMapper.updateById(role);
		} catch (BaseException e) {
			// TODO: handle exception
			return ResponseMessage.getFailed(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
		
	}
	/**
	 * 删除角色
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage deleteRole(RequestMessage requestMessage) {
		try {
			Long id = requestMessage.getBody().getContent().getLong("id");
			int deleteById = this.baseMapper.deleteById(id);
		} catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return ResponseMessage.getSucess();
	}
	/**
	 * 新增角色
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage insertRole(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Role entity = jsonObject.getJSONObject("vo").toJavaObject(Role.class);
			int insert = this.baseMapper.insert(entity);
		} catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;	
	}
}
