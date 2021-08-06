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
	List<Map<String, Object>> queryGroupRole(@Param("gid") Long gid);
	/**
	 * 获取当前用户下的所有拥有的角色名称
	 * @param uid
	 * @return
	 */
	List<Map<String, Object>> getAccountRoleName(@Param("uid") String uid);
}
