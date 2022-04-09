package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.InformationConfigurePhoneProductCustomer;
import com.example.demo.model.InformationPhoneProductDetailCustomer;
import com.example.demo.repository.PhoneProductRepository;

@Service
public class InformationPhoneProductDetailCustomerService {

	@Autowired
	private PhoneProductRepository phoneProductRepository;
	
	public InformationPhoneProductDetailCustomer displayInformationPhoneProductDetailCustomer(Long idPhoneProduct) {
		InformationPhoneProductDetailCustomer i = phoneProductRepository.displayInformationPhoneProductDetailCustomer(idPhoneProduct);
		return i;
	}
	
	
	public InformationConfigurePhoneProductCustomer displayInformationConfigurePhoneProductCustomer(Long idPhoneProduct) {
		InformationConfigurePhoneProductCustomer i = phoneProductRepository.displayInformationConfigurePhoneProductCustomer(idPhoneProduct);
		return i;
	}
	
	
}
