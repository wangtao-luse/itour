package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.member.Account;
import com.itour.persist.AccountMapper;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@Service
public class AccountService extends ServiceImpl<AccountMapper, Account> {

}
