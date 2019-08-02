package com.gcrobot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcrobot.bean.Version;
import com.gcrobot.util.Message;
import com.sun.corba.se.impl.ior.ByteBuffer;

@Controller
@RequestMapping("Version")
public class VersionController {

	private Logger logger = LoggerFactory.getLogger(CatEyeController.class);
	
	/**
	 * app查询最新的版本号
	 * */
	@RequestMapping("findVersion")
	@ResponseBody
	public Message findVersion() {
		logger.info("findVersion被访问");
		Message message = new Message();
		
		Version version=new Version();
		version.setVersionId(2);
		version.setVersionCode(2);
		version.setVersionName("1.2");
		version.setFileName("1.apk");
		
		message.setObj(version);
		message.setIs(true);
		message.setMsg("查询成功");	
		message.setMsgtype(200);
		
		return message;
	}
	
	/**
	 * 设备端查询最新的版本号
	 * */
	@RequestMapping("findDeviceVersion")
	@ResponseBody
	public Message findDeviceVersion() {
		
		Message message = new Message();
		
		Version version=new Version();
		version.setVersionId(2);
		version.setVersionCode(2);
		version.setVersionName("1.2");
		
		message.setObj(version);
		message.setIs(true);
		message.setMsg("查询成功");	
		message.setMsgtype(200);
		
		return message;
	}
	
	
}
