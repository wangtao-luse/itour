package com.itour.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.dto.PageInfo;
import com.itour.model.work.Like;
import com.itour.model.work.dto.LikeDto;
import com.itour.model.work.vo.LikeVo;
import com.itour.persist.LikeMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-15
 */
@Service
public class LikeService extends ServiceImpl<LikeMapper, Like>  {
	/**
	 * 单条查询
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage getLike(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Like likeVo = jsonObject.toJavaObject(Like.class);
			QueryWrapper<Like> queryWrapper = new QueryWrapper<Like>();
			queryWrapper.eq(null!=likeVo.getWid(), "WID", likeVo.getWid());
			queryWrapper.eq(null!=likeVo.getId(), "ID", likeVo.getId());
			queryWrapper.eq(!StringUtils.isEmpty(likeVo.getUid()), "UID", likeVo.getUid());
			Like selectOne = this.baseMapper.selectOne(queryWrapper);
			responseMessage.setReturnResult(selectOne);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 批量修改或保存点赞信息
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage saveOrUpdateBatchLike(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			List<Like> javaList = jsonObject.getJSONArray(Constant.COMMON_KEY_ARR).toJavaList(Like.class);
			this.saveOrUpdateBatch(javaList, javaList.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
		
	}
	/**
	 * 统计指定文章点赞量
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage countLikeList(RequestMessage requestMessage) {
		ResponseMessage response = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String tids = jsonObject.getString("tids");
			List<Map<String, Object>> countNice = this.baseMapper.countLike(tids);
			response.setReturnResult(countNice);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return response;
	}
	public ResponseMessage queryLikeList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			LikeDto favortieDto = jsonObject.getJSONObject(Constant.COMMON_KEY_DTO).toJavaObject(LikeDto.class);
			JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
			if(null!=pageVo) {
				PageInfo page = pageVo.toJavaObject(PageInfo.class);
				List<LikeVo> selectFavoritesList = this.baseMapper.selectLikeList(page, favortieDto);
				page.setRecords(selectFavoritesList);
				responseMessage.setReturnResult(page);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
