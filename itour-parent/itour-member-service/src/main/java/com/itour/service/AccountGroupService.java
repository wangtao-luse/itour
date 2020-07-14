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
	@Transactional
	public ResponseMessage grantGroup(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			JSONArray arrVo = jsonObject.getJSONArray("arr");
			JSONArray uidVo = jsonObject.getJSONArray("uids");		
			QueryWrapper<AccountGroup> queryWrapper = new QueryWrapper<AccountGroup>();
			queryWrapper.in(uidVo.size()>0 ,"U_ID", uidVo);
			List<AccountGroup> selectList = this.baseMapper.selectList(queryWrapper );
			
			List<AccountGroup> insert = new ArrayList<AccountGroup>();
			List<Integer> delete = new ArrayList<Integer>();
				for (Object uid : uidVo) {
					for (Object entity : arrVo) {
						AccountGroup group = new AccountGroup();
						JSONObject groupP = JSONObject.parseObject(JSONObject.toJSONString(entity));
						Integer id = groupP.getInteger("groupId");
						Boolean checked = groupP.getBoolean("checked");
						group.setGroupId(id);
						group.setuId(String.valueOf(uid));
						if(checked) {
							List<AccountGroup> collect = selectList.stream().filter(p->p.getuId().equals(uid)&&p.getGroupId()==id).collect(Collectors.toList());
							if(collect.size()<=0) {
								insert.add(group);
							}
						}else {
							List<AccountGroup> collect = selectList.stream().filter(p->p.getuId().equals(uid)&&p.getGroupId()==id).collect(Collectors.toList());
							if(collect.size()>0) {
								delete.add(collect.get(0).getId());
							}
						}
					}
				}
				if(insert.size()>0) {
					this.saveBatch(insert);
				}
				if(delete.size()>0) {
					this.removeByIds(delete);
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseMessage.getFailed(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
