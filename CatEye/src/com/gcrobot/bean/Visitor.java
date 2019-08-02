package com.gcrobot.bean;

import java.io.Serializable;
import java.util.Date;

public class Visitor implements Serializable{
	
	private Integer visitorId;//int(10)
	private Integer deviceId;//int(10)
	private String fileName;//varchar(100)
	private Date fileTime;//date
	
	
	public Integer getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(Integer visitorId) {
		this.visitorId = visitorId;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getFileTime() {
		return fileTime;
	}
	public void setFileTime(Date date) {
		this.fileTime = date;
	}
	@Override
	public String toString() {
		return "Visitor [visitorId=" + visitorId + ", deviceId=" + deviceId + ", fileName=" + fileName + ", fileTime="
				+ fileTime + "]";
	}
	
	
	
	
	
}
