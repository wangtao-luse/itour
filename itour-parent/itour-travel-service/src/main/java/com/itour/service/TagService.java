package com.itour.service;

import com.itour.model.travel.Tag;
import com.itour.persist.TagMapper;
import com.itour.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-02-13
 */
@Service
public class TagService extends ServiceImpl<TagMapper, Tag> {

}
