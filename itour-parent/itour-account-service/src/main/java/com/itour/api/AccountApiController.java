package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itour.account.api.AccountApi;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.AccountApiService;

@RestController
@RequestMapping("/account")
public class AccountApiController implements AccountApi {
@Autowired
AccountApiService accountApiService; 
@Override
@RequestMapping("/regSub")
public ResponseMessage regSub(RequestMessage requestMessage) {
	// TODO Auto-generated method stub
	return accountApiService.regiesterSub(requestMessage);
}
@Override
@RequestMapping("/loginSub")
public ResponseMessage loginSub(RequestMessage requestMessage) {
	return accountApiService.loginSub(requestMessage);
}

}
