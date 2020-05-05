package com.itour.account.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itour.common.resp.ResponseMessage;
@FeignClient(name = "itour-account-service")
@RequestMapping("/account")
public interface AccountApi {
@RequestMapping(value = "/regiesterSub",produces = {"application/json;charset=UTF-8"})
public ResponseMessage regiesterSub();
@RequestMapping(value = "/loginSub",produces = {"application/json;charset=UTF-8"})
public ResponseMessage loginSub();
}
