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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.model.Screen;
import com.example.demo.service.ScreenService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin_phone")
public class AdminScreenController {

	@Autowired
	private ScreenService screenService;
	
	@Autowired
	private StaffService staffService;
	@GetMapping("/screen")
	public String displayScreen(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		List<Screen> listScreens = screenService.displayScreen();
		model.addAttribute("listScreen", listScreens);
		
		return "admin/screen";
	}
	
	@GetMapping("/insert-screen")
	public String insertScreen(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		return "admin/formInsertScreen";
	}
	
	@GetMapping("/insert-screen-process")
	public String insertScreenProcess(@RequestParam("technology_screen") String technologyScreen,@RequestParam("do_phan_giai") String doPhanGiai) {
		screenService.insertScreen(technologyScreen, doPhanGiai);
		return "redirect:/admin_phone/screen";
	}
}
