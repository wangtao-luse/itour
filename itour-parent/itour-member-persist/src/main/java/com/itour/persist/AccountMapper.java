package com.itour.persist;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.member.Account;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
public interface AccountMapper extends BaseMapper<Account> {
	public Integer getMaxId();
}
