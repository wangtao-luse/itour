package com.itour.persist;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.model.work.WorkComment;
import com.itour.model.work.vo.WorkCommentVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
public interface WorkCommentMapper extends BaseMapper<WorkComment> {
List<WorkCommentVo> commentList(@Param("vo") WorkCommentVo vo);
List<WorkCommentVo> commentList(Page page,@Param("vo") WorkCommentVo vo);
}
