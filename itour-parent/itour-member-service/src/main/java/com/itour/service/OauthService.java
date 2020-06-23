package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.member.Oauth;
import com.itour.persist.OauthMapper;

/**
 * <p>
 * 用户认证表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@Service
public class OauthService extends ServiceImpl<OauthMapper, Oauth> {

}
