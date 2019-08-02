package com.gcrobot.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.gcrobot.bean.Device;
import com.gcrobot.controller.VideoController;
import com.gcrobot.service.RedisDeviceService;

@Service
public class RedisDeviceServiceImpl implements RedisDeviceService{

	private static Logger logger = LoggerFactory.getLogger(VideoController.class);
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public void reidsAddCateye(Device cateye) {
		
		Map<String,String> map=new HashMap<>();
		//标示是设备还是用户
		map.put("deviceId",cateye.getDeviceId().toString());
		map.put("SN", cateye.getSN());
		map.put("state",cateye.getState().toString());
		map.put("deviceName",cateye.getDeviceName());
		System.out.println(map);
		
		try {
			redisTemplate.opsForHash().putAll("device_"+cateye.getDeviceId(),map);
			logger.info("log:缓存key为"+"device_"+cateye.getDeviceId()+"的设备");
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		}
	}
	
	@Override
	public void reidsEditCatName(Integer catId, String catName) {
		
		redisTemplate.opsForHash().put("device_"+catId, "deviceName", catName);
			
	}

	@Override
	public void redisDeleteCat(String[] arr) {
		
		//删除设备  还要删除设别的绑定 视频等资源       暂定功能不开启
		for(String id:arr) {
			redisTemplate.opsForHash().delete("device_"+id,"deviceName","SN","state","deviceId");
			logger.info("删除设备device"+id);
		}
		
	}
	
}
