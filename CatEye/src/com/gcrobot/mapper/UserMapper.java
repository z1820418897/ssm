package com.gcrobot.mapper;

import java.util.List;

import com.gcrobot.bean.User;

public interface UserMapper {
	//持久层   用户的注册
	public void addUser(User user);
	//持久层  注册时校验用户名是否重复
	public Integer findUsersCountByUserName(String userName);
	//持久层  校验密码
	public String findPassword(Integer userId);
	//持久  修改密码
	public Integer editPassword(Integer userId, String newpassword);
	//持久层  根据账号查询email
	public User findEmailByUserName(String userName);
	//修改用户头像
	public void addUserHead(Integer userId, String headUrl);
	
	//修改用户表
	public void setUserInfo(User user);
	
	//绑定设备
	public void bindCat(Integer userId, Integer catId);
	//查询是否已经绑定
	public int findBindCat(Integer userId, Integer catId);
	//根据设别id查询用户
	public List<User> findUserByCatId(Integer catId);
}
