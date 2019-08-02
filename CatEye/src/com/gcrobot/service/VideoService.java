package com.gcrobot.service;

import java.util.Date;
import java.util.List;

import com.gcrobot.bean.File;
import com.gcrobot.bean.Video;
import com.gcrobot.bean.Visitor;

public interface VideoService {
	
	//业务层  添加
	Integer addVideo(Video video);

	//查询每月视频存在的天
	List<String> findVideoExistDate(Integer deviceId, Integer year, Integer month);

	//查询每天存在的视频文件
	List<File> findVideoByDate(Integer deviceId, Integer year, Integer month, Integer day);

	//删除视频文件
	Integer delVideo(List<Integer> list);
	
	//查询访客存在的日期
	List<String> findFangKeDay(Integer deviceId, Integer year, Integer month);

	//添加报警消息记录
	void addFile(File file);

	//添加访客消息
	void addVisitor(Visitor visitor);

	//查询访客消息
	List<Visitor> findVisitorByDate(Integer deviceId, Integer year, Integer month, Integer day);

	//删除访客记录
	Integer delVisitor(List<Integer> idList);
	
	
	
}
