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

import com.example.demo.DTO.CommentAndInformation;
import com.example.demo.DTO.count;
import com.example.demo.model.DisplayProductCustomer;
import com.example.demo.model.GioiThieu;
import com.example.demo.model.ImageProduct;
import com.example.demo.model.InformationConfigurePhoneProductCustomer;
import com.example.demo.model.InformationPhoneProductDetailCustomer;
import com.example.demo.model.KhuyenMai;
import com.example.demo.model.NameAndEmailCustomer;
import com.example.demo.model.PhoneProduct;
import com.example.demo.service.CommentService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.GioiThieuService;
import com.example.demo.service.ImageProductService;
import com.example.demo.service.InformationPhoneProductDetailCustomerService;
import com.example.demo.service.KhuyenMaiService;
import com.example.demo.service.PhoneProductService;

@Controller
@RequestMapping("/customer_phone")
public class CustomerPhoneProductDetail {

	@Autowired
	private ImageProductService imageProductService;
	
	@Autowired
	private PhoneProductService phoneProductService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private InformationPhoneProductDetailCustomerService informationPhoneProductDetailCustomerService;
	
	@Autowired
	private KhuyenMaiService khuyenMaiService;
	
	@Autowired
	private GioiThieuService gioiThieuService;
	
	@Autowired
	private CommentService commentService;
	
	
	@GetMapping("/phone_product_detail")
	public String displayPhoneProductDetail(@RequestParam("id_phone_product") Long idPhoneProduct,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndEmailCustomer c = customerService.displayNameAndEmailCustomer(email);
		model.addAttribute("customer", c);
		
		
		List<ImageProduct> l = imageProductService.displayListImagePhoneProductDetailCustomer(idPhoneProduct);
		model.addAttribute("listImagePhoneProductDetail", l);
		
		PhoneProduct p = phoneProductService.findPhoneProductById(idPhoneProduct);
		model.addAttribute("phone", p);
	
		InformationPhoneProductDetailCustomer i = informationPhoneProductDetailCustomerService.displayInformationPhoneProductDetailCustomer(idPhoneProduct);
		model.addAttribute("informationPhoneProduct1", i);
		
		InformationConfigurePhoneProductCustomer i1 = informationPhoneProductDetailCustomerService.displayInformationConfigurePhoneProductCustomer(idPhoneProduct);
		model.addAttribute("informationConfigurePhoneProduct", i1);
		
		
		List<KhuyenMai> listKhuyenMai = khuyenMaiService.displayInformationKhuyenMaiPhoneProductDetailCustomer(idPhoneProduct);
		model.addAttribute("listKhuyenMai",listKhuyenMai);
		
		List<GioiThieu> listGioiThieu = gioiThieuService.displayGioiThieuPhoneProductDetailCustomer(idPhoneProduct);
		model.addAttribute("listGioiThieu", listGioiThieu);
		
		
		List<DisplayProductCustomer> listProductRandom = phoneProductService.displayProductRandomCustomer(idPhoneProduct);
		model.addAttribute("listProductRandom", listProductRandom);
		
		count countTotalComment = commentService.countTotalComment(idPhoneProduct);
		model.addAttribute("countTotalComment", countTotalComment);
		
		List<CommentAndInformation> listMainComment = commentService.displayMainCommentByIdProduct(idPhoneProduct);
		model.addAttribute("listMainComment", listMainComment);
		
		List<CommentAndInformation> listSecondComment = commentService.displaySecondCommentByIdProduct(idPhoneProduct);
		model.addAttribute("listSecondComment", listSecondComment);
		
		return "user/phoneProductDetail";
		
		
	}
	
}
