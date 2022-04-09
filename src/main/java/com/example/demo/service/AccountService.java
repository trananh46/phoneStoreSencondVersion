package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.model.AccountCustomer;
import com.example.demo.model.AccountCustomer2;
import com.example.demo.model.AccountStaff;
import com.example.demo.model.AccountStaff2;
import com.example.demo.model.Customer;
import com.example.demo.model.Role;
import com.example.demo.model.Staff;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private RoleService roleService;
	
	public void InsertAccountStaff(Staff s,Role r,String email,String password) {
		Account ac = new Account();
		ac.setStaff(s);
		ac.setEmail(email);
		ac.setPassword(bCryptPasswordEncoder.encode(password));
		ac.setRole(r);
		ac.setUserType((long)0);
		accountRepository.save(ac);
	}
	
	public void InsertAccountCustomer(Customer c,String email,String password) {
		
		// Tìm role có roleCode = USER
		Role r = roleService.findRoleByRoleCode("USER");
		// Insert account của khách hàng
		Account ac = new Account();
		ac.setCustomer(c);
		ac.setEmail(email);
		ac.setPassword(bCryptPasswordEncoder.encode(password));
		ac.setRole(r);
		ac.setUserType((long)1);
		accountRepository.save(ac);
	}
	
	
	public List<AccountCustomer2> displayAccountCustomer(){
		List<AccountCustomer> ac =  accountRepository.displayAccountCustomer();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<AccountCustomer2> ac2 = new ArrayList<AccountCustomer2>();
		
		for(AccountCustomer info : ac) {
			AccountCustomer2 a = modelMapper.map(ac, AccountCustomer2.class);
			a.setId_account(info.getId_account());
			a.setName_customer(info.getName_customer());
			a.setEmail(info.getEmail());
			a.setPassword(info.getPassword());
			ac2.add(a);
		}
		return ac2;
	}
	
	public List<AccountStaff2> displayAccountStaff(){
		List<AccountStaff> ac = accountRepository.displayAccountStaff();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<AccountStaff2> ac2 = new ArrayList<AccountStaff2>();
		
		for(AccountStaff info : ac) {
			AccountStaff2 a = modelMapper.map(ac, AccountStaff2.class);
			a.setId_account(info.getId_account());
			a.setName_staff(info.getName_staff());
			a.setEmail(info.getEmail());
			a.setPassword(info.getPassword());
			ac2.add(a);
		}
		
		return ac2;
	}
	
	public void updateAccount(Long idStaff,String email,String password,Long idRole) {
		accountRepository.updateAccount(idStaff, email, password, idRole);
	}
	
	public void deleteAccount(Long idStaff) {
		accountRepository.deleteAccount(idStaff);
	}
	
	public void updateAccountCustomer(Long idCustomer,String email,String password) {
		accountRepository.updateAccountCustomer(idCustomer, email, password);
	}
	
	
	public void deleteAccountCustomer(Long idCustomer) {
		accountRepository.deleteAccountCustomer(idCustomer);
	}
	
	
	public Account findAccountByEmail(String email) {
		Account ac = accountRepository.findAccountByEmail(email);
		return ac;
	}
	
	public Account findAccountByIdCustomer(Long idCustomer) {
		Account ac = accountRepository.displayAccountByIdCustomer(idCustomer);
		return ac;
	}
}
