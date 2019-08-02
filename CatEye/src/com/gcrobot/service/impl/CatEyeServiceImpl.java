package com.gcrobot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.gcrobot.bean.Device;
import com.gcrobot.mapper.CatEyeMapper;
import com.gcrobot.service.CatEyeService;
import com.gcrobot.service.RedisDeviceService;
import com.gcrobot.util.Message;
import com.gcrobot.util.PageHelp;
//业务层
@Service
public class CatEyeServiceImpl implements CatEyeService{

	@Autowired
	private CatEyeMapper catEyeMapper;
	@Autowired
	private RedisTemplate redisTemplate;
	
	//查询数据总条数
	@Override
	public Integer findCount() {
		
		Integer count=catEyeMapper.findCount();
		
		return count;
	}

	//分页查询数据
	@Override
	public List<Device> findCatByPage(PageHelp page) {
		page.setStart((page.getPage()-1)*page.getLimit());
		page.setEnd(page.getLimit());
		
		List<Device> data=catEyeMapper.findCatByPage(page);
		
		return data;
	}

	
	//检测SN是否重复
	@Override
	public boolean findCountBySN(String SN) {
		
		Integer count=catEyeMapper.findCountBySN(SN);
		
		if(count>0) {
			return true;
		}
		
		return false;
	}
	
	//新增cat
	@Override
	public void insertCat(Device cateye) {
		
		catEyeMapper.insertCat(cateye);
	}
		
	//修改cat
	@Override
	public void editCat(Device cateye) {
		
		catEyeMapper.editCat(cateye);
	}

	
	@Override
	public Integer editCatName(Integer catId, String catName) {
		
		
		Integer is=catEyeMapper.editCatName(catId,catName);
			
			
		return is;
	}

	@Override
	public Integer findBaojingCountByCatId(Integer catId) {
		
		
		return catEyeMapper.findBaojingCountByCatId(catId);
	}
	
	@Override
	public void deleteCat(List<String> ids) {
		
		catEyeMapper.deleteCat(ids);
		
	}

	@Override
	public Integer findFangKeCountByCatId(Integer catId) {
		
		return catEyeMapper.findFangKeCountByCatId(catId);
	}

}
