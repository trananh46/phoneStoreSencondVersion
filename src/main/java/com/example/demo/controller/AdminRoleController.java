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

import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import com.example.demo.service.StaffService;

@Controller
@RequestMapping("/admin")
public class AdminRoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private StaffService staffService;
	@GetMapping("/role")
	public String displayRole( Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		
		List<Role> listRole = roleService.displayRole();
		model.addAttribute("listRole", listRole);
		return "admin/role";
	}
	
	@GetMapping("/form-update-role")
	public String updateRole(@RequestParam("idRole") Long idRole,Model model) {
		 Role role = roleService.findRoleById(idRole);
		 model.addAttribute("role", role);
		return "admin/formUpdateRole";
	}
	
	@GetMapping("/update-role-process")
	public String updateRoleProcess(@RequestParam("id_role") Long idRole,@RequestParam("name_role_update") String nameRole,@RequestParam("code_role_update") String codeRole) {
		roleService.updateRole(idRole, nameRole, codeRole);
		return "redirect:/admin/role";
	}
	
	@GetMapping("/insert-role")
	public String insertRole(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String email = userDetails.getUsername();
		NameAndRoleStaff s = staffService.displayNameAndRoleStaff(email);
		model.addAttribute("staff", s);
		return "admin/formInsertRole";
	}
	
	@GetMapping("insert-role-process")
	public String insertRoleProcess(@RequestParam("name_role") String nameRole,@RequestParam("code_role") String codeRole) {
		Role role = new Role();
		role.setNameRole(nameRole);
		role.setCodeRole(codeRole);
		roleService.insertRole(role);
		return "redirect:/admin/role";
	}
	
	@GetMapping("delete-role")
	public String deleteRole(@RequestParam("idRole") Long idRole) {
		roleService.deleteRole(idRole);
		return "redirect:/admin/role";
	}
	
}
