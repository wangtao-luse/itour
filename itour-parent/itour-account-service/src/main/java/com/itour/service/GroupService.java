package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.account.Group;
import com.itour.persist.GroupMapper;

/**
 * <p>
 * 用户组表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-07
 */
@Service
public class GroupService extends ServiceImpl<GroupMapper, Group>  {

}
