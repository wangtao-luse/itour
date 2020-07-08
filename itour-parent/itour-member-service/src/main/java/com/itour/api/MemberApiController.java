package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.member.api.MemberApi;
import com.itour.model.member.RoleRight;
import com.itour.persist.RightMapper;
import com.itour.service.AccountService;
import com.itour.service.GroupRoleService;
import com.itour.service.GroupService;
import com.itour.service.OauthService;
import com.itour.service.RightService;
import com.itour.service.RoleRightService;
import com.itour.service.RoleService;
import com.itour.service.ViewMAccountService;

@RestController
public class MemberApiController implements MemberApi {
	@Autowired
private	RightService rightService;
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
	/**
	 * 平台管理左侧菜单
	 */
    @RequestMapping("/menu/getMenuList")
	@Override
	public ResponseMessage getMenuList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return rightService.getMenuList(requestMessage);
	}
    /**
	 * 登录提交
	 */
	@RequestMapping("/member/loginSub")
	@Override
	public ResponseMessage loginSub(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return oauthService.loginSub(requestMessage);
	}
	/**
	 * 后台管理员注册
	 */
	@RequestMapping("/member/adminSub")
	@Override
	public ResponseMessage adminSub(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return accountService.regiesterSub(requestMessage);
	}
	/**
	 * 后台管理员注册列表
	 */
	@RequestMapping("/member/selectAccountList")
	@Override
	public ResponseMessage selectAccountList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return accountService.selectAccountList(requestMessage);
	}	
	/**
	 * 后台管理员注册列表
	 */
	@RequestMapping("/member/selectViewAccountList")
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
	
	
	

}
