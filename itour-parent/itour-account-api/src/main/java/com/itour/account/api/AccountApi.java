package com.itour.account.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
@FeignClient(name = "itour-account-service")
public interface AccountApi {
/**
 * 注册提交	
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "account/regSub",produces = {"application/json;charset=UTF-8"})
public ResponseMessage regSub(RequestMessage requestMessage);
/**
 * 登录提交
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "account/loginSub",produces = {"application/json;charset=UTF-8"})
public ResponseMessage loginSub(RequestMessage requestMessage);
/**
 * 检验用户信息
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "account/checkOauthId",produces = {"application/json;charset=UTF-8"})
public ResponseMessage checkOauthId(RequestMessage requestMessage);
/**
 * 获取菜单
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "account/queryAccountRight",produces = {"application/json;charset=UTF-8"})
public ResponseMessage queryAccountRight(RequestMessage requestMessage);
/**
 * 忘记密码
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "account/findPwd",produces = {"application/json;charset=UTF-8"})
public ResponseMessage findPwd(RequestMessage requestMessage);
/**
 * 用户组列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "account/getGroupList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getGroupList(RequestMessage requestMessage);
/**
 * 角色列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "account/queryRoleList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage queryRoleList(RequestMessage requestMessage);
/**
 * 管理平台左侧菜单列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "menu/getMenuList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getMenuList(RequestMessage requestMessage);
}

