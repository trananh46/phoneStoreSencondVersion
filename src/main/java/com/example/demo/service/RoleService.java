package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> displayRole(){
		List<Role> listRole = roleRepository.displayRole();
		return listRole;
	}
	
	public Role findRoleById(Long idRole) {
		Role r = roleRepository.findRoleById(idRole);
		return r;
	}
	
	public void updateRole(Long idRole,String nameRole,String codeRole) {
		roleRepository.updateRole(idRole, nameRole, codeRole);
	}
	
	public void insertRole(Role role) {
		roleRepository.save(role);
	}
	
	public void deleteRole(Long idRole) {
		roleRepository.deleteRole(idRole);
	}
	
	
	public Role findRoleByRoleCode(String roleCode) {
		Role r = roleRepository.findRoleByRoleCode(roleCode);
		return r;
	}
	
	
	public Role findRoleByEmail(String email) {
		Role r = roleRepository.findRoleByEmail(email);
		return r;
	}
}
