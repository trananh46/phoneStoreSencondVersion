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

import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.repository.ReceiptAndInfromationCustomer;
import com.example.demo.service.ReceiptService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin_phone")
public class AdminReceiptController {

	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private StaffService staffService;
	@GetMapping("/display-receipt-has-not-approve-yet-customer")
	public String displayListReceiptHasNotApprovedYet(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<ReceiptAndInfromationCustomer> listReceiptHasNotApprovedYet = receiptService.displayListReceiptHasNotApprovedYet();
		model.addAttribute("listReceiptHasNotApprovedYet", listReceiptHasNotApprovedYet);
		return "admin/receiptOfCustomerHasNotApprovedYet";
	}
	
	@GetMapping("/approve-receipt")
	public String approveReceipt(@RequestParam("idReceipt") Long idReceipt) {
		receiptService.approveReceipt(idReceipt);
		return "redirect:/admin_phone/display-receipt-has-not-approve-yet-customer";
	}
	
	@GetMapping("/receipt-customer-approved")
	public String receiptOfCustomerApproved(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<ReceiptAndInfromationCustomer> listReceiptApproved = receiptService.displayListReceiptApproved();
		model.addAttribute("listReceiptApproved", listReceiptApproved);
		
		return "admin/receiptOfCustomerApproved";
	
	}
	
	@GetMapping("/delete-receipt")
	public String deleteReceipt(@RequestParam("idReceipt") Long idReceipt) {
		receiptService.deleteReceiptById(idReceipt);
		return "redirect:/admin_phone/display-receipt-has-not-approve-yet-customer";
	}
	

	
}
