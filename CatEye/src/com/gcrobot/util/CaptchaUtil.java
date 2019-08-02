package com.gcrobot.util;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;
import java.util.Map;

public class CaptchaUtil {

	private static int appid = 1400186073; 
	private static String appkey = "627671d2776c75089234080a6aaa9a04";
	private static int templateId = 278725;
	private static String smsSign ="杭州国辰机器人科技";

	
	public static SmsSingleSenderResult sendCaptcha(String tel, String code,String time) throws JSONException, HTTPException, IOException{
		
		 String[] params = {code,time};
		 SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
		 SmsSingleSenderResult result = ssender.sendWithParam("86", tel, templateId, params, smsSign,"","");
		 
		return result;
		 
		 
	}

}
