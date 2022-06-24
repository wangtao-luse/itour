package com.itour.persist;

import com.itour.model.dictionary.WebsiteVisitInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 网站访问信息统计表 Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2022-06-23
 */
public interface WebsiteVisitInfoMapper extends BaseMapper<WebsiteVisitInfo> {
	WebsiteVisitInfo selectRecentlyOne();
}
