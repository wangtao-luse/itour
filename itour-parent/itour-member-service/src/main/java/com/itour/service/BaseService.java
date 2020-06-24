package com.itour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itour.persist.AccountMapper;
@Service
public class BaseService {
@Autowired
private AccountMapper accountMapper;
public String getUid() {
	int start=10000;
	Integer maxId = this.accountMapper.getMaxId();
	String uid="10000";
	if(null!=maxId) {
		Integer result = start+maxId;
		  uid = result.toString();	
	}
	return uid;
}
}
