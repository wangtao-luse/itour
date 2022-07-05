package com.itour.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
//https://www.icode9.com/content-4-825669.html
public class SftpUtil {

	private static String privateKey;
	private static String userName = "root";
	private static String host = "139.224.42.237";
	private static int port = 22;
	private static String password = "YuGu9512";
	private static ChannelSftp sftp;
static {
	Session session = null;
	try {
		 JSch jsch = new JSch();
         if (privateKey != null) {
             jsch.addIdentity(privateKey);// 设置私钥
         }
          session = jsch.getSession(userName, host, port);
         if (password != null) {
             session.setPassword(password);
         }
         Properties config = new Properties();
         config.put("StrictHostKeyChecking", "no");
         session.setConfig(config);
         session.connect();
         Channel channel = session.openChannel("sftp");
         channel.connect();
         sftp = (ChannelSftp) channel;
        
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		session.disconnect();
	}
}
	public static void main(String[] args) throws JSchException, FileNotFoundException, SftpException {
		
		
	}
}
