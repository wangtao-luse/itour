package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.travel.TransportationType;
import com.itour.persist.TransportationTypeMapper;

/**
 * <p>
 * 交通类型表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class TransportationTypeService extends ServiceImpl<TransportationTypeMapper, TransportationType>  {

}
