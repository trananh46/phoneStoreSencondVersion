package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.KhuyenMai;
import com.example.demo.model.KhuyenMaiPhone;
import com.example.demo.model.PhoneProduct;
import com.example.demo.repository.KhuyenMaiPhoneRepository;
import com.example.demo.repository.KhuyenMaiRepository;

@Service
public class KhuyenMaiService {

	@Autowired
	private KhuyenMaiRepository khuyenMaiRepository;
	
	@Autowired
	private PhoneProductService phoneProductService;
	
	@Autowired
	private KhuyenMaiService khuyenMaiService;
	
	@Autowired
	private KhuyenMaiPhoneRepository khuyenMaiPhoneRepository;
	
	public List<KhuyenMai> displayKhuyenMai(){
		List<KhuyenMai> l = khuyenMaiRepository.displayKhuyenMai();
		return l;
	}
	
	public KhuyenMai findKhuyenMaiById(Long idKhuyenMai) {
		KhuyenMai k = khuyenMaiRepository.findKhuyenMaiById(idKhuyenMai);
		return k;
	}
	
	public void updateKhuyenMai(Long idKhuyenMai,String nameKhuyenMai) {
		khuyenMaiRepository.updateKhuyenMai(idKhuyenMai, nameKhuyenMai);
	}
	
	public void deleteKhuyenMai(Long idKhuyenMai) {
		khuyenMaiRepository.deleteKhuyenMai(idKhuyenMai);
	}
	
	
	public void insertKhuyenMaiPhoneDetail(Long idPhoneProduct,Long idKhuyenMai) {
		PhoneProduct p = phoneProductService.findPhoneProductById(idPhoneProduct);
		KhuyenMai km = khuyenMaiService.findKhuyenMaiById(idKhuyenMai);
		
		KhuyenMaiPhone k = new KhuyenMaiPhone();
		k.setKhuyenMai(km);
		k.setPhoneProduct3(p);
		khuyenMaiPhoneRepository.save(k);
	}
	
	
	public List<KhuyenMai> displayInformationKhuyenMaiPhoneProductDetailCustomer(Long idPhoneProduct){
		List<KhuyenMai> l = khuyenMaiRepository.displayInformationKhuyenMaiPhoneProductDetailCustomer(idPhoneProduct);
		return l;
	}
}
