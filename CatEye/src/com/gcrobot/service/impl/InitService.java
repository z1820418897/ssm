package com.gcrobot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;


@Service
public class InitService implements InitializingBean{

	private static Logger logger = LoggerFactory.getLogger(InitService.class);
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.warn("服务器启动");
		
	}
	
	

}
