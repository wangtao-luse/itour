---前台用户查询视图
CREATE OR  REPLACE VIEW view_a_account AS 
	SELECT
		a.*,
		sex.CNAME SEX_STR,
		utype.CNAME UTYPE_STR, 
		accountStatus.CNAME STATUS_STR
	FROM
		t_a_account a,
		( SELECT d.CODE, d.CNAME FROM t_d_dictionary d WHERE d.CODE_SET = 'SEX'and d.`STATUS`='1') sex,
		( SELECT d.CODE, d.CNAME FROM t_d_dictionary d WHERE d.CODE_SET = 'UTYPE' and d.`STATUS`='1') utype,
	  ( SELECT d.CODE, d.CNAME FROM t_d_dictionary d WHERE d.CODE_SET = 'ACCOUNT_STATUS' and d.`STATUS`='1') accountStatus	
	WHERE
		a.SEX = sex.CODE 
	AND a.UTYPE = utype.CODE 
	AND a.`STATUS`=accountStatus.CODE;
--后台管理员查询视图
CREATE OR  REPLACE VIEW view_m_account AS 
	SELECT
		a.*,
		sex.CNAME SEX_STR,
		utype.CNAME UTYPE_STR, 
		accountStatus.CNAME STATUS_STR
	FROM
		t_m_account a,
		( SELECT d.CODE, d.CNAME FROM t_d_dictionary d WHERE d.CODE_SET = 'SEX'and d.`STATUS`='1') sex,
		( SELECT d.CODE, d.CNAME FROM t_d_dictionary d WHERE d.CODE_SET = 'UTYPE' and d.`STATUS`='1') utype,
	  ( SELECT d.CODE, d.CNAME FROM t_d_dictionary d WHERE d.CODE_SET = 'ACCOUNT_STATUS' and d.`STATUS`='1') accountStatus	
	WHERE
		a.SEX = sex.CODE 
	AND a.UTYPE = utype.CODE 
	AND a.`STATUS`=accountStatus.CODE;
-- 字典表视图
CREATE OR REPLACE VIEW view_d_dictionary AS
SELECT c.*,dstatus.STATUS_STR FROM `t_d_dictionary` c,
(SELECT d.CNAME STATUS_STR,d.`STATUS` FROM t_d_dictionary d WHERE d.CODE_SET='dictionary_status' and `STATUS`='1') dstatus
where c.`STATUS`=dstatus.`STATUS`;