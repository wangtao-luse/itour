package com.itour.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.travel.CommentReplyNice;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2021-04-20
 */
public interface CommentReplyNiceMapper extends BaseMapper<CommentReplyNice> {
	@Select("select c.RID cid,count(*) count from t_t_travel_commentreply_nice c where c.`STATUS`='1' and c.CID in(#{rid}) GROUP BY c.RID")
	List<Map<String,Object>> countCommentReplyNice(@Param("rid") String rid);
}
