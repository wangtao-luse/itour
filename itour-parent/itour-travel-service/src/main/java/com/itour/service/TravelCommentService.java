package com.itour.service;

import com.itour.model.travel.TravelComment;
import com.itour.persist.TravelCommentMapper;
import com.itour.service.TravelCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 旅行信息评论表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-27
 */
@Service
public class TravelCommentService extends ServiceImpl<TravelCommentMapper, TravelComment>  {

}
