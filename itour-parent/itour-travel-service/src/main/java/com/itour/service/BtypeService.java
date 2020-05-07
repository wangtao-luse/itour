package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.travel.Btype;
import com.itour.persist.BtypeMapper;


/**
 * <p>
 * 旅行博客类型表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-06
 */
@Service
public class BtypeService extends ServiceImpl<BtypeMapper, Btype>  {

}
