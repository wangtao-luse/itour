package com.itour.constant;

public class RedisKey {
/***旅行模块**/	
/**
 * 旅行文章点赞
 */
public static String KEY_ITOUR_NICE="itour_nice";
/**
 * 旅行文章评论点赞
 */
public static String KEY_ITOUR_COMMENT_NICE="itour_comment_nice";
/**
 * 旅行文章评论回复点赞
 */
public static String KEY_ITOUR_COMMENTREPLY_NICE="itour_commentReply_nice";

/**
 * 旅行文章浏览量
 */
public static String KEY_ITOUR_PAGEVIEW="itour_pageview";
/**
 * 旅行文章独立访客
 */
public static String KEY_ITOUR_UNIQUEISITOR="itour_uniqueisitor";
/**
 * 旅行文章独立IP
 */
public static String KEY_ITOUR_IP="itour_ip";
/**
 * 访问次数
 */
public static String KEY_ITOUR_VISIT_IEW="itour_visit_iew";
/**
 * 视频播放量
 */
public static String KEY_ITOUR_VIDEO_VIEW="itour_video_view";
/**
 * 旅行文章独立IP统计
 */
public static String KEY_ITOURINFO_IP_LIST="key_itourInfo_ip_list";
/**
 * 所有浏览过攻略的IP
 */
public static String KEY_ITOUR_IPS="itour_ips";
/**
 * 所有浏览过攻略的独立访客
 */
public static String KEY_ITOUR_UNIQUEISITORS="itour_uniqueisitors";
/**
 * 攻略文章统计独立访客
 */
public static String KEY_ITOUR_UNIQUEISITOR_COUNT="itour_uniqueisitor_count";
/**
 * 浏览过的文章的编号
 */
public static String ITOUR_PAGEVIEW_IDS="itour_pageview_ids";
/**
 * 文章浏览量（首页显示）
 */
public static String KEY_ITOUR_PVS="itour_pvs";

//超时时间2小时
public static long TIMEOUT=60*60*2;


/**工作日志部分**/
/**
 *  工作日志独立IP列表
 */
public static String KEY_WORKINFO_IP_LIST="key_workinfo_ip_list";

public static String KEY_WORKINFO_IP_LIST_DATE="key_workinfo_ip_list_date";

/**
 * 工作日志点赞
 */
public static String KEY_WORK_LIKE="work_article_like";
/**
 * 工作日志评论点赞
 */
public static String KEY_WORK_COMMENT_NICE="work_comment_nice";
/**
 * 公作日志评论回复点赞
 */
public static String KEY_WORK_COMMENTREPLY_NICE="work_commentReply_nice";




/***
 * 站点访问量
 */
public static String KEY_ITOUR_PV_COUNT = "key_itour_pv_count";
/***
 * 站点独立访客
 */
public static String KEY_ITOUR_UV_COUNT = "key_itour_uv_count";
/**
 *站点独立IP列表
 */
public static String KEY_ITOUR_IP_LIST = "key_itour_ip_list";

public static String KEY_ITOUR_IP_LIST_DATE ="key_itour_ip_list_date";
/**
 *站点访问次数
 */
public static String KEY_ITOUR_VV_COUNT = "key_itour_vv_count";
//工作日志内容缓存
public static String KEY_WORK_ARTICLE_TEXT ="work_article_text";
//工作日志标签缓存
public static String KEY_WORK_ARTICLE_LABEL ="work_article_label";
//工作日志中，每个用户对应的分类专栏及每个专栏下的文章统计
public static String KEY_WORK_ARTICLE_COLUMN_LIST = "work_article_colum_list";
//工作日志信息
public static String KEY_WORK_ARTICLE_INFO ="work_article_info";

public static String KEY_WORK_ARTICLE_INFO_LIKE ="work_article_info_like";



}
