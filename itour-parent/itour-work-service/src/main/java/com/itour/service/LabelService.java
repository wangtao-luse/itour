package com.itour.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.dto.PageInfo;
import com.itour.model.work.Label;
import com.itour.persist.LabelMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@Service
public class LabelService extends ServiceImpl<LabelMapper, Label> {
	/**
	 * 查询日志标签列表查询
	 * @param requestMessage
	 * @return
	 */
public ResponseMessage queryLabelList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		//1.获取参数
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Label vo = jsonObject.getJSONObject(Constant.COMMON_KEY_VO).toJavaObject(Label.class);
		JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
		//2.构建查询条件
		QueryWrapper<Label> queryWrapper = new QueryWrapper<Label>();
		queryWrapper.eq(!StringUtils.isEmpty(vo.getUid()),"UID", vo.getUid());
		queryWrapper.orderByDesc("CREATEDATE");
		//3.执行查询
		if(pageVo!=null) {	
			PageInfo page = pageVo.toJavaObject(PageInfo.class);
			PageInfo selectPage = this.baseMapper.selectPage(page, queryWrapper );
			responseMessage.setReturnResult(selectPage);
		}else {
			List selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}
	}catch (BaseException e) {
		// TODO: handle exception
		e.printStackTrace();
		throw  new BaseException(e.getMessage());
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw  new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
	

}
