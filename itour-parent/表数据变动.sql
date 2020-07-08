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
	( '2003', '管理员角色管理', '2000', '1', '/member/rolePage', '2', '2' );
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
	( '4000', '会员管理', '0', '1', '0', '3', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4001', '会员查询', '4000', '1', '/account/accountPage', '0', '0' );
--------------后台菜单相关结束-------------------------------
--------------字典表初始化-------------------------------------------------------------------
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('SEX','1','男');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('SEX','0','女');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('UTYPE','0','普通用户');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('UTYPE','1','管理员');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('ACCOUNT_STATUS','0','禁用');
INSERT INTO t_d_dictionary(CODE_SET,CODE,CNAME)VALUES('ACCOUNT_STATUS','1','正常');
	