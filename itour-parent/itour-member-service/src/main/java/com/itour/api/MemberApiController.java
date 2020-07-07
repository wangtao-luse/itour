package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.member.api.MemberApi;
import com.itour.service.AccountService;
import com.itour.service.GroupService;
import com.itour.service.OauthService;
import com.itour.service.RightService;
import com.itour.service.RoleService;

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
	 * 后台管理员注册
	 */
	@RequestMapping("/member/selectAccountList")
	@Override
	public ResponseMessage selectAccountList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return accountService.selectAccountList(requestMessage);
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
	@Override
	public ResponseMessage authorizeRoleList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return groupService.authorizeRoleList(requestMessage);
	}
	
	
	

}
