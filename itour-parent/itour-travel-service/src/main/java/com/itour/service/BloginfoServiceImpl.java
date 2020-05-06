package src.service.impl;

import src.entity.Bloginfo;
import src.mapper.BloginfoMapper;
import src.service.BloginfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 旅行博客信息表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-06
 */
@Service
public class BloginfoServiceImpl extends ServiceImpl<BloginfoMapper, Bloginfo> implements BloginfoService {

}
