package com.gcrobot.bean;

import java.io.Serializable;

public class Test implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private int age;
	private String sex;
	private String address;
	private String nmb;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNmb() {
		return nmb;
	}
	public void setNmb(String nmb) {
		this.nmb = nmb;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
