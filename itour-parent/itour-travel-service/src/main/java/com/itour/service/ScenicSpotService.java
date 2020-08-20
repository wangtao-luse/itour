package com.itour.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.model.travel.ScenicSpot;
import com.itour.model.vo.Orderby;
import com.itour.persist.ScenicSpotMapper;

/**
 * <p>
 * 景点信息表 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-25
 */
@Service
public class ScenicSpotService extends ServiceImpl<ScenicSpotMapper, ScenicSpot>  {
	//列表
	public ResponseMessage queryKeywordList(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ScenicSpot scenicSpot = jsonObject.toJavaObject(ScenicSpot.class);
			JSONObject pageVo = jsonObject.getJSONObject("page");
			QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<ScenicSpot>();
			queryWrapper.likeRight("SCENIC_SPOT", scenicSpot.getScenicSpot());
			queryWrapper.eq("ID", scenicSpot.getId());			
			queryWrapper.eq("TYPE", scenicSpot.getType());
			queryWrapper.eq("CCODE", scenicSpot.getCcode());
			queryWrapper.eq("CITY", scenicSpot.getCity());
			  //排序
			List<Orderby> orderby = scenicSpot.getOrderby();
			orderby.forEach(o->{
				String sortType = o.getSortType();
				String sortRule = o.getSortRule();
				if("1".equals(sortRule)) {
					queryWrapper.orderByAsc(sortType);
				}else {
					queryWrapper.orderByDesc(sortType);
				}
				
			});
			if(orderby.size()<=0) {
				queryWrapper.orderByDesc("ID");
			}
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
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	//单条
	public ResponseMessage getScenicSpot(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			ScenicSpot scenicSpot = jsonObject.getJSONObject("vo").toJavaObject(ScenicSpot.class);
			QueryWrapper<ScenicSpot> queryWrapper = new QueryWrapper<ScenicSpot>();
			queryWrapper.eq(null!=scenicSpot.getId(), "ID", scenicSpot.getId());
			ScenicSpot selectOne = this.baseMapper.selectOne(queryWrapper );
			responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	//修改
		public ResponseMessage updateScenicSpot(RequestMessage requestMessage) {
			ResponseMessage responseMessage = ResponseMessage.getSucess();
			try {
				JSONObject jsonObject = requestMessage.getBody().getContent();
				ScenicSpot scenicSpot = jsonObject.getJSONObject("vo").toJavaObject(ScenicSpot.class);
				this.baseMapper.updateById(scenicSpot);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
			}
			return responseMessage;
		}
		//删除
		public ResponseMessage delScenicSpot(RequestMessage requestMessage) {
			ResponseMessage responseMessage = ResponseMessage.getSucess();
			try {
				JSONObject jsonObject = requestMessage.getBody().getContent();
				Integer id = jsonObject.getInteger("id");
				this.baseMapper.deleteById(id);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
			}
			return responseMessage;
		}
}
