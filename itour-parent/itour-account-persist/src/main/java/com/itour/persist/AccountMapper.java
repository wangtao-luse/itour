package com.itour.persist;

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

}
