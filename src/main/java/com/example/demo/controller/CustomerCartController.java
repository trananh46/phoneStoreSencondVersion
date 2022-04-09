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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.NameAndEmailCustomer;
import com.example.demo.model.TotalPriceProjectionOfReceipt;
import com.example.demo.repository.PhoneProductInCart;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ReceiptDetailService;

@Controller
@RequestMapping("/customer")
public class CustomerCartController {

	@Autowired
	private ReceiptDetailService receiptDetailService;

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/cart")
	public String displayCart(@RequestParam("id_customer") Long idCustomer,Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndEmailCustomer c = customerService.displayNameAndEmailCustomer(email);
		model.addAttribute("customer", c);
			
		List<PhoneProductInCart> listPhoneProductInCart = receiptDetailService.displayReceiptDetail(idCustomer);
		int countPhoneProductInCart = listPhoneProductInCart.size();

		TotalPriceProjectionOfReceipt t = receiptDetailService.displayTotalPriceProjectionOfReceipt(idCustomer);

		model.addAttribute("countPhoneProductInCart", countPhoneProductInCart);
		model.addAttribute("listPhoneProductInCart", listPhoneProductInCart);
		model.addAttribute("totalPriceProjectionOfReceipt", t);
		return "user/cart";
	
	}
	
	@GetMapping("/increase-quantity-phone-product")
	public String increaseQuantity(@RequestParam("id_receipt_detail") Long idReceiptDetail,@RequestParam("id_san_pham") Long idSanPham,@RequestParam("id_customer") Long idCustomer,RedirectAttributes redirectAttributes) {
		
		receiptDetailService.increaseQuantity(idReceiptDetail, idSanPham);
		
		redirectAttributes.addAttribute("id_customer", idCustomer);
		return "redirect:/customer/cart";
	}

	
	@GetMapping("/decrease-quantity-phone-product")
	public String decreaseQuantity(@RequestParam("id_receipt_detail") Long idReceiptDetail,@RequestParam("id_san_pham") Long idSanPham,@RequestParam("id_customer") Long idCustomer,RedirectAttributes redirectAttributes) {
		
		receiptDetailService.decreaseQuantity(idReceiptDetail, idSanPham);
		
		redirectAttributes.addAttribute("id_customer", idCustomer);
		return "redirect:/customer/cart";
	}
}
