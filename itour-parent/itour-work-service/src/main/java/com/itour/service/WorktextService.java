package com.itour.service;

import com.itour.common.redis.RedisManager;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.RedisKey;
import com.itour.exception.BaseException;
import com.itour.model.work.Worktext;
import com.itour.persist.WorktextMapper;
import com.itour.service.WorktextService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-07-16
 */
@Service
public class WorktextService extends ServiceImpl<WorktextMapper, Worktext> {
	@Autowired
	RedisManager redisMager;
	/**
	 * 获取日志文本，首先从缓存中获取，缓存中没有，从数据库中获取;
	 * @param requestMessage
	 * @return
	 */
	public ResponseMessage selecWorktextOne(RequestMessage requestMessage) {
		ResponseMessage responseMessage = ResponseMessage.getSucess();
		try {
			JSONObject jsonObject = requestMessage.getBody().getContent();
			Long wid = jsonObject.getLong("wid");
			// 1.从缓存中取
			Object obj = this.redisMager.hget(RedisKey.KEY_WORK_ARTICLE_TEXT, String.valueOf(wid));
			if (null != obj) {
				JSONObject json = (JSONObject)obj;
				Worktext javaObject = json.toJavaObject(Worktext.class);
				responseMessage.setReturnResult(javaObject);
			} else {// 2.从缓存中没有取到,从数据库获取
				QueryWrapper<Worktext> queryWrapper = new QueryWrapper<Worktext>();
				queryWrapper.eq(null != wid, "WID", wid);
				Worktext selectOne = this.baseMapper.selectOne(queryWrapper);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put(String.valueOf(wid), selectOne);
				this.redisMager.hmset(RedisKey.KEY_WORK_ARTICLE_TEXT, map);
				responseMessage.setReturnResult(selectOne);
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		return responseMessage;
	}
}
