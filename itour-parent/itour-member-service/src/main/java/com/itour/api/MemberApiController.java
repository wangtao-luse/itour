package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.member.api.MemberApi;
import com.itour.service.RightService;

@RestController
public class MemberApiController implements MemberApi {
	@Autowired
private	RightService rightService;
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
     * 组列表
     */
	@Override
	public ResponseMessage getGroupList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 角色列表
	 */
	@Override
	public ResponseMessage queryRoleList(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 登录提交
	 */
	@Override
	public ResponseMessage loginSub(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
