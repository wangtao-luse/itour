#建表中遇到的问题
#1.MySQL在使用ALTER语句时如果该列设置了AUTO_INCREMENT,但是ALTER时没有加AUTO_INCREMENT,AUTO_INCREMENT属性将失效;

#创建ITOUR数据库如果ITOUR不存在;
CREATE DATABASE IF NOT EXISTS ITOUR;
#删除数据库
#DROP DATABASE 数据库名称;
#切换数据库
USE ITOUR;
#-新增字段语法
 	#-ALTER TABLE 表名  ADD COLUMN 字段名 数据类型 DEFAULT '默认值' COMMENT '注释';
#-删除字段
	#-alter table 表名 DROP COLUMN 列名;
#-修改字段
   #1.修改字段的数据类型
      #-alter table 表名 MODIFY 字段名 数据类型;
   #2.修改字段名称
      #-alter table 表名 CHANGE 字段名称 新的字段名称 数据类型;
#######-用户模块############

#1.用户表（T_A_ACCOUNT）
DROP TABLE IF EXISTS T_A_ACCOUNT;
CREATE TABLE IF NOT EXISTS T_A_ACCOUNT(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
UID	VARCHAR(10) COMMENT'用户唯一号	唯一(从10000开始)',
SEX	VARCHAR(1)	COMMENT'性别(0：女；1：男)',
CREATEDATE	BIGINT COMMENT'注册时间',	
CREATEIP	VARCHAR(15) COMMENT'注册IP',
LASTTIME	BIGINT COMMENT'上次登录时间',
STATUS	VARCHAR(2) COMMENT'状态(0：禁用；1：正常)',	
UTYPE	VARCHAR(2) COMMENT'用户类型(0:普通用户;1:管理员)'
);
ALTER TABLE T_A_ACCOUNT COMMENT '用户表';
#2.用户认证表(T_A_OAUTH)
DROP TABLE IF EXISTS T_A_OAUTH;
CREATE TABLE IF NOT EXISTS T_A_OAUTH(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号标识',
U_ID 	VARCHAR(10) COMMENT'用户唯一号(外键来源于用户表中的UID)',
OAUTH_ID	VARCHAR(200) UNIQUE COMMENT'第三方登录唯一ID	站内保存手机号码;用户名，邮箱;',
OAUTH_TYPE	VARCHAR(18) COMMENT'第三方登录平台标识(手机号，邮箱，用户名，第三方应用名称（微信，QQ，微博…）)	手机：phone;邮箱：email;QQ:qq;微信：wechat;用户名：uid;微博:weibo',	
CREDENTIAL	VARCHAR(32) COMMENT'密码凭证	站内的保存密码，站外的不保存或保存token',
NICKNAME	VARCHAR(18) COMMENT'昵称',
AVATAR	VARCHAR(120) COMMENT'图像',
PWD	VARCHAR(36) COMMENT'用户的盐	用于登录密码的验证' 
);
ALTER TABLE T_A_OAUTH COMMENT '用户认证表';
#3.登录记录表(T_A_LOGIN_LIST)
DROP TABLE IF EXISTS T_A_LOGIN_LIST;
CREATE TABLE IF NOT EXISTS T_A_LOGIN_LIST(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号标识	主键,自动增长',
U_ID	VARCHAR(10) COMMENT'用户UID	来源于用户表中ID',
OAUTH_ID	VARCHAR(200) COMMENT'登录账号',
OAUTH_TYPE  VARCHAR(18) COMMENT'登录账号类型', 
LOGIN_TIME	BIGINT COMMENT'登录时间',	
LOGIN_IP	VARCHAR(15) COMMENT'登录IP',		
LOGIN_IP_LOOKUP	VARCHAR(18) COMMENT'IP反查结果'	
);
ALTER TABLE T_A_LOGIN_LIST COMMENT '登录记录表';
#4.用户组表(T_A_GROUP)
DROP TABLE IF EXISTS T_A_GROUP;
CREATE TABLE IF NOT EXISTS T_A_GROUP( 
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'组编号',
G_NAME	VARCHAR(30) COMMENT'组名称',
G_PARENT	INT COMMENT'所属组编号',
G_DESC	VARCHAR(50)COMMENT'组描述'	,
CREATEDATE BIGINT COMMENT'创建日期'
);
ALTER TABLE T_A_GROUP COMMENT '用户组表';

#5.用户-组表(T_A_ACCOUNT_GROUP)
DROP TABLE IF EXISTS T_A_ACCOUNT_GROUP;
CREATE TABLE IF NOT EXISTS T_A_ACCOUNT_GROUP(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
U_ID	VARCHAR(10)	COMMENT'用户唯一号	来源于用户标准的UID',
GROUP_ID	INT COMMENT'组编号	来源于用户组表中的ID'
);
ALTER TABLE T_A_ACCOUNT_GROUP COMMENT '用户-组表(中间表)';
#6.角色主表(T_A_ROLE)
DROP TABLE IF EXISTS T_A_ROLE;
CREATE TABLE IF NOT EXISTS T_A_ROLE(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'	编号',
ROLE_NAME	VARCHAR(30) COMMENT'角色名称',		
ROLE_DESC	VARCHAR(50) COMMENT '角色描述',
CREATEDATE  BIGINT COMMENT '创建日期'
);
ALTER TABLE T_A_ROLE COMMENT '角色主表';
#7.角色-组表(T_A_GROUP_ROLE)
DROP TABLE IF EXISTS T_A_GROUP_ROLE;
CREATE TABLE IF NOT EXISTS T_A_GROUP_ROLE(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT '编号(主键,自动增长)',
ROLE_ID	INT	COMMENT '角色编号(来源于角色主表的ID)',
GROUP_ID	INT	COMMENT '组编号(来源于用户组表)'
);
ALTER TABLE T_A_GROUP_ROLE COMMENT '角色-组表';


#8.权限主表(T_A_RIGHT)
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
#9.权限明细（T_A_RIGHT_DETAIL）
DROP TABLE IF EXISTS T_A_RIGHT_DETAIL;
CREATE TABLE IF NOT EXISTS T_A_RIGHT_DETAIL(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
URL	VARCHAR(100) COMMENT'菜单URL',
RIGHT_NO	VARCHAR(10) COMMENT'权限编号	来源于权限主表的菜单唯一号'	,
ISLOGIN	VARCHAR(5)COMMENT'是否登录(anon 不需要登录 authc 需要登录)',
`DESC`	VARCHAR(80) COMMENT'描述',		
ISLOG	VARCHAR(2) COMMENT'是否记日志(0:不需要;1:需要)'
);
ALTER TABLE T_A_RIGHT_DETAIL COMMENT '权限明细';
#9.1角色权限表(T_A_ROLE_RIGHT)
CREATE TABLE IF NOT EXISTS T_A_ROLE_RIGHT(
ID INT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
ROLE_ID INT COMMENT '角色编号',
RIGHT_ID INT COMMENT '权限编号'
);
#10.IP查询表（T_A_IPADDR）
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
#11.消息发送记录表(T_A_MESSAGEINFO)
DROP TABLE IF EXISTS T_A_MESSAGEINFO;
CREATE TABLE IF NOT EXISTS T_A_MESSAGEINFO(
ID INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
SUBJECT VARCHAR(80) COMMENT'主题',
TEXT   VARCHAR(500) COMMENT'消息内容',
`FROM` VARCHAR(80) COMMENT'发送者',
`TO` VARCHAR(80) COMMENT'接受者',
`TYPE` VARCHAR(8) COMMENT'消息类型',
SENDTIME  BIGINT COMMENT '消息发送时间',
AIM VARCHAR(3) COMMENT '消息用途（1:注册验证码）',
ORIGIN VARCHAR(2) COMMENT '消息来源(1:前台;2后台)',
IP VARCHAR(32) COMMENT'ip地址'
);

ALTER TABLE T_A_MESSAGEINFO COMMENT '消息发送记录表';


#################博客模块###############
#1.旅行信息表(T_T_TRAVEL_INFO)
CREATE TABLE IF NOT EXISTS T_T_TRAVEL_INFO(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号(主键)',	
TITLE	VARCHAR(50) COMMENT'标题',	
SUMMARY	VARCHAR(128) COMMENT'简介',
URL    	VARCHAR(300) COMMENT'简介图片视频地址',
TYPE	VARCHAR(2) COMMENT'类型(1:旅行攻略;2.周末攻略;3.景点介绍;4.vlog;5.广告)',
ARTICLE_TYPE VARCHAR(2) COMMENT'文章类型（1：原创;2:非原创）',
UID VARCHAR(10) COMMENT'用户唯一号',
`CODE`	VARCHAR(18) COMMENT'城市代码',	
READ_COUNT INT DEFAULT '0' COMMENT '阅读数',
COMMENT_COUNT INT DEFAULT '0' COMMENT '评论数',
NICE_COUNT INT DEFAULT '0' COMMENT'点赞数',
PV INT DEFAULT '0' COMMENT'浏览量',
PUBLISHTIME BIGINT COMMENT'发布时间',	
UPDATETIME	BIGINT COMMENT'最后发布时间'	
);

ALTER TABLE T_T_TRAVEL_INFO COMMENT '旅行信息表';
#1.1旅行行程安排表（T_T_TRAVEL_SCHEDULE）
CREATE TABLE IF NOT EXISTS T_T_TRAVEL_SCHEDULE(
ID INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号(主键)',
`CODE`VARCHAR(18) COMMENT '城市代码',
LOCATION VARCHAR(50) COMMENT'地点',
TYPE VARCHAR(3) COMMENT '类型'
);
ALTER TABLE T_T_TRAVEL_SCHEDULE COMMENT '旅行行程安排表';
#1.2旅行行程剖析表（T_T_TRAVEL_ANALYSIS）
CREATE TABLE IF NOT EXISTS T_T_TRAVEL_ANALYSIS(
ID INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号(主键)',
`CODE`VARCHAR(18) COMMENT '城市代码',
LOCATION VARCHAR(50) COMMENT'地点',
TYPE VARCHAR(3) COMMENT '类型'
);
ALTER TABLE T_T_TRAVEL_ANALYSIS COMMENT '旅行行程剖析表';
#1.3旅行行程剖析表（T_T_WEEK_INFO）
CREATE TABLE IF NOT EXISTS T_T_WEEK_INFO(
ID	INT PRIMARY KEY	AUTO_INCREMENT COMMENT '编号',
WEEK_CONTENT	LONGTEXT COMMENT'周末攻略文章内容',	
TID	INT COMMENT '文章编号'
);
ALTER TABLE T_T_WEEK_INFO COMMENT '周末攻略内容表';
#2.旅行信息类型表(T_T_TRAVEL_TYPE)
CREATE TABLE IF NOT EXISTS T_T_TRAVEL_TYPE(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号(主键)',	
TNAME VARCHAR(18) COMMENT'类型名称(攻略、景点)',
CREATEDATE	INT COMMENT'创建时间'	
);
ALTER TABLE T_T_TRAVEL_TYPE COMMENT '旅行类型表';

#3.旅行信息专栏表(T_T_TRAVEL_COLUMN)
CREATE TABLE IF NOT EXISTS T_T_TRAVEL_COLUMN(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',	
`COLUMN`	Varchar(18) COMMENT'专栏名称',		
UID	INT	COMMENT'用户ID',
CREATEDATE BIGINT COMMENT'创建日期'
);	
ALTER TABLE T_T_TRAVEL_COLUMN COMMENT '旅行博客专栏表';
#4.旅行信息关键字表(T_T_KEYWORD)
CREATE TABLE IF NOT EXISTS T_T_KEYWORD(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',	
KEYWORD VARCHAR(18) COMMENT'关键字',
CREATEDATE BIGINT COMMENT'创建日期'
);
ALTER TABLE T_T_KEYWORD COMMENT '旅行信息关键字表';
#5.旅行信息-关键字表(T_TRAVEL_KEYWORD)中间表
CREATE TABLE IF NOT EXISTS T_TRAVEL_KEYWORD(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号(主键)',	
BID	INT	COMMENT'旅行信息编号',
KID	INT	COMMENT'关键字的编号'
);
ALTER TABLE T_TRAVEL_KEYWORD COMMENT '旅行信息-关键字表(中间表)';

#6.旅行信息评论表(T_T_TRAVEL_COMMENT)
CREATE TABLE IF NOT EXISTS T_T_TRAVEL_COMMENT(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
COMMENT	VARCHAR(1200) COMMENT'评论内容',		
CTIME	BIGINT COMMENT'评论时间',		
UID	VARCHAR(10)	 COMMENT'用户编号',
TID	INT  COMMENT'旅行信息编号',
NICE_COUNT INT COMMENT'评论点赞数',
STATUS VARCHAR(3) COMMENT '状态（0:待审核状态;1:审核通过;2:审核不通过;3:已删除）'
);
ALTER TABLE T_T_TRAVEL_COMMENT COMMENT '旅行信息评论表';
#7.旅行信息评论回复表(T_B_COMMENT_REPLY)

CREATE TABLE IF NOT EXISTS T_T_COMMENT_REPLY(
ID INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
COMMENT_ID INT COMMENT '评论编号',
REPLY VARCHAR(1200) COMMENT '回复内容',
RTIME BIGINT COMMENT '回复时间',
FROM_UID VARCHAR(10) COMMENT'回复人的ID',
TO_UID VARCHAR(10) COMMENT '回复给目标人的ID',
NICE_COUNT INT COMMENT'评论回复点赞数',
STATUS VARCHAR(3) COMMENT '状态（0:待审核状态;1:审核通过;2:审核不通过;3:已删除）'
);
ALTER TABLE T_T_COMMENT_REPLY COMMENT '旅行信息评论回复表';
#7.1旅行信息评论点赞表
CREATE TABLE IF NOT EXISTS T_T_TRAVEL_COMMENT_NICE (
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
CID	INT	COMMENT'攻略评论表编号',
UID	VARCHAR(10)	COMMENT'用户编号',
CREATEDATE	BIGINT	COMMENT'点赞时间',
STATUS VARCHAR(2) DEFAULT '1' COMMENT'状态（0:取消;1:有效）'
);
ALTER TABLE T_T_TRAVEL_COMMENT COMMENT '旅行信息评论点赞表';
#7.2旅行信息评论回复点赞表
CREATE TABLE IF NOT EXISTS T_T_TRAVEL_COMMENT_REPLY_NICE (
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
RID	INT	COMMENT'攻略评论回复表编号',
UID	VARCHAR(10)	COMMENT'用户编号',
CREATEDATE	BIGINT	COMMENT'点赞时间',
STATUS VARCHAR(2) DEFAULT '1' COMMENT'状态（0:取消;1:有效）'
);
ALTER TABLE T_T_TRAVEL_COMMENT COMMENT '旅行信息评论回复点赞表';
#8.旅行博客收藏表(T_B_COLLECT)
CREATE TABLE IF NOT EXISTS T_B_COLLECT(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
TID	INT	COMMENT'博客编号',
CTIME	BIGINT	COMMENT'收藏时间',
UID	VARCHAR(10)	COMMENT'收藏用户编号',
FID	INT	COMMENT'所属收藏夹',
STATUS VARCHAR(2)COMMENT'状态(1:收藏;2:取消收藏)'
);
ALTER TABLE T_B_COLLECT COMMENT '旅行博客收藏表';
#-8.1旅行收藏夹(T_B_FAVORITES)
CREATE TABLE IF NOT EXISTS T_B_FAVORITES(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
FAVORITE	VARCHAR(18)	COMMENT'收藏夹名称',
SUBTITLE	VARCHAR(80)	COMMENT'收藏夹描述',
UID	VARCHAR(10)	COMMENT'所属用户编号',
CREATEDATE	INT	COMMENT'创建时间',
STATUS VARCHAR(2)COMMENT'状态(0:已删除;1:正常)'
);
ALTER TABLE T_B_FAVORITES COMMENT '旅行收藏夹表';
#9.旅行攻略点赞表(T_T_NICE)
CREATE TABLE IF NOT EXISTS T_T_NICE (
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
TID	INT	COMMENT'旅行信息编号',
UID	VARCHAR(10)	COMMENT'用户编号',
CREATEDATE	BIGINT	COMMENT'点赞时间',
STATUS VARCHAR(2) DEFAULT '1' COMMENT'状态（0:取消;1:有效）'
);
ALTER TABLE T_T_TRAVEL_COMMENT COMMENT '点赞表';
#9.1 旅行信息浏览数表(T_T_PAGEVIEW)
CREATE TABLE IF NOT EXISTS T_T_PAGEVIEW(
ID	INT COMMENT'编号',
TID	INT COMMENT'旅行信息编号',
UID	VARCHAR(18)COMMENT'用户',
PAGE_VEW	INT	COMMENT'浏览量' DEFAULT 0,
UNIQUE_ISITOR INT	COMMENT'独立访客' DEFAULT 0,
IP	INT	COMMENT'独立IP' DEFAULT 0,
VISIT_IEW	INT	COMMENT'访问次数' DEFAULT 0,
VIDEO_VIEW	INT	COMMENT'视频播放量' DEFAULT 0,
CREATEDATE	BIGINT	COMMENT'浏览日期'
);
ALTER TABLE T_T_PAGEVIEW COMMENT '旅行信息浏览数表';

#10.城市信息表(T_T_LOCATION)
CREATE TABLE IF NOT EXISTS T_T_LOCATION(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号	',
PID INT COMMENT'上级主键',
COUNTRY_CODE	VARCHAR(18)	COMMENT'国家代码',
COUNTRY	VARCHAR(18)	COMMENT'国家名称',
COUNTRY_EN	VARCHAR(50)	COMMENT'国家名称对应的英文',
CODE	VARCHAR(18)	COMMENT'城市代码',
CITY_EN	VARCHAR(18)	COMMENT'城市名称',
CITY	VARCHAR(18)	COMMENT'城市中文名称',
BOPOMOFO VARCHAR(60) COMMENT '对应拼音',
PID INT COMMENT'上一级主键',
LEVEL VARCHAR(3) COMMENT '地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县',
SIGNIMG	VARCHAR(500)	COMMENT'城市标志图片',
LOCIMG	VARCHAR(500)	COMMENT'位置图片',
DISTRICTIMG	VARCHAR(500)	COMMENT'行政区域划分图片',
DISTRICTDESC VARCHAR(500)COMMENT'行政区域划分介绍',
LOCATION VARCHAR(500)	COMMENT'地理位置',
INTROUDUCE VARCHAR(500)	COMMENT'简短介绍',
WEATHER	VARCHAR(500)	COMMENT'天气简介'
);
ALTER TABLE T_T_LOCATION COMMENT '城市信息表';
--地区表
CREATE TABLE IF NOT EXISTS T_T_REGION(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'地区主键编号',
REGION_NAME	VARCHAR(50)	COMMENT'地区名称',
REGION_CODE	VARCHAR(6)	COMMENT'行政地区编号',
REGION_PARENT_ID VARCHAR(6) COMMENT'上级主键',
REGION_LEVEL VARCHAR(3) COMMENT '地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县'

);
ALTER TABLE T_T_REGION  ADD UNIQUE(REGION_CODE);
#10.1交通信息表（T_T_TRANSPORTATION_INFO）
CREATE TABLE IF NOT EXISTS T_T_TRANSPORTATION_INFO (
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
CODE	VARCHAR(18)	COMMENT'城市代码',
CITY	VARCHAR(18)	COMMENT'城市名称',
TTYPE	VARCHAR(2)	COMMENT'交通类型',
TSITUATION	VARCHAR(500)	COMMENT'交通状况',
ROUTE	VARCHAR(500)	COMMENT'推荐路线',
SKILL	VARCHAR(500)	COMMENT'交通技巧'

);
ALTER TABLE T_T_TRANSPORTATION_INFO COMMENT '交通信息表';
#10.2交通类型表（T_T_TRANSPORTATION_TYPE）
CREATE TABLE IF NOT EXISTS T_T_TRANSPORTATION_TYPE(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
TCODE INT COMMENT 'key',
TRANSPORTATION	VARCHAR(6)	COMMENT'交通类型'
);
ALTER TABLE T_T_TRANSPORTATION_TYPE COMMENT'交通类型表';
#11.景点信息表(T_T_SCENIC_SPOT)
CREATE TABLE IF NOT EXISTS T_T_SCENIC_SPOT(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
SCENICIMG	VARCHAR(500) COMMENT'景点图片',
SCENIC_SPOT 	VARCHAR(18)	COMMENT'景点名称',
TYPE	VARCHAR(3)	COMMENT'景点类型',
SINTROUDUCE	VARCHAR(500)	COMMENT'景点简介',
INTROUDUCE	VARCHAR(500)	COMMENT'景点介绍',
PRICE	DECIMAL(8,2)	COMMENT'门票价格',
CITY	VARCHAR(18)	COMMENT'所在城市',
CCODE	VARCHAR(18)	COMMENT'城市代码',
ADDR	VARCHAR(120)	COMMENT'具体地址',
STRANSPORTATION	VARCHAR(500)	COMMENT'交通',
RECOMMEND DECIMAL(2,1) COMMENT'推荐指数'

);
ALTER TABLE T_T_SCENIC_SPOT COMMENT'景点信息表';
#12.景点类型表(T_T_STYPE)
CREATE TABLE IF NOT EXISTS T_T_STYPE(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
TCODE	INT  COMMENT'Key',
Type	VARCHAR(18)	COMMENT '景点类型'
);
ALTER TABLE T_T_STYPE  ADD UNIQUE(TCODE);
ALTER TABLE T_T_STYPE COMMENT'景点类型表';


############-管理平台##-
#1.用户表（T_M_ACCOUNT）
DROP TABLE IF EXISTS T_M_ACCOUNT;
CREATE TABLE IF NOT EXISTS T_M_ACCOUNT(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
UID	VARCHAR(10) UNIQUE COMMENT '用户唯一号	唯一(从10000开始)',
SEX	VARCHAR(1) COMMENT'性别(0：女；1：男)',	
CREATEDATE	BIGINT COMMENT'注册时间',
CREATEIP	VARCHAR(15) COMMENT'注册IP	',
LASTTIME	BIGINT COMMENT'上次登录时间',
STATUS	VARCHAR(2) COMMENT'状态	0：禁用；1：正常',
UTYPE	VARCHAR(2) COMMENT'用户类型	0:普通用户;1:管理员'
);
ALTER TABLE T_M_ACCOUNT COMMENT '管理员表';

#2.用户认证表(T_M_OAUTH)
DROP TABLE IF EXISTS T_M_OAUTH;
CREATE TABLE IF NOT EXISTS T_M_OAUTH(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号标识	主键,自动增长',	
U_ID 	VARCHAR(10) COMMENT '用户唯一号	外键来源于用户表中的UID',
OAUTH_ID	VARCHAR(200) UNIQUE COMMENT'第三方登录唯一ID	站内保存手机号码;用户名，邮箱;',
OAUTH_TYPE	VARCHAR(18) COMMENT '第三方登录平台标识(手机号，邮箱，用户名，第三方应用名称（微信，QQ，微博…）)	手机：phone;邮箱：email;QQ:qq;微信：wechat;用户名：uid;微博:weibo',
CREDENTIAL	VARCHAR(40) COMMENT '密码凭证	站内的保存密码，站外的不保存或保存token',	
NICKNAME	VARCHAR(18) COMMENT'昵称',
AVATAR	VARCHAR(120)COMMENT '图像',
PWD	VARCHAR(36) COMMENT'用户的盐'
);
ALTER TABLE T_M_OAUTH COMMENT '用户认证表';
#3.登录记录表(T_M_LOGIN_LIST)
DROP TABLE IF EXISTS T_M_LOGIN_LIST;
CREATE TABLE IF NOT EXISTS T_M_LOGIN_LIST(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT '编号标识 主键,自动增长',
U_ID	VARCHAR(10) COMMENT'',#用户UID	来源于用户表中ID
OAUTH_ID	VARCHAR(200)COMMENT'', #-登录账号
OAUTH_TYPE  VARCHAR(18)COMMENT'', #-登录账号类型
LOGIN_TIME	BIGINT COMMENT '登录时间',
LOGIN_IP	VARCHAR(15)COMMENT'登录IP	',	
LOGIN_IP_LOOKUP	VARCHAR(18)COMMENT'IP反查结果'
);
ALTER TABLE T_M_LOGIN_LIST COMMENT '登录记录表';
#4.用户组表(T_M_GROUP)
DROP TABLE IF EXISTS T_M_GROUP;
CREATE TABLE IF NOT EXISTS T_M_GROUP( 
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT '组编号',
G_NAME	VARCHAR(30) COMMENT'组名称',
G_PARENT	INT COMMENT'所属组编号',
G_DESC	VARCHAR(50)COMMENT'组描述',
CREATEDATE BIGINT COMMENT '创建日期'
);
ALTER TABLE T_M_GROUP COMMENT '管理用户组表';
#5.用户-组表(T_M_ACCOUNT_GROUP)
DROP TABLE IF EXISTS T_M_ACCOUNT_GROUP;
CREATE TABLE IF NOT EXISTS T_M_ACCOUNT_GROUP(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT' 编号(主键,自动增长)',
U_ID	VARCHAR(10)	COMMENT'用户唯一号	来源于用户标准的UID',
GROUP_ID	INT	COMMENT'组编号	来源于用户组表中的ID'
);
ALTER TABLE T_M_ACCOUNT_GROUP COMMENT '用户-组表(中间表)';

#6.角色主表(T_M_ROLE)
DROP TABLE IF EXISTS T_M_ROLE;
CREATE TABLE IF NOT EXISTS T_M_ROLE(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号	主键,自动增长',
ROLE_NAME	VARCHAR(30) COMMENT'角色名称',	
ROLE_DESC	VARCHAR(50)COMMENT'角色描述'
);
ALTER TABLE T_M_ROLE COMMENT '角色主表';
#7.组-角色表(T_M_GROUP_ROLE)
DROP TABLE IF EXISTS T_M_GROUP_ROLE;
CREATE TABLE IF NOT EXISTS T_M_GROUP_ROLE(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
ROLE_ID	INT	COMMENT'角色编号	来源于角色主表的ID',
GROUP_ID	INT	COMMENT'组编号(来源于用户组表)'
);
ALTER TABLE T_M_GROUP_ROLE COMMENT '角色-组表';

#8.权限主表(T_M_RIGHT)
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
#9.权限明细（T_M_RIGHT_DETAIL）
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
#9.1角色权限表(T_M_ROLE_RIGHT)
CREATE TABLE T_M_ROLE_RIGHT(
ID INT PRIMARY KEY AUTO_INCREMENT COMMENT'编号',
ROLE_ID INT COMMENT '角色编号',
RIGHT_ID INT COMMENT '角色编号'
);
ALTER TABLE T_M_ROLE_RIGHT COMMENT '角色权限表';
#10.IP记录信息(T_M_IPADDR)
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
#11.数据字典表(t_d_dictionary)
CREATE TABLE IF NOT EXISTS T_D_DICTIONARY(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
CODE_SET VARCHAR(18) COMMENT '字典的目录',
CODE VARCHAR(20) COMMENT '字典代码',
CNAME VARCHAR(30) COMMENT '字典值',
STATUS VARCHAR(2) DEFAULT('1') COMMENT '状态(1:正常;2:不可用)',
CREATEDATE BIGINT COMMENT '创建日期'
);
ALTER TABLE T_D_DICTIONARY COMMENT '字典表';
#12.网站推荐表(T_R_WEBSITE_RECOMMEND)
CREATE TABLE IF NOT EXISTS T_R_WEBSITE_RECOMMEND(
ID	INT PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
NAME	VARCHAR(30)	COMMENT '网站名称',
NAME_EN VARCHAR(30) COMMENT '网站中文名称',
URL	VARCHAR(50)	COMMENT '网址',
NICE	VARCHAR(500)	COMMENT '特色',
ADVANTAGE	VARCHAR(500)	COMMENT '优点',
SHORTCOMING	VARCHAR(300)	COMMENT '缺点',
TYPE	VARCHAR(3)	COMMENT '网站类型',
FEE	VARCHAR(2)	COMMENT '是否收费',
PROFESSION	VARCHAR(40)	COMMENT '所属行业',
JOB	VARCHAR(40)	COMMENT '所属工作',
`INDEX`	FLOAT	COMMENT '推荐指数',
CREATEDATE	BIGINT	COMMENT '录入时间',
UID	INT	COMMENT '录入人',
STATUS INT DEFAULT '1' COMMENT'状态(0:已删除,1:正常)'
);
ALTER TABLE T_R_WEBSITE_RECOMMEND COMMENT '网站推荐表';
#广告信息(T_A_ADVERT)
CREATE TABLE IF NOT EXISTS T_A_ADVERT(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
LOGO	VARCHAR(300)	COMMENT '广告的logo',
BRAND	VARCHAR(18)	COMMENT '品牌名称',
TITLE	VARCHAR(80)	COMMENT '标题',
ADVERIMG	VARCHAR(300)	COMMENT '广告图片',
SUMMARY	VARCHAR(300)	COMMENT '广告简介',
URL	VARCHAR(200)	COMMENT '广告详情地址',
CREATEDATE	BIGINT	COMMENT '创建时间',
TERM	BIGINT	COMMENT '投放结束时间',
STATUS	INT	COMMENT '状态(1:待审核;2待支付;3.已投放)'
);
ALTER TABLE T_A_ADVERT COMMENT '广告信息表';
#旅行信息浏览记录表(T_T_HISTORY)
CREATE TABLE T_T_HISTORY(
ID	INT	PRIMARY KEY AUTO_INCREMENT COMMENT '编号',
U_ID	VARCHAR(10)	COMMENT'用户唯一号',
CREATEDATE	BIGINT	COMMENT'浏览时间',	
TID	INT	COMMENT'旅行信息编号',	
TITLE	VARCHAR(120)	COMMENT'旅行信息标题',	
LOC	VARCHAR(180)	COMMENT'文章地址',	
STATUS	VARCHAR(2)	COMMENT'状态	1:正常;2:以删除'
);
ALTER TABLE T_T_HISTORY COMMENT '旅行信息浏览记录表';
