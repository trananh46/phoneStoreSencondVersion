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

import com.example.demo.model.KhuyenMai;
import com.example.demo.model.KhuyenMaiPhone;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.model.PhoneProduct;
import com.example.demo.repository.KhuyenMaiPhoneRepository;
import com.example.demo.service.KhuyenMaiService;
import com.example.demo.service.PhoneProductService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin_phone")
public class AdminKhuyenMaiController {

	@Autowired
	private KhuyenMaiService khuyenMaiService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private PhoneProductService phoneProductService;
	

	@GetMapping("/khuyen_mai")
	public String displayKhuyenMai(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<KhuyenMai> l = khuyenMaiService.displayKhuyenMai();
		model.addAttribute("listKhuyenMai", l);
		return "admin/khuyenMai";
	}
	
	@GetMapping("/update_khuyen_mai")
	public String updateKhuyenMai(@RequestParam("idKhuyenMai") Long idKhuyenMai,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		KhuyenMai k = khuyenMaiService.findKhuyenMaiById(idKhuyenMai);
		model.addAttribute("khuyenMai", k);
		return "admin/formUpdateKhuyenMai";
	}
	
	
	@GetMapping("/update_khuyen_mai_process")
	public String updateKhuyenMaiProcess(@RequestParam("name_khuyen_mai") String nameKhuyenMai,@RequestParam("id_khuyen_mai") Long idKhuyenMai) {
		khuyenMaiService.updateKhuyenMai(idKhuyenMai, nameKhuyenMai);
		return "redirect:/admin_phone/khuyen_mai";
	}
	
	@GetMapping("/delete_khuyen_mai")
	public String deleteKhuyenMai(@RequestParam("idKhuyenMai") Long idKhuyenMai) {
		khuyenMaiService.deleteKhuyenMai(idKhuyenMai);
		return "redirect:/admin_phone/khuyen_mai"; 
	}
	
	
	@GetMapping("/khuyen_mai_phone_product_detail")
	public String displayAllKhuyenMaiPhoneDetail(@RequestParam("idPhoneProduct") Long idPhoneProduct,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<KhuyenMai> listKhuyenMai = khuyenMaiService.displayKhuyenMai();
		model.addAttribute("listKhuyenMai", listKhuyenMai);
		
		Long idPhone = idPhoneProduct;
		model.addAttribute("idPhoneProduct", idPhone);
		
		return "admin/formInsertKhuyenMaiPhoneDetail";
	}
	
	@GetMapping("/insert_khuyen_mai_phone_process")
	public String insertKhuyenMaiPhoneProductDetail(@RequestParam("id_phone_product") Long idPhoneProduct,@RequestParam("id_khuyen_mai") Long idKhuyenMai) {
		
		khuyenMaiService.insertKhuyenMaiPhoneDetail(idPhoneProduct, idKhuyenMai);
		return "redirect:/admin_phone/phone_product";
			
	}
}
