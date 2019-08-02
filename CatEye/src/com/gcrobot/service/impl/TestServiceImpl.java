package com.gcrobot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcrobot.bean.Test;
import com.gcrobot.mapper.TestMapper;
import com.gcrobot.service.TestService;


@Service
public class TestServiceImpl implements TestService{
	@Autowired
	private TestMapper testMapper;

	@Override
	public List<Test> findCat() {
		// TODO Auto-generated method stub
		return testMapper.findCat();
	}

}
