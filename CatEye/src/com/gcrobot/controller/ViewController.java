package com.gcrobot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

	
	@RequestMapping("main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("cateye")
	public String cateye() {
		return "cateye";
	}
	@RequestMapping("version")
	public String version() {
		return "version";
	}
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
}
