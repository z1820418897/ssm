package com.gcrobot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcrobot.bean.User;
import com.gcrobot.mapper.UserMapper;
import com.gcrobot.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	//业务层   用户的注册
	@Override
	public User addUser(User user) {
		 
		try {
			userMapper.addUser(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return user;
	}

	
	//业务层  用户名校验是否重复
	@Override
	public Integer findUsersCountByUserName(String userName) {
		
		Integer count = userMapper.findUsersCountByUserName(userName);
		
		return count;
	}


	//业务层 校验密码
	@Override
	public String findPassword(Integer userId) {
		String password=userMapper.findPassword(userId);
		
		return password;
	}


	@Override
	public Integer editPassword(Integer userId, String newpassword) {
		
		Integer is=userMapper.editPassword(userId,newpassword);
		
		return is;
	}


	@Override
	public User findEmailByUserName(String userName) {
		
		User username=userMapper.findEmailByUserName(userName);
		
		
		return username;
	}

	
	@Override
	public void addUserHead(Integer userId, String headUrl) {
		
		userMapper.addUserHead(userId,headUrl);
		
	}


	@Override
	public void setUserInfo(User user) {
		
		userMapper.setUserInfo(user);
		
		
	}


	@Override
	public void bindCat(Integer userId, Integer catId) {
		// TODO Auto-generated method stub
		userMapper.bindCat(userId,catId);
	}


	@Override
	public int findBindCat(Integer userId, Integer catId) {
		
		int count=userMapper.findBindCat(userId,catId);
		
		return count;
	}


	@Override
	public List<User> findUserByCatId(Integer catId) {
		
		List<User> list=userMapper.findUserByCatId(catId);
		
		
		return list;
	}

}
