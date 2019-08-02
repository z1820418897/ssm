package com.gcrobot.bean;

import java.io.Serializable;
import java.util.Date;

public class Video implements Serializable{

	private Integer videoId;
	private Integer deviceId;
	private String videoName;
	private Date videoTime;
	private String img;
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public Date getVideoTime() {
		return videoTime;
	}
	public void setVideoTime(Date videoTime) {
		this.videoTime = videoTime;
	}
	
	
	 
}
