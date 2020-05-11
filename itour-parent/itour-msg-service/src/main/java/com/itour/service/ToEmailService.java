package com.itour.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.alibaba.fastjson.JSONObject;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.exception.BaseException;
import com.itour.model.msg.EmailMessage;

public class ToEmailService {
public ResponseMessage toEmail(RequestMessage requestMessage) throws MessagingException {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		EmailMessage emailMessage = jsonObject.getJSONObject("vo").toJavaObject(EmailMessage.class);
		//发邮件次数有限制
		//https://blog.csdn.net/qq_41833449/article/details/88822591
	           Properties props = new Properties();
	           props.setProperty("mail.smtp.auth", "true");
	           props.setProperty("mail.transport.protocol", "smtp");
	           props.put("mail.smtp.host","smtp.163.com");// smtp服务器地址
	           
	           Session session = Session.getInstance(props);
	           session.setDebug(true);
	           
	           Message msg = new MimeMessage(session);
	           msg.setSubject("这是一个测试程序....");
	           msg.setText("你好!这是我的第一个javamail程序---WQ");
	           msg.setFrom(new InternetAddress("wwangtaoc11@163.com"));//发件人邮箱(我的163邮箱)
	           msg.setRecipient(Message.RecipientType.TO,
	                   new InternetAddress("3398660467@qq.com")); //收件人邮箱(我的QQ邮箱)
	           msg.saveChanges();

	           Transport transport = session.getTransport();           
	           transport.connect("wwangtaoc11@163.com","VAKMXBHEUSDJWCDH");//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)
	           
	           transport.sendMessage(msg, msg.getAllRecipients());
	           
	           System.out.println("邮件发送成功...");
	           transport.close();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	
	return responseMessage;
}
}
