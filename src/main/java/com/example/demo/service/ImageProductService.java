package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ImageProduct;
import com.example.demo.repository.ImageProductRepository;

@Service
public class ImageProductService {

	@Autowired
	private ImageProductRepository imageProductRepository;
	
	public void insertImageProduct(Long idPhoneProduct,String image) {
		ImageProduct i = new ImageProduct();
		i.setImage(image);
		i.setIdPhoneProduct(idPhoneProduct);
		imageProductRepository.save(i);
	}
	
	public ImageProduct findImageByNameAndIdProduct(Long idProduct,String image) {
		ImageProduct i = imageProductRepository.findImageByNameAndIdProduct(idProduct, image);
		return i;
	}
	
	
	public List<ImageProduct> displayListImagePhoneProductDetailCustomer(Long idPhoneProduct){
		List<ImageProduct> l = imageProductRepository.displayListImagePhoneProductDetailCustomer(idPhoneProduct);
		return l ;
	}
}
