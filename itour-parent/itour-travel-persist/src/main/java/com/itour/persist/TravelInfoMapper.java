package com.itour.persist;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.travel.TravelInfo;
import com.itour.model.travel.dto.TravelInfoDto;
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
	 List<TravelInfoDto> selectDynamicList(Map<String,Object> map);
	 List<TravelInfoDto> selectDynamicList(PageInfo page,Map<String,Object> map);
}
