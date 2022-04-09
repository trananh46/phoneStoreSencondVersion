package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.model.Ram;
import com.example.demo.service.RamService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin_phone")
public class AdminRamController {

	@Autowired
	private RamService ramService;
	
	@Autowired
	private StaffService staffService;
	
	

	
	@GetMapping("/ram")
	public String displayRam(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);	
		
		List<Ram> listRam = ramService.displayRam();
		model.addAttribute("listRam", listRam);
		return "admin/ram";
	}
}
