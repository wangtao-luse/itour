package com.itour.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.work.CommentReply;
import com.itour.model.work.dto.CommentReplyDto;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
public interface CommentReplyMapper extends BaseMapper<CommentReply> {
	List<CommentReplyDto> queryCommentReplyList(@Param("vo") CommentReplyDto vo);

}
