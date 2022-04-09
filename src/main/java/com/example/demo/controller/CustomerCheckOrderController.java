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
import com.example.demo.model.NameAndEmailCustomer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ReceiptDetailService;

@Controller
@RequestMapping("/customer")
public class CustomerCheckOrderController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ReceiptDetailService receiptDetailService;
	
	@GetMapping("/display-check-order")
	public String displayCheckOrder(@RequestParam("idCustomer") Long idCustomer,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndEmailCustomer c = customerService.displayNameAndEmailCustomer(email);
		model.addAttribute("customer", c);
	
		List<informationPhoneAndReceiptDetailFromIdReceipt> listReceipt = receiptDetailService.listReceiptDetailHasNotApprovedByIdCustomer(idCustomer);
		model.addAttribute("listReceiptHaveNotApproved", listReceipt);
		
		long sizeListReceipt = listReceipt.size();
		model.addAttribute("sizeListReceipt", sizeListReceipt);
		return "user/checkStatusOrder";
	
	}
	
	@GetMapping("/display-check-order-payed")
	public String displayCheckOrderPayed(@RequestParam("idCustomer") Long idCustomer,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndEmailCustomer c = customerService.displayNameAndEmailCustomer(email);
		model.addAttribute("customer", c);
	
		List<informationPhoneAndReceiptDetailFromIdReceipt> listReceipt = receiptDetailService.listReceiptDetailApprovedByIdCustomer(idCustomer);
		model.addAttribute("listReceiptApproved", listReceipt);
		
		long sizeListReceipt = listReceipt.size();
		model.addAttribute("sizeListReceipt", sizeListReceipt);
		return "user/checkStatusOrderPayed";
		
	}
}
