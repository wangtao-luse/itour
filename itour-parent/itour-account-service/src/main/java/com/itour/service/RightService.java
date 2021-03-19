package com.itour.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.account.Right;
import com.itour.persist.RightMapper;

/**
 * <p>
 * 权限主表 服务类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-28
 */
@Service
public class RightService extends ServiceImpl<RightMapper,Right> {
/**
 * 获取菜单
 * @param requestMessage
 * @return
 */
public ResponseMessage getMenuList(RequestMessage requestMessage) {
 	ResponseMessage responseMessage = ResponseMessage.getSucess();
	JSONArray jsonArray = new JSONArray();
	try {
		String uid = requestMessage.getBody().getOauthId();
		List<Right> menuList = this.baseMapper.getMenuList(uid);	
		for (Right right : menuList) {
			JSONObject jsonObject = new JSONObject();
			//上级菜单ID:为0时表示一级菜单
		    String parentId = right.getParentId();
		    String menuNo = right.getMenuNo();
			if("0".equals(parentId)) {//一级菜单
				jsonObject.put("text", right.getMenu());
				jsonObject.put("state","closed");
				JSONArray children = new JSONArray();
				for (Right right2 : menuList) {
					//菜单的父级菜单
					String parentId2 = right2.getParentId();
					if(menuNo.equals(parentId2)) {//是该一级菜单下的二级菜单					
						JSONObject child = new JSONObject();
						           child.put("text", right2.getMenu());					           
						           		JSONObject attr = new JSONObject();
								        attr.put("url", right2.getUrl());
								   child.put("attributes", attr);
						children.add(child);
						
					}
				
				}
				jsonObject.put("children", children);
				jsonArray.add(jsonObject);
				
			}
			
			
		}
		responseMessage.setReturnResult(jsonArray);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	
	return responseMessage;
}
public ResponseMessage getRightList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Right right = jsonObject.getJSONObject("right").toJavaObject(Right.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		QueryWrapper<Right> queryWrapper = new QueryWrapper<Right>();
		/**精确查询**/
		queryWrapper.eq(null!=right.getId(), "ID", right.getId());
		queryWrapper.eq(!StringUtils.isEmpty(right.getMenuNo()), "MENU_NO", right.getMenuNo());
		queryWrapper.eq(!StringUtils.isEmpty(right.getParentId()), "PARENT_ID", right.getParentId());
		queryWrapper.eq(!StringUtils.isEmpty(right.getMenuType()), "MENU_TYPE", right.getMenuType());
		/**模糊查询**/
		queryWrapper.likeRight(!StringUtils.isEmpty(right.getMenu()), "MENU", right.getMenu());
		queryWrapper.like(!StringUtils.isEmpty(right.getUrl()), "url", right.getUrl());
		if(null!=pageVo) {
			Page page = pageVo.toJavaObject(Page.class);			
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
			responseMessage.setReturnResult(selectPage);
		}else {
			List<Right> selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
return responseMessage;	
}
}
