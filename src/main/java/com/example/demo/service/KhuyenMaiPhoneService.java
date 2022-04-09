package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.KhuyenMaiPhoneProduct;
import com.example.demo.model.KhuyenMaiPhoneProductDetail;
import com.example.demo.repository.KhuyenMaiPhoneRepository;

@Service
public class KhuyenMaiPhoneService {

	@Autowired
	private KhuyenMaiPhoneRepository khuyenMaiPhoneRepository;
	
	public List<KhuyenMaiPhoneProductDetail> displayKhuyenMaiPhoneByIdPhone(Long idPhoneProduct){
		List<KhuyenMaiPhoneProductDetail> l = khuyenMaiPhoneRepository.displayKhuyenMaiByIdPhone(idPhoneProduct);
		return l;
	}
	
	
	public List<KhuyenMaiPhoneProduct> displayKhuyenMaiByEachPhoneProduct(){
		List<KhuyenMaiPhoneProduct> listKhuyenMaiByEachPhoneProduct = khuyenMaiPhoneRepository.displayKhuyenMaiByEachPhoneProduct();
		return listKhuyenMaiByEachPhoneProduct;
	}
}
