--建表中遇到的问题
--1.MySQL在使用ALTER语句时如果该列设置了AUTO_INCREMENT,但是ALTER时没有加AUTO_INCREMENT,AUTO_INCREMENT属性将失效;

--创建ITOUR数据库如果ITOUR不存在;
CREATE DATABASE IF NOT EXISTS ITOUR;
--删除数据库
--DROP DATABASE 数据库名称;
--切换数据库
USE ITOUR;

---------------用户模块------------------------

--1.用户表（T_A_ACCOUNT）
DROP TABLE IF EXISTS T_A_ACCOUNT;
CREATE TABLE IF NOT EXISTS T_A_ACCOUNT(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
UID	VARCHAR(10) COMMENT'用户唯一号	唯一(从10000开始)',
SEX	VARCHAR(1)	COMMENT'性别(0：女；1：男)',
CREATEDATE	INT COMMENT'注册时间',	
CREATEIP	VARCHAR(15) COMMENT'注册IP',
LASTTIME	INT COMMENT'上次登录时间',
STATUS	VARCHAR(2) COMMENT'状态(0：禁用；1：正常)',	
UTYPE	VARCHAR(2) COMMENT'用户类型(0:普通用户;1:管理员')
);
ALTER TABLE T_A_ACCOUNT COMMENT '用户表';
--2.用户认证表(T_A_OAUTH)
DROP TABLE IF EXISTS T_A_OAUTH;
CREATE TABLE IF NOT EXISTS T_A_OAUTH(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMIT'编号标识',
U_ID 	VARCHAR(10) COMMENT'用户唯一号(外键来源于用户表中的UID)',
OAUTH_ID	VARCHAR(200) COMMENT'第三方登录唯一ID	站内保存手机号码;用户名，邮箱;',
OAUTH_TYPE	VARCHAR(18) COMMENT'第三方登录平台标识(手机号，邮箱，用户名，第三方应用名称（微信，QQ，微博…）)	手机：phone;邮箱：email;QQ:qq;微信：wechat;用户名：uid;微博:weibo',	
CREDENTIAL	VARCHAR(32) COMMENT'密码凭证	站内的保存密码，站外的不保存或保存token',
NICKNAME	VARCHAR(18) COMMENT'昵称',
AVATAR	VARCHAR(120) COMMENT'图像',
PWD	VARCHAR(36) COMMENT'用户的盐	用于登录密码的验证' 
);
ALTER TABLE T_A_OAUTH COMMENT '用户认证表';
--3.登录记录表(T_A_LOGIN_LIST)
DROP TABLE IF EXISTS T_A_LOGIN_LIST;
CREATE TABLE IF NOT EXISTS T_A_LOGIN_LIST(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号标识	主键,自动增长',
U_ID	VARCHAR(10) COMMENT'用户UID	来源于用户表中ID',
OAUTH_ID	VARCHAR(200) COMMENT'登录账号',
OAUTH_TYPE  VARCHAR(18) COMMENT'登录账号类型', 
LOGIN_TIME	INT COMMENT'登录时间',	
LOGIN_IP	VARCHAR(15) COMMENT'登录IP',		
LOGIN_IP_LOOKUP	VARCHAR(18) COMMENT'IP反查结果'	
);
ALTER TABLE T_A_LOGIN_LIST COMMENT '用户认证表';
--4.用户组表(T_A_GROUP)
DROP TABLE IF EXISTS T_A_GROUP;
CREATE TABLE IF NOT EXISTS T_A_GROUP( 
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'组编号',
G_NAME	VARCHAR(30) COMMENT'组名称',
G_PARENT	INT COMMENT'所属组编号',
G_DESC	VARCHAR(50)COMMENT'组描述'	
);
ALTER TABLE T_A_GROUP COMMENT '用户组表';

--5.用户-组表(T_A_ACCOUNT_GROUP)
DROP TABLE IF EXISTS T_A_ACCOUNT_GROUP;
CREATE TABLE IF NOT EXISTS T_A_ACCOUNT_GROUP(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
U_ID	VARCHAR(10)	COMMENT'用户唯一号	来源于用户标准的UID',
GROUP_ID	INT COMMENT'组编号	来源于用户组表中的ID'
);
ALTER TABLE T_A_ACCOUNT_GROUP COMMENT '用户-组表(中间表)';
--6.角色主表(T_A_ROLE)
DROP TABLE IF EXISTS T_A_ROLE;
CREATE TABLE IF NOT EXISTS T_A_ROLE(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'	编号',
ROLE_NAME	VARCHAR(30) COMMENT'角色名称',		
ROLE_DESC	VARCHAR(50) COMMENT '角色描述'	
);
ALTER TABLE T_A_ROLE COMMENT '角色主表';
--7.角色-组表(T_A_GROUP_ROLE)
DROP TABLE IF EXISTS T_A_GROUP_ROLE;
CREATE TABLE IF NOT EXISTS T_A_GROUP_ROLE(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT '编号(主键,自动增长)',
ROLE_ID	INT	COMMENT '角色编号(来源于角色主表的ID)',
GROUP_ID	INT	COMMENT '组编号(来源于用户组表)'
);
ALTER TABLE T_A_GROUP_ROLE COMMENT '角色-组表';


--8.权限主表(T_A_RIGHT)
DROP TABLE IF EXISTS T_A_RIGHT;
CREATE TABLE IF NOT EXISTS T_A_RIGHT(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
MENU_NO	VARCHAR(10)	COMMENT '菜单唯一号',
MENU	VARCHAR(18)	COMMENT'菜单名称',
PARENT_ID	VARCHAR(18)	COMMENT'上级菜单编号(0:顶级菜单)',
MENU_TYPE	VARCHAR(2) COMMENT'菜单类型(1:菜单;2:按钮)',
URL VARCHAR(60) COMMENT'菜单url',
M_ORDER VARCHAR(18) COMMENT'一级菜单的顺序',
S_ORDER VARCHAR(18) COMMENT'二级菜单的顺序'
);
ALTER TABLE T_A_RIGHT COMMENT '权限主表';
--9.权限明细（T_A_RIGHT_DETAIL）
DROP TABLE IF EXISTS T_A_RIGHT_DETAIL;
CREATE TABLE IF NOT EXISTS T_A_RIGHT_DETAIL(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
URL	VARCHAR(100) COMMENT'菜单URL',
RIGHT_NO	VARCHAR(10) COMMENT'权限编号	来源于权限主表的菜单唯一号'	,
ISLOGIN	VARCHAR(5)COMMENT'是否登录(anon 不需要登录 authc 需要登录)',
DESC	VARCHAR(80) COMMENT'描述',		
ISLOG	VARCHAR(2) COMMENT'是否记日志(0:不需要;1:需要)'
);
ALTER TABLE T_A_RIGHT_DETAIL COMMENT '权限明细';
--9.1角色权限表(T_A_ROLE_RIGHT)
CREATE TABLE IF NOT EXISTS T_A_ROLE_RIGHT(
ID INT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
ROLE_ID INT COMMENT '角色编号',
RIGHT_ID INT COMMENT '权限编号'
);
--10.IP查询表（T_A_IPADDR）
DROP TABLE IF EXISTS T_A_IPADDR;
CREATE TABLE IF NOT EXISTS T_A_IPADDR(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
IP  VARCHAR(18) COMMENT 'IP地址',		
ISP	VARCHAR(60) COMMENT '网络服务供应商',	
COUNTRY  VARCHAR(18) COMMENT '国家',
COUNTRYCODE VARCHAR(18) COMMENT'网络域名', 
REGION	 VARCHAR(18) COMMENT '地区（省）',
REGIONNAME VARCHAR(18) COMMENT '地区拼音缩写', 
CITY	VARCHAR(18) COMMENT'城市',
AREA	VARCHAR(18) COMMENT '区',
LATITUDE VARCHAR(18) COMMENT'纬度',		
LONGITUDE VARCHAR(18)COMMENT'经度'	

);
--11.消息发送记录表(T_A_MESSAGEINFO)
DROP TABLE IF EXISTS T_A_MESSAGEINFO;
CREATE TABLE IF NOT EXISTS T_A_MESSAGEINFO(
ID INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
SUBJECT VARCHAR(80) COMMENT'主题',
TEXT   VARCHAR(500) COMMENT'消息内容',
`FROM` VARCHAR(80) COMMENT'发送者',
`TO` VARCHAR(80) COMMENT'接受者',
`TYPE` VARCHAR(8) COMMENT'消息类型',
SENDTIME  INT COMMENT '消息发送时间',
AIM VARCHAR(3) COMMENT '消息用途（1:注册验证码）',
ORIGIN VARCHAR(2) COMMENT '消息来源(1:前台;2后台)',
IP VARCHAR(32) COMMENT'ip地址'
);

ALTER TABLE T_A_MESSAGEINFO COMMENT '消息发送记录表';


----------------------------------博客模块------------------------------
--1.旅行博客信息表(T_B_BLOGINFO)
CREATE TABLE IF NOT EXISTS T_B_BLOGINFO(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号(主键)',	
BLOGTITLE	VARCHAR(50) COMMENT'博客标题',	
BLOGBRIEF	VARCHAR(128) COMMENT'博客简介',	
BLOGPLAN	TEXT COMMENT'旅行安排',		
BLOGEXPLAIN	TEXT COMMENT'行程解剖',		
BLOGTYPE	VARCHAR(2) COMMENT'博客类型(1:旅行攻略；2.周末攻略3.景点介绍;(来源于博客类型表)'),	
BLOGCOLUMN	INT	COMMENT'所属专栏	来源旅行博客专栏表的ID',
CODE	VARCHAR(18) COMMENT'城市代码',	
PUBLISHTIME INT COMMENT'发布时间',	
UPDATETIME	INT COMMENT'最后发布时间'	
);
ALTER TABLE T_B_BLOGINFO COMMENT '旅行博客信息表';

--2.旅行博客类型表(T_B_TYPE)
CREATE TABLE IF NOT EXISTS T_B_TYPE(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号(主键)',	
TNAME VARCHAR(18) COMMENT'类型名称(	攻略、景点)',
CREATEDATE	INT COMMENT'创建时间'	
);
ALTER TABLE T_B_TYPE COMMENT '旅行博客类型表';

--3.旅行博客专栏表(T_B_BCOLUMN)
CREATE TABLE IF NOT EXISTS T_B_BCOLUMN(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',	
BCOLUMN	Varchar(18) COMMENT'专栏名称',		
UID	INT	COMMENT'用户ID'
);	
ALTER TABLE T_B_BCOLUMN COMMENT '旅行博客专栏表';
--4.旅行博客关键字表(T_B_KEYWORD)
CREATE TABLE IF NOT EXISTS T_B_KEYWORD(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',	
Keyword VARCHAR(18) COMMENT'关键字	博客关键字'
);
ALTER TABLE T_B_KEYWORD COMMENT '旅行博客关键字表';
--5.旅行博客-关键字表(T_B_BLOG_KEYWORD)中间表
CREATE TABLE IF NOT EXISTS T_B_BLOG_KEYWORD(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号(主键)',	
BID	INT	COMMENT'博客编号(来源博客信息表的ID)',
KID	INT	COMMENT'关键字的编号(来源于关键字表的ID （一篇博客最多可设置3个关键字）)'
);
ALTER TABLE T_B_BLOG_KEYWORD COMMENT '旅行博客-关键字表(中间表)';
--6.博客评论表(T_B_COMMENT)
CREATE TABLE IF NOT EXISTS T_B_COMMENT(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号	',
COMMENT	TEXT COMMENT'评论内容',		
CTIME	INT COMMENT'评论时间',		
UID	INT	 COMMENT'用户编号',
BID	INT  COMMENT'博客编号'	
);
ALTER TABLE T_B_COMMENT COMMENT '博客评论表';
-------------------------管理平台-----
--1.用户表（T_M_ACCOUNT）
DROP TABLE IF EXISTS T_M_ACCOUNT;
CREATE TABLE IF NOT EXISTS T_M_ACCOUNT(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
UID	VARCHAR(10) COMMENT '用户唯一号	唯一(从10000开始)',
SEX	VARCHAR(1) COMMENT'性别(0：女；1：男)',	
CREATEDATE	INT COMMENT'注册时间',
CREATEIP	VARCHAR(15) COMMENT'注册IP	',
LASTTIME	INT COMMENT'上次登录时间',
STATUS	VARCHAR(2) COMMENT'状态	0：禁用；1：正常',
UTYPE	VARCHAR(2) COMMENT'用户类型	0:普通用户;1:管理员'
);
ALTER TABLE T_M_ACCOUNT COMMENT '管理员表';

--2.用户认证表(T_M_OAUTH)
DROP TABLE IF EXISTS T_M_OAUTH;
CREATE TABLE IF NOT EXISTS T_M_OAUTH(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号标识	主键,自动增长',	
U_ID 	VARCHAR(10) COMMENT '用户唯一号	外键来源于用户表中的UID',
OAUTH_ID	VARCHAR(200) COMMENT'第三方登录唯一ID	站内保存手机号码;用户名，邮箱;',
OAUTH_TYPE	VARCHAR(18) COMMENT '第三方登录平台标识(手机号，邮箱，用户名，第三方应用名称（微信，QQ，微博…）)	手机：phone;邮箱：email;QQ:qq;微信：wechat;用户名：uid;微博:weibo',
CREDENTIAL	VARCHAR(40) COMMENT '密码凭证	站内的保存密码，站外的不保存或保存token',	
NICKNAME	VARCHAR(18) COMMENT'昵称',
AVATAR	VARCHAR(120)COMMENT '图像',
PWD	VARCHAR(36) COMMENT'用户的盐'
);
ALTER TABLE T_M_OAUTH COMMENT '用户认证表';
--3.登录记录表(T_M_LOGIN_LIST)
DROP TABLE IF EXISTS T_M_LOGIN_LIST;
CREATE TABLE IF NOT EXISTS T_M_LOGIN_LIST(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT '编号标识 主键,自动增长',
U_ID	VARCHAR(10) COMMENT'',--用户UID	来源于用户表中ID
OAUTH_ID	VARCHAR(200)COMMENT'', ---登录账号
OAUTH_TYPE  VARCHAR(18)COMMENT'', ---登录账号类型
LOGIN_TIME	INT COMMENT '登录时间',
LOGIN_IP	VARCHAR(15)COMMENT'登录IP	',	
LOGIN_IP_LOOKUP	VARCHAR(18)COMMENT'IP反查结果'
);
ALTER TABLE T_M_LOGIN_LIST COMMENT '登录记录表';
--4.用户组表(T_M_GROUP)
DROP TABLE IF EXISTS T_M_GROUP;
CREATE TABLE IF NOT EXISTS T_M_GROUP( 
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT '组编号',
G_NAME	VARCHAR(30) COMMENT'组名称',
G_PARENT	INT COMMENT'所属组编号',
G_DESC	VARCHAR(50)COMMENT'组描述'
);
ALTER TABLE T_M_GROUP COMMENT '管理用户组表';
--5.用户-组表(T_M_ACCOUNT_GROUP)
DROP TABLE IF EXISTS T_M_ACCOUNT_GROUP;
CREATE TABLE IF NOT EXISTS T_M_ACCOUNT_GROUP(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT' 编号(主键,自动增长)',
U_ID	VARCHAR(10)	COMMENT'用户唯一号	来源于用户标准的UID',
GROUP_ID	INT	COMMENT'组编号	来源于用户组表中的ID'
);
ALTER TABLE T_M_ACCOUNT_GROUP COMMENT '用户-组表(中间表)';

--6.角色主表(T_M_ROLE)
DROP TABLE IF EXISTS T_M_ROLE;
CREATE TABLE IF NOT EXISTS T_M_ROLE(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号	主键,自动增长',
ROLE_NAME	VARCHAR(30) COMMENT'角色名称',	
ROLE_DESC	VARCHAR(50)COMMENT'角色描述'
);
ALTER TABLE T_M_ROLE COMMENT '角色主表';
--7.组-角色表(T_M_GROUP_ROLE)
DROP TABLE IF EXISTS T_M_GROUP_ROLE;
CREATE TABLE IF NOT EXISTS T_M_GROUP_ROLE(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
ROLE_ID	INT	COMMENT'角色编号	来源于角色主表的ID',
GROUP_ID	INT	COMMENT'组编号(来源于用户组表)'
);
ALTER TABLE T_M_GROUP_ROLE COMMENT '角色-组表';

--8.权限主表(T_M_RIGHT)
DROP TABLE IF EXISTS T_M_RIGHT;
CREATE TABLE IF NOT EXISTS T_M_RIGHT(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
MENU_NO	VARCHAR(10)	COMMENT '菜单唯一号',
MENU	VARCHAR(18)	COMMENT'菜单名称',
PARENT_ID	VARCHAR(18)	COMMENT'上级菜单编号(0:顶级菜单)',
MENU_TYPE	VARCHAR(2) COMMENT'菜单类型(1:菜单;2:按钮)',
URL VARCHAR(60) COMMENT'菜单url',
M_ORDER VARCHAR(18) COMMENT'一级菜单的顺序',
S_ORDER VARCHAR(18) COMMENT'二级菜单的顺序'
);
ALTER TABLE T_M_RIGHT COMMENT '权限主表';
--9.权限明细（T_M_RIGHT_DETAIL）
DROP TABLE IF EXISTS T_M_RIGHT_DETAIL;
CREATE TABLE IF NOT EXISTS T_M_RIGHT_DETAIL(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号(主键,自动增长)',	
URL	VARCHAR(100) COMMENT'菜单URL',	
RIGHT_NO	VARCHAR(10) COMMENT'权限编号(来源于权限主表的菜单唯一号)',
ISLOGIN	VARCHAR(5) COMMENT'是否登录(anon 不需要登录 authc 需要登录)',
`DESC`	VARCHAR(80) COMMENT'描述',		
ISLOG	VARCHAR(2) COMMENT '是否记日志(0:不需要;1:需要)'
);
ALTER TABLE T_M_RIGHT_DETAIL COMMENT '权限明细';
--9.1角色权限表(T_M_ROLE_RIGHT)
CREATE TABLE T_M_ROLE_RIGHT(
ID INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
ROLE_ID INT COMMENT '角色编号',
RIGHT_ID INT COMMENT '角色编号'
);
ALTER TABLE T_M_ROLE_RIGHT COMMENT '角色权限表';
--10.IP记录信息(T_M_IPADDR)
CREATE TABLE IF NOT EXISTS T_M_IPADDR(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
IP  VARCHAR(18) COMMENT 'IP地址',		
ISP	VARCHAR(60) COMMENT '网络服务供应商',	
COUNTRY  VARCHAR(18) COMMENT '国家',
COUNTRYCODE VARCHAR(18) COMMENT'网络域名', 
REGION	 VARCHAR(18) COMMENT '地区（省）',
REGIONNAME VARCHAR(18) COMMENT '地区拼音缩写', 
CITY	VARCHAR(18) COMMENT'城市',
AREA	VARCHAR(18) COMMENT '区',
LATITUDE VARCHAR(18) COMMENT'纬度',		
LONGITUDE VARCHAR(18)COMMENT'经度'	

);
ALTER TABLE T_M_IPADDR COMMENT 'IP记录信息';
--11.数据字典表(t_d_dictionary)
CREATE TABLE IF NOT EXISTS t_d_dictionary(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
CODE_SET VARCHAR(18) COMMENT '字典的目录',
CODE VARCHAR(20) COMMENT '字典代码',
CNAME VARCHAR(30) COMMENT '字典值',
STATUS VARCHAR(2) DEFAULT('1') COMMENT '状态(1:正常;2:不可用)'
);
ALTER TABLE t_d_dictionary COMMENT '字典表';