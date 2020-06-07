package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.resp.ResponseMessage;
import com.itour.model.account.RoleRight;
import com.itour.persist.RoleRightMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-06
 */
@Service
public class RoleRightService extends ServiceImpl<RoleRightMapper, RoleRight>  {
}
