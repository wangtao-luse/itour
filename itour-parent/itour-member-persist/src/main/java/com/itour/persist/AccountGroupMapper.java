package com.itour.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.member.AccountGroup;

/**
 * <p>
 * 用户-组表(中间表) Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-06-24
 */
public interface AccountGroupMapper extends BaseMapper<AccountGroup> {
	public List<Map<String,Object>> getPowerGroupList(@Param("uid") String uid);

}
