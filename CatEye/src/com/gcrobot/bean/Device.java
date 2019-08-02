package com.gcrobot.bean;

import java.io.Serializable;

public class Device implements Serializable{
	
	private Integer deviceId;
	private String SN;
	private Integer state;
	
	private String deviceName;
	
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getSN() {
		return SN;
	}
	public void setSN(String sN) {
		SN = sN;
	}
	
	
}
