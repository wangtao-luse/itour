package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.EmailService;
@RestController
@RequestMapping("/msg")
public class MessagInfoApiService implements MessageApi{
	@Autowired
	EmailService emailService;
	@RequestMapping("sendEmailCode")
	@Override
	public ResponseMessage sendEmailCode(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return emailService.sendEmail(requestMessage);
	}

	
}
