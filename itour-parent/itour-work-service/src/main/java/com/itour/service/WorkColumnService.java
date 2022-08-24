package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.help.StringHelper;
import com.itour.model.dto.PageInfo;
import com.itour.model.work.WorkColumn;
import com.itour.model.work.vo.WorkColumnVo;
import com.itour.persist.WorkColumnMapper;
import com.itour.service.WorkColumnService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-13
 */
@Service
public class WorkColumnService extends ServiceImpl<WorkColumnMapper, WorkColumn> {
	/**
	 * 专栏分类列表
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage queryColumnList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkColumn vo = jsonObject.getJSONObject(Constant.COMMON_KEY_VO).toJavaObject(WorkColumn.class);
			JSONObject pageVo = jsonObject.getJSONObject(Constant.COMMON_KEY_PAGE);
			QueryWrapper queryWrapper = new QueryWrapper<WorkColumn>();
			queryWrapper.eq(StringHelper.isEmpty(vo.getUid()), "UID", vo.getUid());
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
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	/**
	 * 查询对应用户下创建的分类专栏及专栏下的文章统计数据，用于页面展示
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage getShowColumnList(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			String uid = jsonObject.getString("uid");
			List<WorkColumnVo> showColumnList = this.baseMapper.getShowColumnList(uid);
			responseMessage.setReturnResult(showColumnList);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	public ResponseMessage selectOneColumn(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			WorkColumn vo = jsonObject.getJSONObject(Constant.COMMON_KEY_VO).toJavaObject(WorkColumn.class);
			QueryWrapper<WorkColumn> queryWrapper = new QueryWrapper<WorkColumn>();
			queryWrapper.eq(null!=vo.getId(), "ID", vo.getId());
			WorkColumn selectOne = this.baseMapper.selectOne(queryWrapper );
			responseMessage.setReturnResult(selectOne);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
	
}
