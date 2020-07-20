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
	( '2004', '新增', '2003', '2', '0', '2', '0-1' );
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


	