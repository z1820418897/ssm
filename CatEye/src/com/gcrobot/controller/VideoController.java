package com.gcrobot.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gcrobot.bean.Video;
import com.gcrobot.bean.Visitor;
import com.gcrobot.service.VideoService;
import com.gcrobot.util.Message;
import com.gcrobot.util.MyJsonList;

@Controller
@RequestMapping("/video")
public class VideoController {
	//http:10.10.2.179:8080/GC_Robot/video/uploadFangKe
	private Logger logger = LoggerFactory.getLogger(VideoController.class);
	
	@Value("${file}")
	private String file;
	
	@Value("${filepath}")
	private String filepath;
	
	
	
	//@Value("${imgpath}")
	private String imgpath="C:/Users/admin/Desktop/file/img";
	//@Value("")
	private String ffmpeg="D:/ffmpeg/bin/ffmpeg.exe";
	
	
	@Autowired
	private VideoService videoService;
	
	
	/**
	 * 报警消息的上传该换接口
	 * 
	 * */
	
	@RequestMapping("/uploadBJ")
	@ResponseBody
	public Message uploadBJ(Integer deviceId,Integer type,MultipartFile video,MultipartFile photo1,MultipartFile photo2,MultipartFile photo3,MultipartFile photo4,MultipartFile photo5) {
		logger.info("uploadBj被访问");
		
		Message message = new Message();
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd,HHmmssSSS");
			String formatStr =formatter.format(date);
			
			String ymd = formatStr.substring(0,formatStr.indexOf(","));
			String hmss = formatStr.substring(formatStr.indexOf(",")+1,formatStr.length());
			String newfilepath=deviceId+"/"+ymd;
			
			if(type==0) {
				
				String dirpath=file+"/video/"+newfilepath;
				
				File dir = new File(dirpath);
				if (isExist(dirpath)) {
					dir.mkdir();
				}
				
				String filename = video.getOriginalFilename();
				String newFileName = hmss + filename.substring(filename.lastIndexOf("."));
				
				
				File writeFile = new File(dirpath,newFileName);
				video.transferTo(writeFile);
				
				
				String imgdirpath="img/"+imgpath+"/"+newfilepath;
				File imgdir = new File(imgdirpath);
				if (isExist(imgdirpath)) {
					imgdir.mkdir();
				}
				
				String imgnewFileName=hmss+".jpg";
				
				boolean image = screenImage(ffmpeg,dirpath+"/"+newFileName,imgdirpath+"/"+imgnewFileName,"1280","1280");
				
				if(image) {
					logger.info("成功");
				}else {
					logger.info("失败");
				}
				
				com.gcrobot.bean.File file=new com.gcrobot.bean.File();
				
				file.setDeviceId(deviceId);
				file.setType(0);
				
				file.setFileName("video/"+newfilepath+"/"+newFileName);
				file.setFileTime(date);
				file.setImg("img/"+newfilepath+"/"+imgnewFileName);
				
				videoService.addFile(file);
				
			}else if(type==1) {
				String uuid="/"+UUID.randomUUID().toString().replace("-", "").substring(0,4);
				String dirpath=file+"/photo/"+newfilepath+uuid;
				String filename = photo1.getOriginalFilename();
				
				String img="";
				File dir = new File(dirpath);
				if (isExist(dirpath)) {
					dir.mkdir();
				}
					
				if(photo1!=null) {
					String newFileName= hmss +UUID.randomUUID().toString().replace("-", "").substring(0,4)+ filename.substring(filename.lastIndexOf("."));
					img=newFileName;
					File writeFile = new File(dirpath,newFileName);
					photo1.transferTo(writeFile);
				}
				
				if(photo2!=null) {
					String newFileName= hmss +UUID.randomUUID().toString().replace("-", "").substring(0,4)+ filename.substring(filename.lastIndexOf("."));
					File writeFile = new File(dirpath,newFileName);
					photo2.transferTo(writeFile);
				}
				
				if(photo3!=null) {
					String newFileName= hmss +UUID.randomUUID().toString().replace("-", "").substring(0,4)+ filename.substring(filename.lastIndexOf("."));
					File writeFile = new File(dirpath,newFileName);
					photo3.transferTo(writeFile);
				}
				
				if(photo4!=null) {
					String newFileName= hmss +UUID.randomUUID().toString().replace("-", "").substring(0,4)+ filename.substring(filename.lastIndexOf("."));
					File writeFile = new File(dirpath,newFileName);
					photo4.transferTo(writeFile);
				}
				
				if(photo5!=null) {
					String newFileName= hmss +UUID.randomUUID().toString().replace("-", "").substring(0,4)+ filename.substring(filename.lastIndexOf("."));
					File writeFile = new File(dirpath,newFileName);
					photo5.transferTo(writeFile);
				}
				
				com.gcrobot.bean.File file=new com.gcrobot.bean.File();
				
				file.setDeviceId(deviceId);
				file.setType(1);
				
				file.setFileName("photo/"+newfilepath+uuid);
				file.setFileTime(date);
				file.setImg("photo/"+newfilepath+"/"+uuid+"/"+img);
				
				videoService.addFile(file);
				
			}
			
			message.setIs(true);
			message.setMsg("上传成功");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			message.setIs(false);
			message.setMsg("上传失败");
		}
		return message;
	}
	
	
	/**
	 * 视频文件上传
	 * 控制层
	 * 添加视频信息到数据库
	 * */
	
	@RequestMapping("/upload")
	@ResponseBody
	public Message upLoadVideo(MultipartFile VideoFile,Integer deviceId,HttpServletRequest request) {
		logger.info("upLoadVideo被访问");
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd,HHmmssSSS");
		String formatStr =formatter.format(date);
		
		String ymd = formatStr.substring(0, formatStr.indexOf(","));
		String hmss = formatStr.substring(formatStr.indexOf(",")+1,formatStr.length());
		String newfilepath=deviceId+"/"+ymd;
		
		String dirpath=filepath+"/"+newfilepath;
		System.out.println("文件夹路径"+dirpath);
		
		File dir = new File(dirpath);
		if (isExist(dirpath)) {
			dir.mkdir();
		}
		
		String filename = VideoFile.getOriginalFilename();
		String newFileName = hmss + filename.substring(filename.lastIndexOf("."));
		
		Message message = new Message();
		
		try {
			
	        File writeFile = new File(dirpath,newFileName);
			VideoFile.transferTo(writeFile);
			
			//----------------------------------------------------------
			
			String imgdirpath=imgpath+"/"+newfilepath;
			File imgdir = new File(imgdirpath);
			if (isExist(imgdirpath)) {
				imgdir.mkdir();
			}
			
			String imgnewFileName=hmss+".jpg";
			
			boolean image = screenImage(ffmpeg,dirpath+"/"+newFileName,imgdirpath+"/"+imgnewFileName,"1280","1280");
			
			if(image) {
				logger.info("成功");
			}else {
				logger.info("失败");
			}
			
			
			System.out.println("全路径"+dirpath+"/"+newFileName);
			System.out.println("文件名"+newFileName);
			System.out.println("拼接路径名"+newfilepath);
			
			Video video=new Video();
			video.setDeviceId(deviceId);
			video.setVideoName(newfilepath+"/"+newFileName);
			video.setVideoTime(date);
			video.setImg(newfilepath+"/"+imgnewFileName);
			
			videoService.addVideo(video);
			
			message.setIs(true);
			message.setMsg("保存成功");
			message.setMsgtype(200);
			
		} catch (Exception e) {
			System.out.println("VideoController-upLoadVideo-ex");
			e.printStackTrace();
			
			message.setIs(false);
			message.setMsg("文件保存失败");
			message.setMsgtype(500);
			
		}
		logger.info("upLoadVideo响应完成");
		return message;
	}
	
	
	/**
	 *控制层
	 *根据年月查询视频存在的天数
	 * */
	@RequestMapping("/findVideoExistDate")
	@ResponseBody
	public MyJsonList findVideoExistDate(Integer deviceId,Integer year,Integer month) {
		logger.info("findVideoExistDate访问");
		
		MyJsonList myJsonList = new MyJsonList();
		try {
			List<String> list=videoService.findVideoExistDate(deviceId,year,month);
			
			if(list.size()>0) { 
				myJsonList.setIshas(true);
			}else {
				myJsonList.setIshas(false);
			}
			
			
			myJsonList.setData(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			myJsonList.setIshas(false);
			logger.error("查询视频存在天数异常");
		}
		return myJsonList;
	}
	
	/*@RequestMapping("/findVideoExistDate")
	@ResponseBody
	public List<String> findVideoExistDate(Integer deviceId,Integer year,Integer month) {
		List<String> list=videoService.findVideoExistDate(deviceId,year,month);
		return list;
	}*/
	
	/**
	 * 控制层
	 * 查询当天的视频文件
	 * */
	@RequestMapping("/findVideoByDate")
	@ResponseBody
	public MyJsonList findVideoByDate(Integer deviceId,Integer year,Integer month,Integer day) {
		
		logger.info("findVideoByDate访问");
		MyJsonList myJsonList = new MyJsonList();
		
		try {
			
			List<com.gcrobot.bean.File> list=videoService.findVideoByDate(deviceId,year,month,day);
			logger.info(list.toString());
			
			if(list.size()>0) {
				myJsonList.setIshas(true);
			}else {
				myJsonList.setIshas(false);
			}
			
			for(int i=0;i<list.size();i++) {
				com.gcrobot.bean.File file=list.get(i);
				if(file.getType()==1) {
					List<String> fileNames = getFileName(file.getFileName());
					logger.info(this.file+"/"+file.getFileName());
					file.setPhotos(fileNames);
				}
				list.set(i,file);
			}
			
			myJsonList.setData(list);
			
			
		} catch (Exception e) { 
			
			e.printStackTrace();
			myJsonList.setIshas(false);
			logger.error("查询当天视频文件异常");
			
		}
		
		logger.info("findVideoByDate响应完成");
		
		return myJsonList;
	}
	
	/**
	 * 控制层
	 * 历史视频信息删除
	 * */
	@RequestMapping("/delVideo")
	@ResponseBody
	public Message delVideo(@RequestParam("ids") String ids,@RequestParam("names") String names,@RequestParam("imgs") String imgs) {
		logger.info(imgs+"iiiiiiiiiiiiiiiiiiii"+ids+"-------------------->"+names+filepath);
		Message message = new Message();
		try {
			String[] arrId = ids.split(",");
			List<Integer> idList=new ArrayList<>();
			
			String[] arrimg=imgs.split(",");
			for(int i=0;i<arrimg.length;i++) {
				boolean deleteimg = deleteFile(imgpath+"/"+arrimg[i]);
				logger.info("deleteimg"+deleteimg);
			}
			
			String[] arrName = names.split(",");
			for(int i=0;i<arrName.length;i++) {
				//logger.warn("挺好------");
				boolean deleteFile = deleteFile(filepath+"/"+arrName[i]);
				if(deleteFile) {
					idList.add(Integer.parseInt(arrId[i]));
				}
			}
			
			//数据库操作
			Integer is=videoService.delVideo(idList);
			
			if(is==arrId.length) {
				message.setIs(true);
				message.setMsg("全部删除成功");
				message.equals(200);
			}else if(is>0){
				message.setIs(true);
				message.setMsg("部分删除成功");
				message.equals(199);
			}else {
				message.setIs(false);
				message.setMsg("删除失败");
				message.equals(500);
			}
		} catch (Exception e) {
			message.setIs(false);
			message.setMsg("删除失败");
			message.equals(500);
		}
		
		return message;
	}
	
	
	
	/**
	 * 访客图片上传
	 * */
	@RequestMapping("/uploadFangKe")
	@ResponseBody 
	public Message uploadFangKe(MultipartFile photoFile,Integer deviceId,HttpServletRequest request) {
		logger.info("uploadFangKe被访问");
		Message msg=new Message();
		
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd,HHmmssSSS");
			String formatStr =formatter.format(date);
			
			String ymd = formatStr.substring(0,formatStr.indexOf(","));
			String hmss = formatStr.substring(formatStr.indexOf(",")+1,formatStr.length());
			String newfilepath=deviceId+"/"+ymd;
			
			String dirpath=file+"/visitor/"+newfilepath;
			
			File dir = new File(dirpath);
			if (isExist(dirpath)) {
				dir.mkdir();
			}
			
			String filename = photoFile.getOriginalFilename();
			String newFileName = hmss + filename.substring(filename.lastIndexOf("."));
			
			File writeFile = new File(dirpath,newFileName);
			photoFile.transferTo(writeFile);
			
			Visitor visitor = new Visitor();
			visitor.setDeviceId(deviceId);
			visitor.setFileName("/visitor/"+newfilepath+"/"+newFileName);
			visitor.setFileTime(date);
			
			videoService.addVisitor(visitor);
			
			msg.setIs(true);
			msg.setMsg("上传成功");
			logger.error(visitor.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.setIs(false);
			msg.setMsg("上传失败");
		}
		
		
		return msg;
	}
	
	
	
	/**
	 * 查询存在访客记录的日期
	 * */
	@RequestMapping("/findFangKeDay")
	@ResponseBody
	public MyJsonList findFangKeDay(Integer deviceId,Integer year,Integer month) {
		logger.info("findVideoExistDate访问");
		MyJsonList myJsonList = new MyJsonList();
		
		try {
			
			List<String> list=videoService.findFangKeDay(deviceId,year,month);
			
			if(list.size()>0) {
				myJsonList.setIshas(true);
			}else {
				myJsonList.setIshas(false);
			}
			myJsonList.setData(list);
				
		} catch (Exception e) {
			
			e.printStackTrace();
			myJsonList.setIshas(false);
			logger.error("查询视频存在天数异常");
		}
		
		return myJsonList;
	}
	
	/**
	 * 根据具体时间查询对应的记录
	 * */
	@RequestMapping("/findVisitorByDate")
	@ResponseBody
	public MyJsonList findVisitorByDate(Integer deviceId,Integer year,Integer month,Integer day) {
		
		logger.info("findVideoByDate访问");
		MyJsonList myJsonList = new MyJsonList();
		
		try {
			
			List<Visitor> list=videoService.findVisitorByDate(deviceId,year,month,day);
			
			
			if(list.size()>0) {
				myJsonList.setIshas(true);
			}else {
				myJsonList.setIshas(false);
			}
			
			myJsonList.setData(list);
			logger.error(list.toString());
			
		} catch (Exception e) { 
			
			e.printStackTrace();
			myJsonList.setIshas(false);
			logger.error("查询当天视频文件异常");
			
		}
		
		logger.info("findVideoByDate响应完成");
		
		return myJsonList;
	}
	
	
	/**
	 * 删除访客记录
	 * */
	@RequestMapping("/delVisitor")
	@ResponseBody
	public Message delVisitor(@RequestParam("ids") String ids,@RequestParam("names") String names) {
		Message message = new Message();
		
		logger.info("delVisitor被访问");
		
		String[] arrId = ids.split(",");
		List<Integer> idList=new ArrayList<>();
		
		String[] arrName = names.split(",");
		for(int i=0;i<arrName.length;i++) {
			//logger.warn("挺好------");
			boolean deleteFile = deleteFile(filepath+"/"+arrName[i]);
			if(deleteFile) {
				idList.add(Integer.parseInt(arrId[i]));
			}
		}
		//数据库操作
		Integer is=videoService.delVisitor(idList);
		
		if(is==arrId.length) {
			message.setIs(true);
			message.setMsg("全部删除成功");
			message.equals(200);
		}else if(is>0){
			message.setIs(true);
			message.setMsg("部分删除成功");
			message.equals(199);
		}else {
			message.setIs(false);
			message.setMsg("删除失败");
			message.equals(500);
		}
		
		
		return message;
	}
	
	
	
	//------------------------------------------------------
	/**
	 * 判断文件夹是否存在
	 * @param path 文件夹路径
	 * true 文件不存在，false 文件存在不做任何操作
	 */
	private  boolean isExist(String filePath) {
		String paths[] = filePath.split("/");
		String dir = paths[0];
		for (int i = 0; i < paths.length - 2; i++) {
			try {
				dir = dir + "/" + paths[i + 1];
				File dirFile = new File(dir);
				if (!dirFile.exists()) {
					dirFile.mkdir();
					System.out.println("创建目录为：" + dir);
				}
			} catch (Exception err) {
				err.printStackTrace();
				logger.error("创建文件夹错误");
			}
		}
		File fp = new File(filePath);
		if(!fp.exists()){
			return true; // 文件不存在
		}else{
			return false; // 文件存在不做处理
		}
	}

	/**
	 * 删除文件
	 * @param path 文件路径
	 * true 文件删除成功或者文件根本不存在   false 文件删除失败
	 */
	
	public  boolean deleteFile(String fileName) {
		try {
			File file = new File(fileName);
	        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	                System.out.println("删除单个文件" + fileName + "成功！");
	                return true;
	            } else {
	            	logger.error(fileName+"-->删除失败");
	                return false;
	            }
	        } else {
	        	logger.error(fileName+"-->不存在");
	            return true;
	        }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(fileName+"-->无法删除");
			return false;
			
		}
        
    }
	
	/**
	 * 获取文件夹下的所有文件名
	 * */
	public List<String> getFileName(String path) {
		
		
		List<String> files=new ArrayList<>();
        File f = new File(this.file+"/"+path);
        if (!f.exists()) {
            return null;
        }
        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                logger.info(fs.getName() + " [目录]");
            } else {
                files.add(path+"/"+fs.getName());
            }
        }
        
		return files;
    }
	
	
	/**
	 * 获取缩略图
	 * @throws Exception 
	 * */
	private  boolean screenImage(String ffmpegPath, String upFilePath, String mediaPicPath, String width, String height) throws Exception {
		// 创建一个List集合来保存从视频中截取图片的命令
	    List<String> cutpic = new ArrayList<String>();
	    cutpic.add(ffmpegPath);
	    cutpic.add("-i");
	    cutpic.add(upFilePath); // 要截图的视频源文件
	    cutpic.add("-y");
	    cutpic.add("-f");
	    cutpic.add("image2");
	    cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
	    cutpic.add("1"); // 添加起始时间为第17秒
	    cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
	    cutpic.add("0.001"); // 添加持续时间为1毫秒
	    cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
	    cutpic.add(width + "*" + height); // 添加截取的图片大小为350*240
	    cutpic.add(mediaPicPath); // 添加截取的图片的保存路径
	    BufferedReader br=null;
	    ProcessBuilder builder = new ProcessBuilder();
	    Process process = null;
	    try {
	        builder.command(cutpic);
	        builder.redirectErrorStream(true);
	        process = builder.start();
	       /* inputStream = process.getInputStream();
	        int data=0;
	        while((data=inputStream.read())!=-1) {
	        	System.out.println((byte)data);
	        }
	        errorStream= process.getErrorStream();
	        data=0;
	        while((data=errorStream.read())!=-1) {
	        	System.out.println((byte)data);
	        }*/
	        
	        br = new BufferedReader(new InputStreamReader(process.getInputStream()));    
	        String output = null;    
	        while (null != (output = br.readLine()))  
	        {    
	            System.out.println(output);     
	        }    
	        int waitFor = process.waitFor();
	        System.out.println(waitFor+"===============================================");
	        //return true; 
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.error("获取缩略图错误！！！！！");
	    	return false;
	    }finally {
	    	br.close();
	    	process.destroy();
		}
	    
	    return true;
	}
	
}
