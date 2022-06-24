package com.test.socket;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class TestServerSocket  {
	@Test
public void testServerSocket() {
	TestServerSocket t = new TestServerSocket();
	t.initServerSocket();
}
public static void main(String[] args) {
	TestServerSocket t = new TestServerSocket();
	t.initServerSocket();
}
	
public  void initServerSocket() {
	try {
		// 初始化服务端socket并且绑定9999端口		 
	    int port = 8080;
		int backlog = 10;
		InetAddress bindAddr = InetAddress.getByName("1.116.226.147");
		ServerSocket serverSocket  =new ServerSocket(port, backlog, bindAddr );
	    
	    //等待客户端的连接
	    Socket socket = serverSocket.accept();
	    System.out.println("等待客户端的连接");
	   
	} catch (Exception e) {
		// TODO: handle exception
	}
	
}


public void initClientSocket() {
	try {
		Socket socket =new Socket("127.0.0.1",9999);
		 
        BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String str="你好，这是我的第一个socket";

        bufferedWriter.write(str);

	} catch (Exception e) {
		// TODO: handle exception
	}
}
}
