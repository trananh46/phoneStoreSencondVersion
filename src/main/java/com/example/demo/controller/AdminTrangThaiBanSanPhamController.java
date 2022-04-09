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

import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.model.TrangThaiPhoneProduct;
import com.example.demo.service.StaffService;
import com.example.demo.service.TrangThaiPhoneProductService;

@Controller
@RequestMapping("/admin_phone")
public class AdminTrangThaiBanSanPhamController {

	@Autowired
	private TrangThaiPhoneProductService trangThaiPhoneProductService;
	
	@Autowired
	private StaffService staffService;
	
	@GetMapping("/trang_thai_ban_san_pham")
	public String displayTrangThai(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		
		List<TrangThaiPhoneProduct> listTrangThaiSanPham = trangThaiPhoneProductService.displayTrangThaiSanPham();
		model.addAttribute("listTrangThaiSanPham", listTrangThaiSanPham);
		return "admin/trangThaiSanPham";
	}
}
