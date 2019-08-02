package com.zhc.test;

import java.net.Socket;

public class TestSocker {
public static void main(String[] args) {
	String ip="10.10.2.76";
	int port=23180;
	
	try {
		Socket socket=new Socket(ip,port);
		
	} catch (Exception e) {
		e.printStackTrace();
	} 
	
}
}
