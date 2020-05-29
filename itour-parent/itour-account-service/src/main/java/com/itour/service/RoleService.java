package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.model.account.Role;
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

public ResponseMessage getMenuList(RequestMessage requestMessage) {
	return null;
	
}
	
}
