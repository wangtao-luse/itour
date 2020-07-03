package com.itour.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.account.Right;
import com.itour.model.account.Role;
import com.itour.persist.RightMapper;
import com.itour.persist.RoleMapper;

/**
 * <p>
 * 角色主表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-28
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> {
	@Autowired
	RightMapper rightMapper;
/**
 * 角色列表查询
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
public ResponseMessage getRole(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();	
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Role selectById = this.baseMapper.selectById(jsonObject.getInteger("id"));
		responseMessage.setReturnResult(selectById);
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
		QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
		queryWrapper.eq("ROLE_NAME", role.getRoleName());
		queryWrapper.ne("ID", role.getId());
		Integer selectCount = this.baseMapper.selectCount(queryWrapper);
		if(selectCount>0) {
			throw new BaseException(ExceptionInfo.EXCEPTION_ROLE);
		}
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
		Role role = jsonObject.getJSONObject("vo").toJavaObject(Role.class);
		QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
		queryWrapper.eq("ROLE_NAME", role.getRoleName());
		
		queryWrapper.ne(null!=role.getId(),"ID", role.getId());
		Integer selectCount = this.baseMapper.selectCount(queryWrapper);
		if(selectCount>0) {
			throw new BaseException(ExceptionInfo.EXCEPTION_ROLE);
		}
		int insert = this.baseMapper.insert(role);
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
/***
 * 角色授权权限列表
 * @param requestMessage
 * @return
 */
public ResponseMessage authorizeRightList(RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONArray jsonArray = new JSONArray();		
		//获取根菜单
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> authorizeRightList = this.rightMapper.authorizeRightList(map);
		for (Map<String, Object> right : authorizeRightList) {
			
		}
		//一级菜单
		List<Right> collect =null;
		//子菜单
		List<Right> collectSon =null;
		for (Right right : collect) {
			JSONObject jsonObject =new JSONObject();
			//parentId:0:为顶级菜单;
			String parentId = right.getParentId();
			String menuNo = right.getMenuNo();
			if("0".equals(parentId)) {//一级菜单				
				jsonObject.put("text", right.getMenu());
				jsonObject.put("id", right.getId());
				jsonObject.put("state", "open");
				JSONArray childArr = new JSONArray();
				for (Right right2 : collectSon) {
					JSONObject childObj = new JSONObject();							
					String pid = right2.getParentId();
					String menuType = right2.getMenuType();
					String sMenuNo =right2.getMenuNo();
					if("1".equals(menuType)&&pid.equals(menuNo)) {//一级菜单下的二级菜单
						childObj.put("text", right2.getMenu());
						childObj.put("id", right2.getId());
						childObj.put("state", "open");
						childArr.add(childObj);
						JSONArray grandsonArr = new JSONArray();
						for (Right right3 : collectSon) {
							String parentId2 = right3.getParentId();
							String menuType2 = right3.getMenuType();
							if("2".equals(menuType2)&&parentId2.equals(sMenuNo)) {//二级菜单下的按钮
								JSONObject grandsonObj = new JSONObject();
								grandsonObj.put("text", right3.getMenu());
								grandsonObj.put("id", right3.getId());
								grandsonObj.put("state", "open");
								grandsonArr.add(grandsonObj);
							}
						}
						childObj.put("children", grandsonArr);
						
				
					}
				}
				jsonObject.put("children", childArr);
			}
			
			jsonArray.add(jsonObject);
			
		}
		responseMessage.setReturnResult(jsonArray);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return responseMessage;
}
}
