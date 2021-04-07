package com.itour.constant;

public class RedisKey {
/**
 * 旅行文章点赞
 */
public static String KEY_NICE="itour_nice";

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

//超时时间2小时
public static long TIMEOUT=60*60*2;
}
