package com.itour.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.member.Role;

/**
 * <p>
 * 角色主表 Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
public interface RoleMapper extends BaseMapper<Role> {
	List<Map<String, Object>> queryGroupRole(@Param("gid") Integer gid);
}
