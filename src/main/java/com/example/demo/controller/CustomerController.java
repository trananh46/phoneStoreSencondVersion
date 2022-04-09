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

import com.example.demo.DTO.KhuyenMaiPhoneProduct;
import com.example.demo.model.DisplayProductCustomer;
import com.example.demo.model.GioiThieu;
import com.example.demo.model.HangDienThoai;
import com.example.demo.model.NameAndEmailCustomer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.GioiThieuService;
import com.example.demo.service.HangDienThoaiService;
import com.example.demo.service.KhuyenMaiPhoneService;
import com.example.demo.service.PhoneProductService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PhoneProductService phoneProductService;
	
	@Autowired
	private HangDienThoaiService hangDienThoaiService;
	
	@Autowired
	private KhuyenMaiPhoneService khuyenMaiPhoneService;
	
	@Autowired
	private GioiThieuService gioiThieuService;
	
	@GetMapping("/home")
	public String home(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndEmailCustomer c = customerService.displayNameAndEmailCustomer(email);
		model.addAttribute("customer", c);
			
		List<HangDienThoai> listHangDienThoai = hangDienThoaiService.displayHangDienThoai();
		model.addAttribute("listHangDienThoai", listHangDienThoai);
		
		List<DisplayProductCustomer> l = phoneProductService.displayProductCustomer();
		model.addAttribute("listProductCustomer", l);
		
		List<KhuyenMaiPhoneProduct> listKhuyenMaiByEachPhoneProduct = khuyenMaiPhoneService.displayKhuyenMaiByEachPhoneProduct();
		model.addAttribute("listKhuyenMaiByEachPhoneProduct", listKhuyenMaiByEachPhoneProduct);
		
		List<GioiThieu> listGioiThieuPhone = gioiThieuService.displayGioiThieuPhoneInCustomerPage();
		model.addAttribute("listGioiThieuPhone", listGioiThieuPhone);
		
		
		return "user/index";
	}
	
}
