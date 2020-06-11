package com.itour.account.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
@FeignClient(name = "itour-account-service")
public interface AccountApi {
@RequestMapping(value = "account/regSub",produces = {"application/json;charset=UTF-8"})
public ResponseMessage regSub(RequestMessage requestMessage);
@RequestMapping(value = "account/loginSub",produces = {"application/json;charset=UTF-8"})
public ResponseMessage loginSub(RequestMessage requestMessage);
@RequestMapping(value = "account/checkOauthId",produces = {"application/json;charset=UTF-8"})
public ResponseMessage checkOauthId(RequestMessage requestMessage);
@RequestMapping(value = "account/queryAccountRight",produces = {"application/json;charset=UTF-8"})
public ResponseMessage queryAccountRight(RequestMessage requestMessage);
@RequestMapping(value = "account/updatepwd",produces = {"application/json;charset=UTF-8"})
public ResponseMessage updatePwd(RequestMessage requestMessage);
@RequestMapping(value = "account/getGroupList",produces = {"application/json;charset=UTF-8"})
public ResponseMessage getGroupList(RequestMessage requestMessage);

}
