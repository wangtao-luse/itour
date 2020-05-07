package com.itour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.Btype;
import com.itour.persist.BtypeMapper;


/**
 * <p>
 * 旅行博客类型表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-06
 */
@Service
public class BtypeService extends ServiceImpl<BtypeMapper, Btype>  {
	@Autowired
	BtypeMapper btypeMapper;
/**
 * 旅行博客类型查询
 * @param requestMessage
 * @return
 */
public ResponseMessage queryBtype(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		JSONObject pageJson = jsonObject.getJSONObject("page");
		QueryWrapper<Btype> queryWrapper = new QueryWrapper<Btype>();
		if(null!=pageJson) {
			Page page = pageJson.toJavaObject(Page.class);
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper);
			responseMessage.setReturnResult(selectPage);
		}else {
			List<Btype> selectList = this.baseMapper.selectList(queryWrapper);
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
 * 旅行博客类型新增
 * @param requestMessage
 * @return
 */
public ResponseMessage insertBtype(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 旅行博客类型修改
 * @param requestMessage
 * @return
 */
public ResponseMessage updateBtype(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 旅行博客类型删除
 * @param requestMessage
 * @return
 */
public ResponseMessage deleteBtype(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
