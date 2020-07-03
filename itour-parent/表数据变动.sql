--2020-7-2 wangtao
--------------后台菜单相关开始-------------------------------
---一级菜单 url给0;PARENT_ID给0;
---二级菜单M_ORDER给所属一级菜单的M_ORDER
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1000', '权限管理', '0', '1', '0', '1', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1001', '组管理', '1000', '1', '/account/groupPage', '1', '0' );
INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1002', '角色管理', '1000', '1', '/account/rolePage', '1','1' );

INSERT INTO t_m_right (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2000', '管理员管理', '0', '1', '0', '1', '0' );
--------------后台菜单相关结束-------------------------------

	