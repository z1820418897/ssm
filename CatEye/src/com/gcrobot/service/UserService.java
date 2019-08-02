package com.gcrobot.service;

import java.util.List;

import com.gcrobot.bean.User;

public interface UserService {
	
	// 业务层 用户的注册
	public User addUser(User user);
	
	//业务层  校验用户名是否重复
	public Integer findUsersCountByUserName(String userName);

	//业务层  校验密码
	public String findPassword(Integer userId);

	//修改用户密码
	public Integer editPassword(Integer userId, String newpassword);

	//根据账号查询用户绑定的email
	public User findEmailByUserName(String userName);

	//修改用户头像
	public void addUserHead(Integer userId, String headUrl);
	
	//修改用户信息  公共
	public void setUserInfo(User user);

	//绑定设备
	public void bindCat(Integer userId, Integer catId);
	
	//查询设备是否已经被绑定
	public int findBindCat(Integer userId, Integer catId);
	
	//查询数据库 根据设备id 查询用户
	public List<User> findUserByCatId(Integer catId);
	
}
