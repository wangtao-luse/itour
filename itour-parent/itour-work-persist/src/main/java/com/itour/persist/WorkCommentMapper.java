package com.itour.persist;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.model.work.WorkComment;
import com.itour.model.work.vo.WorkCommentDto;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
public interface WorkCommentMapper extends BaseMapper<WorkComment> {
List<WorkCommentDto> commentList(@Param("vo") WorkCommentDto vo);
List<WorkCommentDto> commentList(Page page,@Param("vo") WorkCommentDto vo);
}
