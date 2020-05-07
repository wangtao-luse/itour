package com.itour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.Bcolumn;
import com.itour.persist.BcolumnMapper;

/**
 * <p>
 * 旅行博客专栏表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-06
 */
@Service
public class BcolumnService extends ServiceImpl<BcolumnMapper, Bcolumn> {
	@Autowired
	BcolumnMapper bcolumnMapper;
	/**
	 * 旅行博客专栏查询
	 * @param requestMessage
	 * @return
	 */
public ResponseMessage queryColumnList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		 JSONObject jsonObject = requestMessage.getBody().getContent();
		 JSONObject pageJson = jsonObject.getJSONObject("page");
		 QueryWrapper<Bcolumn> queryWrapper = new QueryWrapper<Bcolumn>();
		 if(null!=pageJson) {
			 Page page = pageJson.toJavaObject(Page.class);
			 Page selectPage = this.baseMapper.selectPage(page, queryWrapper); 
			 responseMessage.setReturnResult(selectPage);
		 }else {
			 List<Bcolumn> selectList = this.baseMapper.selectList(queryWrapper);
			 responseMessage.setReturnResult(selectList);
		 }
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 旅行博客专栏新增
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage insertColumnList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		 JSONObject jsonObject = requestMessage.getBody().getContent();
		 Bcolumn bcolumn = jsonObject.getJSONObject("vo").toJavaObject(Bcolumn.class);
		 this.baseMapper.insert(bcolumn);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 旅行博客专栏修改
 * @param requestMessage
 * @return
 */
public ResponseMessage updateColumnList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		 JSONObject jsonObject = requestMessage.getBody().getContent();
		 Bcolumn bcolumn = jsonObject.getJSONObject("vo").toJavaObject(Bcolumn.class);
		 this.baseMapper.updateById(bcolumn);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
/**
 * 旅行博客专栏删除
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage deleteColumnList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		 JSONObject jsonObject = requestMessage.getBody().getContent();
		 Long id = jsonObject.getLong("id");
		 this.baseMapper.deleteById(id);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}

}
