package com.itour.member.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
@FeignClient(name = "itour-member-service")
public interface MemberApi {
	/**
	 * 管理平台菜单列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "menu/getMenuList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getMenuList(RequestMessage requestMessage);
    /**
	 * 登录提交
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/loginSub",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage loginSub(RequestMessage requestMessage);
	/**
	 * 后台管理员注册
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/adminSub",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage adminSub(RequestMessage requestMessage);
	/**
	 * 后台管理员列表查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/selectAccountList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage selectAccountList(RequestMessage requestMessage);
	/**
	 * 后台管理员列表查询（视图）
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/selectViewAccountList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage selectViewAccountList(RequestMessage requestMessage);
	/**
	 * 用户组列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getGroupList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getGroupList(RequestMessage requestMessage);	
	/**
	 * 角色列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/queryRoleList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryRoleList(RequestMessage requestMessage);
	/**
	 * 权限列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getRightList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getRightList(RequestMessage requestMessage);
	/**
	/**
	 * 权限列表（视图）
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getViewRightList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getViewRightList(RequestMessage requestMessage);
	/**
	 * 组授权角色
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/authorizeRoleList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage authorizeRoleList(RequestMessage requestMessage);
	/**
	 * 组插入
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/insertGroup",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage insertGroup(RequestMessage requestMessage);
	/**
	 * 修改组
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/updateGroup",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage updateGroup(RequestMessage requestMessage);
	/**
	 * 组查询单条
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getGroup",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getGroup(RequestMessage requestMessage);
	/**
	 * 组授权角色
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/powerRole",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage powerRole(RequestMessage requestMessage);
	/**
	 * 角色授权列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/authorizeRightList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage authorizeRightList(RequestMessage requestMessage);
	/**
	 * 角色授权
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/powerRight",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage powerRight(RequestMessage requestMessage);
	
	/**
	 * 后台管理员账号详情（视图）
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getViewOauthList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getViewOauthList(RequestMessage requestMessage);
	/**
	 * 后台管理员账号详情
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getOAuthList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getOAuthList(RequestMessage requestMessage);

	/**
	 * 后台管理员查询指定组下的所有用户列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getViewMAccountGroupList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getViewMAccountGroupList(RequestMessage requestMessage);
	/**
	 * 后台管理员分配管理员
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/grantAccount",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage grantAccount(RequestMessage requestMessage);
	/**
	 * 删除该用户所属组
	 * @param requestMessage
	 * @return
	 */	
	@RequestMapping(value = "member/deleteAccountGroup",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage deleteAccountGroup(RequestMessage requestMessage);
	/**
	 * 获取当前用户下的组名称
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getAccountGroupName",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getAccountGroupName(RequestMessage requestMessage);
	/**
	 * 获取当前用户下的组名称
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getAccountRoleName",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getAccountRoleName(RequestMessage requestMessage);
	/**
	 * 获取当前用户下的权限
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getAccountRightDetial",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getAccountRightDetial(RequestMessage requestMessage);
	/**
	 * 角色查询单条
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getRole",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getRole(RequestMessage requestMessage);
	/**
	 * 角色新增
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/insertRole",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage insertRole(RequestMessage requestMessage);
	/**
	 * 用户查询单条
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getAccount",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getAccount(RequestMessage requestMessage);
	/**
	 * 用户修改
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/updateAccount",produces = {"application/json;charset=UTF-8"})
	ResponseMessage updateAccount(RequestMessage requestMessage);
	/**
	 * 检查邮箱,用户名是否可用
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/checkOauthId",produces = {"application/json;charset=UTF-8"})
	ResponseMessage checkOauthId(RequestMessage requestMessage);
	/**
	 * 注册提交
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/regSub",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage regSub(RequestMessage requestMessage);
	
	

	
}
