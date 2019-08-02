package com.gcrobot.mapper;

import java.util.List;

import com.gcrobot.bean.Root;
import com.gcrobot.bean.Version;
import com.gcrobot.util.PageHelp;

public interface WebMapper {

	Root findRootNameIsEmpty(String username);

	Integer findCount();

	List<Version> findVersionByPage(PageHelp page);

	void saveVersion(Version version);

	void deleteVersion(List<String> list);

	List<String> findFiles(List<String> list);
	
	
}
