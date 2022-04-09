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

import com.example.demo.DTO.KhuyenMaiPhoneProduct;
import com.example.demo.model.DisplayProductCustomer;
import com.example.demo.model.HangDienThoai;
import com.example.demo.model.NameAndEmailCustomer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.HangDienThoaiService;
import com.example.demo.service.KhuyenMaiPhoneService;
import com.example.demo.service.PhoneProductService;

@Controller
@RequestMapping("/customer_phone")
public class CustomerHangDienThoaiController {

	@Autowired
	private PhoneProductService phoneProductService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private HangDienThoaiService hangDienThoaiService;
	
	@Autowired
	private KhuyenMaiPhoneService khuyenMaiPhoneService;
	
	@GetMapping("/display_phone_by_hang_dien_thoai")
	public String displayPhoneByHangDienThoai(@RequestParam("idHangDienThoai") Long idHangDienThoai,@RequestParam("nameHangDienThoai") String nameHangDienThoai,Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndEmailCustomer c = customerService.displayNameAndEmailCustomer(email);
		model.addAttribute("customer", c);
		
		List<HangDienThoai> listHangDienThoai = hangDienThoaiService.displayHangDienThoai();
		model.addAttribute("listHangDienThoai", listHangDienThoai);
		
		
		List<DisplayProductCustomer> listPhoneByHangDienThoai = phoneProductService.displayPhoneByHangDienThoai(idHangDienThoai);
		model.addAttribute("listProductCustomer", listPhoneByHangDienThoai);
		
		
		List<KhuyenMaiPhoneProduct> listKhuyenMaiByEachPhoneProduct = khuyenMaiPhoneService.displayKhuyenMaiByEachPhoneProduct();
		model.addAttribute("listKhuyenMaiByEachPhoneProduct", listKhuyenMaiByEachPhoneProduct);
		
		String nameHangDienThoai1 = nameHangDienThoai;
		model.addAttribute("nameHangDienThoai", nameHangDienThoai1);
		
		
		
		return "user/listPhoneByHangDienThoai";
	}
}
