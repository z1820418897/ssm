package com.gcrobot.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class File implements Serializable{

	private Integer fileId;//int(10)
	private Integer deviceId;//int(10)
	private Integer type;//int(2)
	private String fileName;//varchar(30)
	private Date fileTime;//varchar(30)
	private String img;//varchar(30)
	
	private List<String> photos;
	
	public Integer getFileId() {
		return fileId;
	}
	public List<String> getPhotos() {
		return photos;
	}
	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public void setFileTime(Date fileTime) {
		this.fileTime = fileTime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	
	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", deviceId=" + deviceId + ", type=" + type + ", fileName=" + fileName
				+ ", fileTime=" + fileTime + ", img=" + img + "]";
	}
	
	
}
