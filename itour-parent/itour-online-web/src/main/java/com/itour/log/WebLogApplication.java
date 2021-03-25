package com.itour.log;
 
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.itour.common.DuplicateSubmitToken;
import com.itour.common.req.RequestBody;
import com.itour.common.req.RequestHeader;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
 
 
@Aspect
@Component
public class WebLogApplication {
    private static Logger logger = (Logger) LoggerFactory.getLogger(WebLogApplication.class);
       //计算方法调用时间
       ThreadLocal<Long> time=new ThreadLocal<>();
       //防重复提交
       private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
               // 最大缓存 100 个
               .maximumSize(100)
               // 设置缓存过期时间为S
               .expireAfterWrite(3, TimeUnit.SECONDS)
               .build();
    @Pointcut("execution(public * com.itour.controller..*.*(..))")
    public void webLog() {
    }
 
    @Before("webLog()") //在切入点的方法运行之前的时候
    public void logBeforeController(JoinPoint joinPoint) {
    	// 调用方法开始时间
    	 long startTime = System.currentTimeMillis();
         time.set(startTime); 
         //请求参数（用于日志处理）
        requestInfo(joinPoint);
 
    }

	
 
    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void doAfterReturning(JoinPoint JoinPoint ,Object object) throws Throwable {
        // 处理完请求，返回内容
    	if(object instanceof JSON) {
    		 System.out.println("########后置通知########");
    	        String json=JSONObject.toJSONString(object);     	       
    	    	   long endTime = System.currentTimeMillis();
    	           long  t= endTime-time.get();
    	           JSONObject jsonObject1 = JSONObject.parseObject(json);
    	                      jsonObject1.put("time",t);
    	            logger.info("RESPONSE:"+jsonObject1.toJSONString());
    	      
    	}else {
    		 long endTime = System.currentTimeMillis();
	           long  t= endTime-time.get();
	           JSONObject result = new JSONObject();
	           result.put("time",t);
	           result.put("page", object);
	            logger.info("RESPONSE:"+result.toJSONString());
    	}
        
        
        
 
    }
   
    @After("webLog()")
    public void doAfter(JoinPoint JoinPoint ){
    System.out.println("########最终通知####");
    }
    @AfterThrowing(pointcut = "webLog()")
    public void afterThrow(JoinPoint JoinPoint ){
        System.out.println("#####报错了######" );
        
    }
    @Around("webLog()")
  public Object  doAround(ProceedingJoinPoint proceedingJoinPoint ) throws Exception{
        try {
        	 time.set(System.currentTimeMillis());
             System.out.println("###########方法前#########");
        	//获取被增强的方法相关信息
        	MethodSignature signature =(MethodSignature)proceedingJoinPoint.getSignature();
        	Method method = signature.getMethod();        	
        	String name = method.getName();
        	Object[] args = proceedingJoinPoint.getArgs();
        	//防重复提交
        	//ResponseMessage duplicateSubmit = duplicateSubmit(method, name, args);           
            Object proceed = proceedingJoinPoint.proceed(args);
            System.out.println("###########方法后#########");
            return proceed;
        }catch (BaseException e) {
            e.printStackTrace();
            return ResponseMessage.getFailed(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
        } catch (Throwable e) {
			// TODO Auto-generated catch block
        	e.printStackTrace();
            return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
    }
    /**
     * 防重复提交 基于本地缓存
     * @param method
     * @param name
     * @param args
     * @return
     */
	public ResponseMessage duplicateSubmit(Method method, String name, Object[] args) {
		DuplicateSubmitToken annotation = method.getAnnotation(DuplicateSubmitToken.class);
		ResponseMessage resp = ResponseMessage.getSucess();
		String key =name +args;
		if(!StringUtils.isEmpty(key)) {
			if(CACHES.getIfPresent(key)!=null) {
				
				resp.setResultCode("00");
				resp.setResultMessage("请勿重复请求");
				return resp;
			}
		}else {
			CACHES.put(key, key);
		}
		return resp;
	}
	public void requestInfo(JoinPoint joinPoint) {
		String string = joinPoint.toString();
        Object[] args = joinPoint.getArgs();
        final Object target = joinPoint.getTarget();
        System.out.println("########前置通知########");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        RequestMessage requestMessage = new RequestMessage();
        RequestHeader requestHeader = new RequestHeader();
        RequestBody requestbody = new RequestBody();
        //url
        requestHeader.setRequestURI(request.getRequestURI());//返回除去host（域名或者ip）部分的路径
        requestHeader.setRequestURL(request.getRequestURL());//返回全路径
        requestHeader.setContextPath(request.getContextPath());//返回工程名部分，如果工程映射为/，此处返回则为空
        requestHeader.setServletPath(request.getServletPath());////返回除去host和工程名部分的路径
 
        //addr
        requestHeader.setRemoteAddr(request.getRemoteAddr());//获得客户端的ip地址
        requestHeader.setLocalAddr(request.getLocalAddr());////获取服务器的IP地址
        requestHeader.setServerName(request.getServerName());//当前页面所在的服务器的名字
        requestHeader.setServerPort(request.getServerPort());
        requestHeader.setScheme(request.getScheme());//返回当前页面使用的协议，http 或是 https;
        requestHeader.setRemoteHost(request.getRemoteHost());//获得客户端的主机名        
        requestMessage.setRequestHeader(requestHeader);
        Map<String, Object> parameters = new HashMap<String, Object>();
        Enumeration<String> en = request.getParameterNames();
        while (en.hasMoreElements()) {
            String name = en.nextElement();
            String value = request.getParameter(name);
            parameters.put(name, value);
 
        }
        JSONObject content = new JSONObject(parameters);
        requestbody.setContent(content);
        requestMessage.setRequestHeader(requestHeader);
        requestMessage.setBody(requestbody);
        logger.info("REQUEST:"+JSONObject.toJSONString(requestMessage));
	}
}