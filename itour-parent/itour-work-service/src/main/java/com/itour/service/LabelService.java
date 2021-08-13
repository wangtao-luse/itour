package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.vo.PageInfo;
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
	 * 列表查询
	 * @param requestMessage
	 * @return
	 */
public ResponseMessage queryLabelList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Label vo = jsonObject.getJSONObject(Constant.COMMON_KEY_VO).toJavaObject(Label.class);
		JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
		QueryWrapper queryWrapper = new QueryWrapper<Label>();
		queryWrapper.eq(StringUtils.isEmpty(vo.getUid()),"UID", vo.getUid());
		queryWrapper.orderByDesc("CREATEDATE");
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
