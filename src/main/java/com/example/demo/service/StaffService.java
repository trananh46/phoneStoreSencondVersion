package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.ListStaff;
import com.example.demo.model.ListStaff2;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.model.Role;
import com.example.demo.model.Staff;
import com.example.demo.model.StaffUpdate;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.StaffRepository;

@Service
public class StaffService {
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public List<ListStaff2> displayStaff(){
		List<ListStaff>  listStaff =  staffRepository.displayStaff();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<ListStaff2> listStaff2 = new ArrayList<ListStaff2>();
		
		for(ListStaff ls : listStaff) {
			ListStaff2 l = modelMapper.map(listStaff,ListStaff2.class);
			l.setId_staff(ls.getId_staff());
			l.setName_staff(ls.getName_staff());
			l.setEmail(ls.getEmail());
			l.setRole_name(ls.getRole_name());
			l.setSdt(ls.getSdt());
			listStaff2.add(l);
		}
		
		return listStaff2;
	}
	
	public void insertStaff(String nameStaff,String email,String password,String sdt,Long idRole) {
		
		//Insert nhân viên vào bảng staff
		Staff s = new Staff();
		s.setNameStaff(nameStaff);
		s.setSdt(sdt);
		staffRepository.save(s);
		
		// tìm xem nhân viên vừa nhập có tồn tại trong database ko
		Staff staff =  staffRepository.findStaffByNameAndPhoneNumber(nameStaff, sdt);	
		// tìm role mà nhân viên có
		Role r = roleService.findRoleById(idRole);		
		//Insert nhân viên vừa thêm vào bảng account
		accountService.InsertAccountStaff(staff, r, email, password);	
	}
	
	
	public StaffUpdate findStaffByIdToUpdate(Long idStaff) {
		StaffUpdate s = staffRepository.findStaffByIdToUpdate(idStaff);
		return s;
	}
	
	public void updateStaff(Long idStaff,String nameStaff,String sdt) {
		staffRepository.updateStaff(idStaff, nameStaff, sdt);
	}
	
	public void deleteStaff(Long idStaff) {
		staffRepository.deleteStaff(idStaff);
	}
	
	public NameAndRoleStaff displayNameAndRoleStaff(String email) {
		NameAndRoleStaff s = staffRepository.displayNameAndRoleStaff(email);
		return s;
	}
}
