package com.example.demo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.model.Role;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Account account = accountService.findAccountByEmail(email);
		
		if(account != null) {
			Role r = roleService.findRoleByEmail(email);
			Set<String> roleSet = new HashSet<String>();
			roleSet.add("ROLE_"+r.getCodeRole());		
			
			List<GrantedAuthority> authorities =roleSet.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
			return new User(account.getEmail(),account.getPassword(),authorities);
		}		
		return null;		
	}	
}
