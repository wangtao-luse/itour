package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.travel.Stype;
import com.itour.persist.StypeMapper;

/**
 * <p>
 * 景点类型表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class StypeService extends ServiceImpl<StypeMapper, Stype>  {

}
