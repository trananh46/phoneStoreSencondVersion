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
import com.example.demo.model.PhoneProductAndDetail;
import com.example.demo.service.PhoneProductService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin-phone")
public class AdminAdditionalFunctionController {

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private PhoneProductService phoneProductService;
	
	@GetMapping("/list-locked-phone-product")
    public String displayListLockedPhoneProduct(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
    	
    	List<PhoneProductAndDetail> listPhoneProduct = phoneProductService.displayListLockedPhoneProduct();
    	model.addAttribute("listPhoneProduct", listPhoneProduct);
    	
    	return "admin/listLockedPhoneProduct";
    }
}
