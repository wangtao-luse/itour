package com.itour.connector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itour.api.WorkApi;
import com.itour.common.HttpDataUtil;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;

@Service
public class WorkConnector {
@Autowired
private WorkApi workApi;
/**
 * 日志标签列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryLabelList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.queryLabelList(requestMessage);
}
/**
 * 日志分类专栏列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryColumnList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.queryColumnList(requestMessage);
}
/**
 * 新增或修改工作日志
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage savaOrUpdateWorkInfo(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.savaOrUpdateWorkInfo(requestMessage);
}
/**
 * 工作日志查询单条
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selectWorkInfoOne(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.selectWorkInfoOne(requestMessage);
}
/**
 * 个人博客列表(前台使用包含用户图像，昵称等信息)
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selectWorkInfoList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.selectWorkInfoList(requestMessage);
}
/**
 * 个人博客单条(前台（详情）使用带用户信息(图像，昵称))
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selectWorkInfo(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.selectWorkInfo(requestMessage);
}
/**
 * 个人博客内容单条查询
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage selecWorktextOne(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.selecWorktextOne(requestMessage);
}
/**
 * 个人博客标签列表查询
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage workTagList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.workTagList(requestMessage);
}
/**
 * 个人博客专栏列表查询
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage workColList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.workColList(requestMessage);
}
/**
 * 个人博客点赞提交
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage likeSub(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.likeSub(requestMessage);
}
/**
 * 个人博客评论列表
 * @param jsonObject
 * @param request
 * @return
 */
public ResponseMessage queryCommentList(JSONObject jsonObject,HttpServletRequest request) {
	RequestMessage requestMessage = HttpDataUtil.postData(jsonObject, request);
	return workApi.queryCommentList(requestMessage);
}
}
