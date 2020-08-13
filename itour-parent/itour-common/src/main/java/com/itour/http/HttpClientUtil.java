package com.itour.http;

import java.io.IOException;
import java.sql.Timestamp;
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
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
 String url="http://103.36.172.214:8020/system/TradingQuotation";
 Timestamp ts = new Timestamp(System.currentTimeMillis());
	String sign = "48E664759161EAA7A167C6A872D56C97";
	String industry_code = "ZGZX";
	String sync_mode = "1";
	JSONArray jsonArray = new JSONArray();
	jsonArray.add(0, sign);
	jsonArray.add(1, "热轧");
	jsonArray.add(2, "热轧");
	jsonArray.add(3, 1);
	jsonArray.add(4, 1);
	JSONObject request = new JSONObject();
	request.put("timestamp", ts);
	request.put("industry_code", industry_code);
	request.put("sign", sign);
	request.put("sync_mode", sync_mode);
	request.put("data", jsonArray);
	String httpPost = HttpClientUtil.httpPost(url, request.toJSONString());
	System.out.println(httpPost);
	
}	
/**
 * HttpPostMultipartFormData方法测试例子
 */
public static void testHttpPostData() {
	MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	JSONObject jsonObject = new JSONObject();
	builder.addTextBody("json", jsonObject.toJSONString());
	builder.setContentType(ContentType.MULTIPART_FORM_DATA);
	HttpEntity entity = builder.build();
	String url = null;
	String str = HttpClientUtil.httpPostData(url, entity);
	
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
		if(!StringUtils.isEmpty(jsonParam)) {
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

	public static String httpPostData(String url, HttpEntity entity) {
		String str = "";
		HttpPost httpPost=null;
		try {
			// post请求返回结果
			HttpClient httpClient = HttpClients.createDefault();
			 httpPost = new HttpPost(url);
			// 设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(180 * 1000)
					.setConnectTimeout(180 * 1000).build();
			httpPost.setConfig(requestConfig);
			httpPost.setEntity(entity);
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
			if(null!=httpPost) {
				httpPost.releaseConnection();
			}
		}
		return str;
	}
}
