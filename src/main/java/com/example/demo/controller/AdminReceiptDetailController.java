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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.informationPhoneAndReceiptDetailFromIdReceipt;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.service.ReceiptDetailService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin")
public class AdminReceiptDetailController {

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private ReceiptDetailService receiptDetailService;
	
	@GetMapping("/list-receipt-detail-by-id-receipt")
	public String displayListReceiptDetailByIdReceipt(@RequestParam("idReceipt") Long idReceipt,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<informationPhoneAndReceiptDetailFromIdReceipt> listReceipt = receiptDetailService.displayListReceiptDetailByIdReceipt(idReceipt);
		model.addAttribute("listReceipt", listReceipt);
		
		return "admin/listReceiptDetailByIdReceipt";
	}
}
