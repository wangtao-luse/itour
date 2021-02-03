package com.itour.member.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseMessage getMenuList(@RequestBody RequestMessage requestMessage);
    /**
	 * 登录提交
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/loginSub",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage loginSub(@RequestBody RequestMessage requestMessage);
	/**
	 * 后台管理员注册
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/adminSub",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage adminSub(@RequestBody RequestMessage requestMessage);
	/**
	 * 后台管理员列表查询
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/selectAccountList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage selectAccountList(@RequestBody RequestMessage requestMessage);
	/**
	 * 后台管理员列表查询（视图）
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/selectViewAccountList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage selectViewAccountList(@RequestBody RequestMessage requestMessage);
	/**
	 * 用户组列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getGroupList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getGroupList(@RequestBody RequestMessage requestMessage);	
	/**
	 * 角色列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/queryRoleList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage queryRoleList(@RequestBody RequestMessage requestMessage);
	/**
	 * 权限列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getRightList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getRightList(@RequestBody RequestMessage requestMessage);
	/**
	/**
	 * 权限列表（视图）
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getViewRightList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getViewRightList(@RequestBody RequestMessage requestMessage);
	/**
	 * 组授权角色
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/authorizeRoleList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage authorizeRoleList(@RequestBody RequestMessage requestMessage);
	/**
	 * 组插入
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/insertGroup",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage insertGroup(@RequestBody RequestMessage requestMessage);
	/**
	 * 修改组
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/updateGroup",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage updateGroup(@RequestBody RequestMessage requestMessage);
	/**
	 * 组查询单条
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getGroup",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getGroup(@RequestBody RequestMessage requestMessage);
	/**
	 * 组授权角色
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/powerRole",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage powerRole(@RequestBody RequestMessage requestMessage);
	/**
	 * 角色授权列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/authorizeRightList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage authorizeRightList(@RequestBody RequestMessage requestMessage);
	/**
	 * 角色授权
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/powerRight",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage powerRight(@RequestBody RequestMessage requestMessage);
	
	/**
	 * 后台管理员账号详情（视图）
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getViewOauthList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getViewOauthList(@RequestBody RequestMessage requestMessage);
	/**
	 * 后台管理员账号详情
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getOAuthList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getOAuthList(@RequestBody RequestMessage requestMessage);

	/**
	 * 后台管理员查询指定组下的所有用户列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getViewMAccountGroupList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getViewMAccountGroupList(@RequestBody RequestMessage requestMessage);
	/**
	 * 后台管理员分配管理员
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/grantAccount",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage grantAccount(@RequestBody RequestMessage requestMessage);
	/**
	 * 删除该用户所属组
	 * @param requestMessage
	 * @return
	 */	
	@RequestMapping(value = "member/deleteAccountGroup",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage deleteAccountGroup(@RequestBody RequestMessage requestMessage);
	/**
	 * 获取当前用户下的组名称
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getAccountGroupName",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getAccountGroupName(@RequestBody RequestMessage requestMessage);
	/**
	 * 获取当前用户下的组名称
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getAccountRoleName",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getAccountRoleName(@RequestBody RequestMessage requestMessage);
	/**
	 * 获取当前用户下的权限
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getAccountRightDetial",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getAccountRightDetial(@RequestBody RequestMessage requestMessage);
	/**
	 * 角色查询单条
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getRole",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getRole(@RequestBody RequestMessage requestMessage);
	/**
	 * 角色新增
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/insertRole",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage insertRole(@RequestBody RequestMessage requestMessage);
	/**
	 * 用户查询单条
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/getAccount",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getAccount(@RequestBody RequestMessage requestMessage);
	/**
	 * 用户修改
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/updateAccount",produces = {"application/json;charset=UTF-8"})
	ResponseMessage updateAccount(@RequestBody RequestMessage requestMessage);
	/**
	 * 检查邮箱,用户名是否可用
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/checkOauthId",produces = {"application/json;charset=UTF-8"})
	ResponseMessage checkOauthId(@RequestBody RequestMessage requestMessage);
	/**
	 * 注册提交
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/regSub",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage regSub(@RequestBody RequestMessage requestMessage);
	
	

	
}
