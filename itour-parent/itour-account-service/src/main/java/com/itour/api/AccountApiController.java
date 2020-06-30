package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itour.account.api.AccountApi;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.AccountService;
import com.itour.service.GroupRoleService;
import com.itour.service.GroupService;
import com.itour.service.OauthService;
import com.itour.service.RightDetailService;
import com.itour.service.RightService;
import com.itour.service.RoleService;

@RestController
public class AccountApiController implements AccountApi {
@Autowired
AccountService accountService; 
@Autowired
OauthService oauthService;
@Autowired
RightService rightService;
@Autowired
RightDetailService rightDetailService;
@Autowired
GroupService groupService;
@Autowired
RoleService roleService;
@Autowired
GroupRoleService groupRoleService;

/**
 * 注册提交
 */
@Override
@RequestMapping("/account/regSub")
public ResponseMessage regSub(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return accountService.regiesterSub(requestMessage);
}
/**
 * 登录提交
 */
@Override
@RequestMapping("/account/loginSub")
public ResponseMessage loginSub(@RequestBody RequestMessage requestMessage) {
	return oauthService.loginSub(requestMessage);
}
/**
 * 检查账号信息
 */
@Override
@RequestMapping("/account/checkOauthId")
public ResponseMessage checkOauthId(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return oauthService.checkOauthId(requestMessage);
}
/**
 * 获取用户权限
 */
@Override
@RequestMapping("/account/queryAccountRight")
public ResponseMessage queryAccountRight(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return rightDetailService.queryAccountRight(requestMessage);
}
@Override
@RequestMapping("/account/findPwd")
public ResponseMessage findPwd(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return oauthService.updateCredential(requestMessage);
}
/**
 * 获取用户组列表
 */
@Override
@RequestMapping("/account/getGroupList")
public ResponseMessage getGroupList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return groupService.queryGroupList(requestMessage);
}
@Override
@RequestMapping("/account/insertGroup")
public ResponseMessage insertGroup(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return groupService.insertGroup(requestMessage);
}

/**
 * 获取用户角色列表
 */
@Override
@RequestMapping("/account/queryRoleList")
public ResponseMessage queryRoleList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return roleService.queryRoleList(requestMessage);
}

@Override
@RequestMapping("/menu/getMenuList")
public ResponseMessage getMenuList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return rightService.getMenuList(requestMessage);
}
/**
 * 用户组授权角色列表
 */
@Override
@RequestMapping("/account/authorizeRoleList")
public ResponseMessage authorizeRoleList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return groupService.authorizeRoleList(requestMessage);
}
/**
 * 用户组授权角色
 */
@Override
@RequestMapping("/account/powerRole")
public ResponseMessage powerRole(RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return groupRoleService.powerRole(requestMessage);
}

}
