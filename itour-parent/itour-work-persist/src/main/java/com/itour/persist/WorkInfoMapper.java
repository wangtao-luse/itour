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
import com.itour.model.work.vo.WorkInfoVo;

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
 * 个人日志列表，主要提供前台列表展示使用
 * @param page
 * @param workInfoDto
 * @return 个人日志列表
 */
List<WorkInfoVo> selectWorkInfoList(Page page,@Param("vo")WorkInfoVo workInfoDto);
List<WorkInfoVo> selectWorkInfoList(@Param("vo")WorkInfoVo workInfoDto);
/**
 * 个人日志单条(主要提供前台详情页面使用用户信息(图像，昵称))
 * @param workInfoDto
 * @return 个人日志单条
 */
WorkInfoVo selectWorkInfo(@Param("vo")WorkInfoVo workInfoDto);
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
List<WorkInfoVo> searchTextList(Page page,@Param("dto")WorkInfoDto workInfoDto);
/**
 * 个人中心统计
 * @param workInfoDto
 * @return
 */
WorkInfoVo getInfoData(@Param("vo")WorkInfoVo workInfoDto);
/**
 * 个人中心列表
 * @param page
 * @param workInfoDto
 * @return
 */
List<WorkInfoVo> selectDynamicList(Page page,@Param("vo")WorkInfoVo workInfoDto);
/**
 * 修改浏览量
 * @param entityList
 * @return
 */
int updatePvBatch(Collection<WorkInfo> entityList);
/**
 * 根据专栏查询列表个人
 * @param page
 * @param workInfoDto
 * @return
 */
List<WorkInfoVo> queryWorkByColList(Page page,@Param("vo")WorkInfoVo workInfoDto);
List<WorkInfoVo> queryWorkByColList(@Param("vo")WorkInfoVo workInfoDto);


}
