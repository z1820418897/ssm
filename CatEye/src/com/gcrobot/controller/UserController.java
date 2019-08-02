package com.gcrobot.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gcrobot.bean.User;
import com.gcrobot.service.RedisUserService;
import com.gcrobot.service.UserService;
import com.gcrobot.util.CaptchaUtil;
import com.gcrobot.util.Email;
import com.gcrobot.util.EmailUtil;
import com.gcrobot.util.FileHelps;
import com.gcrobot.util.Message;
import com.github.qcloudsms.SmsSingleSenderResult;

@Controller
@RequestMapping(value="/User")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//@Value("${}")
	private String applog="C:/Users/admin/Desktop/file/applog";
	
	//@Value("${}")
	private String headUrl="C:/Users/admin/Desktop/file/head";
	
	@Value("${emailhost}")
	private String emailhost;
	@Value("${emailname}")
	private String emailname;
	@Value("${emailpassword}")
	private String emailpassword;
	
	@Autowired
	private UserService userService;

	@Autowired
	private RedisUserService redisTemplateService;
	
	//注册验证  弃用
	/*@RequestMapping("/emailCode")
	@ResponseBody
	public Message sendEmail(String url) {
		Message message = new Message();
		try {
			Random random=new Random();
			String code="";
			for(int i=0;i<6;i++) {
				code+=random.nextInt(10);
			}
			redisTemplateService.addCode(url, code, 5);
			logger.info("验证码缓存到redis");
			
			Email email=new Email();
			email.setHost(emailhost);
			email.setSender(emailname);
			email.setName(emailname);
			email.setUsername(emailname);
			email.setPassword(emailpassword);
			email.setReceiver(url);
			email.setTitle("国辰智能");
			email.setMsg("随机生成验证码："+code+"（注意：3分钟内有效）");
			
			boolean is = EmailUtil.send(email);
			
			if(is) {
				logger.info("邮箱验证码已经发送成功"+code);
				message.setIs(true);
				return message;
			}else {
				logger.info("邮箱验证码发送失败"+code);
				message.setIs(false);
				return message;
			}
		} catch (Exception e) {
			logger.info("邮箱验证码发送失败");
			message.setIs(false);
			return message;
		}
		
	}*/
	
	
//===========================================================================================================================	
	
	/**
	 * 短信验证码接口
	 * */
	@RequestMapping("/msgCode")
	@ResponseBody
	public Message  sendMsg(String url) {
		Message message = new Message();
		logger.info("sendMsg被访问");
		try {
			Random random=new Random();
			String code="";
			for(int i=0;i<6;i++) {
				code+=random.nextInt(10);
			}
			
			redisTemplateService.addCode(url,code,5);
			logger.info("验证码缓存到redis");
			
			SmsSingleSenderResult sendCaptcha = CaptchaUtil.sendCaptcha(url,code,"5");
			
			int result = sendCaptcha.result;
			
			if(result==0) {
				
				logger.info("短信验证码已经发送成功"+code);
				message.setIs(true);
				return message;
			}else {
				
				logger.info("短信验证码已经发送失败，错误"+result);
				message.setIs(false);
				return message;
			}
			
		} catch (Exception e) {
			logger.info("短信验证码发送失败");
			message.setIs(false);
			return message;
		}
		
	}
	
	/**
	 * 忘记密码 验证账号是否存在接口
	 * */
	@RequestMapping(value="/findPhone")
	@ResponseBody
	public Message findPhone(String userName) {
		Message message = new Message();
		User user=userService.findEmailByUserName(userName);
		
		if(user==null||user.getUserId()<=0) {
			message.setIs(false);
			message.setMsg("该手机号没有被注册");
			return message;
		}
		
		
		try {
			Random random=new Random();
			String code="";
			for(int i=0;i<6;i++) {
				code+=random.nextInt(10);
			}
			System.out.println(System.currentTimeMillis());
			
			redisTemplateService.addCode(userName, code, 5);
			logger.info("验证码缓存到redis");
			
			
			
			System.out.println(System.currentTimeMillis());
			SmsSingleSenderResult sendCaptcha = CaptchaUtil.sendCaptcha(userName,code,"5");
			int result = sendCaptcha.result;
		
			System.out.println(System.currentTimeMillis());
			
			if(result==0) {
				
				logger.info("短信验证码已经发送成功"+code);
				message.setIs(true);
				message.setMsg("验证码成功发送");
				message.setObj(user);
				return message;
			}else {
				
				logger.info("短信验证码已经发送失败，错误代码"+result);
				message.setIs(false);
				message.setMsg("验证码发送失败");
				
				return message;
			}
		} catch (Exception e) {
			logger.info("验证码发送失败");
			message.setMsg("验证码发送失败");
			message.setIs(false);
			return message;
		}
		
	}	
	
	@RequestMapping(value="/forgetPasswordByPhone")
	@ResponseBody
	public Message forgetPasswordByPhone(String userName,String code,Integer userId,String password) {
		Message msg = new Message();
		
		String redisCode = redisTemplateService.getCode(userName);
		
		if(redisCode==null||!redisCode.equals(code)) {
			msg.setIs(false);
			msg.setMsgtype(204);
			msg.setMsg("验证码错误");
			return msg;
		}else {
			try {
				redisTemplateService.editPassword(userId, password);
				Integer is= userService.editPassword(userId,password);
				
				msg.setIs(true);
				msg.setMsg("重置密码成功");
				msg.setMsgtype(200);
			} catch (Exception e) {
				
				e.printStackTrace();
				msg.setIs(false);
				msg.setMsg("重置密码失败");
			}
			
			return msg;
			
		}
		
	}
	
	
	
	
//===========================================================================================================================	
	// 控制层 用户的注册
	/**
	 * 
	 * */
	@RequestMapping(value="addUser")
	@ResponseBody
	public Message addUser(@RequestParam(value="userName",defaultValue="") String userName, @RequestParam(value="passWord",defaultValue="") String passWord,@RequestParam(value="code",defaultValue="") String code) {
		logger.info("addUser访问");
		
		Message msg = new Message();
		if(userName.equals("")||passWord.equals("")||code.equals("")) {
			msg.setIs(false);
			msg.setMsgtype(203);
			msg.setMsg("请求格 式错误为空");
			return msg;
		}
	
			
		String redisCode = redisTemplateService.getCode(userName);
		if(redisCode==null||!redisCode.equals(code)) {
			msg.setIs(false);
			msg.setMsgtype(204);
			msg.setMsg("验证码错误，请重新输入");
			return msg;
		}
		
		try {
			
			Integer countByUserName = userService.findUsersCountByUserName(userName);
			if (countByUserName > 0) {
				msg.setIs(false);
				msg.setMsgtype(201);
				msg.setMsg("该用户名已经注册！");
				return msg;
			}
			
			User user = new User();
			user.setPassWord(passWord);
			user.setUserName(userName);
			
			
			// 插入数据库
			User addUser = userService.addUser(user);
			
			// 成功后插入redis 
			redisTemplateService.reidsAddUser(addUser);

			msg.setIs(true);
			msg.setMsgtype(200);
			msg.setMsg("注册成功");
			msg.setObj(addUser);
		} catch (Exception e) {
			msg.setIs(false);
			msg.setMsgtype(500);
			msg.setMsg("注册失败,稍后重试");
			
			e.printStackTrace();
		}
		
		System.out.println("log:注册账号" + userName);	
		
		return msg;
	}

	@RequestMapping(value="editUser",params = {"userId","oldpassword","newpassword"})
	@ResponseBody
	public Message eidtUser(@RequestParam(value="userId",defaultValue="0")Integer userId,@RequestParam("oldpassword")String oldpassword,@RequestParam("newpassword")String newpassword) {
		
		Message msg=new Message();
		
		try {
			String password = redisTemplateService.findPassword(userId);
			if(password==null||!password.equals(oldpassword)) {
				String userPwd=userService.findPassword(userId);
				if(userPwd==null){
					msg.setIs(false);
					msg.setMsg("用户不存在");
					msg.setMsgtype(500);
					return msg;
				}else if(!userPwd.equals(oldpassword)){
					msg.setIs(false);
					msg.setMsg("原始密码不正确，无法修改");
					msg.setMsgtype(500);
					return msg;
				}
			}
			
			
			redisTemplateService.editPassword(userId, newpassword);
			
			Integer is= userService.editPassword(userId,newpassword);
			
			msg.setIs(true);
			msg.setMsg("修改密码成功");
			msg.setMsgtype(200);
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setIs(false);
			msg.setMsg("网络不好，请稍后重新修改");
			msg.setMsgtype(500);
		}
		
		return msg;
		
	}
	
	@RequestMapping(value="/uploadLog")
	@ResponseBody
	public Message uploadLog(Integer userId,MultipartFile logFile) {
		logger.info("uploadLog被访问");
		Message message = new Message();
		FileHelps fileHelps = new FileHelps();
		
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd,HHmmssSSS");
			String formatStr =formatter.format(date);
			
			String ymd = formatStr.substring(0, formatStr.indexOf(","));
			String hmss = formatStr.substring(formatStr.indexOf(",")+1,formatStr.length());
			
			String filename = logFile.getOriginalFilename();
			String newFileName = "log"+filename.substring(filename.lastIndexOf("."));
			
			String newfilepath=userId+"/"+ymd;
			String dirpath=applog+"/"+newfilepath;
			
			File dir = new File(dirpath);
			if (fileHelps.isExist(dirpath)) {
				dir.mkdir();
			}
			
			File writeFile = new File(dirpath,newFileName);
			
			logFile.transferTo(writeFile);
			
			message.setIs(true);
			message.setMsg("收到文件");
			message.setMsgtype(200);
		} catch (Exception e) {
			e.printStackTrace();
			message.setIs(false);
			message.setMsg("网络状态不佳，请稍后重试");
			message.setMsgtype(500);
			logger.error("日志保存错误");
		}
		
		logger.info("uploadLog访问结束");
		return message;
		
	}
	
	//点击找回密码   验证账号，查询账号的email
	@RequestMapping(value="/findEmail")
	@ResponseBody
	public Message forgetPassword(String userName) {
		Message message = new Message();
		User user=userService.findEmailByUserName(userName);
		
		if(user==null||user.getUserId()<=0) {
			message.setIs(false);
			message.setMsg("该手机号没有被注册");
			return message;
		}
		
		
		try {
			Random random=new Random();
			String code="";
			for(int i=0;i<6;i++) {
				code+=random.nextInt(10);
			}
			System.out.println(System.currentTimeMillis());
			redisTemplateService.addCode(user.getEmail(), code, 3);
			logger.info("验证码缓存到redis");
			
			Email email=new Email();
			email.setHost(emailhost);
			email.setSender(emailname);
			email.setName(emailname);
			email.setUsername(emailname);
			email.setPassword(emailpassword);
			email.setReceiver(user.getEmail());
			email.setTitle("国辰智能");
			email.setMsg("随机生成验证码："+code+"（注意：3分钟内有效）");
			
			System.out.println(System.currentTimeMillis());
			boolean is = EmailUtil.send(email);
			System.out.println(System.currentTimeMillis());
			if(is) {
				logger.info("邮箱验证码已经发送成功"+code);
				message.setIs(true);
				message.setMsg("验证码发送成功");
				message.setObj(user);
				return message;
			}else {
				logger.info("邮箱验证码发送失败"+code);
				message.setIs(false);
				return message;
			}
		} catch (Exception e) {
			logger.info("邮箱验证码发送失败");
			message.setIs(false);
			return message;
		}
		
	}	
	
	@RequestMapping(value="/forgetPasswordByEmail")
	@ResponseBody
	public Message forgetPasswordByEmail(String email,String code,Integer userId,String password) {
		Message msg = new Message();
		
		String redisCode = redisTemplateService.getCode(email);
		if(redisCode==null||!redisCode.equals(code)) {
			msg.setIs(false);
			msg.setMsgtype(204);
			msg.setMsg("验证码错误，请输入正确的验证码");
			return msg;
		}else {
			try {
				redisTemplateService.editPassword(userId, password);
				Integer is= userService.editPassword(userId,password);
				
				msg.setIs(true);
				msg.setMsg("重置密码成功");
				msg.setMsgtype(200);
			} catch (Exception e) {
				
				e.printStackTrace();
				msg.setIs(false);
				msg.setMsg("重置密码失败");
			}
			
			return msg;
			
		}
		
	}
	
	/**--------------------------------------------------------------------------------------------------------------------*/
	//头像上传
	@RequestMapping(value="/uploadHead")
	@ResponseBody
	public Message uploadHead(Integer userId,MultipartFile head) {
		logger.info("uploadHead被访问");
		Message message = new Message();
		FileHelps fileHelps = new FileHelps();
		
		try {
			String filename = head.getOriginalFilename();
			//String newFileName = userId+filename.substring(filename.lastIndexOf("."));
			String newFileName = userId+System.currentTimeMillis()+".jpg";
			
			String dirpath=headUrl+"/"+userId;
			File dir = new File(dirpath);
			if (fileHelps.isExist(dirpath)) {
				dir.mkdir();
			}
			
			File writeFile = new File(dirpath,newFileName);
			
			head.transferTo(writeFile);
			
			String headUrl=userId+"/"+newFileName;
			
			userService.addUserHead(userId,headUrl);
			User user = new User();
			user.setUserId(userId);
			user.setHeadUrl(headUrl);
			
			redisTemplateService.setUserInfo(user);
			
			//---------------------------------------------------------------------------------------------------------------
			message.setIs(true);
			message.setMsg("上传 成功");
			message.setMsgtype(200);
			message.setObj(headUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
			message.setIs(false);
			message.setMsg("上传 失败");
			message.setMsgtype(500);
		}
		
		return  message;
	}
	
	@RequestMapping(value="/getUserInfo")
	@ResponseBody
	public Message getUserInfo(Integer userId) {
		Message message = new Message();
		try {
			
			User user=redisTemplateService.getUserInfo(userId);
			
			if(user==null) {
				logger.error("redis没有查到这个id，请去数据库确认，数据库存在说明未同步，数据库不存在说明，改用户被删除了");
			}
			//logger.info(user.getEmail()+"---"+user.getNickName());
			message.setIs(true);
			message.setMsg("查询成功");
			message.setMsgtype(200);
			message.setObj(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			message.setIs(false);
			message.setMsg("该用户缓存和数据库不同步，请更换测试账号");
			message.setMsgtype(500);
		}
		
		return message;
	}
	
	
	
	@RequestMapping(value="/setUserInfo")
	@ResponseBody
	public Message setUserInfo(User user,String code) {
		Message message = new Message();
		logger.info(user.toString()+code);
		if(code!=null&&code!="") {
			String redisCode=redisTemplateService.getCode(user.getEmail());
			if(!code.equals(redisCode)) {
				message.setIs(false);
				message.setMsg("验证码错误");
				return message;
			}
		}
		try {
			userService.setUserInfo(user);
			redisTemplateService.setUserInfo(user);
			message.setIs(true);
			message.setMsg("成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis错误");
			message.setIs(false);
			
		}
		return message;
	}
	
	
	
	
	
	
	
	
	/**
	 * 生成分享id
	 * */
	@RequestMapping(value="/configShare")
	@ResponseBody
	public Message configShare(Integer userId,Integer catId){
		
		Message message = new Message();
		
		try {
			
			if(userId!=null &&userId>0 &&catId!=null&&catId>0) {
				String shareId = UUID.randomUUID().toString().replaceAll("-","");
				
				String key="share_"+userId+"_"+catId;
				String value=shareId;
				redisTemplateService.setShareId(key,value);
				
				message.setIs(true);
				message.setObj(shareId);
				message.setMsg("成功");
				message.setMsgtype(200);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			message.setIs(false);
			message.setMsg("服务器错误");
		}
			
		return message;
	}
	
	
	
	
	/**
	 * 分享绑定设备
	 * */
	@RequestMapping(value="/bindCat")
	@ResponseBody
	public Message bindCat(Integer shareUserId,Integer userId,Integer catId,String shareId) {
		Message message = new Message();
		
		
		try {
			
			
			if(userId!=null &&userId>0 &&catId!=null&&catId>0) {
				
				String key="share_"+shareUserId+"_"+catId;
				String sid=redisTemplateService.getShareId(key);
				if(StringUtils.isEmpty(sid)||!shareId.equals(sid)) {
					message.setIs(false);
					message.setMsg("二维码已经过期");
					return message;
				}
				
				boolean bindCountByRedis=redisTemplateService.findBindCat(userId,catId);
				if(bindCountByRedis) {
					message.setIs(false);
					message.setMsg("你已经绑定该设备");
					return message;
				}else {
					int bindCountByMysql=userService.findBindCat(userId,catId);
					if(bindCountByMysql!=0) {
						message.setIs(false);
						message.setMsg("你已经绑定该设备");
						return message;
					}
				}
					
				
			}
			
			userService.bindCat(userId,catId);
			redisTemplateService.bindCat(userId,catId);
			message.setIs(true);
			message.setMsg("成功绑定设备");
			return message;
			
		} catch (Exception e) {
			e.printStackTrace();
			message.setIs(false);
			message.setMsg("绑定失败");
		}
		
		return message;
	}
	
	
	@RequestMapping(value="/findUserByCatId")
	@ResponseBody
	public Message findUserByCatId(Integer catId) {
		Message msg=new Message();
		
		if(catId>0) {
			try {
				Set<String> userIdList=redisTemplateService.findUserIdByCatId(catId);
				List<User> Userlist=new ArrayList<User>();
				
				for (String str : userIdList) {
					User userInfo = redisTemplateService.getUserInfo(Integer.parseInt(str));
					Userlist.add(userInfo);
				}
				
				if(Userlist.isEmpty()) {
					Userlist=userService.findUserByCatId(catId);
				}
				
				msg.setIs(true);
				msg.setMsg("成功");
				msg.setObj(Userlist);
				return msg;
			} catch (Exception e) {
				msg.setIs(false);
				msg.setMsg("查询数据失败");
				return msg;
			}
		}
		
		msg.setIs(false);
		msg.setMsg("设备不存在");
		
		return msg;
		
	}
}
