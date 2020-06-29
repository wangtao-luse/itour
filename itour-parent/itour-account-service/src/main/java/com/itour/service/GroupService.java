package com.itour.service;

import java.util.ArrayList;
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
import com.itour.model.account.Group;
import com.itour.persist.GroupMapper;
import com.itour.persist.RoleMapper;

/**
 * <p>
 * 用户组表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-07
 */
@Service
public class GroupService extends ServiceImpl<GroupMapper, Group>  {
	@Autowired
	RoleMapper roleMapper;
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
		QueryWrapper<Group> queryWrapper = new QueryWrapper<Group>();
		queryWrapper.eq("G_NAME", group.getgName());
		Integer selectCount = this.baseMapper.selectCount(queryWrapper);
		if(selectCount>0) {
			throw new BaseException(ExceptionInfo.EXCEPTION_GROUP);
		}
	    this.baseMapper.insert(group);	    
	}catch (BaseException e) {
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
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
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
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	
	return responseMessage;
}
/**
 * 授权角色 
 * @param requestMessage
 * @return
 */
public ResponseMessage authorizeRole(RequestMessage requestMessage) {	
	try {
		Integer id = requestMessage.getBody().getContent().getInteger("id");
		Group group = this.baseMapper.selectById(id);
		//所有的组
		List<Group> glist = new ArrayList<Group>();
		glist.add(group);
		getNextGroup(glist, id);
		JSONArray jsonArray = new JSONArray();		
		for (Group g : glist) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", g.getId());
			jsonObject.put("text",g.getgDesc());
			jsonObject.put("state", "open");
			jsonArray.add(jsonObject);
			List<Map<String, Object>> queryGroupRole = roleMapper.queryGroupRole(g.getId());
			JSONArray childArray = new JSONArray();			
			for (Map<String, Object> role : queryGroupRole) {
				JSONObject childJsonObject = new JSONObject();	
				childJsonObject.put("id", role.get("id"));
				childJsonObject.put("text", role.get("roleName"));
				childJsonObject.put("state", "open");
				String str =(String) role.get("groupId");
				if("0".equals(str)) {
					childJsonObject.put("checked",false );
				}else {
					childJsonObject.put("checked",false );
				}
				
				childArray.add(childJsonObject);
			}
			jsonObject.put("children", childArray);
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
	
	return null;
}
/**
 * 获取当期组下的所有后代
 * @param list
 * @param id
 */
public void getNextGroup(List<Group> list,Integer id){
	QueryWrapper<Group> queryWrapper = new QueryWrapper<Group>();
	queryWrapper.eq("gParent", id);
	List<Group> selectList = this.baseMapper.selectList(queryWrapper);
	for (Group group : selectList) {
		list.add(group);
		getNextGroup(list, group.getId());
	}
	
}
}
