package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.work.Worktext;
import com.itour.persist.WorktextMapper;
import com.itour.service.WorktextService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-16
 */
@Service
public class WorktextService extends ServiceImpl<WorktextMapper, Worktext> {
	public ResponseMessage selecWorktextOne(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Long wid = jsonObject.getLong("wid");
		QueryWrapper<Worktext> queryWrapper = new QueryWrapper<Worktext>();
		queryWrapper.eq(null!=wid, "WID", wid);
		Worktext selectOne = this.baseMapper.selectOne(queryWrapper );
		responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
