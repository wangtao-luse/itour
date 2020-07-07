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
	@RequestMapping(value = "member/authorizeRoleList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage authorizeRoleList(RequestMessage requestMessage);

	
}
