package com.gcrobot.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gcrobot.bean.Device;
import com.gcrobot.bean.Root;
import com.gcrobot.bean.Version;
import com.gcrobot.service.WebService;
import com.gcrobot.util.FileHelps;
import com.gcrobot.util.LayuiDataGrid;
import com.gcrobot.util.Message;
import com.gcrobot.util.PageHelp;

@Controller
public class WebController {
	
	private static final String String = null;

	private Logger logger = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	private WebService webService;
	
	@Value("${app}")
	private String app;
	
	@RequestMapping("/loginInfo")
	public String loginInfo(String username,String password,HttpServletRequest request) {
		logger.error("loginInfo访问"+password+username);
		if(!StringUtils.isEmpty(username)&& !StringUtils.isEmpty(password)) {
			Root root=webService.findRootNameIsEmpty(username);
			if(!StringUtils.isEmpty(root)&& root.getPassword().equals(password)) {
				request.getSession().setAttribute("gc_user", username);
				return "redirect:/main";
			}
		}
		return "redirect:/login";
	}
	
	
	
	
	@RequestMapping("/version/data")
	@ResponseBody
	public LayuiDataGrid versionData(PageHelp page) {
		LayuiDataGrid ldg=new LayuiDataGrid();
		if(page.getPage()<=0||page.getLimit()<=0) {
			ldg.setCode(1);
			ldg.setCount(0);
			ldg.setData(null);
			ldg.setMsg("请求格式错误");
			return ldg;
		}
		try {
			
			Integer count=webService.findCount();
			List<Version> data=webService.findVersionByPage(page);
			List<Version> datas=new ArrayList<>();
			for (Version version : data) {
				version.setStringTime(new SimpleDateFormat("yyyy-MM-dd").format(version.getTime()));
				logger.error(version.getStringTime());
				datas.add(version);
			}
			
			ldg.setCode(0);
			ldg.setCount(count);
			ldg.setData(datas);
			ldg.setMsg("查询成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			ldg.setCode(1);
			ldg.setCount(0);
			ldg.setData(null);
			ldg.setMsg("后台接口出现错误，请联系技术人员。");
		}
		
		return ldg;
	}
	
	
	@RequestMapping("/version/upload")
	@ResponseBody
	public Message uploadVersion(MultipartFile file,HttpSession session) {
		Message message = new Message();
		String fileName=file.getOriginalFilename();
		
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmssSSS");
		String formatStr =formatter.format(date);
		fileName=fileName.substring(0, fileName.indexOf("."))+"-"+formatStr+fileName.substring(fileName.indexOf("."), fileName.length());
		message.setObj(fileName);
		
		logger.error(fileName);
		
		FileHelps fileHelps = new FileHelps();
		File dir = new File(app);
		if (fileHelps.isExist(app)) {
			dir.mkdir();
		}
		File writeFile = new File(app,fileName);
		try {
			
			file.transferTo(writeFile);
			
		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
			message.setIs(false);
			message.setMsg("服务器保存失败");
		}
		
		
		message.setIs(true);
		message.setMsg("文件上传成功");
		
		session.setAttribute("fileName",fileName);
		
		
		return message;
		
	}
	
	
	@RequestMapping("/version/save")
	@ResponseBody
	public Message saveVersion(Integer versionCode,String versionName,String des,HttpSession session) {
		Message msg=new Message();
		
		
		String fileName=(String)session.getAttribute("fileName");
		if(StringUtils.isEmpty(fileName)) {
			msg.setIs(false);
			msg.setMsgtype(400);
			msg.setMsg("请先上传文件");
			return msg;
		}
		
		try {
			
			Version version=new Version();
			version.setVersionCode(versionCode);
			version.setVersionName(versionName);
			version.setFileName(fileName);
			version.setDes(des);
			version.setTime(new Date());
		
			webService.saveVersion(version);
			
			logger.error(versionCode+versionName+des+"--"+fileName+"---------------session中获取到的app");
			msg.setIs(true);
			msg.setMsg("新版本发布成功");
			msg.setMsgtype(200);
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setIs(false);
			msg.setMsg("插入数据库失败");
			msg.setMsgtype(500);
		}
		
		return msg;
	}
	@RequestMapping("/version/delete")
	@ResponseBody
	public Message deleteVersion(String ids) {
		
		//根据id查询地址  遍历删除文件		
		
		Message msg=new Message();
		
		String[] arr=ids.split(",");
		List<String> list=Arrays.asList(arr);
		
		try {
			if(arr.length>0) {
				
				List<String> files=webService.findFiles(list);
				
				webService.deleteVersion(list);
				
				for (String string : files) {
					File file = new File(app,string);
					file.delete();
				}
				
				msg.setIs(true);
				msg.setMsgtype(200);
				msg.setMsg("删除成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setIs(false);
			msg.setMsgtype(500);
			msg.setMsg("删除失败");
		}
		
		return msg;
	}
}

