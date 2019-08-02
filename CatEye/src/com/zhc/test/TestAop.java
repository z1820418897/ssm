package com.zhc.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

public class TestAop implements tt{

	public String aa() {
		System.out.println("aa执行了");
		return "我是aa";
	}
	
}
