package com.itour.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.itour.model.member.Role;
import com.itour.persist.RightMapper;
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
	@Autowired
	private RightMapper rightMapper;
	/**
	 * 角色查询
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryRoleList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();	
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Role roleVo = jsonObject.getJSONObject("vo").toJavaObject(Role.class);
			Page pageVo = jsonObject.getJSONObject("page").toJavaObject(Page.class);			
			QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
			/**精确查询**/
			queryWrapper.eq(null != roleVo.getId(), "ID", roleVo.getId());
			/**模糊查询**/
			queryWrapper.like(!StringUtils.isEmpty(roleVo.getRoleName()),"ROLE_NAME", roleVo.getRoleName());
			queryWrapper.like(!StringUtils.isEmpty(roleVo.getRoleDesc()),"ROLE_DESC", roleVo.getRoleDesc());
			
			if(pageVo!=null) {
				Page selectPage = this.baseMapper.selectPage(pageVo, queryWrapper );	
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
			QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
			queryWrapper.eq("ROLE_NAME", role.getRoleName());
			queryWrapper.ne("ID", role.getId());
			queryWrapper.or().eq("ROLE_DESC", role.getRoleDesc()).ne("ID", role.getId());
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
			queryWrapper.or().eq("ROLE_DESC", role.getRoleDesc());		
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
			JSONObject jsonobjctoVo = requestMessage.getBody().getContent();
			//获取根菜单
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("rid", jsonobjctoVo.getInteger("roleId"));
			List<Map<String, Object>> authorizeRightList = this.rightMapper.authorizeRightList(map);
			for (Map<String, Object> right : authorizeRightList) {
				JSONObject jsonObject =new JSONObject();
				//parentId:0:为顶级菜单;
				Object  parentId = String.valueOf(right.get("PARENT_ID"));
				Object menuNo = right.get("MENU_NO");
				Object rid = right.get("ROLE_ID");
				if("0".equals(parentId)){//一级菜单
					jsonObject.put("id", right.get("ID"));//权限编号-->insert
					jsonObject.put("text", right.get("MENU"));//菜单名称--->显示					
					jsonObject.put("rrid", right.get("RRID"));//中间表编号-->insert
					jsonObject.put("menuNo", menuNo);
					jsonObject.put("state", "open");
					if("0".equals(rid)) {
						jsonObject.put("checked", false);
					}else {
						jsonObject.put("checked",true);
					}	
					JSONArray childArr = new JSONArray();
					for (Map<String, Object> right2 : authorizeRightList) {
						JSONObject childObj = new JSONObject();							
						String pid =String.valueOf(right2.get("PARENT_ID"));
						Object menuType = right2.get("MENU_TYPE");
						Object sMenuNo =right2.get("MENU_NO");
						Object role_ID=String.valueOf(right2.get("ROLE_ID"));					
						if("1".equals(menuType)&&pid.equals(menuNo)) {//一级菜单下的二级菜单
							childObj.put("id", right2.get("ID"));//权限编号
							childObj.put("text", right2.get("MENU"));//菜单名称
							childObj.put("rrid", right2.get("RRID"));//中间表的编号---》insert
							childObj.put("menuNo", sMenuNo);
							childObj.put("state", "open");						
							if("0".equals(role_ID)) {
								childObj.put("checked", false);
							}else {
								childObj.put("checked",true);
							}						
							childArr.add(childObj);
							JSONArray grandsonArr = new JSONArray();
							for (Map<String, Object> right3 : authorizeRightList) {
								Object parentId2 = String.valueOf(right3.get("PARENT_ID"));
								Object menuType2 = right3.get("MENU_TYPE");
								Object roleID =String.valueOf(right3.get("ROLE_ID"));
								if("2".equals(menuType2)&&parentId2.equals(sMenuNo)) {//二级菜单下的按钮
									JSONObject grandsonObj = new JSONObject();
									grandsonObj.put("id", right3.get("ID"));
									grandsonObj.put("text", right3.get("MENU"));
									grandsonObj.put("rrid", right3.get("RRID"));
									grandsonObj.put("menuNo", right3.get("MENU_NO"));
									grandsonObj.put("state", "open");
									if("0".equals(roleID)) {
										grandsonObj.put("checked", false);
									}else {
										grandsonObj.put("checked", true);
									}
									grandsonArr.add(grandsonObj);
								}
							}
							childObj.put("children", grandsonArr);
							
					
						}
					}
					jsonObject.put("children", childArr);
				}
				if(!jsonObject.isEmpty()) {
					jsonArray.add(jsonObject);
				}
				
				
			}
			responseMessage.setReturnResult(jsonArray);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return responseMessage;
	}

}
