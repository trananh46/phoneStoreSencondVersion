package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ListStaff2;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.model.Role;
import com.example.demo.model.StaffUpdate;
import com.example.demo.service.AccountService;
import com.example.demo.service.RoleService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin")
public class AdminStaffController {

	@Autowired
	private StaffService staffService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/home")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		return "admin/ListStaff";
	}

	@GetMapping("/staff")
	public String displayStaff(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		List<ListStaff2> listStaffs = staffService.displayStaff();
		model.addAttribute("listStaff", listStaffs);
		return "admin/listStaff";
	}

	@GetMapping("/insert-staff")
	public String insertStaff(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		List<Role> listRole = roleService.displayRole();
		model.addAttribute("listRole", listRole);
		return "admin/formInsertStaff";
	}

	@GetMapping("/insert-staff-process")
	public String insertStaffProcess(@RequestParam("name_staff") String nameStaff, @RequestParam("email") String email,
			@RequestParam("password") String password, @RequestParam("sdt") String sdt,
			@RequestParam("id_role") Long idRole) {
		staffService.insertStaff(nameStaff, email, password, sdt, idRole);
		return "redirect:/admin/staff";
	}

	@GetMapping("/update-staff")
	public String updateStaff(@RequestParam("idStaff") Long idStaff, Model model) {
		StaffUpdate s = staffService.findStaffByIdToUpdate(idStaff);
		model.addAttribute("s", s);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff staff = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", staff);
		
		List<Role> listRole = roleService.displayRole();
		model.addAttribute("listRole", listRole);
		return "admin/formUpdateStaff";
	}

	@PostMapping("/update-staff-process")
	public String updateStaffProcess(@RequestParam("id_staff") Long idStaff,
			@RequestParam("name_staff_update") String nameStaffUpdate, @RequestParam("email_update") String emailUpdate,
			@RequestParam("password_update") String passwordUpdate, @RequestParam("sdt_update") String sdt,
			@RequestParam("id_role_update") Long idRoleUpdate) {
		staffService.updateStaff(idStaff, nameStaffUpdate, sdt);
		accountService.updateAccount(idStaff, emailUpdate, passwordUpdate, idRoleUpdate);
		return "redirect:/admin/staff";
	}

	@GetMapping("/delete-staff-and-account")
	public String deleteStaffAndAccount(@RequestParam("idStaff") Long idStaff) {
		accountService.deleteAccount(idStaff);
		staffService.deleteStaff(idStaff);
		return "redirect:/admin/staff";
	}
}
