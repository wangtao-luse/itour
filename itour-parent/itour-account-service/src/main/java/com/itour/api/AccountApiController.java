package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itour.account.api.AccountApi;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.AccountApiService;
import com.itour.service.OauthApiService;

@RestController
@RequestMapping("/account")
public class AccountApiController implements AccountApi {
@Autowired
AccountApiService accountApiService; 
@Autowired
OauthApiService oauthApiService;
@Override
@RequestMapping("/regSub")
public ResponseMessage regSub(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return accountApiService.regiesterSub(requestMessage);
}
@Override
@RequestMapping("/loginSub")
public ResponseMessage loginSub(@RequestBody RequestMessage requestMessage) {
	return oauthApiService.loginSub(requestMessage);
}
@Override
@RequestMapping("/checkOauthId")
public ResponseMessage checkOauthId(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return oauthApiService.checkOauthId(requestMessage);
}

}
