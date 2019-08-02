package com.gcrobot.service;


import com.gcrobot.bean.Device;

public interface RedisDeviceService {
	
	//添加设备
	public void reidsAddCateye(Device cateye);

	//修改设备昵称
	public void reidsEditCatName(Integer catId, String catName);
	
	//删除设备
	public void redisDeleteCat(String[] arr);
	
	
}
