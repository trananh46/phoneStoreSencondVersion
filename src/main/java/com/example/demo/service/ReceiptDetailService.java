package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.informationPhoneAndReceiptDetailFromIdReceipt;
import com.example.demo.model.PhoneProduct;
import com.example.demo.model.Receipt;
import com.example.demo.model.ReceiptDetail;
import com.example.demo.model.TotalPriceProjectionOfReceipt;
import com.example.demo.repository.PhoneProductInCart;
import com.example.demo.repository.ReceiptDetailRepository;

@Service
public class ReceiptDetailService {

	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	private PhoneProductService phoneProductService;
	
	@Autowired
	private ReceiptDetailRepository receiptDetailRepository;
	
	public void createReceiptDetail(Long idPhoneProduct) {
		Receipt r = receiptService.findReceiptStatusNullAndDateNull();
		
		PhoneProduct p = phoneProductService.findPhoneProductById(idPhoneProduct);
		
		Long totalPrice = p.getPrice() * 1;
		ReceiptDetail rd = new ReceiptDetail();
		rd.setReceipt(r);
		rd.setPhoneProduct(p);
		rd.setQuantity((long)1);
		rd.setTotalPrice(totalPrice);
		receiptDetailRepository.save(rd);
		
	}
	
	public List<PhoneProductInCart> displayReceiptDetail(Long idCustomer){
		List<PhoneProductInCart> l = receiptDetailRepository.displayReceiptDetail(idCustomer);
		return l;
	}
	
	public void increaseQuantity(Long idReceiptDetail,Long idPhoneProduct) {
		ReceiptDetail r = receiptDetailRepository.findReceiptDetailById(idReceiptDetail);
		PhoneProduct p = phoneProductService.findPhoneProductById(idPhoneProduct);

		Long currentQuantity = r.getQuantity();
		Long newQuantity = currentQuantity + 1;
		Long newTotalPrice = p.getPrice() * newQuantity;
		
		r.setQuantity(newQuantity);
		r.setTotalPrice(newTotalPrice);
		receiptDetailRepository.save(r);
		
	}
	
	
	public void decreaseQuantity(Long idReceiptDetail,Long idPhoneProduct) {
		ReceiptDetail r = receiptDetailRepository.findReceiptDetailById(idReceiptDetail);
		PhoneProduct p = phoneProductService.findPhoneProductById(idPhoneProduct);

		Long currentQuantity = r.getQuantity();
		Long newQuantity = currentQuantity - 1;
		Long newTotalPrice = p.getPrice() * newQuantity;
		
		r.setQuantity(newQuantity);
		r.setTotalPrice(newTotalPrice);
		receiptDetailRepository.save(r);
		
	}
	
	public TotalPriceProjectionOfReceipt displayTotalPriceProjectionOfReceipt(Long idCustomer) {
		TotalPriceProjectionOfReceipt t = receiptDetailRepository.displayTotalPriceProjectionOfReceipt(idCustomer);
		return t;
	}
	
	public void deleteReceiptDetailById(Long idReceiptDetail) {
		receiptDetailRepository.deleteReceiptDetailById(idReceiptDetail);
	}
	
	
	public void deleteReceiptDetailByIdReceipt(Long idReceipt) {
		receiptDetailRepository.deleteReceiptDetailByIdReceipt(idReceipt);
	}
	
	
	public List<informationPhoneAndReceiptDetailFromIdReceipt> displayListReceiptDetailByIdReceipt( Long idReceipt){
		List<informationPhoneAndReceiptDetailFromIdReceipt> listReceipt = receiptDetailRepository.listReceiptDetailByIdReceipt(idReceipt);
		return listReceipt;
	}
	
	public List<informationPhoneAndReceiptDetailFromIdReceipt> listReceiptDetailHasNotApprovedByIdCustomer(Long idCustomer){
		List<informationPhoneAndReceiptDetailFromIdReceipt> listReceipt = receiptDetailRepository.listReceiptDetailHasNotApprovedByIdCustomer(idCustomer);
		return listReceipt;
	}
	
	public List<informationPhoneAndReceiptDetailFromIdReceipt> listReceiptDetailApprovedByIdCustomer(Long idCustomer){
		List<informationPhoneAndReceiptDetailFromIdReceipt> listReceipt = receiptDetailRepository.listReceiptDetailApprovedByIdCustomer(idCustomer);
		return listReceipt;
	}
}
