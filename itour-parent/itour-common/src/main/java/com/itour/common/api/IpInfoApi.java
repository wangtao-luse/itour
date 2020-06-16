package com.itour.common.api;


import com.itour.http.HttpURLConnectionUtil;
/**相关博客
 * https://blog.csdn.net/elon8000/article/details/99835564
 * @author wwang IP解析
 *
 */
public class IpInfoApi {	
/**
 * 使用搜狐的API
 * @return
 */
public static String sohuIpInfo() {	
	String serverurl="http://pv.sohu.com";
	String requesturl="/cityjson?ie=utf-8";
	String visitGet = HttpURLConnectionUtil.visitGet(serverurl, requesturl);	
	return visitGet;
}
/**
 * 
 * @param lang 为空字符串返回英文
 * @param ip 如果ip不为空字符串,根据IP查询
 * @return
 */
public static String showIpInfo(String lang,String ip) {
	String serverurl="http://ip-api.com";
	String requesturl="/json/"+ip+"?lang="+lang;	
	String visitGet = HttpURLConnectionUtil.visitGet(serverurl, requesturl);
	return visitGet;
	
}
public static void main(String[] args) {
	String taobaoIpInfo = sohuIpInfo();
	String ip="115.236.69.180";// //118.182.201.41
	String showIpInfo = showIpInfo("zh-CN",ip);
 
}
}
