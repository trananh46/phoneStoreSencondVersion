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

import com.example.demo.model.Battery;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.service.BatteryService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin_phone")
public class AdminBatteryController {

	@Autowired
	private BatteryService batteryService;
	
	@Autowired
	private StaffService staffService;
	
	@GetMapping("/battery")
	public String displayBattery(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);		
		
		List<Battery> listBatteries = batteryService.displayBattery();
		model.addAttribute("listBattery", listBatteries);
		return "admin/battery";
	}
	
	
	@GetMapping("/insert-battery")
	public String insertBattery(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);	
		return "admin/formInsertBattery";
		
	}
	
	@GetMapping("/insert-battery-process")
	public String insertBatteryProcess(@RequestParam("name_battery") String nameBattery,Model model) {
		batteryService.insertBattery(nameBattery);
		return "redirect:/admin_phone/battery";
	}
	
	@GetMapping("/update-bettery")
	public String updateBattery(@RequestParam("idBattery") Long idBattery,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);	
		
		Battery b = batteryService.findBatteryById(idBattery);
		model.addAttribute("Battery", b);
		
		return "admin/formUpdateBattery";
	}
	
	@GetMapping("/update-battery-process")
	public String updateBatteryProcess(@RequestParam("id_battery") Long idBattery,@RequestParam("name_battery") String nameBattery) {
		batteryService.updateBattery(idBattery, nameBattery);
		return "redirect:/admin_phone/battery";
	}
	
	@GetMapping("/delete-bettery")
	public String deleteBattery(@RequestParam("idBattery") Long idBattery) {
		batteryService.deleteBattery(idBattery);
		return "redirect:/admin_phone/battery";
	}
}
