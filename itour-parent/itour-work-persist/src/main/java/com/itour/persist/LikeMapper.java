package com.itour.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.model.work.Like;
import com.itour.model.work.dto.LikeDto;
import com.itour.model.work.vo.LikeVo;
import com.itour.model.work.vo.WorkInfoVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2021-07-15
 */
public interface LikeMapper extends BaseMapper<Like> {
	@Select("select c.TID tid, count(*) count from t_w_like c  where c.`STATUS`='1'  and c.WID in(#{tid}) GROUP BY c.TID")
	List<Map<String,Object>> countLike(@Param("tid") String tid);
	
	List<LikeVo> selectLikeList (Page page,@Param("dto") LikeDto dto);
	
	Like checkIsLike(Like like);
}
