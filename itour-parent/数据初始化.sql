--2020-7-3 wangtao 
   --前台t_a_group初始化
INSERT INTO t_a_group ( G_NAME, G_DESC, G_PARENT )
VALUES
	( 'Gnormal', '普通会员组', '0' );
INSERT INTO t_a_group ( G_NAME, G_DESC, G_PARENT )
VALUES
	( 'Gvip', 'VIP会员组', '0' );
   --前台t_a_role初始化	
INSERT INTO t_a_role
VALUES
	( ROLE_NAME, ROLE_DESC ) 
VALUE
	( 'Rnormal', '普通用户' );
INSERT INTO t_a_role
VALUES
	( ROLE_NAME, ROLE_DESC ) 
VALUE
	( 'Rvip', 'VIP用户' );
INSERT INTO t_a_role
VALUES
	( ROLE_NAME, ROLE_DESC ) 
VALUE
	( 'RSAdmin', '超级管理员' );
INSERT INTO t_a_role
VALUES
	( ROLE_NAME, ROLE_DESC ) 
VALUE
	( 'RNAdmin', '普通管理员' );

   --前台t_m_group初始化
INSERT INTO t_m_group ( G_NAME, G_DESC, G_PARENT )
VALUES
	( 'Gnormal', '普通会员组', '0' );
INSERT INTO t_m_group ( G_NAME, G_DESC, G_PARENT )
VALUES
	( 'Gvip', 'VIP会员组', '0' );	
	
 --后台t_m_role初始化	
INSERT INTO t_m_role
VALUES
	( ROLE_NAME, ROLE_DESC ) 
VALUE
	( 'Rnormal', '普通用户' );
INSERT INTO t_m_role
VALUES
	( ROLE_NAME, ROLE_DESC ) 
VALUE
	( 'Rvip', 'VIP用户' );
INSERT INTO t_m_role
VALUES
	( ROLE_NAME, ROLE_DESC ) 
VALUE
	( 'RSAdmin', '超级管理员' );
INSERT INTO t_m_role
VALUES
	( ROLE_NAME, ROLE_DESC ) 
VALUE
	( 'RNAdmin', '普通管理员' );