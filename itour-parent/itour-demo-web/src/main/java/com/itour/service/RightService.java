package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.dao.RightMapper;
import com.itour.model.Right;



/**
 * <p>
 * 权限主表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-23
 */
@Service
public class RightService extends ServiceImpl<RightMapper, Right> {
public Page queryRightList(Page page){
	Wrapper<Right> queryWrapper = new QueryWrapper<Right>();
   Page selectPage = (Page) this.baseMapper.selectPage(page, queryWrapper);
     
	return selectPage;
}
}
