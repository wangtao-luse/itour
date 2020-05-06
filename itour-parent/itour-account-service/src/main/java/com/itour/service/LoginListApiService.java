package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.account.LoginList;
import com.itour.persist.LoginListMapper;

/**
 * <p>
 * 用户认证表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-05
 */
@Service
public class LoginListApiService extends ServiceImpl<LoginListMapper, LoginList>  {

}
