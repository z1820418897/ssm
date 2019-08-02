package com.gcrobot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcrobot.bean.Test;
import com.gcrobot.service.TestService;
import com.gcrobot.util.LayuiDataGrid;
import com.gcrobot.util.PageHelp;

@Controller
public class TestController {
	
	
@Autowired
private TestService testService;
	

	@RequestMapping("test")
	@ResponseBody
	public String test() {
		
		return "test";
	} 
	
	
	
	
	
	@RequestMapping("data")
	@ResponseBody
	public LayuiDataGrid data(PageHelp page) {
		System.out.println(page.getLimit());
		
		List<Test> data=testService.findCat();
		LayuiDataGrid ldg=new LayuiDataGrid();
		ldg.setCode(0);
		ldg.setCount(data.size());
		ldg.setData(data);
		ldg.setMsg("");
		 
		
		return ldg;
	}
	
	@RequestMapping("index")
	public String index() {
		
		
		return "index";
	}
	@RequestMapping("first")
	public String first() {
		
		return "first";
	}
	@RequestMapping("datagrid")
	public String datagrid() {
		
		return "datagrid";
	}
	
	@RequestMapping("save")
	@ResponseBody
	public String save(Test data) {
		System.out.println(data.getAge());
		
		return "OK";
	}
	
}
