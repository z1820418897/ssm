package com.gcrobot.service;

import java.util.List;

import com.gcrobot.bean.Device;
import com.gcrobot.util.Message;
import com.gcrobot.util.PageHelp;
//业务层 
public interface CatEyeService {

	//查询总设备数
	public Integer findCount();
	
	//分页查询所有的设备
	public List<Device> findCatByPage(PageHelp page);

	//检测是否重复的SN
	public boolean findCountBySN(String sn);
	
	//新增设备
	public void insertCat(Device cateye);

	//修改设备
	public void editCat(Device cateye);
	
	//修改名称
	public Integer editCatName(Integer catId, String catName);

	//查询当天报警信息的总条数
	public Integer findBaojingCountByCatId(Integer catId);

	//删除设备
	public void deleteCat(List<String> list);

	//查询当天的访客记录的行总条数
	public Integer findFangKeCountByCatId(Integer catId);
	
	
	
	
}
