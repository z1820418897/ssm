package com.zhc.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestTomcat {

	
	
	public static void main(String[] args) {
		
		System.out.println("---------------------------------------------------------------开始启动"+System.currentTimeMillis());
		
		List<MyThread> list=new ArrayList<>();
		
		for(int i=0;i<10000;i++) {
			
			MyThread thread=new MyThread();
			
			list.add(thread);
			
		}
		System.out.println("---------------------------------------------------------------创建完成"+System.currentTimeMillis());
		for(MyThread thread:list) {
			thread.start();
		}
		
		System.out.println("---------------------------------------------------------------启动完毕"+System.currentTimeMillis());
	}
	
}

