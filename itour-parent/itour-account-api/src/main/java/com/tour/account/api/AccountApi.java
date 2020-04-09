package com.tour.account.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
@FeignClient(name = "itour-account-service")
public interface AccountApi {
@RequestMapping(value = "/hello",produces = {"application/json;charset=UTF-8"})
public String hello();
}
