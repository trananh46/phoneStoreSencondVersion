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

import com.example.demo.DTO.CommentAndInformation;
import com.example.demo.model.Comment;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.service.CommentService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin")
public class AdminCommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private StaffService staffService;
	@PostMapping("/insert-comment-customer")
	public String insertCommentCustomer(@ModelAttribute Comment c,RedirectAttributes redirectAttributes){
		commentService.insertCommentCustomer(c);
		
		redirectAttributes.addAttribute("id_phone_product", c.getPhoneProduct());
		return "redirect:/customer_phone/phone_product_detail";
	}
	
	
	@GetMapping("/all-comment-of-customer")
	public String displayAllCommentOfCustomer(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<CommentAndInformation> listComment = commentService.displayAllCommentOfCustomer();
		model.addAttribute("listComment", listComment);
		return "admin/listCommentOfCustomer";
	}
	
	
	@GetMapping("/all-comment-detail")
	public String displayCommentDetailByIdComment(@RequestParam("id_comment") Long idComment,Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		CommentAndInformation maintComment = commentService.findMainCommentByIdComment(idComment);
		model.addAttribute("maintComment", maintComment);
		
		List<CommentAndInformation> listSecondComment = commentService.displaySecondCommentByIdComment(idComment);
		model.addAttribute("secondComment", listSecondComment);
		
		return "admin/commentDetail";

	}
	
	@GetMapping("/reply-comment-of-customer")
	public String replyCommentOfCustomer(@ModelAttribute Comment c,RedirectAttributes redirectAttributes) {
		commentService.replyCommentOfCustomer(c);
		redirectAttributes.addAttribute("id_comment", c.getIdCommentDetail());
		return "redirect:/admin/all-comment-detail"; 
	}
	
	@GetMapping("/list-comment-of-customer-has-not-replied")
	public String displayListCommentOfCustomerHasNotReplied(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<CommentAndInformation> listComment = commentService.displayListCommentOfCustomerHasNotReplied();
		model.addAttribute("listComment", listComment);
		return "admin/listCommentHasNotReplied";
	}
	
	@GetMapping("/list-comment-of-customer-replied")
	public String displayListCommentOfCustomerReplied(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<CommentAndInformation> listComment = commentService.displayListCommentOfCustomerReplied();
		model.addAttribute("listComment", listComment);
		return "admin/listCommentReplied";
	}
	
	@GetMapping("/delete-second-comment-by-id")
	public String deleteSecondCommentById(@RequestParam("id_second_comment") Long idSecondComment,@RequestParam("id_main_comment") Long idMainComment,RedirectAttributes redirectAttributes) {	
		commentService.deleteCommentById(idSecondComment);
		
		redirectAttributes.addAttribute("id_comment", idMainComment);
		return "redirect:/admin/all-comment-detail";
	}
	
	@GetMapping("/delete-main-comment-by-id")
	public String deleteMainCommentById(@RequestParam("id_comment") Long idComment) {	
		commentService.deleteMainCommentById(idComment);
		return "redirect:/admin/all-comment-of-customer";
	}
}
