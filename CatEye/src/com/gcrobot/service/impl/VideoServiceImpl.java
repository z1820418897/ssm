package com.gcrobot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcrobot.bean.File;
import com.gcrobot.bean.Video;
import com.gcrobot.bean.Visitor;
import com.gcrobot.mapper.VideoMapper;
import com.gcrobot.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService{

	@Autowired
	private VideoMapper videoMapper;
	
	@Override
	public Integer addVideo(Video video) {
		
		Integer is=videoMapper.addVideo(video);
		
		return is;
	}

	
	@Override
	public List<String> findVideoExistDate(Integer deviceId, Integer year, Integer month) {
		
		List<String> list=videoMapper.findVideoExistDate(deviceId,year,month);
		
		
		return list;
	}


	@Override
	public List<File> findVideoByDate(Integer deviceId, Integer year, Integer month, Integer day) {
		
		List<File> list=videoMapper.findVideoByDate(deviceId,year,month,day);
		
		
		return list;
	}


	@Override
	public Integer delVideo(List<Integer> list) {
		
		Integer is=videoMapper.delVideo(list);
		
		return is;
	}   


	@Override
	public List<String> findFangKeDay(Integer deviceId, Integer year, Integer month) {
		
		
		return videoMapper.findFangKeDay(deviceId,year,month);
	}


	@Override
	public void addFile(File file) {
		
		videoMapper.addFile(file);
		
	}


	@Override
	public void addVisitor(Visitor visitor) {
		
		videoMapper.addVisitor(visitor);
		
	}


	@Override
	public List<Visitor> findVisitorByDate(Integer deviceId, Integer year, Integer month, Integer day) {
		
		return videoMapper.findVisitorByDate(deviceId,year,month,day);
	}


	@Override
	public Integer delVisitor(List<Integer> idList) {
		
		Integer is=videoMapper.delVisitor(idList);
		
		return is;
	}
	
}
