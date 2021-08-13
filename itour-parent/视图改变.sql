#-前台用户查询视图
CREATE OR  REPLACE VIEW view_a_account AS 
	SELECT
		a.*,
		sex.CNAME SEX_STR,
		utype.CNAME UTYPE_STR, 
		accountStatus.CNAME STATUS_STR
	FROM
		T_A_ACCOUNT a,
		( SELECT d.CODE, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'SEX'and d.`STATUS`='1') sex,
		( SELECT d.CODE, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'UTYPE' and d.`STATUS`='1') utype,
	  ( SELECT d.CODE, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'ACCOUNT_STATUS' and d.`STATUS`='1') accountStatus	
	WHERE
		a.SEX = sex.CODE 
	AND a.UTYPE = utype.CODE 
	AND a.`STATUS`=accountStatus.CODE;
#后台管理员查询视图
CREATE OR  REPLACE VIEW view_m_account AS 
	SELECT
		a.*,
		sex.CNAME SEX_STR,
		utype.CNAME UTYPE_STR, 
		accountStatus.CNAME STATUS_STR
	FROM
		T_M_ACCOUNT a,
		( SELECT d.CODE, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'SEX'and d.`STATUS`='1') sex,
		( SELECT d.CODE, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'UTYPE' and d.`STATUS`='1') utype,
	  ( SELECT d.CODE, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'ACCOUNT_STATUS' and d.`STATUS`='1') accountStatus	
	WHERE
		a.SEX = sex.CODE 
	AND a.UTYPE = utype.CODE 
	AND a.`STATUS`=accountStatus.CODE;
# 字典表视图
CREATE OR REPLACE VIEW view_d_dictionary AS
SELECT c.*,dstatus.STATUS_STR FROM `T_D_DICTIONARY` c ,
(SELECT d.CODE,d.CODE_SET, d.`STATUS`,d.CNAME STATUS_STR FROM T_D_DICTIONARY d WHERE d.CODE_SET='dictionary_status') dstatus
WHERE c.STATUS=dstatus.`CODE`;
#后台管理员权限表视图
CREATE OR REPLACE VIEW view_m_right AS
SELECT  a.*, type.CNAME MENU_TYPE_STR FROM T_M_RIGHT a,
(SELECT c.`CODE`,c.CNAME  FROM T_D_DICTIONARY c where c.CODE_SET='right_type') type
where a.MENU_TYPE=type.`CODE`;
#-前台会员权限表视图
CREATE OR REPLACE VIEW view_a_right AS
SELECT  a.*, type.CNAME MENU_TYPE_STR FROM T_A_RIGHT a,
(SELECT c.`CODE`,c.CNAME  FROM T_D_DICTIONARY c where c.CODE_SET='right_type') type
where a.MENU_TYPE=type.`CODE`;
#-前台会员账号查询详情
CREATE OR REPLACE VIEW view_a_oauth AS
SELECT
	c.ID,
	c.U_ID,
	c.OAUTH_ID,
	c.OAUTH_TYPE,
	c.NICKNAME,
	c.AVATAR,
	type.CNAME OAUTH_TYPE_STR ,
	d.CREATEDATE
FROM
	T_A_OAUTH c,
	( SELECT d.`CODE`, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'OAUTH_TYPE' ) type,
T_A_ACCOUNT	 d
WHERE
	c.OAUTH_TYPE = type.`CODE` AND c.U_ID=d.UID;
#-后台管理员账号查询详情
CREATE OR REPLACE VIEW view_m_oauth AS
SELECT
	c.ID,
	c.U_ID,
	c.OAUTH_ID,
	c.OAUTH_TYPE,
	c.NICKNAME,
	c.AVATAR,
	type.CNAME OAUTH_TYPE_STR ,
	d.CREATEDATE
FROM
	T_M_OAUTH c,
	( SELECT d.`CODE`, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'OAUTH_TYPE' ) type,
T_M_ACCOUNT	 d
WHERE
	c.OAUTH_TYPE = type.`CODE` AND c.U_ID=d.UID;
#-前台查询指定组下的会员信息
CREATE OR REPLACE VIEW view_a_account_group AS
SELECT
	b.*,
	c.GROUP_ID,
	s.CNAME STATUS_STR,
	u.CNAME UTYPE_STR 
FROM
	`T_A_ACCOUNT` b
	LEFT JOIN ( SELECT * FROM T_A_ACCOUNT_GROUP ) c ON b.UID = c.U_ID,
	( SELECT d.`CODE`, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'ACCOUNT_STATUS' AND d.`STATUS` = '1' ) s,
	( SELECT d.`CODE`, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'UTYPE' AND d.`STATUS` = '1' ) u 
WHERE
	s.CODE = b.STATUS 
	AND u.`CODE` = b.UTYPE;
#-后台查询指定组下的会员信息
CREATE OR REPLACE VIEW view_m_account_group AS
SELECT
	b.*,
	c.GROUP_ID,
	s.CNAME STATUS_STR,
	u.CNAME UTYPE_STR 
FROM
	`T_M_ACCOUNT` b
	LEFT JOIN ( SELECT * FROM T_M_ACCOUNT_GROUP ) c ON b.UID = c.U_ID,
	( SELECT d.`CODE`, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'ACCOUNT_STATUS' AND d.`STATUS` = '1' ) s,
	( SELECT d.`CODE`, d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET = 'UTYPE' AND d.`STATUS` = '1' ) u 
WHERE
	s.CODE = b.STATUS 
	AND u.`CODE` = b.UTYPE;
#-消息列表
CREATE OR REPLACE VIEW view_m_messageinfo AS
	SELECT c.*,aim.CNAME AIM_STR ,origin.CNAME ORIGIN_STR FROM T_A_MESSAGEINFO c,
	(SELECT d.`CODE`,d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET='MSG_AIM' AND d.`STATUS`='1') aim,
	(SELECT d.`CODE`,d.CNAME FROM T_D_DICTIONARY d WHERE d.CODE_SET='MSG_ORIGIN' AND d.`STATUS`='1') origin
	WHERE c.AIM=aim.`CODE` and c.ORIGIN=origin.`CODE`;
#博客列表视图
CREATE OR REPLACE VIEW view_travelInfo_oauth AS
SELECT c.*,a.NICKNAME,a.AVATAR FROM T_T_TRAVEL_INFO c, T_A_OAUTH a WHERE a.OAUTH_TYPE='email' AND a.U_ID=c.UID AND c.`STATUS` in('10','30');
#旅行攻略评论表视图
CREATE OR REPLACE VIEW view_travel_comment AS
SELECT c.*,tmp.AVATAR,tmp.NICKNAME FROM T_T_TRAVEL_COMMENT c,(SELECT * FROM t_a_oauth c WHERE c.OAUTH_TYPE='email') tmp WHERE c.UID=tmp.U_ID;
#旅行攻略评论回复表视图
CREATE OR REPLACE VIEW view_comment_reply AS
SELECT c.*,tmp.AVATAR FROM_AVATAR,tmp.NICKNAME FROM_NICKNAME,tmp1.AVATAR TO_AVATAR,tmp1.NICKNAME TO_NICKNAME FROM T_T_COMMENT_REPLY c,
(SELECT c.U_ID,c.NICKNAME,c.AVATAR FROM t_a_oauth c WHERE c.OAUTH_TYPE='email') tmp,
(select c.U_ID,c.NICKNAME,c.AVATAR FROM t_a_oauth c WHERE c.OAUTH_TYPE='email') tmp1
WHERE c.from_uid=tmp.U_ID and c.TO_UID=tmp1.U_ID;
#旅行标签视图
CREATE OR REPLACE VIEW view_travel_tag AS
SELECT c.id,c.tid,c.tag_id,d.tag,d.uid FROM t_t_travel_tag c ,t_t_tag d WHERE c.TAG_ID=d.ID;
#旅行信息和周末攻略表视图(审核的时候)
CREATE OR REPLACE VIEW view_travelInfo_weekinfo AS
SELECT 	c.ID,	c.`STATUS`,	d.WEEK_CONTENT FROM 	T_T_TRAVEL_INFO c,t_t_week_info d	WHERE c.ID = d.TID AND c.`STATUS`='20';
#旅行攻略分类视图
CREATE OR REPLACE VIEW view_travel_column AS
SELECT c.ID,c.`COLUMN`,c.UID,c.CREATEDATE,d.TID FROM t_t_travel_column c,t_t_travelinfo_column d
 where c.ID=d.CID;  
 
 
#工作日志模块---------------------------------------------------------------
#工作日志审核视图
CREATE OR REPLACE VIEW view_workInfo_worktext AS
SELECT 	c.ID,	c.`STATUS`,	d.WCONTENT FROM 	t_w_work_info c,t_w_worktext d	WHERE c.ID = d.wid  AND c.`STATUS`='20';
 
 