package com.gcrobot.util;

import java.io.Serializable;

public class Email implements Serializable{
	public static final String CODEDFORMAT="UTF-8";//编码格式
	private String host;//服务器
	private String sender; // 发件人的邮箱
	private String receiver; // 收件人的邮箱
	private String name; // 发件人昵称
	private String username; // 发件账号
	private String password; // 发件密码
	private String title; // 标题
	private String msg; // 内容(支持HTML)
	  
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}


	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}


	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
}
