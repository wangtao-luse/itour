package com.itour.persist;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.account.Right;

import feign.Param;

/**
 * <p>
 * 权限主表 Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-05-28
 */
public interface RightMapper extends BaseMapper<Right> {
	public List<Right> getMenuList(@Param("uid") String uid);
	public List<Map<String,Object>> authorizeRightList(Map<String, Object> map);
}
