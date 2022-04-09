package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ImagePhoneProduct;
import com.example.demo.model.ImagePhoneProductDetail;
import com.example.demo.model.ImageProduct;
import com.example.demo.model.PhoneProduct;
import com.example.demo.repository.ImagePhoneProductRepository;

@Service
public class ImagePhoneProductService {

	@Autowired
	private ImagePhoneProductRepository imagePhoneProductRepository;
	
	
	public void insertImagePhoneProduct(PhoneProduct p,ImageProduct ip) {
		ImagePhoneProduct i = new ImagePhoneProduct();
		i.setImageProduct(ip);
		i.setPhoneProduct(p);
		imagePhoneProductRepository.save(i);
	}
	
	public List<ImagePhoneProductDetail> displayAllImageByIdPhone(Long idPhoneProduct){
		List<ImagePhoneProductDetail> l = imagePhoneProductRepository.displayAllImageByIdPhone(idPhoneProduct);
		return l;
	}
	
}
