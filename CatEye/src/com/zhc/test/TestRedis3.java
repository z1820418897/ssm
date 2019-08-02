package com.zhc.test;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisZSetCommands.Limit;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

public class TestRedis3 {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-redis.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		
		
		/*Set<TypedTuple> set1=new HashSet<TypedTuple>();
		Set<TypedTuple> set2=new HashSet<TypedTuple>();
		
		int j=9;
		for(int i=1;i<=9;i++) {
			j--;
			Double score1=Double.valueOf(i);
			String value1="x"+i;
			
			Double score2=Double.valueOf(j);
			String value2=j%2==1?"y"+j:"x"+j;
			
			TypedTuple typedTuple1=new DefaultTypedTuple(value1,score1);
			set1.add(typedTuple1);
			
			TypedTuple typedTuple2=new DefaultTypedTuple(value2,score2);
			set1.add(typedTuple2);
			
		}
		redisTemplate.opsForZSet().add("zset1",set1);
		redisTemplate.opsForZSet().add("zset2",set2);*/
		
		
		
		
		Long size=null;
		
		size=redisTemplate.opsForZSet().zCard("zset1");
		System.out.println(size);
		size=redisTemplate.opsForZSet().count("zset1",0, 6);
		System.out.println(size);
		
		Set set=null;
		set=redisTemplate.opsForZSet().range("zset1", 1, 5);
		
		Range range=new Range();
		Limit limit=new Limit();
		limit.count(3);
		limit.offset(4);
		
		set=redisTemplate.opsForZSet().rangeByLex("zs2",range,limit);
		System.out.println(set);
	}
	
}
