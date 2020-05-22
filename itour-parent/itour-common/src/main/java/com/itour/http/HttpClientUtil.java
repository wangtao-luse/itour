package com.itour.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
//https://blog.csdn.net/zhliro/article/details/46877519
private static  HttpClientContext create = HttpClientContext.create();
public static void main(String[] args) {
	//POST方式
	//1.创建HttpClient对象
	//2.创建HttpPost对象
	//3.设置POST请求传参
	//4.执行请求处理响应
	//5.释放资源
	//GET方式
	//1.创建HttpClient对象
	//2.创建HttpGet对象
	//3.执行GET请求
	//4获取响应实体
	//5.处理响应实体
	//6.释放资源
	
	
}	
public static String httpPost(String uri,String jsonParam) {
	HttpClient httpClient = null;
	HttpPost httpPost=null;
	String str="";
	try {
		//1.创建HttpClient对象
		 httpClient = HttpClients.createDefault();
		//2.创建HttpPost对象
		 httpPost = new HttpPost(uri);	
		//设置请求和传输超时时间
		RequestConfig config = RequestConfig.custom().setSocketTimeout(60*1000).setConnectTimeout(60*1000).build();
		httpPost.setConfig(config);
		//3.设置POST请求传参
		if(StringUtils.isEmpty(jsonParam)) {
			// 解决中文乱码问题
			StringEntity entity = new StringEntity(jsonParam, "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
		}
		//4.执行请求处理响应		
		HttpResponse result = httpClient.execute(httpPost);
		if(200==result.getStatusLine().getStatusCode()) {
			//// 读取服务器返回过来的json字符串数据
			 str = EntityUtils.toString(result.getEntity(),"UTF-8");
		}
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		//5.释放资源
		httpPost.releaseConnection();
	}
	return str;
}

public static String httpGet(String url) {
	String result="";
	HttpGet httpGet =null;
	try {
		//1.创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();	
		//2.创建HttpGet对象
		httpGet = new HttpGet(url);
		//设置请求和传输超时时间
		RequestConfig config = RequestConfig.custom().setSocketTimeout(60*1000).setConnectTimeout(60*1000).build();
		httpGet.setConfig(config);
		//3.执行GET请求
		CloseableHttpResponse response = httpClient.execute(httpGet);
		//4处理响应实体
		// 请求发送成功，并得到响应
		if (response.getStatusLine().getStatusCode() == 200) {
			// 读取服务器返回过来的json字符串数据
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "utf-8");
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		//5.释放资源
		httpGet.releaseConnection();
	}
	
	return result;
}
/**
 * 获取IP
 * @param request
 * @param response
 * @return
 */
public static String getRemoteHost(HttpServletRequest request){
    String ip = request.getHeader("x-forwarded-for");
    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getHeader("Proxy-Client-IP");
    }

    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getHeader("WL-Proxy-Client-IP");
    }

    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
        ip = request.getRemoteAddr();
    }
    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
}
public static String httpPostByKeyValue(String url, String json) {
	String str = "";
	// post请求返回结果
	HttpClient httpClient = HttpClients.createDefault();
	HttpPost httpPost = new HttpPost(url);
	// 设置请求和传输超时时间
	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(180*1000).setConnectTimeout(180*1000).build();
	httpPost.setConfig(requestConfig);
	try {
		List<BasicNameValuePair> nv=new ArrayList<BasicNameValuePair>();
		nv.add(new BasicNameValuePair("JSON", json));
		UrlEncodedFormEntity uefEntity=new UrlEncodedFormEntity(nv,"UTF-8");
		httpPost.setEntity(uefEntity);
		HttpResponse result = httpClient.execute(httpPost);
		// 请求发送成功，并得到响应
		if (result.getStatusLine().getStatusCode() == 200) {
			try {
				// 读取服务器返回过来的json字符串数据
				str = EntityUtils.toString(result.getEntity(), "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		httpPost.releaseConnection();
	}
	return str;
}
}
