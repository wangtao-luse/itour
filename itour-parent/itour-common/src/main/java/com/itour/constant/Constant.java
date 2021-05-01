package com.itour.constant;

public class Constant {
//系统相关设置
public static final String SUCCESS_CODE="10";
public static final String FAILED_CODE="00";
public static final String LOGIN = "99";
public static final String SUCESS_MESSAGE="操作成功";
public static final String FAILED_MESSAGE="操作失败";
public static final String FAILED_SYSTEM_ERROR="系统繁忙,请稍后再试";
public static final String FAILED_NOAUTHOR="您没有操作该资源的权限";
//服务层返回的key
public static final String COMMON_KEY_RESULT="result";
public static final String COMMON_KEY_PAGE="page";
public static final String COMMON_KEY_LIST="list";
public static final String COMMON_KEY_SUM="sum";
public static final String COMMON_KEY_COUNT="count";
public static final String COMMON_KEY_ARR="arr";
public static final String COMMON_KEY_VO="vo";
//请求的key
public static final String COMMON_VO_NEEDTOTAL="needTotal";

//评论待审核
public static final String COMMON_STATUS_CHECKING="0";
//评论审核通过
public static final String COMMON_STATUS_CHECKED="1";
//评论审核不通过
public static final String COMMON_STATUS_CHECK="2";
//评论已删除
public static final String COMMON_STATUS_DELETED="3";


//文章 草稿
public static final String ARTICLE_STATUS_DRAFT="0";
//文章 待审核
public static final String ARTICLE_STATUS_CHECKING="1";
// 文章 审核通过
public static final String ARTICLE_STATUS_CHECKED="2";
//文章 审核通过
public static final String ARTICLE_STATUS_CHECK="3";
// 文章 已删除
public static final String ARTICLE_STATUS_DELETED="4";







}
