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

import com.example.demo.model.Camera;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.service.CameraService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin_phone")
public class AdminCameraController {

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private CameraService cameraService;
	
	@GetMapping("/camera")
	public String displayListCamera(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<Camera> listCamera = cameraService.displayListCamera();
		model.addAttribute("listCamera", listCamera);
		return "admin/camera";
	}
}
