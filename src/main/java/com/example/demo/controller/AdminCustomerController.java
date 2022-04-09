package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.CustomerList2;
import com.example.demo.model.CustomerUpdate;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.service.AccountService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin")
public class AdminCustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private StaffService staffService;
	@GetMapping("/list-customer")
	public String displayCustomer(Model model) {
		List<CustomerList2> listCustomers = customerService.displayCustomer();
		model.addAttribute("listCustomer", listCustomers);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		return "admin/listCustomer";
	}
	
	@GetMapping("/update-customer")
	public String updateCustomer(@RequestParam("idCustomer") Long idCustomer,Model model) {
		CustomerUpdate customer = customerService.findCustomerByIdToUpdate(idCustomer);
		model.addAttribute("customer", customer);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		return "admin/formUpdateCustomer";
	}
	
	
	@PostMapping("/update-customer-process")
	public String updateCustomerProcess(@RequestParam("id_customer") Long idCustomer,@RequestParam("name_customer_update") String nameCustomerUpdate,@RequestParam("email_update") String emaiUpdate,@RequestParam("password_update") String passwordUpdate,@RequestParam("sdt_update") String sdtUpdate) {
		customerService.updateCustomerById(idCustomer, nameCustomerUpdate, sdtUpdate);
		accountService.updateAccountCustomer(idCustomer, emaiUpdate, passwordUpdate);
		return"redirect:/admin/list-customer";
	}
	
	
	@GetMapping("/delete-customer-and-account")
	public String deleteCustomerAndAccount(@RequestParam("idCustomer") Long idCustomer) {
		accountService.deleteAccountCustomer(idCustomer);
		customerService.deleteCustomerById(idCustomer);
		return "redirect:/admin/list-customer";
	}
}
