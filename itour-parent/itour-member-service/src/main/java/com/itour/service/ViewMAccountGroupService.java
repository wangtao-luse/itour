package com.itour.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.member.vo.ViewMAccountGroup;
import com.itour.persist.ViewMAccountGroupMapper;


/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-15
 */
@Service
public class ViewMAccountGroupService extends ServiceImpl<ViewMAccountGroupMapper, ViewMAccountGroup> {

	/**
	 * 获取指定组下的所有用户
	 * @param requestMessage
	 * @return
	 */
		public ResponseMessage getViewMAccountGroupList(RequestMessage requestMessage) {
			ResponseMessage responseMessage = ResponseMessage.getSucess();
			try {
				JSONObject jsonObject = requestMessage.getBody().getContent();
				String type = jsonObject.getString("type");
				ViewMAccountGroup viewVo = jsonObject.getJSONObject("view").toJavaObject(ViewMAccountGroup.class);
				JSONObject pageVo = jsonObject.getJSONObject("page");
				QueryWrapper<ViewMAccountGroup> queryWrapper = new QueryWrapper<ViewMAccountGroup>();
				if("1".equals(type)) {
					queryWrapper.notInSql("UID", "SELECT U_ID FROM t_a_account_group c WHERE c.GROUP_ID="+viewVo.getGroupId());
				}else if("2".equals(type)) {
					queryWrapper.notInSql("UID", "SELECT U_ID FROM t_m_account_group c WHERE c.GROUP_ID="+viewVo.getGroupId());
				}else {
					queryWrapper.eq("GROUP_ID", viewVo.getGroupId());
				}
				
				queryWrapper.eq("STATUS","1");
				queryWrapper.eq(!StringUtils.isEmpty(viewVo.getUid()), "UID", viewVo.getUid());			
				queryWrapper.eq(!StringUtils.isEmpty(viewVo.getUtype()), "UTYPE", viewVo.getUtype());			
				if(null!=pageVo) {
					Page page = pageVo.toJavaObject(Page.class);				
					Page selectPage = this.baseMapper.selectPage(page, queryWrapper );
					responseMessage.setReturnResult(selectPage);
				}else {
					List<ViewMAccountGroup> selectList = this.baseMapper.selectList(queryWrapper);
					responseMessage.setReturnResult(selectList);
				}
			} catch (Exception e) {
				// TODO: handle exception
				throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
			}
			return responseMessage;
		
	}
}
