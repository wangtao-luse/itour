package com.itour.persist;

import com.itour.model.work.CommentLike;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
public interface CommentLikeMapper extends BaseMapper<CommentLike> {
	@Select("select c.CID cid,count(*) count from t_w_comment_like c where c.`STATUS`='1' and c.CID in(#{cid}) GROUP BY c.CID")
	List<Map<String,Object>> countCommentLike(@Param("cid") String cid);
}
