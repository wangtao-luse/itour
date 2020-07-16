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
import com.itour.model.account.dto.ViewAAccountGroup;
import com.itour.persist.ViewAAccountGroupMapper;


/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-15
 */
@Service
public class ViewAAccountGroupService extends ServiceImpl<ViewAAccountGroupMapper, ViewAAccountGroup> {
	/**
	 * 获取指定组下的所有用户
	 * @param requestMessage
	 * @return
	 */
		public ResponseMessage getViewAAccountGroupList(RequestMessage requestMessage) {
			ResponseMessage responseMessage = ResponseMessage.getSucess();
			try {
				JSONObject jsonObject = requestMessage.getBody().getContent();
				String type = jsonObject.getString("type");
				ViewAAccountGroup viewVo = jsonObject.getJSONObject("view").toJavaObject(ViewAAccountGroup.class);
				JSONObject pageVo = jsonObject.getJSONObject("page");
				QueryWrapper<ViewAAccountGroup> queryWrapper = new QueryWrapper<ViewAAccountGroup>();
				if("1".equals(type)) {
					queryWrapper.notInSql("UID", "SELECT U_ID FROM t_a_account_group c WHERE c.GROUP_ID="+viewVo.getGroupId());
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
					List<ViewAAccountGroup> selectList = this.baseMapper.selectList(queryWrapper);
					responseMessage.setReturnResult(selectList);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return responseMessage;
		
	}
}
