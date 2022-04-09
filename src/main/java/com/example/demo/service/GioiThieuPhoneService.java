package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.GioiThieu;
import com.example.demo.model.GioiThieuPhone;
import com.example.demo.model.GioiThieuPhoneProductDetail;
import com.example.demo.model.PhoneProduct;
import com.example.demo.repository.GioiThieuPhoneRepository;

@Service
public class GioiThieuPhoneService {

	@Autowired
	private GioiThieuPhoneRepository gioiThieuPhoneRepository;
	
	@Autowired
	private GioiThieuService gioiThieuService;
	
	@Autowired
	private PhoneProductService phoneProductService;
	
	public void insertGioiThieuPhone(Long idGioiThieu,Long idPhoneProduct) {
		
		GioiThieu g = gioiThieuService.findGioiThieuById(idGioiThieu);
		
		PhoneProduct p = phoneProductService.findPhoneProductById(idPhoneProduct);
		
		GioiThieuPhone gt = new GioiThieuPhone();
		gt.setGioiThieu(g);
		gt.setPhoneProduct(p);
		gioiThieuPhoneRepository.save(gt);
		
	}
	
	public List<GioiThieuPhoneProductDetail> displayGioiThieuPhoneProductDetailByIdPhone(Long idPhoneProduct){
		List<GioiThieuPhoneProductDetail> l = gioiThieuPhoneRepository.displayGioiThieuPhoneProductDetailByIdPhone(idPhoneProduct);
		return l;
	}
	
	public void deleteGioiThieuPhoneProductDetail(Long idGioiThieuPhone) {
		gioiThieuPhoneRepository.deleteGioiThieuPhoneProductDetail(idGioiThieuPhone);
	}
}
