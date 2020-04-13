package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itour.model.Contract;
import com.itour.persist.ContractMapper;

@Controller
public class AccountApiService {
	@Autowired
	ContractMapper contractMapper;
	@RequestMapping("/list")
	@ResponseBody
	public Contract getList() {
		Contract queryById = contractMapper.queryById(177L);
		return queryById;
		
	}

}
