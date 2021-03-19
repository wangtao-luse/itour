package com.itour.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.account.RoleRight;
import com.itour.persist.RoleRightMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2020-06-06
 */
@Service
public class RoleRightService extends ServiceImpl<RoleRightMapper, RoleRight>  {
     @Transactional
	public ResponseMessage powerRight(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
    	 ResponseMessage responseMessage = ResponseMessage.getSucess();
        try {
        	JSONObject jsonObject = requestMessage.getBody().getContent();
    		JSONArray jsonArray = jsonObject.getJSONArray("arr");
    		List<RoleRight> insert =new ArrayList<RoleRight>();
    		List<Integer> delete =new ArrayList<Integer>();
    		for (Object arrVo : jsonArray) {
    			JSONObject object = JSONObject.parseObject(JSONObject.toJSONString(arrVo));	
    			Integer id = object.getInteger("rrid");
    			Integer roleId = jsonObject.getInteger("roleId");
    			Integer rightId = object.getInteger("rightId");
    			Boolean checked = object.getBoolean("checked");
    			if(checked) {
    				RoleRight roleRight = new RoleRight();
    				roleRight.setId(id);
    				roleRight.setRoleId(roleId);
    				roleRight.setRightId(rightId);
    				insert.add(roleRight);
    			}else {
    				
    				delete.add(id);
    			}
    		}
    		if(insert.size()>0) {
    			this.saveOrUpdateBatch(insert);
    		}    		
    		if(delete.size()>0) {
    			this.baseMapper.deleteBatchIds(delete);
    		}
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
		}
		
		return responseMessage;
	}
}
