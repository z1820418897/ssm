package com.zhc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.gcrobot.bean.User;

public class TestRedis2 {
public static void main(String[] args) {
	
	
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-redis.xml");

	RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
	
	User user = new User();
	user.setUserId(1);
	user.setUserName("zzz");
	user.setPassWord("123");
	
	redisTemplate.opsForValue().set("jdktestuser",user);
	
	User str=(User)redisTemplate.opsForValue().get("jdktestuser");
	
	System.out.println(str.getUserName());
}
}
