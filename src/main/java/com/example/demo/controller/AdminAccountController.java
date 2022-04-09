package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.AccountCustomer2;
import com.example.demo.model.AccountStaff;
import com.example.demo.model.AccountStaff2;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.service.AccountService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private StaffService staffService;
	@GetMapping("/account-customer")
	public String displayAccountCustomer(Model model) {
		List<AccountCustomer2> ac = accountService.displayAccountCustomer();
		model.addAttribute("listAccountCustomer", ac);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		return "admin/accountCustomer";
	}
	
	@GetMapping("/account-staff")
	public String displayAccountStaff(Model model) {
		List<AccountStaff2> s = accountService.displayAccountStaff();
		model.addAttribute("listAccountStaff", s);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff staff = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", staff);
		return "admin/accountStaff";
	}
	
	
}
