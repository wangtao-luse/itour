package com.itour.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.member.Right;


/**
 * <p>
 * 权限主表 Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
public interface RightMapper extends BaseMapper<Right> {
 List<Right> getMenuList(@Param("uid")String uid);
}
