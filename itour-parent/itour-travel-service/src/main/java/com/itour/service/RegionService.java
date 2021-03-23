package com.itour.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.Region;
import com.itour.persist.RegionMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-03-20
 */
@Service
public class RegionService extends ServiceImpl<RegionMapper, Region>   {
	//城市组装
	public ResponseMessage getRegionList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			QueryWrapper<Region> queryWrapper = new QueryWrapper<Region>();
			List<Region> selectList = this.baseMapper.selectList(queryWrapper);
			List<Region> provinceList = selectList.stream().filter(t->"1".equals(t.getRegionLevel())).collect(Collectors.toList());
			List<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
			provinceList.forEach(p->{
				HashMap<String,Object>  map = new HashMap<String, Object>();
				List<Region> cityList = selectList.stream().filter(c->c.getRegionParentId().equals(p.getRegionCode())).collect(Collectors.toList());
				map.put(p.getRegionName(), cityList);
				result.add(map);
			});
			responseMessage.setReturnResult(result);
		} catch (BaseException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(e.getMessage());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
