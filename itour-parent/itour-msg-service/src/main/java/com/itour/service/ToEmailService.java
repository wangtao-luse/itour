package com.itour.service;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ConstantMessage;
import com.itour.exception.BaseException;
import com.itour.model.msg.MessageText;
import com.itour.persist.MessageTextMapper;

public class ToEmailService  extends ServiceImpl<MessageTextMapper, MessageText> {
	/**
	 * 发送email
	 * @param email
	 * @return
	 */

private void toEmail(MessageText email){
	try {
		//发邮件次数有限制
		//https://blog.csdn.net/qq_41833449/article/details/88822591
	           Properties props = new Properties();
	           props.setProperty("mail.smtp.auth", "true");
	           props.setProperty("mail.transport.protocol", "smtp");
	           props.put("mail.smtp.host","smtp.163.com");// smtp服务器地址
	           
	           Session session = Session.getInstance(props);
	           session.setDebug(true);
	           
	           Message msg = new MimeMessage(session);
	           msg.setSubject(email.getSubject());
	           msg.setText(email.getText());
	           msg.setFrom(new InternetAddress(email.getFrom()));//发件人邮箱
	           msg.setRecipient(Message.RecipientType.TO,
	                   new InternetAddress(email.getTo())); //收件人邮箱
	           msg.saveChanges();

	           Transport transport = session.getTransport();           
	           transport.connect("wwangtaoc11@163.com","VAKMXBHEUSDJWCDH");//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)	           
	           transport.sendMessage(msg, msg.getAllRecipients());	           
	           transport.close();
	           MessageText msgText = new MessageText();
	           BeanUtils.copyProperties(msgText, email);
	           msgText.setType(ConstantMessage.EMAIL);	           
	           this.baseMapper.insert(msgText);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	} 
	
}
/**
 * 发送email验证码
 * @param requestMessage
 * @return
 */
@Transactional
public ResponseMessage sendCode(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		MessageText messageText = jsonObject.getJSONObject("vo").toJavaObject(MessageText.class);
		String aim = messageText.getAim();
		Random r = new Random();
	    int nextInt = r.nextInt(999999)+100000;    
	    String sendCode = String.valueOf(nextInt);
	    String text =ConstantMessage.MSGPREFIX;
	    if(ConstantMessage.REGCODE.equals(aim)) {
	    	text+=messageText.getAim()+":"+sendCode+","+ConstantMessage.MSGTIME;
	    }
	    messageText.setText(text);
		 this.toEmail(messageText);		
		responseMessage.setReturnResult(sendCode);
	} catch (BaseException e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(e.getMessage());
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	
	return null;
}
public static void main(String[] args) throws MessagingException {
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
}
}
