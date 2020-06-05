package com.itour.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**fastJson帮助类
 * map--->json-->list
 * @author wwang
 *
 * @param <T>
 */
public class FastJsonUtil {
/**
 * Map 转JsonObject对象
 * @param map Map
 * @return JSONObject
 */
public static JSONObject mapToJson(Map map) {
	String text = JSON.toJSONString(map);
	JSONObject parseObject = JSONObject.parseObject(text);
	return parseObject;
}
/**
 * Map转为实体List
 * @param <T>
 * @param map  Map
 * @param clazz  实体
 * @param key   键
 * @return  List
 */
public static <T> List<T> mapToList(Map<String,Object> map,Class<T> clazz,String key){
	  JSONObject mapToJson = FastJsonUtil.mapToJson(map);
	 List<T> javaList = mapToJson.getJSONArray(key).toJavaList(clazz);
	return javaList;
}
/**
 * Map 转实体类
 * @param <T>
 * @param map  map
 * @param clazz 类
 * @param key  键
 * @return  实体类型对象
 */
public static <T> T mapToObject(Map<String,Object> map,Class<T> clazz,String key){
	JSONObject mapToJson = FastJsonUtil.mapToJson(map);
	 T javaObject = mapToJson.getJSONObject(key).toJavaObject(clazz);
	return javaObject;
}
public static String mapTosStirng(Map<String,Object>map,String key) {
	JSONObject mapToJson = FastJsonUtil.mapToJson(map);
	String string = mapToJson.getString(key);
	return string;
}
}
