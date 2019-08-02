package com.zhc.test;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import com.gcrobot.util.CaptchaUtil;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

public class SendMsgDemo {

	@Test
	public void testSendMsg() {
		System.out.println("java短信验证码测试接口开始运行");
		
		try {
			SmsSingleSenderResult sendCaptcha = CaptchaUtil.sendCaptcha("15941660170", "1234","五十二亿五千六百万");
			System.out.println("发送结果："+sendCaptcha);
			
			
		} catch (JSONException | HTTPException | IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
