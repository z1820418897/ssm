package com.gcrobot.util;

import java.io.Serializable;
import java.util.List;

public class LayuiDataGrid implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;
	private String msg;
	private int count;
	private List data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
