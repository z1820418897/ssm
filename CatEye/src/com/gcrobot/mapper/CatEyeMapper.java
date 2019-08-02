package com.gcrobot.mapper;

import java.util.List;

import com.gcrobot.bean.Device;
import com.gcrobot.util.PageHelp;

//持久层
public interface CatEyeMapper {

	//查询数据总条数
	public Integer findCount();

	//分页查询所有数据
	public List<Device> findCatByPage(PageHelp page);

	//检测是否重复的SN
	public Integer findCountBySN(String sN);
	
	//新增设备
	public void insertCat(Device cateye);

	//修改设备
	public void editCat(Device cateye);

	//修改昵称
	public Integer editCatName(Integer catId, String catName);
	
	//查询当天报警消息的总条数
	public Integer findBaojingCountByCatId(Integer catId);

	//删除设备
	public void deleteCat(List<String> ids);

	
	//查询当天访客的总条数
	public Integer findFangKeCountByCatId(Integer catId);
}
