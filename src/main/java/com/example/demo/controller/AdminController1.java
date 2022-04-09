package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController1 {

	@GetMapping("/display-header")
	public String displayHeader() {
		return "user/test1";
	}
	
//	@GetMapping("/display-footer")
//	public String displayFooter() {
//		
//	}
}
