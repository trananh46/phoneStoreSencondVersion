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

import com.example.demo.DTO.StatisticBudget;
import com.example.demo.DTO.StatisticBudgetByKindOfPhone;
import com.example.demo.DTO.StatisticBudgetDTO;
import com.example.demo.DTO.informationPhoneAndReceiptDetailFromIdReceipt;
import com.example.demo.DTO.year;
import com.example.demo.model.HangDienThoai;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.model.PhoneProductAndDetail;
import com.example.demo.service.HangDienThoaiService;
import com.example.demo.service.StaffService;
import com.example.demo.service.StatisticPhoneProductService;

@Controller
@RequestMapping("/admin")
public class AdminStatisticPhoneProductController {

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private StatisticPhoneProductService statisticPhoneProductService;
	
	@Autowired
	private HangDienThoaiService hangDienThoaiService;
	
	@GetMapping("/statistic-phone-product")
	public String displayStatisticPhoneProduct(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<StatisticBudget> listStatisticBudgetYear = statisticPhoneProductService.statisticBudgetYear();
		model.addAttribute("listStatisticBudgetYear", listStatisticBudgetYear);
		
		List<year> displayListYear = statisticPhoneProductService.displayListYearsOfReceipt();
		model.addAttribute("years", displayListYear);
		
		List<HangDienThoai> listHangDienThoai = hangDienThoaiService.displayHangDienThoai();
		model.addAttribute("hangDienThoai", listHangDienThoai);
		
		return "admin/statisticPhoneProduct";
	}
	
	
	@GetMapping("/statistic-phone-product-by-every-month")
	public String displayStatisticPhoneProductByEveryMonth(@RequestParam("id_year") Long year,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<StatisticBudgetDTO> listStatisticBudgetMonth = statisticPhoneProductService.statisticBudgetMonth(year);
		model.addAttribute("listStatisticBudgetMonth", listStatisticBudgetMonth);
		
		List<year> displayListYear = statisticPhoneProductService.displayListYearsOfReceipt();
		model.addAttribute("years", displayListYear);
		
		List<HangDienThoai> listHangDienThoai = hangDienThoaiService.displayHangDienThoai();
		model.addAttribute("hangDienThoai", listHangDienThoai);
		
		model.addAttribute("year", year);
		return "admin/statisticPhoneProductByMonths";
	}
	
	
	@GetMapping("/list-phone-product-by-year-and-month")
	public String displayListPhoneProductByYearAndMonth(@RequestParam("year") Long year,@RequestParam("month") Long month,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		
		List<informationPhoneAndReceiptDetailFromIdReceipt> listPhoneProduct = statisticPhoneProductService.displayListPhoneProductByYearAndMonth(year, month);
		model.addAttribute("listPhoneProduct", listPhoneProduct);
		
		
		return "admin/listPhoneProductHasBought";

	}
	
	@GetMapping("/display-statistic-by-kind-of-phone")
	public String displayStatisticByKindOfPhone(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<year> displayListYear = statisticPhoneProductService.displayListYearsOfReceipt();
		model.addAttribute("years", displayListYear);
		
		List<HangDienThoai> listHangDienThoai = hangDienThoaiService.displayHangDienThoai();
		model.addAttribute("hangDienThoai", listHangDienThoai);
		
		List<StatisticBudgetByKindOfPhone> listStatisticByKindOfPhone = statisticPhoneProductService.displayStatisticByKindOfPhone();
		model.addAttribute("statisticByHangDienThoai", listStatisticByKindOfPhone);
		return "admin/statisticByKindOfPhone";

	}
	
	
	@GetMapping("/list-phone-product-by-id-hang-dien-thoai")
	public String displayListPhoneProductByYearAndMonth(@RequestParam("id_hang_dien_thoai") Long idHangDienThoai,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<informationPhoneAndReceiptDetailFromIdReceipt> listPhoneProduct = statisticPhoneProductService.displayListPhoneProductByIdHangDienThoai(idHangDienThoai);
		model.addAttribute("listPhoneProduct", listPhoneProduct);
		
		return "admin/listPhoneProductHasBought";

	}
}
