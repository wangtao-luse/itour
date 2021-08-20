package com.itour.persist;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.model.work.Comment;
import com.itour.model.work.dto.CommentDto;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
public interface CommentMapper extends BaseMapper<Comment> {
List<CommentDto> commentList(@Param("vo") CommentDto vo);
List<CommentDto> commentList(Page page,@Param("vo") CommentDto vo);
}
