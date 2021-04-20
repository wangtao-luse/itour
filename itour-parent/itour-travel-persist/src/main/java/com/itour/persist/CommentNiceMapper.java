package com.itour.persist;

import com.itour.model.travel.CommentNice;

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
 * @since 2021-04-20
 */
public interface CommentNiceMapper extends BaseMapper<CommentNice> {
	@Select("select c.CID cid,count(*) count from t_t_travel_comment_nice c where c.`STATUS`='1' and c.CID in(#{cid}) GROUP BY c.CID")
	List<Map<String,Object>> countCommentNice(@Param("cid") String cid);
}
