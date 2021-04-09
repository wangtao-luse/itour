package com.itour.service;

import com.itour.model.travel.CommentReply;
import com.itour.persist.CommentReplyMapper;
import com.itour.service.CommentReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 旅行信息评论回复表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-04-09
 */
@Service
public class CommentReplyService extends ServiceImpl<CommentReplyMapper, CommentReply> {

}
