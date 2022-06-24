package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.dictionary.WebsiteVisitInfo;
import com.itour.persist.WebsiteVisitInfoMapper;
import com.itour.service.WebsiteVisitInfoService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 网站访问信息统计表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2022-06-23
 */
@Service
public class WebsiteVisitInfoService extends ServiceImpl<WebsiteVisitInfoMapper, WebsiteVisitInfo> {
	/**
	 * 网站访问信息列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage selectWebsiteVisitInfoList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WebsiteVisitInfo vo = jsonObject.getJSONObject("vo").toJavaObject(WebsiteVisitInfo.class);
			JSONObject pageVo = jsonObject.getJSONObject("page");
			QueryWrapper<WebsiteVisitInfo> queryWrapper = new QueryWrapper<WebsiteVisitInfo>();
			if(null!=pageVo) {
				Page page = pageVo.toJavaObject(Page.class);			
				Page selectPage = this.baseMapper.selectPage(page, queryWrapper );
				responseMessage.setReturnResult(selectPage);
			}else {
				List selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 获取最近的1条网站访问记录
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage selectRecentlyOne(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			WebsiteVisitInfo selectRecentlyOne = this.baseMapper.selectRecentlyOne();
			responseMessage.setReturnResult(selectRecentlyOne);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return responseMessage;
	}
	/**
	 * 批量修改网站访问记录
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage saveOrUpdateVisitInfoBatch(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			List<WebsiteVisitInfo> entityList = jsonObject.getJSONArray("arr").toJavaList(WebsiteVisitInfo.class);
			this.saveOrUpdateBatch(entityList, entityList.size());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return responseMessage;
	}

}
