package com.gcrobot.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gcrobot.bean.Device;
import com.gcrobot.service.CatEyeService;
import com.gcrobot.service.RedisDeviceService;
import com.gcrobot.util.FileHelps;
import com.gcrobot.util.LayuiDataGrid;
import com.gcrobot.util.Message;
import com.gcrobot.util.PageHelp;

@Controller
@RequestMapping(value="/cateye")
public class CatEyeController {
	private Logger logger = LoggerFactory.getLogger(CatEyeController.class);
	
	//@Value({})
	private String applog="C:/Users/admin/Desktop/file/catlog";;
	
	@Autowired
	private CatEyeService catEyeService;
	@Autowired
	private RedisDeviceService redisDeviceService;
	
	//控制层  分页获取设备
	@RequestMapping(value="data",params = {"page","limit"})
	@ResponseBody
	public LayuiDataGrid findCatByPage(PageHelp page) {
		
		LayuiDataGrid ldg=new LayuiDataGrid();
		if(page.getPage()<=0||page.getLimit()<=0) {
			ldg.setCode(1);
			ldg.setCount(0);
			ldg.setData(null);
			ldg.setMsg("请求格式错误");
			return ldg;
		}
		try {
			Integer count=catEyeService.findCount();
			List<Device> data=catEyeService.findCatByPage(page);
			
			ldg.setCode(0);
			ldg.setCount(count);
			ldg.setData(data);
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
	
	//设备的添加 修改 
	@RequestMapping(value="save")
	@ResponseBody
	public Message save(Integer id,String sn) {
			
		logger.error("sn--"+sn+"--id--"+id);
		Message msg=new Message();
		boolean is=catEyeService.findCountBySN(sn);
		if(is) {
			msg.setIs(false);
			msg.setMsg("该设备号已经被注册使用");
			return msg;
		}
		
		try {
			if(id==null||id.equals("")) {
				Device cateye=new Device();
				cateye.setSN(sn);
				cateye.setState(1);
				cateye.setDeviceName("默认昵称");
				
				catEyeService.insertCat(cateye);
				redisDeviceService.reidsAddCateye(cateye);
				
				msg.setIs(true);
				msg.setMsg("注册成功");
				msg.setMsgtype(200);
				msg.setObj(cateye);
				
			}else if(id!=null && !id.equals("")) {
				Device cateye=new Device();
				cateye.setDeviceId(id);
				cateye.setSN(sn);
				catEyeService.editCat(cateye);
				msg.setIs(true);
				msg.setMsgtype(200);
				msg.setMsg("修改成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setIs(false);
			msg.setMsgtype(500);
			msg.setMsg("服务器异常，联系技术人员");
		}
		return msg;
	}
	
	
	@RequestMapping("/deleteCat")
	@ResponseBody
	public Message deleteCat(String ids) {
		Message msg=new Message();
		logger.error(ids);
		String[] arr=ids.split(",");
		List<String> list=Arrays.asList(arr);
		try {
			if(arr.length>0) {
				redisDeviceService.redisDeleteCat(arr);
				catEyeService.deleteCat(list);
				
				msg.setIs(true);
				msg.setMsgtype(200);
				msg.setMsg("修改成功");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setIs(false);
			msg.setMsgtype(500);
			msg.setMsg("删除失败");
		}
		
		return msg;
	}
	
	
	@RequestMapping("/editCatName")
	@ResponseBody
	public Message editCatName(Integer catId,String catName) {
		
		Message msg=new Message();
		try {
			redisDeviceService.reidsEditCatName(catId, catName);
			Integer is = catEyeService.editCatName(catId,catName);
			msg.setIs(true);
			msg.setMsg("修改成功");
			msg.setMsgtype(200);
		}catch (Exception e) {
			e.printStackTrace();
			msg.setIs(false);
			msg.setMsg("修改失败");
			msg.setMsgtype(500);
		}
		
		return msg;
	}
	
	@RequestMapping(value="/uploadDeviceLog")
	@ResponseBody
	public Message uploadLog(Integer deviceId,MultipartFile logFile) {
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
			
			String newfilepath=deviceId+"/"+ymd;
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
	
	@RequestMapping("/findBaojingCountByCatId")
	@ResponseBody
	public Message findBaojingCountByCatId(Integer catId){
		Message msg=new Message();
		try {
			Integer count=catEyeService.findBaojingCountByCatId(catId);
			
			Integer count2=catEyeService.findFangKeCountByCatId(catId);
			
			Map<String, Integer> map=new HashMap<>();
			map.put("baojing", count);
			map.put("fangke", count2);
			System.out.println(catId+"-------------"+count);
			msg.setIs(true);
			msg.setMsg("成功");
			msg.setMsgtype(200);
			
			msg.setObj(map);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			msg.setIs(false);
			msg.setMsg("失败");
			msg.setMsgtype(200);
			msg.setObj(0);
			
		}
		
		return msg;
	}
}
