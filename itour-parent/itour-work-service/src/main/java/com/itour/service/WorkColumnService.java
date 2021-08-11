package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.vo.PageInfo;
import com.itour.model.work.WorkColumn;
import com.itour.persist.WorkColumnMapper;
import com.itour.service.WorkColumnService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

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
public class WorkColumnService extends ServiceImpl<WorkColumnMapper, WorkColumn> {
	public ResponseMessage queryColumnList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkColumn vo = jsonObject.getJSONObject(Constant.COMMON_KEY_VO).toJavaObject(WorkColumn.class);
			JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
			QueryWrapper queryWrapper = new QueryWrapper<WorkColumn>();
			if(pageVo!=null) {	
				PageInfo page = pageVo.toJavaObject(PageInfo.class);
				IPage selectPage = this.baseMapper.selectPage(page, queryWrapper );
				responseMessage.setReturnResult(selectPage);
			}else {
				List selectList = this.baseMapper.selectList(queryWrapper);
				responseMessage.setReturnResult(selectList);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return responseMessage;
	}
}
