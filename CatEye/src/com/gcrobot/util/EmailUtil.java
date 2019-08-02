package com.gcrobot.util;

import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
		
	
	//发送邮箱验证码
	public static boolean send(Email email) {
		 HtmlEmail emailUtil = new HtmlEmail();
		 try {
		      // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
			 emailUtil.setHostName(email.getHost());
		      // 字符编码集的设置
			 emailUtil.setCharset(email.CODEDFORMAT);
		      // 收件人的邮箱
			 emailUtil.addTo(email.getReceiver());
		      // 发送人的邮箱
			 emailUtil.setFrom(email.getSender(), email.getName());
		      // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
			 emailUtil.setAuthentication(email.getUsername(), email.getPassword());
		      // 要发送的邮件主题
			 emailUtil.setSubject(email.getTitle());
		      // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
			 emailUtil.setMsg(email.getMsg());
		      // 发送
			 emailUtil.send();
			 
		     return true;
		    } catch (Exception e) {
		      e.printStackTrace();
		      return false;
		    }
				
	}
	
}
