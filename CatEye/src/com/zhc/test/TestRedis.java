package com.zhc.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import com.gcrobot.bean.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

public class TestRedis {

	public static void main(String[] args) {

		/*
		 * Jedis jedis=new Jedis("10.10.2.176",6379); jedis.auth("123456");//如果需要密码
		 * 
		 * int i=0;//记录操作次数
		 * 
		 * try { long start = System.currentTimeMillis();//开始毫秒数 while(true) { long end
		 * = System.currentTimeMillis(); if(end-start>1000) { break; } i++;
		 * jedis.set("zhenghuachen"+i, i+""); } } finally { jedis.close(); }
		 * System.out.println("redis每秒操作："+i+"次");
		 */

		// 测试读写操作
		/*
		 * JedisPoolConfig pc=new JedisPoolConfig(); pc.setMaxIdle(50);
		 * pc.setMaxTotal(100); pc.setMaxWaitMillis(20000);
		 * 
		 * JedisPool pool=new JedisPool(pc,"10.10.2.176"); Jedis jedis =
		 * pool.getResource(); jedis.auth("123456");
		 * 
		 * long start = System.currentTimeMillis(); //创建流水线 Pipeline pipelined =
		 * jedis.pipelined();
		 * 
		 * 
		 * for(int i=0;i<100000;i++) { int j=i+1; //pipelined.set("key"+i, "value"+i);
		 * //pipelined.get("key"+i); pipelined.del("key"+i); }
		 * 
		 * List list = pipelined.syncAndReturnAll();
		 * 
		 * long end = System.currentTimeMillis();
		 * 
		 * 
		 * System.out.println("消耗时间"+(end-start));
		 */

		/*
		 * ApplicationContext applicationContext=new
		 * ClassPathXmlApplicationContext("applicationContext-redis.xml");
		 * 
		 * RedisTemplate redisTemplate=applicationContext.getBean(RedisTemplate.class);
		 * 
		 * Test test=new Test(); test.setId(10); test.setAge(18); test.setAddress("上海");
		 * 
		 * //使用连接池中同一个连接操作 SessionCallback callback=new SessionCallback<Test>() {
		 * 
		 * @Override public Test execute(RedisOperations ops) throws DataAccessException
		 * { ops.boundValueOps("test1").set(test);
		 * 
		 * return (Test)ops.boundValueOps("test1").get(); } }; //不保证使用的是连接池里面的同一个连接
		 * redisTemplate.opsForValue().se t("test1", test); Test
		 * tt=(Test)redisTemplate.opsForValue().get("test1");
		 * 
		 * //使用事务 jdk1.8的Lambda表达式 SessionCallback
		 * callback=(SessionCallback)(RedisOperations ops)->{ //开启事务 ops.multi();
		 * 
		 * ops.boundValueOps("test2").set("郑华晨1"); String
		 * t1=(String)ops.boundValueOps("test2").get();
		 * System.out.println("还没有提交事务 方法根本还没有执行 也没有访问redis  所以test2="+t1);
		 * 
		 * //提交事务 List list = ops.exec();
		 * 
		 * //提交完后 在获取 String t2=(String)ops.boundValueOps("test2").get();
		 * 
		 * return t2; };
		 * 
		 * 
		 * 
		 * //Test tt=(Test)redisTemplate.execute(callback); String
		 * ss=(String)redisTemplate.execute(callback); System.out.println(ss);
		 */
		
		//流水线操作hash
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-redis.xml");

		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);

		// 使用事务 jdk1.8的Lambda表达式
		SessionCallback callback = (SessionCallback) (RedisOperations ops) -> {
			
			
			for(int i=0;i<100000;i++) {
				int j=i+1;
				Test test=new Test();
				test.setAddress("上海"+j);
				test.setAge(18);
				test.setId(j);
				Map<String, String> map=new HashMap<>();
				map.put("address", test.getAddress());
				map.put("age", test.getAge()+"");
				map.put("id", test.getId()+"");
				
				BoundHashOperations boundHashOperations = ops.boundHashOps("id"+j);
				
				
				//boundHashOperations.putAll(map);
				
				//List<String> values = boundHashOperations.values();
				//ops.boundValueOps("key"+j).set("value"+j);
				//ops.boundValueOps("key"+j).get();
				//ops.delete("key"+j);
				
				boundHashOperations.delete("address","age","id");
				//boundHashOperations.entries();
				
			}
			
			return null;
		};

		long start = System.currentTimeMillis();
		List<Map> list=redisTemplate.executePipelined(callback);
		/*List list = new ArrayList<>(Arrays.asList("address","age","id"));
		List aa=redisTemplate.opsForHash().multiGet("id1", list);*/
		long end=System.currentTimeMillis();
		System.out.println(list);
		System.out.println("流水线消耗了"+(end-start));
	}
}
