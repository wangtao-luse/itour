package com.itour.service;

import com.itour.model.work.WorkInfo;
import com.itour.persist.WorkInfoMapper;
import com.itour.service.WorkInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@Service
public class WorkInfoService extends ServiceImpl<WorkInfoMapper, WorkInfo> {

}
