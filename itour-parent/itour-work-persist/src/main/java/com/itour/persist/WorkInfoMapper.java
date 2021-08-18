package com.itour.persist;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itour.model.work.Label;
import com.itour.model.work.WorkColumn;
import com.itour.model.work.WorkInfo;
import com.itour.model.work.dto.WorkInfoDto;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
public interface WorkInfoMapper extends BaseMapper<WorkInfo> {
/**
 * 个人博客列表
 * @param page
 * @param workInfoDto
 * @return
 */
List<WorkInfoDto> selectWorkInfoList(Page page,@Param("vo")WorkInfoDto workInfoDto);
List<WorkInfoDto> selectWorkInfoList(@Param("vo")WorkInfoDto workInfoDto);
/**
 * 个人博客单条(前台详情页面使用用户信息(图像，昵称))
 * @param workInfoDto
 * @return
 */
WorkInfoDto selectWorkInfo(@Param("vo")WorkInfoDto workInfoDto);
/**
 * 个人博客标签查询
 * @param id
 * @return
 */
List<Label> workTagList(@Param("id") Long id);

/**
 * 个人博客分类专栏
 * @param id
 * @return
 */
List<WorkColumn> workColList(@Param("id") Long id);




















/**
 * 搜索页面使用
 * @param workInfoDto
 * @return
 */
List<WorkInfoDto> searchTextList(Page page,@Param("vo")WorkInfoDto workInfoDto);
/**
 * 个人中心统计
 * @param workInfoDto
 * @return
 */
WorkInfoDto getInfoData(@Param("vo")WorkInfoDto workInfoDto);
/**
 * 个人中心列表
 * @param page
 * @param workInfoDto
 * @return
 */
List<WorkInfoDto> selectDynamicList(Page page,@Param("vo")WorkInfoDto workInfoDto);
/**
 * 修改浏览量
 * @param entityList
 * @return
 */
int updatePvBatch(Collection<WorkInfo> entityList);


}
