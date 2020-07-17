package com.itour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.MessageinfoService;
import com.itour.service.ViewMMessageinfoService;
@RestController
@RequestMapping("/msg")
public class MessagInfoApiController implements MessageApi{
	@Autowired
	MessageinfoService messageinfoService;
	@Autowired
	ViewMMessageinfoService viewMMessageinfoService;
	@RequestMapping("/sendEmailCode")
	@Override
	public ResponseMessage sendEmailCode(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return messageinfoService.sendEmail(requestMessage);
	}
	@Override
	public ResponseMessage queryMessageList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return messageinfoService.queryMessageList(requestMessage);
	}
	@RequestMapping("/queryViewMessageList")
	@Override
	public ResponseMessage queryViewMessageList(@RequestBody RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return viewMMessageinfoService.queryViewMessageList(requestMessage);
	}

	
}
