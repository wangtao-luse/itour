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
@RequestMapping(value = "/account/regSub",produces = {"application/json;charset=UTF-8"})
public ResponseMessage regSub(RequestMessage requestMessage);
/**
 * 登录提交
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/loginSub",produces = {"application/json;charset=UTF-8"})
public ResponseMessage loginSub(RequestMessage requestMessage);
/**
 * 检验用户信息
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/checkOauthId",produces = {"application/json;charset=UTF-8"})
public ResponseMessage checkOauthId(RequestMessage requestMessage);

/**
 * 忘记密码
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/findPwd",produces = {"application/json;charset=UTF-8"})
public ResponseMessage findPwd(RequestMessage requestMessage);
/**
 * 用户组列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/getGroupList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getGroupList(RequestMessage requestMessage);
/**
 * 用户组新增
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/insertGroup",produces = {"application/json;charset=UTF-8"})
ResponseMessage insertGroup(RequestMessage requestMessage);
/**
 * 菜单列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/menu/getMenuList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getMenuList(RequestMessage requestMessage);
/**
 * 组授权列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "account/authorizeRoleList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage authorizeRoleList(RequestMessage requestMessage);
/**
 * 组授权
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "account/powerRole",produces = {"application/json;charset=UTF-8"})
public ResponseMessage powerRole(RequestMessage requestMessage);
/**
 * 修改用户组
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "account/updateGroup",produces = {"application/json;charset=UTF-8"})
public ResponseMessage updateGroup(RequestMessage requestMessage);
/**
 * 用户组查询单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "account/getGroup",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getGroup(RequestMessage requestMessage);
/**
 * 角色列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/queryRoleList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage queryRoleList(RequestMessage requestMessage);
/**
 * 角色单条查询
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/getRole",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getRole(RequestMessage requestMessage);
/**
 * 角色插入
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/insertRole",produces = {"application/json;charset=UTF-8"})
public ResponseMessage insertRole(RequestMessage requestMessage);
/**
 * 角色修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/updateRole",produces = {"application/json;charset=UTF-8"})
public ResponseMessage updateRole(RequestMessage requestMessage);
/**
 * 角色授权权限列表
 * @param postData
 * @return
 */
@RequestMapping(value = "/account/authorizeRightList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage authorizeRightList(RequestMessage postData);
/**
 * 角色授权权限
 * @param postData
 * @return
 */
@RequestMapping(value = "/account/powerRight",produces = {"application/json;charset=UTF-8"})
public ResponseMessage powerRight(RequestMessage postData);
/**
 *前台 权限列表
 * @param postData
 * @return
 */
@RequestMapping(value = "/account/selectRightList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage selectRightList(RequestMessage postData);
/**
 * 前台会员列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/selectAccountList",produces = {"application/json;charset=UTF-8"})
ResponseMessage selectAccountList(RequestMessage requestMessage);
/**
 * 前台会员列表（视图）
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/selectViewAccountList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage selectViewAccountList(RequestMessage requestMessage);
/**
 * 前台权限列表查询（视图）
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/selectViewRightList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage selectViewRightList(RequestMessage requestMessage);
/**
 * 前台会员查看详情
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/getOauthList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getOauthList(RequestMessage requestMessage);
/**
 * 前台会员查看详情(视图)
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/getViewOauthList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getViewOauthList(RequestMessage requestMessage);
/**
 * 获取指定组下的用户列表
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/getViewAAccountGroupList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getViewAAccountGroupList(RequestMessage requestMessage);
/**
 * 分配会员
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/grantAccount",produces = {"application/json;charset=UTF-8"})
public ResponseMessage grantAccount(RequestMessage requestMessage);
/**
 *  删除已分配会员的所属组
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/deleteAccountGroup",produces = {"application/json;charset=UTF-8"})
public ResponseMessage deleteAccountGroup(RequestMessage requestMessage);
/**
 *  获取当前用户下的所有组名称
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/getAccountGroupName",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getAccountGroupName(RequestMessage requestMessage);
/**
 * 获取当前用户下的角色
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/getAccountRoleName",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getAccountRoleName(RequestMessage requestMessage);
/**
 * 获取当前用户下的权限
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/getAccountRightDetial",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getAccountRightDetial(RequestMessage requestMessage);
/**
 * 用户查询单条
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/getAccount",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getAccount(RequestMessage requestMessage);
/**
 * 用户修改
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/updateAccount",produces = {"application/json;charset=UTF-8"})
public ResponseMessage updateAccount(RequestMessage requestMessage);
/**
 * 获取无需登录的资源
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/getAccountRightAnon",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getAccountRightAnon(RequestMessage requestMessage);
/**
 * 前台登录日志查询
 * @param requestMessage
 * @return
 */
@RequestMapping(value = "/account/queryLoginList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage queryLoginList(RequestMessage requestMessage);
}

