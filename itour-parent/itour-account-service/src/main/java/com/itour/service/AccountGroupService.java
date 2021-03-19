package com.itour.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.account.AccountGroup;
import com.itour.persist.AccountGroupMapper;

/**
 * <p>
 * 用户-组表(中间表) 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-07-13
 */
@Service
public class AccountGroupService extends ServiceImpl<AccountGroupMapper, AccountGroup> {
	
	/**
	 * 分配会员
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage grantAccount(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Integer groupId = jsonObject.getInteger("groupId");
		JSONArray jsonArray = jsonObject.getJSONArray("uidArr");
		List<AccountGroup>  list = new ArrayList<AccountGroup>();
		for (Object uid : jsonArray) {
			AccountGroup entity = new AccountGroup();
			entity.setuId(String.valueOf(uid));
			entity.setGroupId(groupId);
			list.add(entity);
		}
		this.saveBatch(list);
	} catch (Exception e) {
		// TODO: handle exception
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
	}
	/**
	 * 删除该用户所属组
	 * @param requestMessage
	 * @return
	 */
	@Transactional
	public ResponseMessage deleteAccountGroup(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Integer groupId = jsonObject.getInteger("groupId");
			JSONArray jsonArray = jsonObject.getJSONArray("uidArr");
			QueryWrapper<AccountGroup> queryWrapper = new QueryWrapper<AccountGroup>();
			queryWrapper.in("U_ID", jsonArray);
			queryWrapper.eq("GROUP_ID", groupId);
			List<AccountGroup> selectList = this.baseMapper.selectList(queryWrapper);
			List<Integer> collect = selectList.stream().map(p->p.getId()).collect(Collectors.toList());
			this.removeByIds(collect);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
