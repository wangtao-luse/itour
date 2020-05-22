package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itour.account.api.AccountApi;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.AccountService;
import com.itour.service.OauthService;

@RestController
@RequestMapping("/account")
public class AccountApiController implements AccountApi {
@Autowired
AccountService accountService; 
@Autowired
OauthService oauthService;
@Override
@RequestMapping("/regSub")
public ResponseMessage regSub(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return accountService.regiesterSub(requestMessage);
}
@Override
@RequestMapping("/loginSub")
public ResponseMessage loginSub(@RequestBody RequestMessage requestMessage) {
	return oauthService.loginSub(requestMessage);
}
@Override
@RequestMapping("/checkOauthId")
public ResponseMessage checkOauthId(@RequestBody RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	System.out.println(1/0);
	return oauthService.checkOauthId(requestMessage);
}

}