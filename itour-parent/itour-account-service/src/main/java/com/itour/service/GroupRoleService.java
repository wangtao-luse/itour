package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.model.account.GroupRole;
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

	public ResponseMessage powerRole(RequestMessage requestMessage) {
		JSONObject jsonObject = requestMessage.getBody().getContent();		
	    JSONArray jsonArray = jsonObject.getJSONArray("arr");
	     List<GroupRole> javaList = jsonArray.toJavaList(GroupRole.class);
	
		return null;
	}
}
