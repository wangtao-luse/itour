package com.itour.persist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 public List<Right> getMenuList(@Param("uid")String uid);

 public List<Map<String, Object>> authorizeRightList(HashMap<String, Object> map);
}
