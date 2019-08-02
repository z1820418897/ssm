package com.gcrobot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcrobot.bean.Device;
import com.gcrobot.bean.Root;
import com.gcrobot.bean.Version;
import com.gcrobot.mapper.WebMapper;
import com.gcrobot.service.WebService;
import com.gcrobot.util.PageHelp;

@Service
public class WebServiceImpl implements WebService{

	@Autowired
	private WebMapper webMapper;

	@Override
	public Root findRootNameIsEmpty(String username) {
		
		
		return webMapper.findRootNameIsEmpty(username);
	}

	@Override
	public Integer findCount() {
			
		
		return webMapper.findCount();
	}

	@Override
	public List<Version> findVersionByPage(PageHelp page) {
		
		page.setStart((page.getPage()-1)*page.getLimit());
		page.setEnd(page.getLimit());
		
		List<Version> data=webMapper.findVersionByPage(page);
		
		
		return data;
	}

	@Override
	public void saveVersion(Version version) {
		
		webMapper.saveVersion(version);
		
	}

	@Override
	public void deleteVersion(List<String> list) {
		
		webMapper.deleteVersion(list);
	}

	@Override
	public List<String> findFiles(List<String> list) {
		
		
		return webMapper.findFiles(list);
	}
	
	
	
	
}
