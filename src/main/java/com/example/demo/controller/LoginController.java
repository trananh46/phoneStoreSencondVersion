package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.CustomerService;

@Controller
public class LoginController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/login")
	public String login() {
		return "login/login";
	}
	
	@GetMapping("/sign-up")
	public String signUp() {
		return "login/signup";
	}
	
	@PostMapping("/sign-up-process")
	public String signUpProcess(@RequestParam("name_customer") String nameCustomer,@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("sdt") String sdt) {
		
		customerService.insertCustomer(nameCustomer, email, password, sdt);
		return "redirect:/login";
	}
	
	@GetMapping("/login/admin")
	public String admin() {
		return "redirect:/login";
	}
	
	@GetMapping("/login/user")
	public String user() {
		return "redirect:/login";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "login/accessDenied";
	}
}
