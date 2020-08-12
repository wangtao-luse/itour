package com.itour.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.advert.Advert;
import com.itour.persist.AdvertMapper;

/**
 * <p>
 * 广告信息表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-08-10
 */
@Service
public class AdvertService extends ServiceImpl<AdvertMapper, Advert> {
	/**
	 * 广告列表查询
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryAdvertList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {		
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Advert advertVo = jsonObject.toJavaObject(Advert.class);
			JSONObject pageJson = jsonObject.getJSONObject("page");
			QueryWrapper<Advert> queryWrapper = new QueryWrapper<Advert>();
			if(pageJson!=null) {
				Page page = pageJson.toJavaObject(Page.class);
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
				responseMessage.setReturnResult(selectPage);
				
			}else {
				List<Advert> selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
	}

}
