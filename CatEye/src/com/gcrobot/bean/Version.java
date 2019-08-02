package com.gcrobot.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Version implements Serializable{
	private Integer versionId;//int(10)
	private Integer versionCode;//int(10)
	private String versionName;//varchar(30)
	private String fileName;
	private String des;
	private Date time;
	
	private String stringTime;
	
	public String getStringTime() {
		return stringTime;
	}
	
	public void setStringTime(String stringTime) {
		this.stringTime = stringTime;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public Integer getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}
	
	public String getVersionName() {
		return versionName;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
	
}
