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
import com.itour.model.member.Group;
import com.itour.persist.GroupMapper;
import com.itour.persist.RoleMapper;

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
	@Autowired
	private RoleMapper roleMapper;
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
				queryWrapper.likeRight(!StringUtils.isEmpty(group.getgDesc()), "G_DESC",group.getgDesc());
				if(null!=group.getgParent()) {
					queryWrapper.eq( "ID", group.getgParent());	
					queryWrapper.or().eq("G_PARENT", group.getgParent());
				}
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
			queryWrapper.or().eq("G_DESC", group.getgDesc());
			Integer selectCount = this.baseMapper.selectCount(queryWrapper);
			if(selectCount>0) {
				throw new BaseException(ExceptionInfo.EXCEPTION_GROUP);
			}
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
			QueryWrapper<Group> queryWrapper = new QueryWrapper<Group>();
			queryWrapper.eq("G_NAME", group.getgName());
			queryWrapper.ne("ID", group.getId());
			queryWrapper.or().eq("G_DESC", group.getgDesc());
			queryWrapper.ne("ID", group.getId());
			Integer selectCount = this.baseMapper.selectCount(queryWrapper);
			if(selectCount>0) {
				throw new BaseException(ExceptionInfo.EXCEPTION_GROUP);
			}
		    int updateById = this.baseMapper.updateById(group);   
		}catch (BaseException e) {
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
	/**
	 * 组授权角色列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage authorizeRoleList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			//1.获取当前组
			Integer id = requestMessage.getBody().getContent().getInteger("id");
			Group group = this.baseMapper.selectById(id);
			//2.获取当前组下的所有的组及子组
			List<Group> glist = new ArrayList<Group>();
			glist.add(group);
			getNextGroup(glist, id);
//			//3.组装数据
			JSONArray jsonArray = new JSONArray();		
			for (Group g : glist) {
				//3.1组装组信息
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", g.getId());
				jsonObject.put("text",g.getgDesc());
				jsonObject.put("state", "open");
				jsonArray.add(jsonObject);
				//3.1获取该组下拥有的角色及所有角色
				List<Map<String, Object>> queryGroupRole = roleMapper.queryGroupRole(g.getId());
				//3.2组装角色信息
				JSONArray childArray = new JSONArray();			
				for (Map<String, Object> role : queryGroupRole) {
					JSONObject childJsonObject = new JSONObject();	
					childJsonObject.put("id", role.get("ID"));
					childJsonObject.put("text", role.get("ROLE_DESC"));
					childJsonObject.put("state", "open");
					//该角色的所属组(授权提交的时候使用)
					childJsonObject.put("gid", g.getId());
					//组-角色表的编号
					childJsonObject.put("grNo", role.get("grNo"));
					//GROUP_ID:0 改角色下没有该角色
					String str =(String) role.get("GROUP_ID");
					if("0".equals(str)) {//该组下没有对应的角色
						childJsonObject.put("checked",false );
					}else {
						childJsonObject.put("checked",true);
						
					}
					
					childArray.add(childJsonObject);
				}
				jsonObject.put("children", childArray);
			}
			responseMessage.setReturnResult(jsonArray);
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
	 * 获取当期组下的所有后代
	 * @param list
	 * @param id
	 */
	public void getNextGroup(List<Group> list,Integer id){
		QueryWrapper<Group> queryWrapper = new QueryWrapper<Group>();
		queryWrapper.eq("G_PARENT", id);
		List<Group> selectList = this.baseMapper.selectList(queryWrapper);
		for (Group group : selectList) {
			list.add(group);
			getNextGroup(list, group.getId());
		}
		
	}
	/**
	 * 用户组查询单条
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage getGroup(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			Group group = requestMessage.getBody().getContent().getJSONObject("vo").toJavaObject(Group.class);
			QueryWrapper<Group> queryWrapper = new QueryWrapper<Group>();
			queryWrapper.eq("ID", group.getId());
			Group selectOne = this.baseMapper.selectOne(queryWrapper);
			responseMessage.setReturnResult(selectOne);
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
	 * 获取当前用户下的组名称
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage getAccountGroupName(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String uid = jsonObject.getString("uid");
			 List<Map<String, Object>> accountGr = this.baseMapper.getAccountGroupName(uid);
			responseMessage.setReturnResult(accountGr);
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
}
