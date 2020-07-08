package com.itour.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.member.GroupRole;
import com.itour.persist.GroupRoleMapper;

/**
 * <p>
   * 组-角色表 服务类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-28
 */
@Service
public class GroupRoleService extends ServiceImpl<GroupRoleMapper,GroupRole> {
    @Transactional
	public ResponseMessage powerRole(RequestMessage requestMessage) {
    	ResponseMessage responseMessage =ResponseMessage.getSucess();
    	try {
    		JSONObject jsonObject = requestMessage.getBody().getContent();		
    	    JSONArray jsonArray = jsonObject.getJSONArray("arr");
    	    List<GroupRole> insertList = new ArrayList<GroupRole>();
    	    List<Integer> deleteList = new ArrayList<Integer>();
    	    for (Object jsonArr : jsonArray) {
    	    	GroupRole gr = new GroupRole();
    	    	JSONObject arr = JSONObject.parseObject(JSONObject.toJSONString(jsonArr));
    	    	Integer groupId = arr.getInteger("groupId");
    	    	Integer roleId = arr.getInteger("roleId");
    	    	boolean checked = arr.getBoolean("checked");
    	    	Integer grNo = arr.getInteger("grNo");    	    	
    	    	if(checked) {//选中
    	    		gr.setGroupId(groupId);
    	    		gr.setRoleId(roleId);    	    		
					gr.setId(grNo);					 
					insertList.add(gr); 
    	    	}else {//未选中
	    		    if(null!=grNo) {
	    		    	deleteList.add(grNo);	
	    		    }
	    		    
    	    	}
    	    	
    		}
    	    //插入新增的角色
    	    if(insertList.size()>0) {
    	    	this.saveOrUpdateBatch(insertList);
    	    }
    	    if(deleteList.size()>0) {//删除授权的角色
    	    	 this.baseMapper.deleteBatchIds(deleteList);
    	    }
    	   
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
	}
}
