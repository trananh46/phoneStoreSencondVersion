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

import com.example.demo.model.Color;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.service.ColorService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin_phone")
public class AdminColorController {

	@Autowired
	private ColorService colorService;
	
	@Autowired
	private StaffService staffService;
	@GetMapping("/color")
	public String displayColor(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<Color> listColor = colorService.displayColor();
		model.addAttribute("listColor", listColor);
		return "admin/color";
	}
	
	@GetMapping("/insert-color")
	public String insertColor(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		return "admin/formInsertColor";
	}
	
	@GetMapping("/insert-color-process")
	public String insertColorProcess(@RequestParam("name_color") String color,Model model) {
		colorService.insertColor(color);
		return "redirect:/admin_phone/color";
	}
	
	@GetMapping("/update-color")
	public String updateColor(@RequestParam("idColor") Long idColor,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		Color c = colorService.findColorById(idColor);
		model.addAttribute("color", c);
		return "admin/formUpdateColor";
	}
	
	@GetMapping("/update-color-process")
	public String updateColorProcess(@RequestParam("id_color") Long idColor,@RequestParam("name_color_update") String nameColorUpdate) {
		colorService.updateColor(idColor, nameColorUpdate);
		return "redirect:/admin_phone/color";
	}
	
	@GetMapping("/delete-color")
	public String deleteColor(@RequestParam("idColor") Long idColor) {
		colorService.deleteColor(idColor);
		return "redirect:/admin_phone/color";
	}
}
