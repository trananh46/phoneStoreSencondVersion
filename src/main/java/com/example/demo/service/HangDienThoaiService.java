package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.model.HangDienThoai;
import com.example.demo.model.NameAndRoleStaff;
import com.example.demo.repository.HangDienThoaiRepository;

@Service
public class HangDienThoaiService {

	@Autowired
	private HangDienThoaiRepository hangDienThoaiRepository;
	
	@Autowired
	private StaffService staffService;
	public List<HangDienThoai> displayHangDienThoai(){		
		List<HangDienThoai> listHangDienThoai = hangDienThoaiRepository.displayHangDienThoai();
		return listHangDienThoai;
	}
	
	
	
	public void insertHangDienThoai(String nameHangDienThoai,String image) {
		HangDienThoai h = new HangDienThoai();
		h.setNameHangDienThoai(nameHangDienThoai);
		h.setImage(image);
		hangDienThoaiRepository.save(h);
	}
	
	
	public void deleteHangDienThoai(Long idHangDienThoai) {
		hangDienThoaiRepository.deleteHangDienThoaiById(idHangDienThoai);
	}
	
	
	public void updateHangDienThoai(String nameHangDienThoai,Long idHangDienThoai) {
		hangDienThoaiRepository.updateHangDienThoai(nameHangDienThoai,idHangDienThoai);
	}
	
	public HangDienThoai findHangDienThoaiById(Long idHangDienThoai) {
		HangDienThoai h = hangDienThoaiRepository.findHangDienThoaiById(idHangDienThoai);
		return h;
	}
	
	public List<HangDienThoai> listHangDienThoaiToUpdatePhoneProduct(Long idHangDienThoai){
		List<HangDienThoai> listHangDienThoai = hangDienThoaiRepository.listHangDienThoaiToUpdatePhoneProduct(idHangDienThoai);
		return listHangDienThoai;
	}
}
