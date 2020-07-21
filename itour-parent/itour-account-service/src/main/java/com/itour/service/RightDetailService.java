package com.itour.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.account.RightDetail;
import com.itour.persist.RightDetailMapper;

/**
 * <p>
 * 权限明细 服务类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-28
 */
@Service
public class RightDetailService extends ServiceImpl<RightDetailMapper, RightDetail>{
	
	/**
	 * 获取该用户下的所有权限
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage getAccountRightDetial(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String uid = jsonObject.getString("uid");
			List<Map<String, Object>> accountRightDetial = this.baseMapper.getAccountRightDetial(uid);
			responseMessage.setReturnResult(accountRightDetial);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
