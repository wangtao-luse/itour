package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.travel.TravelType;
import com.itour.persist.TravelTypeMapper;

/**
 * <p>
 * 旅行博客类型表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class TravelTypeService extends ServiceImpl<TravelTypeMapper, TravelType>  {

}
