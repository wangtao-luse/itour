package com.itour.service;


import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.travel.Stype;
import com.itour.persist.StypeMapper;

/**
 * <p>
 * 景点类型表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class StypeService extends ServiceImpl<StypeMapper, Stype>  {
	//列表
		public ResponseMessage queryStypeList(RequestMessage requestMessage) {
			ResponseMessage response = ResponseMessage.getSucess();
			try {
				JSONObject jsonObject = requestMessage.getBody().getContent();
				Stype stype = jsonObject.toJavaObject(Stype.class);
				JSONObject pageVo = jsonObject.getJSONObject("page");
				QueryWrapper<Stype> queryWrapper = new QueryWrapper<Stype>();
				queryWrapper.orderByAsc("ID");
				if(pageVo!=null) {
					Page page = pageVo.toJavaObject(Page.class);
					Page selectPage = this.baseMapper.selectPage(page, queryWrapper );
					response.setReturnResult(selectPage);
				}else {
					this.baseMapper.selectList(queryWrapper);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
			}
			return response;
		}
		//单条
		public ResponseMessage getStype(RequestMessage requestMessage) {
			ResponseMessage response = ResponseMessage.getSucess();
			try {
				JSONObject jsonObject = requestMessage.getBody().getContent();
				Stype stype = jsonObject.toJavaObject(Stype.class);
				QueryWrapper<Stype> queryWrapper = new QueryWrapper<Stype>();
				queryWrapper.eq(null!=stype.getId(), "ID", stype.getId());
				Stype selectOne = this.baseMapper.selectOne(queryWrapper);
				response.setReturnResult(selectOne);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
			}
			return response;
		}
		//修改
		public ResponseMessage updateStype(RequestMessage requestMessage) {
			ResponseMessage response = ResponseMessage.getSucess();
			try {
				JSONObject jsonObject = requestMessage.getBody().getContent();
				Stype stype = jsonObject.toJavaObject(Stype.class);
				this.baseMapper.updateById(stype);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
			}
			return response;
		}
		//删除
		public ResponseMessage delStype(RequestMessage requestMessage) {
			ResponseMessage response = ResponseMessage.getSucess();
			try {
				JSONObject jsonObject = requestMessage.getBody().getContent();
				Integer id = jsonObject.getInteger("id");
				this.baseMapper.deleteById(id);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
			}
			return response;
		}
}
