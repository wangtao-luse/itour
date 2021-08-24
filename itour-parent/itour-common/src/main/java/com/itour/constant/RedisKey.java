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
public static String KEY_ITOUR_IP_COUNT="itour_ip_count";
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
 * 所有浏览日志的IP
 */
public static String KEY_WORK_IPS="work_ips";
/**
 * 工作日志独立IP统计
 */
public static String KEY_WORK_IP_COUNT="work_ip_count";
/**
 * 工作日志点赞
 */
public static String KEY_WORK_NICE="work_nice";
/**
 * 工作日志评论点赞
 */
public static String KEY_WORK_COMMENT_NICE="work_comment_nice";
/**
 * 公作日志评论回复点赞
 */
public static String KEY_WORK_COMMENTREPLY_NICE="work_commentReply_nice";

}
