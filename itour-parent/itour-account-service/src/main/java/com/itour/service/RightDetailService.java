package com.itour.service;

import java.util.List;

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
	public ResponseMessage queryAccountRight(@RequestBody RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			String uid = requestMessage.getBody().getuId();
			uid="10000";
			List<RightDetail> queryAccountRight = this.baseMapper.queryAccountRight(uid);
			responseMessage.setReturnResult(queryAccountRight);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}

}
