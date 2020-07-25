package com.itour.service;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.model.travel.Location;
import com.itour.persist.LocationMapper;


/**
 * <p>
 * 城市信息表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class LocationService extends ServiceImpl<LocationMapper, Location>  {

}
