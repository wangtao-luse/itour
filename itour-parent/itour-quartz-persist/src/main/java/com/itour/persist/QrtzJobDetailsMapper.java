package com.itour.persist;

import com.itour.model.quartz.QrtzJobDetails;

import java.util.List;
import java.util.Map;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-08-28
 */
public interface QrtzJobDetailsMapper extends BaseMapper<QrtzJobDetails> {
	<T> List<Map<String,Object>> queryTriggersList(IPage<T> page,Map map);
	<T> Map<String,Object> getTrigger(Map map);
}
