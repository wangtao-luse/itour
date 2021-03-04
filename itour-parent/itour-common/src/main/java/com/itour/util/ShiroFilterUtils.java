package com.itour.util;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShiroFilterUtils {
	/**
     *是否是Ajax请求,如果是ajax请求响应头会有，x-requested-with
     * @param request
     * @return true ajax;fase 非ajax
     */
    public static boolean isAjax(ServletRequest request){
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest)request).getHeader("X-Requested-With"));
    }
    /**
     * response 设置超时
     * @param hresponse
     * @param resultMap
     * @throws IOException
     */
    public static void out(ServletResponse servletResponse){
        HttpServletResponse response = (HttpServletResponse) servletResponse;  
        response.setCharacterEncoding("UTF-8");
        //在响应头设置session状态
        response.setHeader("session-status", "timeout");
    }

}
