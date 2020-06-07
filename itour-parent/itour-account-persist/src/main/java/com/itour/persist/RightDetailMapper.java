package com.itour.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.account.RightDetail;

/**
 * <p>
 * 权限明细 Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-06-06
 */
public interface RightDetailMapper extends BaseMapper<RightDetail> {
	public List<RightDetail> queryAccountRight(@Param(value = "uid") String uid);

}
