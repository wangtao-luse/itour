package com.itour.member.api;

import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

public interface MemberApi {
	/**
	 * 管理平台菜单列表
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "menu/getMenuList",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage getMenuList(RequestMessage requestMessage);
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
	 * 登录提交
	 * @param requestMessage
	 * @return
	 */
	@RequestMapping(value = "member/loginSub",produces = {"application/json;charset=UTF-8"})
	public ResponseMessage loginSub(RequestMessage requestMessage);
}
