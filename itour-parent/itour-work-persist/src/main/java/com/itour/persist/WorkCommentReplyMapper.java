package com.itour.persist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.work.WorkCommentReply;
import com.itour.model.work.dto.WorkCommentReplyDto;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
public interface WorkCommentReplyMapper extends BaseMapper<WorkCommentReply> {
	List<WorkCommentReplyDto> queryCommentReplyList(@Param("vo") WorkCommentReplyDto vo);

}
