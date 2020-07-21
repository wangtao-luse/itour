package com.itour.persist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itour.model.account.RightDetail;

/**
 * <p>
 * 权限明细 Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2020-06-06
 */
public interface RightDetailMapper extends BaseMapper<RightDetail> {
    /**
     * 获取当前用户下的所有权限
     * @param uid
     * @return
     */
	List<Map<String,Object>>getAccountRightDetial(@Param("uid")String uid);

}
