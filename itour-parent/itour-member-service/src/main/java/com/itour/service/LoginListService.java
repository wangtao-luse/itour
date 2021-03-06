package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.member.LoginList;
import com.itour.persist.LoginListMapper;


/**
 * <p>
 * 登录记录表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@Service
public class LoginListService extends ServiceImpl<LoginListMapper, LoginList> {

}
