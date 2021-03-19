package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.travel.Favorites;
import com.itour.persist.FavoritesMapper;
import com.itour.service.FavoritesService;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 旅行收藏夹表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-09-10
 */
@Service
public class FavoritesService extends ServiceImpl<FavoritesMapper, Favorites> {
	/**
	 * 收藏夹列表
	 * @param requestMessage
	 * @return
	 */
public ResponseMessage queryFavoriteList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Favorites favortieVo = jsonObject.getJSONObject("vo").toJavaObject(Favorites.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		QueryWrapper<Favorites> queryWrapper = new QueryWrapper<Favorites>();
		queryWrapper.eq(!StringUtils.isEmpty(favortieVo.getUid()), "UID", favortieVo.getUid());
		if(null!=pageVo) {
			Page page = pageVo.toJavaObject(Page.class);
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
			responseMessage.setReturnResult(selectPage);
		}else {
			List<Favorites> selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 收藏夹新增
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage insertFavorite(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Favorites favoriteVo = jsonObject.getJSONObject("vo").toJavaObject(Favorites.class);
		String favorite = favoriteVo.getFavorite();
		if(StringUtils.isEmpty(favorite)) {
			throw new BaseException(ExceptionInfo.EXCEPTION_FAVORITE);
		}
		this.baseMapper.insert(favoriteVo);
	} catch (BaseException e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(e.getMessage());
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 收藏夹修改|删除
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage updateFavorite(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Favorites favoriteVo = jsonObject.getJSONObject("vo").toJavaObject(Favorites.class);
		String favorite = favoriteVo.getFavorite();
		if(StringUtils.isEmpty(favorite)) {
			throw new BaseException(ExceptionInfo.EXCEPTION_FAVORITE);
		}
		this.baseMapper.updateById(favoriteVo);
	} catch (BaseException e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(e.getMessage());
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 收藏夹物理删除
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage delFavorite(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Integer id = jsonObject.getInteger("id");
		this.baseMapper.deleteById(id);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
