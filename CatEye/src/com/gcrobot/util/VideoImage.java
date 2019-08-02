package com.gcrobot.util;

import java.util.ArrayList;
import java.util.List;

public class VideoImage {
	
public static void main(String[] args) {
	//handler("D:\\ffmpeg\\bin\\ffmpeg.exe","C:\\Users\\admin\\Desktop\\video\\5.mp4","C:\\Users\\admin\\Desktop\\video\\img\\5.jpg");
	screenImage("D:\\ffmpeg\\bin\\ffmpeg.exe","C:\\Users\\admin\\Desktop\\video\\5.mp4","C:\\Users\\admin\\Desktop\\video\\img\\5.jpg","720","1280");
}


public static void handler(String ffmpegPath,String upFilePath,String mediaPicPath){
	  List<String> cutpic = new ArrayList<String>();
		cutpic.add(ffmpegPath);
		cutpic.add("-i");
		cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
		cutpic.add("-y");
		cutpic.add("-f");
		cutpic.add("image2");
		cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
		cutpic.add("0.1"); // 添加起始时间为第17秒
		cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
		cutpic.add("0.001"); // 添加持续时间为1毫秒
		cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
		cutpic.add("360*640"); // 添加截取的图片大小为350*240
		cutpic.add(mediaPicPath); // 添加截取的图片的保存路 径

		boolean mark = true;
		ProcessBuilder builder = new ProcessBuilder();
		try {
		   
		    builder.command(cutpic);
		    builder.redirectErrorStream(true);
		    // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
		    //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
		    builder.start();
		} catch (Exception e) {
		    mark = false;
		    System.out.println(e);
		    e.printStackTrace();
		}
}


/**
 * 
 * @param ffmpegPath    转码工具的存放路径
 * @param upFilePath    要截图的视频源文件
 * @param mediaPicPath	添加截取的图片的保存路径
 * @param width			截图的宽
 * @param height		截图的高
 * @return
 */
private static boolean screenImage(String ffmpegPath, String upFilePath, String mediaPicPath, String width, String height) {
	
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

    ProcessBuilder builder = new ProcessBuilder();
    try {
    	
        builder.command(cutpic);
        builder.redirectErrorStream(true);
        builder.start();
    } catch (Exception e) {
        e.printStackTrace();
    	return false;
    }
    return true;
}

}
