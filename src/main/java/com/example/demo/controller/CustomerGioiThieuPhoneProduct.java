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

import com.example.demo.model.GioiThieu;
import com.example.demo.model.HangDienThoai;
import com.example.demo.model.NameAndEmailCustomer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.GioiThieuService;
import com.example.demo.service.HangDienThoaiService;

@Controller
@RequestMapping("/customer")
public class CustomerGioiThieuPhoneProduct {

	@Autowired
	private GioiThieuService gioiThieuService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private HangDienThoaiService hangDienThoaiService;
	
	@GetMapping("/gioi-thieu-phone-product")
	public String displayGioiThieuPhoneById(@RequestParam("id_gioi_thieu_phone_product") Long idGioiThieuPhoneProduct,Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndEmailCustomer c = customerService.displayNameAndEmailCustomer(email);
		model.addAttribute("customer", c);
			
		List<HangDienThoai> listHangDienThoai = hangDienThoaiService.displayHangDienThoai();
		model.addAttribute("listHangDienThoai", listHangDienThoai);
		
		GioiThieu g = gioiThieuService.findGioiThieuById(idGioiThieuPhoneProduct);
		model.addAttribute("gioiThieu", g);
		
		return "user/gioiThieuPhoneDetail";
	}
}
