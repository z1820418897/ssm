package com.gcrobot.util;

import java.io.Serializable;
import java.util.List;

public class MyJsonList implements Serializable{

	private List Data;
	private boolean ishas;
	
	@Override
	public String toString() {
		return "MyJsonList [Data=" + Data + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public List getData() {
		return Data;
	}

	public void setData(List data) {
		Data = data;
	}
	
	public boolean isIshas() {
		return ishas;
	}

	public void setIshas(boolean ishas) {
		this.ishas = ishas;
	}
}
