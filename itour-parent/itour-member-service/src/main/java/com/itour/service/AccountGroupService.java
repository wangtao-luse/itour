package com.itour.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.model.member.AccountGroup;
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
	public ResponseMessage grantGroup(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		JSONObject jsonObject = requestMessage.getBody().getContent();
		JSONArray arrVo = jsonObject.getJSONArray("arr");
		JSONArray uidVo = jsonObject.getJSONArray("uids");
		List<AccountGroup> insert = new ArrayList<AccountGroup>();
		QueryWrapper<AccountGroup> queryWrapper = new QueryWrapper<AccountGroup>();
	//	queryWrapper.in("U_ID", coll);
		//queryWrapper.in("GROUP_ID", values);
		this.baseMapper.selectList(queryWrapper );
		List<Integer> delete = new ArrayList<Integer>();
		try {
			for (Object uid : uidVo) {
				for (Object ag : arrVo) {
					AccountGroup g = new AccountGroup();
					JSONObject arr = JSONObject.parseObject(JSONObject.toJSONString(ag));
					Integer id = arr.getInteger("gid");
					Boolean checked = arr.getBoolean("checked");
					g.setGroupId(id);
					g.setuId(String.valueOf(uid));					
					if(checked) {
						insert.add(g);
					}else {
						delete.add(id);
					}
					
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return responseMessage;
	}
}
