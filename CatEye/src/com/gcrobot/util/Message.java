package com.gcrobot.util;

import java.io.Serializable;

public class Message implements Serializable{
	
	private boolean is;//请求结果
	private int msgtype;//结果类型  200 正确
	private String msg;//结果消息
	private Object obj;//携带数据
	
	public boolean isIs() {
		return is;
	}
	public int getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(int msgtype) {
		this.msgtype = msgtype;
	}
	public void setIs(boolean is) {
		this.is = is;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public static Message UnloginMessage() {
		Message msg=new Message();
		msg.is=false;
		msg.msgtype=500;
		msg.msg="请先登录";
		return msg;
	}
	
	public static Message ParamError() {
		Message msg=new Message();
		msg.is=false;
		msg.msgtype=302;
		msg.msg="请求格式错误";
		
		return msg;
	}
}
