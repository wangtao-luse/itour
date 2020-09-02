package com.itour.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.travel.Nice;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-09-01
 */
public interface NiceMapper extends BaseMapper<Nice> {
@Select("select c.TID tid, count(*) count from t_t_nice c  where c.`STATUS`='1'  and c.TID in(#{tid}) GROUP BY c.TID")
List<Map<String,Object>> countNice(@Param("tid") String tid);
}
