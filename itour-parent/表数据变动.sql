--2020-7-2 wangtao
--------------后台菜单相关开始-------------------------------
---一级菜单 url给0;PARENT_ID给0;
---二级菜单M_ORDER给所属一级菜单的M_ORDER
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1000', '权限管理', '0', '1', '0', '1', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1001', '会员组管理', '1000', '1', '/account/groupPage', '1', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1004', '新增', '1001', '2', '', '1', '0-1' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1005', '编辑', '1001', '2', '', '1', '0-2' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1006', '授权', '1001', '2', '', '1', '0-3' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1007', '分配会员', '1001', '2', '', '1', '0-4' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1002', '会员角色管理', '1000', '1', '/account/rolePage', '1','1' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1003', '会员权限管理', '1000', '1', '/account/rightPage', '1','2' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2000', '管理员管理', '0', '1', '0', '2', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2001', '管理员信息', '2000', '1', '/member/adminPage', '2', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2002', '管理员组管理', '2000', '1', '/member/groupPage', '2', '1' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2007', '新增', '2002', '2', '0', '2', '0-1' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2008', '编辑', '2002', '2', '0', '2', '0-2' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2009', '授权', '2002', '2', '0', '2', '0-3' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2010', '分配会员', '2002', '2', '0', '2', '0-4' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2003', '管理员角色管理', '2000', '1', '/member/rolePage', '2', '2' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2012', '新增', '2003', '2', '0', '2', '0-1' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2005', '编辑', '2003', '2', '0', '2', '0-2' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2006', '授权', '2003', '2', '0', '2', '0-3' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2004', '管理员权限管理', '2000', '1', '/member/rightPage', '2', '3' );	
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2011', '编辑', '2004', '21', '', '2', '2' );	
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '3000', '数据字典管理', '0', '1', '0', '3', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '3001', '数据字典信息', '3000', '1', '/dictionary/dictPage', '2', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '3002', '编辑', '3001', '2', '0', '2', '0-1' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4000', '会员管理', '0', '1', '0', '3', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4001', '会员查询', '4000', '1', '/account/accountPage', '0', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4002', '新增', '4001', '2', '0', '0', '0-1' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4003', '编辑', '4001', '2', '0', '0', '0-2' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4004', '查询详情', '4000', '2', '0', '0', '0-3' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '5000', '消息管理', '0', '1', '0', '4', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '5001', '消息管理', '5000', '1', '/msg/msgPage', '4', '0' );

--------------后台菜单明细相关开始------------------------------------
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/selectViewAccountList','4000','authc','前台会员列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/dictionary/getViewDictionaryList','3001','authc','数据字典列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/login','-1','anon','登录页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/loginSub','-1','anon','登录提交','1');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/adminPage','2001','authc','管理员信息页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/selectAccountList','2001','authc','管理员信息列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/selectViewAccountList','2001','authc','管理员信息列表(视图)','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/groupPage','2002','authc','管理员组管理页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/getGroupList','2002','authc','管理员组管理列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/groupRoleP','2009','authc','管理员组管理-授权页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/authorizeRoleList','2009','authc','管理员组管理-授权列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/powerRole','2009','authc','管理员组管理-授权角色','1');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/groupAddP','-1','authc','管理员组管理-新增|编辑页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/insertGroup','2007','authc','管理员组管理-新增','1');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/updateGroup','2008','authc','管理员组管理-修改','1');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/getGroup','-1','authc','管理员组管理-查询单条','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/rolePage','2003','authc','管理员角色管理页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/getRoleList','2003','authc','管理员角色管理列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/roleRightP','2006','authc','管理员角色管理-授权页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/authorizeRightList','2006','authc','管理员角色管理-授权列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/powerRight','2006','authc','管理员角色管理-授权','1');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/roleAddP','-1','authc','角色管理-新增|编辑页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/insertRole','2012','authc','管理员角色管理-新增','1');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/updateRole','2005','authc','管理员角色管理-编辑','1');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/getRole','-1','authc','管理员角色管理-查询单条','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/rightPage','2004','authc','管理员权限管理页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/getRightList','2004','authc','管理员权限管理列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/getViewRightList','2004','authc','管理员权限管理列表（视图）','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/dictionary/getDictionaryList','3001','authc','数据字典列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/dictionary/getViewDictionaryList','3001','authc','数据字典列表（视图）','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/dictionary/dictPage','3001','authc','数据字典页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/groupPage','1001','authc','会员组管理页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/getGroupList','1001','authc','会员组管理-列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/selectGroup','1001','authc','会员组管理-查看单条','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/groupAddP','-1','authc','会员组管理-新增|编辑页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/insertGroup','1004','authc','会员组管理-新增','1');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/getGroup','1001','authc','会员组管理-查询单条','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/updateGroup','1005','authc','会员组管理-修改','1');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/groupRoleP','1006','authc','会员组管理-授权角色页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/authorizeRoleList','1006','authc','会员组管理-授权角色列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/powerRole','1006','authc','会员组管理-授权角色','1');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/groupAccountP','1007','authc','会员组管理-分配会员页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/getViewAAccountGroupList','1007','authc','会员组管理-分配会员-获取当前组及子组下的会员','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/rightPage','1003','authc','会员权限管理页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/selectRightList','1003','authc','会员权限管理列表','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/selectViewRightList','1003','authc','会员权限管理列表（视图）','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/msg/msgPage','5001','authc','消息管理页面','0');
INSERT INTO t_m_right_detail(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/msg/queryViewMessageList','5001','authc','消息管理列表(视图)','0');
--------------字典表初始化-------------------------------------------------------------------
--2020-7-9 wangtao
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('SEX','1','男');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('SEX','0','女');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('UTYPE','0','普通用户');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('UTYPE','1','管理员');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('ACCOUNT_STATUS','0','禁用');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('ACCOUNT_STATUS','1','正常');
INSERT INTO t_d_dictionary(CODE_SET,`CODE`,CNAME)VALUES('dictionary_status','1','正常');
INSERT INTO t_d_dictionary(CODE_SET,`CODE`,CNAME)VALUES('dictionary_status','2','不可用');
INSERT INTO t_d_dictionary(CODE_SET,`CODE`,CNAME)VALUES('right_type','1','菜单');
INSERT INTO t_d_dictionary(CODE_SET,`CODE`,CNAME)VALUES('right_type','2','按钮');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('OAUTH_TYPE','email','邮箱');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('OAUTH_TYPE','uname','用户名');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('OAUTH_TYPE','phone','手机');
INSERT INTO t_d_dictionary(CODE_SET,`CODE`,CNAME)VALUES('OAUTH_TYPE','wechat','微信');
INSERT INTO t_d_dictionary(CODE_SET,`CODE`,CNAME)VALUES('OAUTH_TYPE','weibo','微博');
INSERT INTO t_d_dictionary(CODE_SET,`CODE`,CNAME)VALUES('OAUTH_TYPE','qq','QQ');
INSERT INTO t_d_dictionary(CODE_SET,`CODE`,CNAME)VALUES('MSG_AIM','1','注册验证码');
INSERT INTO t_d_dictionary(CODE_SET,`CODE`,CNAME)VALUES('MSG_ORIGIN','1','前台');
INSERT INTO t_d_dictionary(CODE_SET,`CODE`,CNAME)VALUES('MSG_ORIGIN','2','后台');


	