package com.itour.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.model.travel.Favorites;
import com.itour.model.travel.dto.FavoritesDto;

/**
 * <p>
 * 旅行收藏夹表 Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-09-10
 */
public interface FavoritesMapper extends BaseMapper<Favorites> {
	List<FavoritesDto> selectFavoritesList(@Param("vo") FavoritesDto vo);
	List<FavoritesDto> selectFavoritesList(Page page,@Param("vo") FavoritesDto vo);
}
