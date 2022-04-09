package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerList;
import com.example.demo.model.CustomerList2;
import com.example.demo.model.CustomerUpdate;
import com.example.demo.model.NameAndEmailCustomer;
import com.example.demo.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AccountService accountService;
	
	public void insertCustomer(String nameCustomer,String email,String password,String sdt) {
		Customer c = new Customer();
		c.setNameCustomer(nameCustomer);
		c.setPhoneNumber(sdt);
		customerRepository.save(c);
		
		// tìm khách hàng vừa được insert vào database
		Customer customer = customerRepository.findCustomerByNameAndPhoneNumber(nameCustomer, sdt);
		// thêm tài khoản khách hàng
		accountService.InsertAccountCustomer(customer, email, password);
	}
	
	public List<CustomerList2> displayCustomer(){
		List<CustomerList> listCustomer = customerRepository.displayCustomer();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<CustomerList2> listCustomer2 = new ArrayList<CustomerList2>();
		
		for(CustomerList info:listCustomer) {
			CustomerList2 c = modelMapper.map(listCustomer, CustomerList2.class);			
			c.setId_customer(info.getId_customer());
			c.setName_customer(info.getName_customer());
			c.setEmail(info.getEmail());
			c.setPhone_number(info.getPhone_number());
			listCustomer2.add(c);
		}
		return listCustomer2;
	}
	
	
	public CustomerUpdate findCustomerByIdToUpdate(Long idCustomer) {
		CustomerUpdate customer = customerRepository.findCustomerToUpdate(idCustomer);
		return customer;
	}
	
	
	public void updateCustomerById(Long idCustomer,String nameCustomer,String sdt) {
		customerRepository.updateCustomerById(idCustomer, nameCustomer, sdt);
	}
	
	
	public void deleteCustomerById(Long idCustomer) {
		customerRepository.deleteCustomerById(idCustomer);
	}
	
	public NameAndEmailCustomer displayNameAndEmailCustomer(String email) {
		NameAndEmailCustomer c = customerRepository.displayNameAndEmailCustomer(email);
		return c;
	}
	
	
	public Customer findCustomerByEmail(String email) {
		Customer c = customerRepository.findCustomerByEmail(email);
		return c;
	}
	
	public Customer findCustomerById(Long idCustomer) {
		Customer c = customerRepository.findCustomerById(idCustomer);
		return c;
	}
	
	
	public void updateInformationCustomer(CustomerDTO c) {
		
		Customer c1 = customerRepository.findCustomerById(c.getIdCustomer());
		c1.setNameCustomer(c.getNameCustomer().toUpperCase());
		c1.setCity(c.getCity().toUpperCase());
		c1.setDistrict(c.getDistrict().toUpperCase());
		c1.setPhuongXa(c.getPhuongXa().toUpperCase());
		c1.setAddress(c.getAddress().toUpperCase());
		c1.setPhoneNumber(c.getPhoneNumber());
		c1.setBirthday(LocalDate.parse(c.getBirthday()));
		customerRepository.save(c1);
	}
}
