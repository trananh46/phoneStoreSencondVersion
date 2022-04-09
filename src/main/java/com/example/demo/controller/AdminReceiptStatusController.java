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
import com.example.demo.model.StatusReceipt;
import com.example.demo.service.StaffService;
import com.example.demo.service.StatusReceiptService;

@Controller
@RequestMapping("/admin")
public class AdminReceiptStatusController {

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private StatusReceiptService statusReceiptService;
	
	@GetMapping("/status-receipt")
	public String displayStatusReceipt(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<StatusReceipt> listStatusReceipt = statusReceiptService.displayListStatusReceipt();
		model.addAttribute("listStatusReceipt", listStatusReceipt);
		
		
		return "admin/listStatusReceipt";
	}
	
	
	@GetMapping("/insert-status-receipt")
	public String displayFormInsertStatusReceipt(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		return "admin/formInsertReceiptStatus";
		
	}
	
	@GetMapping("/insert-status-receipt-process")
	public String insertStatusReceipt(@RequestParam("name_status_receipt") String nameStatusReceipt) {
		statusReceiptService.insertStatusReceipt(nameStatusReceipt);
		return "redirect:/admin/status-receipt";
	}
}
