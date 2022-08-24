package com.itour.common.resp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



import com.itour.constant.Constant;
import com.itour.help.StringHelper;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;



public final class ResponseMessage implements Serializable {

	private static final long serialVersionUID = 1L;
    //返回状态码(00:失败;10:成功)
	private String resultCode;
	//返回提示消息
	private String resultMessage;
	//返回数据
	private Map<String, Object> returnResult = new HashMap<String, Object>();
	public ResponseMessage() {
	}
	//为了快速构建ResponseMessage对象
	public ResponseMessage(String resultCode, String resultMessage) {
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}
	//快速构建ResponseMessage对象,将Map封装到返回对象中
	public ResponseMessage(Map map) {
		this.resultCode = Constant.SUCCESS_CODE;
		this.resultMessage = Constant.SUCESS_MESSAGE;
		this.returnResult=map;
	}
	//快速构建ResponseMessage对象,将数据封装到返回对象中
	public ResponseMessage(Object object) {
		this.resultCode = Constant.SUCCESS_CODE;
		this.resultMessage = Constant.SUCESS_MESSAGE;
		this.returnResult.put(Constant.COMMON_KEY_RESULT, object);
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public Map<String, Object> getReturnResult() {
		return returnResult;
	}
	public void setReturnResult(Map<String, Object> returnResult) {
		this.returnResult = returnResult;
	}

	 //添加可变参数注意是为了解决Could not extract response: no suitable HttpMessageConverter found for respose type
	//具体原因不清楚。。。。。待解决
	  public void setReturnResult(Object object,Object...obj) { 
		  Map<String,Object> map = new  HashMap<String, Object>();
		  map.put(Constant.COMMON_KEY_RESULT, object);
		  this.returnResult = map;
	  }
	  

	public  ResponseMessage add(Object object) {
		this.returnResult.put(Constant.COMMON_KEY_DATA, object);
		return this;
	}

	public ResponseMessage add(String key, Object object) {
	     returnResult.put(key, object);
		return this;
	}

	public static ResponseMessage getSucess() {
		return new ResponseMessage(Constant.SUCCESS_CODE, Constant.SUCESS_MESSAGE);
	}
	public static ResponseMessage getSucess(String successMsg) {
		return new ResponseMessage(Constant.SUCCESS_CODE, successMsg);
	}

	public static ResponseMessage getFailed() {
		return new ResponseMessage(Constant.FAILED_CODE, Constant.FAILED_MESSAGE);
	}
	public static ResponseMessage getFailed(String faileMsg) {
		return new ResponseMessage(Constant.FAILED_CODE, faileMsg);
	}
	/**
	 * 判断是否调用成功
	 * @param resp
	 * @return
	 */
	public static boolean isSuccessResult(ResponseMessage resp) {
		if(ObjectUtil.isNull(resp)) {
			return false;
		}
		Map<String, Object> map = resp.getReturnResult();
		
		return Constant.SUCCESS_CODE.equals(resp.getResultCode())&&!StringHelper.isEmpty(resp.getReturnResult());
	}
	/**
	 * 判断是否调用失败
	 * @param resp
	 * @return
	 */
	public static boolean isFailResult(ResponseMessage resp) {
		if(ObjectUtil.isNull(resp)) {
			return false;
		}
		return Constant.FAILED_CODE.equals(resp.getResultCode())||StringHelper.isEmpty(resp.getReturnResult());
	}
	/**
	 * 判断调用后是否返回数据
	 * @param resp
	 * @return 
	 */
	public static boolean resultIsEmpty(ResponseMessage resp) {
		if(ObjectUtil.isNull(resp)) {
			return true;
		}
		boolean code = Constant.SUCCESS_CODE.equals(resp.getResultCode());
		boolean returnResult = !StringHelper.isEmpty(resp.getReturnResult());
		Map<String, Object> result = resp.getReturnResult();
		Object object = result.get(Constant.COMMON_KEY_RESULT);
		return code && returnResult &&(object == null || "".equals(object));
		
	}

}
