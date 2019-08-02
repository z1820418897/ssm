package com.gcrobot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.gcrobot.bean.User;
import com.gcrobot.service.RedisUserService;

@Service
public class RedisUserServiceImpl implements RedisUserService{
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	
	@Override
	public void reidsAddUser(User user) {
		
		Map<String,String> map=new HashMap<>();
		//标示是设备还是用户
		map.put("userId",user.getUserId().toString());
		map.put("userName",user.getUserName());
		map.put("PassWord",user.getPassWord());
		
		
		System.out.println(map);
		
		try {
			redisTemplate.opsForHash().putAll("user_"+user.getUserId().toString(),map);
			System.out.println("log:缓存key为user_"+user.getUserId()+"的用户");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public String findPassword(Integer userId) {
		
		try {
			Map<?, ?> keyValMap=redisTemplate.opsForHash().entries("user_"+userId);
			if(keyValMap.isEmpty()) {
				return null;
			}
			return (String)keyValMap.get("PassWord");
		} catch (Exception e) {
			e.printStackTrace();	
			return null;
		}
	}
	
	@Override
	public void editPassword(Integer userId,String password) {
		
		redisTemplate.opsForHash().put("user_"+userId,"PassWord",password);
		System.out.println(userId+"重新设置password"+password);	
		
	}


	@Override
	public void addCode(String acc,String Code, int date) {
		
		redisTemplate.opsForValue().set("code-"+acc,Code,60*date,TimeUnit.SECONDS);
		
	}

	@Override
	public String getCode(String email) {
		try {
			String code=(String)redisTemplate.opsForValue().get("code-"+email);
			return code;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public User getUserInfo(Integer userId) {
			
		Map<?, ?> keyValMap=redisTemplate.opsForHash().entries("user_"+userId);
	
		if(keyValMap.isEmpty()) {
			return null;
		}else {
			User user = new User();
			if(keyValMap.containsKey("email")) {
				user.setEmail(keyValMap.get("email").toString());
			}else {
				user.setEmail("");
			}
			
			if(keyValMap.containsKey("userId")) {
				user.setUserId(Integer.parseInt((String)keyValMap.get("userId")));
			}else {
				user.setUserId(0);
			}
			
			if(keyValMap.containsKey("userName")) {
				user.setUserName(keyValMap.get("userName").toString());
			}else {
				user.setUserName("");
			}
			
			if(keyValMap.containsKey("PassWord")) {
				user.setPassWord(keyValMap.get("PassWord").toString());
			}else {
				user.setPassWord("");
			}
			
			if(keyValMap.containsKey("headUrl")) {
				user.setHeadUrl(keyValMap.get("headUrl").toString());
			}else {
				user.setHeadUrl("");
			}
			
			if(keyValMap.containsKey("nickName")) {
				user.setNickName(keyValMap.get("nickName").toString());
			}else {
				user.setNickName("");
			}
			
			return user;
		}
		
		
	}


	@Override
	public void setUserInfo(User user) {
		
		if(user.getNickName()!="" && user.getNickName()!=null) {
			redisTemplate.opsForHash().put("user_"+user.getUserId(),"nickName",user.getNickName());
		}
		if(user.getEmail()!="" && user.getEmail()!=null) {
			redisTemplate.opsForHash().put("user_"+user.getUserId(),"email",user.getEmail());
		}
		if(user.getHeadUrl()!="" && user.getHeadUrl()!=null) {
			redisTemplate.opsForHash().put("user_"+user.getUserId(),"headUrl",user.getHeadUrl());
		}
		
	}


	@Override
	public void bindCat(Integer userId, Integer catId) {
		// u_1 d_1
		
		redisTemplate.boundSetOps("u_"+userId).add(catId.toString());
		redisTemplate.boundSetOps("d_"+catId).add(userId.toString());
		
	}


	@Override
	public void setShareId(String key, String value) {
		
		redisTemplate.opsForValue().set(key, value,180,TimeUnit.SECONDS);
		
	}


	@Override
	public String getShareId(String key) {


		
		return (String) redisTemplate.opsForValue().get(key);
	}


	@Override
	public boolean findBindCat(Integer userId, Integer catId) {
		
		if(redisTemplate.boundSetOps("u_"+userId).isMember(catId.toString())||redisTemplate.boundSetOps("d_"+catId).isMember(userId.toString())) {
			return true;
		}
		
		return false;
	}


	@Override
	public Set<String> findUserIdByCatId(Integer catId) {
		
		Set<String> list = redisTemplate.boundSetOps("d"+catId).members();
		
		return list;
	}	
	
	
}
