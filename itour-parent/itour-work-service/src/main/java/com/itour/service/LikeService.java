package com.itour.service;

import com.itour.model.work.Like;
import com.itour.persist.LikeMapper;
import com.itour.service.LikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-15
 */
@Service
public class LikeService extends ServiceImpl<LikeMapper, Like> implements LikeService {

}
