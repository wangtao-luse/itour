package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.itour.api.MessageApi;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.service.ToEmailService;
@RestController
public class MessagApiService implements MessageApi{
	@Autowired
	ToEmailService toEmailService;
	@Override
	public ResponseMessage sendCode(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return toEmailService.sendCode(requestMessage);
	}

	
}
