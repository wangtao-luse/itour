package com.itour.persist;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.account.Account;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-05-03
 */
public interface AccountMapper extends BaseMapper<Account> {
	public Integer getMaxId();
	@Select("SELECT count(ID) totalAccount  FROM t_a_account c where 1=1 ${ew.sqlSegment}")
	public <T> Map<String, Object> totalAccount(@Param("ew") Wrapper<T> wrapper);

}
