package com.itour.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.account.Group;

/**
 * <p>
 * 用户组表 Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-05-07
 */
public interface GroupMapper extends BaseMapper<Group> {
List<Map<String,Object>> getAccountGroupName(@Param("uid")String uid);
}
