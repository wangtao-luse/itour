package com.itour.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.model.account.Right;
import com.itour.persist.RightMapper;

/**
 * <p>
 * 权限主表 服务类
 * </p>
 *
 * @author wangtao
 * @since 2020-05-28
 */
public class RightService extends ServiceImpl<RightMapper,Right> {
public ResponseMessage getMenuList(RequestMessage requestMessage) {
	String uid = requestMessage.getBody().getOauthId();
	List<Right> menuList = this.baseMapper.getMenuList(uid);
	return null;
}

}
