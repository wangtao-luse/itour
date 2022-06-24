package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.model.dictionary.WebsiteIpInfo;
import com.itour.model.dictionary.WebsiteVisitInfo;
import com.itour.persist.WebsiteIpInfoMapper;
import com.itour.service.WebsiteIpInfoService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站访独立IP访问记录 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2022-06-24
 */
@Service
public class WebsiteIpInfoService extends ServiceImpl<WebsiteIpInfoMapper, WebsiteIpInfo> {
	/**
	 * 批量修或添加改网站访问记录
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage saveOrUpdateIpInfoBatch(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			List<WebsiteIpInfo> entityList = jsonObject.getJSONArray("arr").toJavaList(WebsiteIpInfo.class);
			this.saveOrUpdateBatch(entityList, entityList.size());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return responseMessage;
	}

}
