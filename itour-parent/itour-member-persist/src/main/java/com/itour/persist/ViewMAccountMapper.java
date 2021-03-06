package com.itour.persist;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.member.dto.ViewMAccount;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-07-08
 */
public interface ViewMAccountMapper extends BaseMapper<ViewMAccount> {
	@Select("SELECT count(ID) totalAccount  FROM view_m_account c where 1=1 ${ew.sqlSegment}")
	public <T> Map<String, Object> totalAccount(@Param("ew") Wrapper<T> wrapper);
}
