package com.itour.persist;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.member.vo.ViewMOauth;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-07-10
 */
public interface ViewMOauthMapper extends BaseMapper<ViewMOauth> {
	 @Select("SELECT count(ID) totalOauth  FROM view_m_oauth c where 1=1 ${ew.sqlSegment}")
		public <T> Map<String, Object> totalOauth(@Param("ew") Wrapper<T> wrapper);
}
