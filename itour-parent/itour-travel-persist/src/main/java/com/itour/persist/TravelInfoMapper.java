package com.itour.persist;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.travel.TravelInfo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2021-02-12
 */
public interface TravelInfoMapper extends BaseMapper<TravelInfo> {
	 int updatePvBatch(Collection<TravelInfo> entityList);
}
