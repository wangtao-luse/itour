package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.account.Oauth;
import com.itour.persist.OauthMapper;

/**
 * <p>
 * 用户认证表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-03
 */
@Service
public class OauthApiService extends ServiceImpl<OauthMapper, Oauth> {

}
