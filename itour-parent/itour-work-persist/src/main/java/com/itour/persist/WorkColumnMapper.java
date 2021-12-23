package com.itour.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.work.WorkColumn;
import com.itour.model.work.vo.WorkColumnVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
public interface WorkColumnMapper extends BaseMapper<WorkColumn> {
	
	List<WorkColumnVo> getShowColumnList(@Param("uid") String uid);

}
