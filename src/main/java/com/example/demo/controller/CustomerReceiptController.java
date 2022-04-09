package com.example.demo.controller;

import java.time.LocalDate;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Customer;
import com.example.demo.model.NameAndEmailCustomer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ReceiptDetailService;
import com.example.demo.service.ReceiptService;

@Controller
@RequestMapping("/customer_receipt")
public class CustomerReceiptController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ReceiptService receiptService;

	@Autowired
	private ReceiptDetailService receiptDetailService;

	@GetMapping("/create_receipt_customer")
	public String createReceiptCustomer(@RequestParam("id_product") Long idPhoneProduct, Model model,
			RedirectAttributes redirectAttributes) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndEmailCustomer c = customerService.displayNameAndEmailCustomer(email);
		model.addAttribute("customer", c);

		Customer c1 = customerService.findCustomerByEmail(email);
		Long idCustomer = c1.getIdCustomer();

		Long countReceiptStatusNullAndDateNull = receiptService.countReceiptStatusNullAndDateNull();

		if (countReceiptStatusNullAndDateNull == 0) {
			// store a new receipt without status,data,note
			receiptService.createReceiptCustomer(idCustomer);

			// when we have a receipt we will find the receipt that without status,data and
			// then create the detail receipt
			receiptDetailService.createReceiptDetail(idPhoneProduct);

			// we will create a parameter to inform to user that they have put product on
			// cart successfully
			String notification = "Thêm Sản Phẩm Vào Giỏ Thành Công ";
			model.addAttribute("notification", notification);

			redirectAttributes.addAttribute("id_phone_product", idPhoneProduct);
			return "redirect:/customer_phone/phone_product_detail";

		} else if (countReceiptStatusNullAndDateNull == 1) {
			// when we have a receipt we will find the receipt that without status,data and
			// then create the detail receipt
			receiptDetailService.createReceiptDetail(idPhoneProduct);

			// we will create a parameter to inform to user that they have put product on 
			//cart successfully
			String notification = "Thêm Sản Phẩm Vào Giỏ Thành Công ";
			model.addAttribute("notification", notification);

			redirectAttributes.addAttribute("id_phone_product", idPhoneProduct);
			return "redirect:/customer_phone/phone_product_detail";
		}else {
			return "/customer/home";
		}
	}
	
	@GetMapping("/delete-receipt-detail-by-id")
	public String deleteReceiptDetailById(@RequestParam("id_receipt_detail") Long idReceiptDetail,@RequestParam("id_customer") Long idCustomer,RedirectAttributes redirectAttributes) {
		receiptDetailService.deleteReceiptDetailById(idReceiptDetail);
		redirectAttributes.addAttribute("id_customer", idCustomer);
		return "redirect:/customer/cart";
	}
	
	@GetMapping("/update-receipt-by-id")
	public String updateReceipt(@RequestParam("id_customer") Long idCustomer,Model model) {
		
		long statusBuying =  receiptService.updateReceipt(idCustomer);
		if(statusBuying == 0) {
			model.addAttribute("status", statusBuying);
			return "user/completedBuying";
		}else {
			model.addAttribute("status", statusBuying);
			return "user/completedBuying";
		}
		
	}
}
