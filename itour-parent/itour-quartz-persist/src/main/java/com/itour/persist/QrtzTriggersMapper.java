package com.itour.persist;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.quartz.QrtzTriggers;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-08-27
 */
public interface QrtzTriggersMapper extends BaseMapper<QrtzTriggers> {
List<Map<String,Object>> queryTriggersList(Map map);
}
