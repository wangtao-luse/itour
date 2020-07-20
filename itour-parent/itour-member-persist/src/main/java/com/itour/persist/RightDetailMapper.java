package com.itour.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.member.RightDetail;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
public interface RightDetailMapper extends BaseMapper<RightDetail> {
	List<Map<String,Object>>getAccountRightDetial(@Param("uid")String uid);
}
