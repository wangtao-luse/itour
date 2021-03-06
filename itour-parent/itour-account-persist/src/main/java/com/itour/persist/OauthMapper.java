package com.itour.persist;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.account.Oauth;

/**
 * <p>
 * 用户认证表 Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-05-03
 */
public interface OauthMapper extends BaseMapper<Oauth> {

	@Select("SELECT count(ID) totalOauth  FROM t_a_oauth c where 1=1 ${ew.sqlSegment}")
	public <T> Map<String, Object> totalOauth(@Param("ew") Wrapper<T> wrapper);

}
