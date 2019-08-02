package com.gcrobot.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gcrobot.controller.VideoController;

public class FileHelps {

	private  Logger logger = LoggerFactory.getLogger(VideoController.class);
	/**
	 * 判断文件夹是否存在
	 * @param path 文件夹路径
	 * true 文件不存在，false 文件存在不做任何操作
	 */
	public  boolean isExist(String filePath) {
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
	
}
