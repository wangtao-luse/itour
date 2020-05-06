package src.service.impl;

import src.entity.Btype;
import src.mapper.BtypeMapper;
import src.service.BtypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 旅行博客类型表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-06
 */
@Service
public class BtypeServiceImpl extends ServiceImpl<BtypeMapper, Btype> implements BtypeService {

}
