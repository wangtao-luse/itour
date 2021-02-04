#2020-7-2 wangtao
#初始化超级管理账号相关
INSERT t_m_account ( UID, SEX, CREATEDATE, CREATEIP, LASTTIME, `STATUS`, UTYPE )
VALUES
	( '10000', '1', 1612231334241, '112.65.61.2',1612231334241 ,'1', '0' );
INSERT t_m_oauth (U_ID,OAUTH_ID,OAUTH_TYPE,CREDENTIAL,NICKNAME,PWD)VALUES(
 '10000','3398660467@qq.com','email','64846c862d3c4fe65b9c659b1398ef77dfc51bd0','wangtao','a42617d417d7418dbcf5c30770fa0f7e'
);
INSERT t_m_oauth (U_ID,OAUTH_ID,OAUTH_TYPE,CREDENTIAL,NICKNAME,PWD)VALUES(
 '10000','wangtao','uname','64846c862d3c4fe65b9c659b1398ef77dfc51bd0','wangtao','a42617d417d7418dbcf5c30770fa0f7e'
);
INSERT t_m_account_group(U_ID,GROUP_ID)VALUES('10000','1');
INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','1000');
INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','1001');
INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','1002');
INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','1003');
INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','1007');

INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','2000');
INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','2001');
INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','2002');
INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','2003');
INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','2006');
INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','2009');

INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','4000');
INSERT  t_m_role_right(ROLE_ID,RIGHT_ID) VALUES('2','4001');
#######后台菜单相关开始###############-
#-一级菜单 url给0;PARENT_ID给0;
#-二级菜单M_ORDER给所属一级菜单的M_ORDER
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1000', '权限管理', '0', '1', '0', '1', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1001', '会员组管理', '1000', '1', '/account/groupPage', '1', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1004', '新增', '1001', '2', '', '1', '0-1' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1005', '编辑', '1001', '2', '', '1', '0-2' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1006', '授权', '1001', '2', '', '1', '0-3' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1007', '分配会员', '1001', '2', '', '1', '0-4' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1002', '会员角色管理', '1000', '1', '/account/rolePage', '1','1' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1011', '新增', '1002', '2', '0', '1','1-1' );	
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1012', '编辑', '1002', '2', '0', '1','1-2' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1013', '授权', '1002', '2', '0', '1','1-3' );	
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1003', '会员权限管理', '1000', '1', '/account/rightPage', '1','2' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1008', '新增', '1003', '2', '0', '1','2-1' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1009', '编辑', '1003', '2', '0', '1','2-2' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '1010', '授权', '1003', '2', '0', '1','2-3' );	
	
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2000', '管理员管理', '0', '1', '0', '2', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2001', '管理员信息', '2000', '1', '/member/adminPage', '2', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2013', '新增', '2001', '2', '0', '2', '0-1' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2014', '编辑', '2001', '2', '0', '2', '0-2' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2015', '查看详情', '2001', '2', '0', '2', '0-3' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2002', '管理员组管理', '2000', '1', '/member/groupPage', '2', '1' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2007', '新增', '2002', '2', '0', '2', '0-1' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2008', '编辑', '2002', '2', '0', '2', '0-2' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2009', '授权', '2002', '2', '0', '2', '0-3' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2010', '分配会员', '2002', '2', '0', '2', '0-4' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2003', '管理员角色管理', '2000', '1', '/member/rolePage', '2', '2' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2012', '新增', '2003', '2', '0', '2', '0-1' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2005', '编辑', '2003', '2', '0', '2', '0-2' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2006', '授权', '2003', '2', '0', '2', '0-3' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2004', '管理员权限管理', '2000', '1', '/member/rightPage', '2', '3' );	
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '2011', '编辑', '2004', '21', '', '2', '2' );	
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '3000', '数据字典管理', '0', '1', '0', '3', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '3001', '数据字典信息', '3000', '1', '/dictionary/dictPage', '2', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '3002', '编辑', '3001', '2', '0', '1', '0-1' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '3003', '网站信息', '3000', '1', '/dictionary/websitePage', '2', '2' );	
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4000', '会员管理', '0', '1', '0', '3', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4001', '会员查询', '4000', '1', '/account/accountPage', '0', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4002', '新增', '4001', '2', '0', '0', '0-1' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4003', '编辑', '4001', '2', '0', '0', '0-2' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4004', '查询详情', '4000', '2', '0', '0', '0-3' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '4005', '会员登录日志查询', '4000', '1', '/account/loginListPage', '1', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '5000', '消息管理', '0', '1', '0', '4', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '5001', '消息管理', '5000', '1', '/msg/msgPage', '4', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '6000', '旅行信息管理', '0', '1', '0', '1', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '6001', '旅行信息查询', '6000', '1', '/travel/travelPage', '5', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '6002', '旅行信息类型管理', '6000', '1', '/travel/travelTypePage', '5', '1' );	
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '6003', '旅行信息评论管理', '6000', '1', '/travel/travelCommntPage', '5', '2' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '6004', '旅行信息专栏管理', '6000', '1', '/travel/travelColPage', '5', '3' );	
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '6005', '旅行位置信息管理', '6000', '1', '/travel/travelLocPage', '5', '4' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '7000', '广告管理', '0', '1', '0', '1', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '7001', '广告信息管理', '7000', '1', '/ad/advertPage', '6', '1' );	
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '8000', '工作日志管理', '0', '1', '0', '1', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '8001', '工作日志信息管理', '8000', '1', '/work/logPage', '7', '1' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '9000', '系统管理', '0', '1', '0', '9', '0' );
INSERT INTO T_M_RIGHT (MENU_NO, MENU, PARENT_ID, MENU_TYPE, URL, M_ORDER, S_ORDER )
VALUES
	( '9001', '定时任务管理', '9000', '1', '/quartz/quartzManagerPage', '8', '1' );

#######后台菜单明细相关开始##################
#-1.需要及记录日志且需要单独权限的方法需要添加权限明细(新增,修改，需要权限的按钮)
#-2.新增,修改操作需要记录日志
#-3.查询和页面跳转的方法不需要插入权限明细

INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/insertGroup','2007','authc','管理员组管理-新增','1');
INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/updateGroup','2008','authc','管理员组管理-修改','1');
INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/powerRole','2009','authc','管理员组管理-授权角色','1');
INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/grantAccount','2010','authc','管理员组管理-分配会员','1');

INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/insertRole','2012','authc','管理员角色管理-新增','1');
INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/updateRole','2005','authc','管理员角色管理-编辑','1');
INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/powerRight','2006','authc','管理员角色管理-授权','1');

INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/member/adminDetailP','2015','authc','管理员信息-查看详情','0');

INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/insertGroup','1004','authc','会员组管理-新增','1');
INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/updateGroup','1005','authc','会员组管理-修改','1');
INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/powerRole','1006','authc','会员组管理-授权角色','1');
INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/grantAccount','1007','authc','会员组管理-分配会员','1');

INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/insertRole','1011','authc','会员角色管理-新增','1');
INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/updateRole','1012','authc','会员角色管理-修改','1');
INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/account/powerRight','1013','authc','会员角色管理-授权','1');

INSERT INTO T_M_RIGHT_DETAIL(URL,RIGHT_NO,ISLOGIN,`DESC`,ISLOG)VALUES
('/dictionary/updateDictionary','3002','authc','数据字典表-修改','1');

#######字典表初始化#################################-
#2020-7-9 wangtao
INSERT INTO T_D_DICTIONARY(CODE_SET,CODE,CNAME,CREATEDATE)VALUES('SEX','1','男',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,CODE,CNAME,CREATEDATE)VALUES('SEX','0','女',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,CODE,CNAME,CREATEDATE)VALUES('UTYPE','0','普通用户',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,CODE,CNAME,CREATEDATE)VALUES('UTYPE','1','管理员',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,CODE,CNAME,CREATEDATE)VALUES('ACCOUNT_STATUS','0','禁用',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,CODE,CNAME,CREATEDATE)VALUES('ACCOUNT_STATUS','1','正常',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('dictionary_status','1','正常',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('dictionary_status','2','不可用',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('right_type','1','菜单',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('right_type','2','按钮',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,CODE,CNAME,CREATEDATE)VALUES('OAUTH_TYPE','email','邮箱',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,CODE,CNAME,CREATEDATE)VALUES('OAUTH_TYPE','uname','用户名',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,CODE,CNAME,CREATEDATE)VALUES('OAUTH_TYPE','phone','手机',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('OAUTH_TYPE','wechat','微信',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('OAUTH_TYPE','weibo','微博',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('OAUTH_TYPE','qq','QQ',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('MSG_AIM','1','注册验证码',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('MSG_ORIGIN','1','前台',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('MSG_ORIGIN','2','后台',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('WEBSITE_FEE','1','收费',1612414470071);
INSERT INTO T_D_DICTIONARY(CODE_SET,`CODE`,CNAME,CREATEDATE)VALUES('WEBSITE_FEE','0','免费',1612414470071);

###前台菜单明细表#################################
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/index','-1','anon','首页','0');
##用户模块
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/account/loginSub','-1','anon','登录提交','1');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/account/reg','-1','anon','注册页面','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/account/regSub','-1','anon','注册提交','1');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/account/checkRegName','-1','anon','检查用户名是否可用','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/account/checkEmail','-1','anon','检查邮箱是否可用','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/account/registerSucess','-1','anon','注册成功页面','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/account/setpwd','-1','anon','忘记密码页面','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/account/findPwd','-1','anon','修改密码','1');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/account/checkUserName','-1','anon','检查用户是否 存在','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/getVerifyImage','-1','anon','生成滑块拼图验证码','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/checkImageCode','-1','anon','校验滑块拼图验证码','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/checkemailCode','-1','anon','校验邮箱验证码','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/msg/sendCodetoEmail','-1','anon','发送验证码','0');
##旅行模块################################-
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/travel/queryTravelInfoList','-1','anon','旅行信息列表查询','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/travel/selectTravelInfoById','-1','authc','旅行信息查询单条','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/travel/updateTravelInfo','1000','authc','旅行信息修改','1');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/travel/delTravelInfo','1001','authc','旅行信息删除','1');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/travel/getLocation','-1','authc','城市信息单条查询','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/travel/queryTransportationInfoList','-1','authc','交通信息列表查询','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/travel/queryViewTravelinfoOauthList','-1','anon','旅行信息列表（视图）','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/travel/selectViewTravelinfoOauthById','-1','anon','旅行信息单条（视图）','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/travel/planPage','-1','authc','旅行信息详情','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/travel//infoAddPage','1002','authc','旅行信息新增','0');

INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/favoriteModal','-1','authc','旅行信息收藏浮出框','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/favoriteAddModal','-1','authc','旅行信息收藏创建收藏夹浮出框','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/queryFavoriteList','-1','authc','旅行信息收藏夹列表','0');
INSERT INTO T_A_RIGHT_DETAIL(URL,RIGHT_No,ISLOGIN,`DESC`,ISLOG)
VALUES('/insertFavorite','-1','authc','旅行信息创建收藏夹','0');
#-前台权限表###########################-
INSERT INTO T_A_RIGHT(MENU_NO,MENU,PARENT_ID,MENU_TYPE,URL,M_ORDER,S_ORDER)
VALUES('1000','旅行信息修改','0','2','','0','0');
INSERT INTO T_A_RIGHT(MENU_NO,MENU,PARENT_ID,MENU_TYPE,URL,M_ORDER,S_ORDER)
VALUES('1001','旅行信息删除','0','2','','0','1');
INSERT INTO T_A_RIGHT(MENU_NO,MENU,PARENT_ID,MENU_TYPE,URL,M_ORDER,S_ORDER)
VALUES('1002','旅行信息新增','0','2','','0','2');

	