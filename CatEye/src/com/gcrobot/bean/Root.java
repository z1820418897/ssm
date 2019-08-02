package com.gcrobot.bean;

import java.io.Serializable;

public class Root implements Serializable{
	private Integer rootId;//int(11)
	private String rootName;//varchar(20)
	private String password;//varchar(20)
	
	public Integer getRootId() {
		return rootId;
	}
	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}
	public String getRootName() {
		return rootName;
	}
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
