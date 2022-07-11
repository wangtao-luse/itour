package com.itour.service;

import com.itour.common.req.RequestMessage;
import com.itour.common.resp.ResponseMessage;
import com.itour.constant.Constant;
import com.itour.constant.ConstantMessage;
import com.itour.exception.BaseException;
import com.itour.model.quartz.Messageinfo;
import com.itour.persist.MessageinfoMapper;
import com.itour.service.MessageinfoService;
import com.itour.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2021-05-08
 */
@Service
public class MessageinfoService extends ServiceImpl<MessageinfoMapper, Messageinfo> {
	private final static Logger logger=LoggerFactory.getLogger(MessageinfoService.class);
	/**
	 * 发送email
	 * @param email
	 * @return	 */
@Transactional
public ResponseMessage sendEmail(RequestMessage requestMessage){
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Messageinfo email = jsonObject.getJSONObject("vo").toJavaObject(Messageinfo.class);
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
	           msg.setFrom(new InternetAddress("wwangtaoc11@163.com"));//发件人邮箱
	           msg.setRecipient(Message.RecipientType.TO,
	                   new InternetAddress(email.getTo())); //收件人邮箱
	           msg.saveChanges();

	           Transport transport = session.getTransport();           
	           transport.connect("wwangtaoc11@163.com","VAKMXBHEUSDJWCDH");//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)	           
	           transport.sendMessage(msg, msg.getAllRecipients());	           
	           transport.close();
	           Messageinfo msgText = new Messageinfo();
	           BeanUtils.copyProperties(msgText, email);
	           msgText.setSendtime(DateUtil.currentLongDate());
	           msgText.setType(ConstantMessage.EMAIL);
	           msgText.setFrom("wwangtaoc11@163.com");
	           this.baseMapper.insert(msgText);
	           responseMessage.setReturnResult(email);
	}catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	} 
	return responseMessage;
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
		           logger.info("邮件发送成功...");
		           transport.close();
}
/**
 * 查看发送的消息列表
 * @param requestMessage
 * @return
 */
public ResponseMessage queryMessageList(RequestMessage requestMessage) {
	ResponseMessage responseMessage = ResponseMessage.getSucess();
	try {
		JSONObject jsonObject = requestMessage.getBody().getContent();
		Messageinfo msgVo = jsonObject.getJSONObject("msg").toJavaObject(Messageinfo.class);
		JSONObject pageVo = jsonObject.getJSONObject("page");
		QueryWrapper<Messageinfo> queryWrapper = new QueryWrapper<Messageinfo>();
		queryWrapper.eq(!StringUtils.isEmpty(msgVo.getType()),"TYPE", msgVo.getType());
		if(null!=pageVo) {	
			Page page = pageVo.toJavaObject(Page.class);
			Page selectPage = this.baseMapper.selectPage(page, queryWrapper );
			responseMessage.setReturnResult(selectPage);
		}else {
			List selectList = this.baseMapper.selectList(queryWrapper);
			responseMessage.setReturnResult(selectList);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new BaseException(Constant.FAILED_SYSTEM_ERROR);
	}
	return responseMessage;
}
}
