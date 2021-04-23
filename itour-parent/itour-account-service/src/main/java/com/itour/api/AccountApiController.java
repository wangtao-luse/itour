package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itour.account.api.AccountApi;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.AccountGroupService;
import com.itour.service.AccountService;
import com.itour.service.GroupRoleService;
import com.itour.service.GroupService;
import com.itour.service.IpaddrService;
import com.itour.service.LoginListService;
import com.itour.service.OauthService;
import com.itour.service.RightDetailService;
import com.itour.service.RightService;
import com.itour.service.RoleRightService;
import com.itour.service.RoleService;
import com.itour.service.ViewAAccountGroupService;
import com.itour.service.ViewAAccountService;
import com.itour.service.ViewAOauthService;
import com.itour.service.ViewARightService;

@RestController
public class AccountApiController implements AccountApi {
@Autowired
private AccountService accountService; 
@Autowired
private OauthService oauthService;
@Autowired
private RightService rightService;
@Autowired
private RightDetailService rightDetailService;
@Autowired
private GroupService groupService;
@Autowired
private RoleService roleService;
@Autowired
private GroupRoleService groupRoleService;
@Autowired
private RoleRightService roleRightService;
@Autowired
private ViewAAccountService viewAAccountService;
@Autowired
private ViewARightService viewARightService;
@Autowired
private ViewAOauthService viewAOauthService;
@Autowired
private ViewAAccountGroupService viewAAccountGroupService;
@Autowired
private AccountGroupService accountGroupService;
@Autowired
private LoginListService loginListService;
@Autowired
private IpaddrService ipaddrService;
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
 * 忘记密码
 */
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
/**
 * 用户组信息添加
 */
@Override
@RequestMapping("/account/insertGroup")
public ResponseMessage insertGroup(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return groupService.insertGroup(requestMessage);
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
public ResponseMessage powerRole(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return groupRoleService.powerRole(requestMessage);
}
/**
 * 修改用户组信息
 */
@Override
@RequestMapping("/account/updateGroup")
public ResponseMessage updateGroup(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return groupService.updateGroup(requestMessage);
}
/**
 * 用户组查询单条
 */
@Override
@RequestMapping("/account/getGroup")
public ResponseMessage getGroup(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return groupService.getGroup(requestMessage);
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
/**
 * 角色查询单条
 */
@Override
@RequestMapping("/account/getRole")
public ResponseMessage getRole(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return roleService.getRole(requestMessage);
}
/**
 * 角色插入
 */
@Override
@RequestMapping("/account/insertRole")
public ResponseMessage insertRole(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return roleService.insertRole(requestMessage);
}
/**
 * 角色修改
 */
@Override
@RequestMapping("/account/updateRole")
public ResponseMessage updateRole(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return roleService.updateRole(requestMessage);
}
/**
 * 获取前台菜单
 */
@Override
@RequestMapping("/menu/getMenuList")
public ResponseMessage getMenuList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return rightService.getMenuList(requestMessage);
}
/**
 * 角色授权权限列表
 */
@Override
@RequestMapping("/account/authorizeRightList")
public ResponseMessage authorizeRightList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return roleService.authorizeRightList(requestMessage);
}
/**
 * 角色授权
 */
@Override
public ResponseMessage powerRight(@RequestBody RequestMessage postData) {
	// TODO Auto-generated method stub
	return roleRightService.powerRight(postData);
}
/**
 *前台 权限列表
 * @param postData
 * @return
 */
@Override
public ResponseMessage selectRightList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return rightService.getRightList(requestMessage);
}
/**
 * 前台会员列表
 */
@Override
public ResponseMessage selectAccountList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return accountService.selectAccountList(requestMessage);
}
/**
 * 前台会员列表(视图)
 */
@Override
public ResponseMessage selectViewAccountList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return viewAAccountService.selectViewAccountList(requestMessage);
}
/**
 * 前台用户权限列表（视图）
 */
@Override
public ResponseMessage selectViewRightList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return viewARightService.selectViewRightList(requestMessage);
}
/**
 * 前台用户查看详情
 */
@Override
public ResponseMessage getOauthList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return oauthService.getOAuthList(requestMessage);
}
/**
 * 前台用户查看详情(视图)
 */
@Override
public ResponseMessage getViewOauthList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return viewAOauthService.getViewOAuthList(requestMessage);
}

/**
 * 获取指定的组下的会员
 */
@Override
public ResponseMessage getViewAAccountGroupList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return viewAAccountGroupService.getViewAAccountGroupList(requestMessage);
}
/**
 * 分配会员
 */
@Override
public ResponseMessage grantAccount(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return accountGroupService.grantAccount(requestMessage);
}
/**
 * 删除已分配会员的所属组
 */
@Override
public ResponseMessage deleteAccountGroup(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return accountGroupService.deleteAccountGroup(requestMessage);
}
/**
 * 获取当前用户下的组名称
 */
@Override
public ResponseMessage getAccountGroupName(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return groupService.getAccountGroupName(requestMessage);
}
/**
 * 获取当前用户下的角色名称
 */
@Override
public ResponseMessage getAccountRoleName(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return roleService .getAccountRoleName(requestMessage);
}
/**
 * 获取当前用户下的权限
 */
@Override
public ResponseMessage getAccountRightDetial(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return rightDetailService .getAccountRightDetial(requestMessage);
}
/**
 * 用户单条
 */
@Override
public ResponseMessage getAccount(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return accountService.getAccount(requestMessage);
}
/**
 * 用户修改
 */
@Override
public ResponseMessage updateAccount(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return accountService.updateAccount(requestMessage);
}
/**
 * 获取无需登录的资源
 * @param requestMessage
 * @return
 */
@Override
public ResponseMessage getAccountRightAnon(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return rightDetailService.getAccountRightAnon(requestMessage);
}
//前台登录日志查询
@Override
public ResponseMessage queryLoginList(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return loginListService.queryLoginList(requestMessage);
}
/**
 * 插入IP地址
 */
@Override
public ResponseMessage insertIPAddr(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return ipaddrService.insertIPAddr(requestMessage);
}
/**
 * 修改用户信息认证表
 */
@Override
public ResponseMessage updateOAuthById(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return this.oauthService.updateOAuthById(requestMessage);
}

}
