package com.itour.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
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
	public ResponseMessage grantGroup(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		JSONObject jsonObject = requestMessage.getBody().getContent();
		JSONArray arrVo = jsonObject.getJSONArray("arr");
		JSONArray uidVo = jsonObject.getJSONArray("uids");
		List<AccountGroup> insert = new ArrayList<AccountGroup>();
		List<Integer> delete = new ArrayList<Integer>();
		try {
			for (Object uid : uidVo) {
				for (Object ag : arrVo) {
					AccountGroup g = new AccountGroup();
					JSONObject arr = JSONObject.parseObject(JSONObject.toJSONString(ag));
					Integer id = arr.getInteger("gid");
					Boolean checked = arr.getBoolean("checked");
					g.setId(id);
					g.setuId(String.valueOf(uid));
					if(checked) {
						insert.add(g);
					}else {
						delete.add(id);
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
		}
		return responseMessage;
	}
}
