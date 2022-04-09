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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.model.HangDienThoai;
import com.example.demo.model.NameAndEmailCustomer;
import com.example.demo.service.AccountService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.HangDienThoaiService;

@Controller
@RequestMapping("/customer")
public class CustomerUserPageController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private HangDienThoaiService hangDienThoaiService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/user-page")
	public String displayUserPage(@RequestParam("id_user") Long idUser,Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndEmailCustomer c = customerService.displayNameAndEmailCustomer(email);
		model.addAttribute("customer", c);
			
		List<HangDienThoai> listHangDienThoai = hangDienThoaiService.displayHangDienThoai();
		model.addAttribute("listHangDienThoai", listHangDienThoai);
		
		Customer c1 = customerService.findCustomerById(idUser);
		model.addAttribute("customerInformation", c1);
		
		Account ac = accountService.findAccountByIdCustomer(idUser);
		model.addAttribute("account", ac);
		
		return "user/userPage";
		
	}
	
	@PostMapping("/update-information-user")
	public String updateInformationUser(@ModelAttribute CustomerDTO c,RedirectAttributes redirectAttributes) {	
		customerService.updateInformationCustomer(c);
		redirectAttributes.addAttribute("id_user", c.getIdCustomer());
		return "redirect:/customer/user-page";
	}
}
