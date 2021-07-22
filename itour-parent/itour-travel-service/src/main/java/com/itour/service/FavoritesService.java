package com.itour.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ExceptionInfo;
import com.itour.exception.BaseException;
import com.itour.model.travel.Favorites;
import com.itour.model.travel.dto.FavoritesDto;
import com.itour.model.vo.PageInfo;
import com.itour.persist.FavoritesMapper;
import com.itour.util.DateUtil;

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
 * 前台收藏展示使用
 * @param requestMessage
 * @return
 */
public ResponseMessage selectFavoritesList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		FavoritesDto favortieVo = jsonObject.getJSONObject("vo").toJavaObject(FavoritesDto.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		if(null!=pageVo) {
			PageInfo page = pageVo.toJavaObject(PageInfo.class);
			 List<FavoritesDto> selectFavoritesList = this.baseMapper.selectFavoritesList(page, favortieVo);
			 page.setRecords(selectFavoritesList);
			responseMessage.setReturnResult(page);
		}else {
			List<FavoritesDto> selectList = this.baseMapper.selectFavoritesList(favortieVo);
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
 * 前台个人主页展示使用
 * @param requestMessage
 * @return
 */
public ResponseMessage queryfavList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		FavoritesDto favortieVo = jsonObject.getJSONObject(Constant.COMMON_KEY_VO).toJavaObject(FavoritesDto.class);
		JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
		if(null!=pageVo) {
			PageInfo page = pageVo.toJavaObject(PageInfo.class);
			List<FavoritesDto> selectFavoritesList = this.baseMapper.queryfavList(page, favortieVo);
			page.setRecords(selectFavoritesList);
			responseMessage.setReturnResult(page);
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
		favoriteVo.setCreatedate(DateUtil.currentLongDate());
		favoriteVo.setUid(requestMessage.getBody().getuId());
		favoriteVo.setStatus(Constant.COMMON_DELETE);
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
		Favorites fav = this.baseMapper.selectById(favoriteVo.getId());
		//检查该文件夹是否为自己创建
		if(fav.getUid().equals(requestMessage.getBody().getuId())) {
			this.baseMapper.updateById(favoriteVo);
		}else {
			throw new BaseException(ExceptionInfo.EXCEPTION_FAVORITE_NOAUTH);
		}
		
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
 * 查询单条
 * @param requestMessage
 * @return
 */
public ResponseMessage selectOneFavortie(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Favorites vo = jsonObject.getJSONObject("vo").toJavaObject(Favorites.class);
		QueryWrapper<Favorites> queryWrapper = new  QueryWrapper<Favorites>();
		queryWrapper.eq(null!=vo.getId(),"id", vo.getId());
		Favorites selectOne = this.baseMapper.selectOne(queryWrapper);
		responseMessage.setReturnResult(selectOne);
	} catch (Exception e) {
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
