package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.member.api.MemberApi;
import com.itour.service.AccountGroupService;
import com.itour.service.AccountService;
import com.itour.service.GroupRoleService;
import com.itour.service.GroupService;
import com.itour.service.OauthService;
import com.itour.service.RightDetailService;
import com.itour.service.RightService;
import com.itour.service.RoleRightService;
import com.itour.service.RoleService;
import com.itour.service.ViewMAccountGroupService;
import com.itour.service.ViewMAccountService;
import com.itour.service.ViewMOauthService;
import com.itour.service.ViewMRightService;

@RestController
public class MemberApiController implements MemberApi {
	@Autowired
private	RightService rightService;
	@Autowired
private RightDetailService rightDetailService;
	@Autowired
private OauthService oauthService;
	@Autowired
private RoleService roleService;
	@Autowired
private GroupService groupService;
	@Autowired
private AccountService accountService;
	@Autowired
private GroupRoleService groupRoleService;
	@Autowired
private RoleRightService roleRightService;
	@Autowired
private	ViewMAccountService viewMAccountService;
	@Autowired
private	ViewMRightService viewMRightService;
	@Autowired
private ViewMOauthService viewMOauthService;
	@Autowired
private AccountGroupService accountGroupService;
	@Autowired
private ViewMAccountGroupService viewMAccountGroupService;

	/**
	 * 平台管理左侧菜单
	 */
	@Override
	public ResponseMessage getMenuList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return rightService.getMenuList(requestMessage);
	}
    /**
	 * 登录提交
	 */
	@Override
	public ResponseMessage loginSub(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return oauthService.loginSub(requestMessage);
	}
	/**
	 * 后台管理员注册
	 */
	@Override
	public ResponseMessage adminSub(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return accountService.regiesterSub(requestMessage);
	}
	/**
	 * 后台管理员注册列表
	 */
	@Override
	public ResponseMessage selectAccountList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return accountService.selectAccountList(requestMessage);
	}	
	/**
	 * 后台管理员注册列表
	 */
	@Override
	public ResponseMessage selectViewAccountList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewMAccountService.selectViewAccountList(requestMessage);
	}	
	
    /**
     * 组列表
     */
	@Override
	public ResponseMessage getGroupList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return groupService.queryGroupList(requestMessage);
	}
	/**
	 * 角色列表
	 */
	@Override
	public ResponseMessage queryRoleList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return roleService.queryRoleList(requestMessage);
	}
	/**
	 * 权限列表
	 */
	@Override
	public ResponseMessage getRightList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return rightService.getRightList(requestMessage);
	}
	/**
	 * 组授权角色列表
	 */
	@Override
	public ResponseMessage authorizeRoleList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return groupService.authorizeRoleList(requestMessage);
	}
	/**
	 * 组插入
	 */
	@Override
	public ResponseMessage insertGroup(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return groupService.insertGroup(requestMessage);
	}
	/**
	 * 组修改
	 */
	@Override
	public ResponseMessage updateGroup(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return groupService.updateGroup(requestMessage);
	}
	/**
	 * 组查询单条
	 */
	@Override
	public ResponseMessage getGroup(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return groupService.getGroup(requestMessage);
	}
	/**
	 * 组授权角色
	 */
	@Override
	public ResponseMessage powerRole(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return groupRoleService.powerRole(requestMessage);
	}
	/**
	 * 角色授权权限列表
	 */
	@Override
	public ResponseMessage authorizeRightList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return roleService.authorizeRightList(requestMessage);
	}
	/**
	 * 角色授权
	 */
	@Override
	public ResponseMessage powerRight(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return roleRightService.powerRight(requestMessage);
	}
	/**
	 * 管理员权限列表(视图)
	 */
	@Override
	public ResponseMessage getViewRightList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewMRightService.selectViewRightList(requestMessage);
	}
	/**
	 * 后台管理员账号详情
	 */
	@Override
	public ResponseMessage getOAuthList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return oauthService.getOAuthList(requestMessage);
	}
	/**
	 * 后台管理员账号详情(视图)
	 */
	@Override
	public ResponseMessage getViewOauthList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewMOauthService.getViewOAuthList(requestMessage);
	}

	/**
	 *后台查询指定组下的会员列表
	 */
	@Override
	public ResponseMessage getViewMAccountGroupList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewMAccountGroupService.getViewMAccountGroupList(requestMessage);
	}
	
	/**
	 * 后台管理员分配管理员
	 */
	@Override
	public ResponseMessage grantAccount(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return accountGroupService.grantAccount(requestMessage);
	}
	/**
	 * 删除该用户所属组
	 */
	@Override
	public ResponseMessage deleteAccountGroup(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return accountGroupService.deleteAccountGroup(requestMessage);
	}
	/**
	 * 获取当前用户下的所有组名称
	 */
	@Override
	public ResponseMessage getAccountGroupName(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return groupService.getAccountGroupName(requestMessage);
	}
	/**
	 * 获取当前用户下的所有组名称
	 */
	@Override
	public ResponseMessage getAccountRoleName(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return roleService.getAccountRoleName(requestMessage);
	}
	/**
	 * 获取当前用户下的权限
	 */
	@Override
	public ResponseMessage getAccountRightDetial(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return rightDetailService.getAccountRightDetial(requestMessage);
	}
	/**
	 * 角色查询单条
	 */
	@Override
	public ResponseMessage getRole(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return roleService.getRole(requestMessage);
	}
	/**
	 * 角色新增
	 */
	@Override
	public ResponseMessage insertRole(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return roleService.insertRole(requestMessage);
	}
	/**
	 * 管理员查询单条
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
	 * 检查用户名、邮箱是否可用
	 */
	@Override
	public ResponseMessage checkOauthId(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return oauthService.checkOauthId(requestMessage);
	}
	/**
	 * 注册提交
	 */
	@Override
	public ResponseMessage regSub(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return accountService.regiesterSub(requestMessage);
	}
	/**
	 *  后台管理员单条
	 */
	@Override
	public ResponseMessage selectViewOAuthOne(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewMAccountService.selectViewAccountOne(requestMessage);
	}
	
	
	
	

}
