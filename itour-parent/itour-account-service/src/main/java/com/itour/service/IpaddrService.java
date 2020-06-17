package com.itour.service;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.account.Ipaddr;
import com.itour.persist.IpaddrMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-17
 */
@Service
public class IpaddrService extends ServiceImpl<IpaddrMapper, Ipaddr>  {
public ResponseMessage insertIPAddr(RequestMessage requestMessage){
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Ipaddr ipaddr = jsonObject.getJSONObject("ipaddr").toJavaObject(Ipaddr.class);
		QueryWrapper<Ipaddr> queryWrapper = new QueryWrapper<Ipaddr>();
		queryWrapper.eq("IP", ipaddr.getIp());
		Ipaddr selectOne = this.baseMapper.selectOne(queryWrapper);
		if(selectOne==null){
			this.baseMapper.insert(ipaddr);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);  
	}
	
	return responseMessage;
}
}
