package com.itour.persist;

import com.itour.model.work.ReplyLike;

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
public interface ReplyLikeMapper extends BaseMapper<ReplyLike> {
	@Select("select c.RID cid,count(*) count from t_w_reply_like c where c.`STATUS`='1' and c.CID in(#{rid}) GROUP BY c.RID")
	List<Map<String,Object>> countReplyLike(@Param("rid") String rid);
}
