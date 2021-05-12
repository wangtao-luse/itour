package com.itour.persist;

import java.util.Collection;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.travel.TravelInfo;
import com.itour.model.vo.PageInfo;

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
	 Map selectDynamicList(Map<String,Object> map,PageInfo pageInfo);
}
