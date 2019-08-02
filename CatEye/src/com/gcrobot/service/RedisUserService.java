package com.gcrobot.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gcrobot.bean.User;

public interface RedisUserService {
	
	//添加用户
	public void reidsAddUser(User user);
	
	//修改用户密码----之查询用户
	public String findPassword(Integer userId);

	//修改user密码
	public void editPassword(Integer userId, String password);
	
	//缓存验证码
	public void addCode(String acc,String Code,int date);
	
	//获取验证码
	public String getCode(String email);

	//查询用户信息
	public User getUserInfo(Integer userId);

	//修改用户信息
	public void setUserInfo(User user);
	
	//绑定设备
	public void bindCat(Integer userId, Integer catId);
	
	//保存分享id
	public void setShareId(String key, String value);
	//获取分享id
	public String getShareId(String key);
	//查询分享的设备是否已经绑定
	public boolean findBindCat(Integer userId, Integer catId);
	
	//根据设备id查询其下的设备
	public Set<String> findUserIdByCatId(Integer catId);
	
}
