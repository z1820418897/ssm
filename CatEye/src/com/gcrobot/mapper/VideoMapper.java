package com.gcrobot.mapper;

import java.util.Date;
import java.util.List;

import com.gcrobot.bean.File;
import com.gcrobot.bean.Video;
import com.gcrobot.bean.Visitor;

public interface VideoMapper {

	//持久层 添加video
	Integer addVideo(Video video);

	//持久层 根据年月查询存在视频的天
	List<String> findVideoExistDate(Integer deviceId, Integer year, Integer month);

	//根据天查询视频信息
	List<File> findVideoByDate(Integer deviceId, Integer year, Integer month, Integer day);
	
	//删除视频
	Integer delVideo(List<Integer> list);

	//添加报警记录  视频
	void addFile(File file);
	
	//添加访客记录
	void addVisitor(Visitor visitor);
	
	//查询访客记录
	List<Visitor> findVisitorByDate(Integer deviceId, Integer year, Integer month, Integer day);
	
	//查询存在天数
	List<String> findFangKeDay(Integer deviceId, Integer year, Integer month);

	//删除访客记录
	Integer delVisitor(List<Integer> idList);
		
}
