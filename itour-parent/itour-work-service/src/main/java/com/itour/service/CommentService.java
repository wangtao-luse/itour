package com.itour.service;

import com.itour.model.work.Comment;
import com.itour.persist.CommentMapper;
import com.itour.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {

}
