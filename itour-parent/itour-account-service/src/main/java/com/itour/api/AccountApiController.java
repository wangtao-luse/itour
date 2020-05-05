package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.AccountApiService;

@RestController
@RequestMapping("/account")
public class AccountApiController {
@Autowired
AccountApiService accountApiService; 
@RequestMapping("/regiesterSub")
 public ResponseMessage regiesterSub(RequestMessage requsetMessage) {
	return  accountApiService.regiesterSub(requsetMessage);
 }
@RequestMapping("/loginSub")
public ResponseMessage loginSub(RequestMessage requestMessage) {
	return accountApiService.loginSub(requestMessage);
}
}
