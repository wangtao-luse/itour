package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.member.Group;
import com.itour.persist.GroupMapper;

/**
 * <p>
 * 管理用户组表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@Service
public class GroupService extends ServiceImpl<GroupMapper, Group> {

}
