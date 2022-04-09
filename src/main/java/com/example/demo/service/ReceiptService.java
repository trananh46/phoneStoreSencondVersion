package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Receipt;
import com.example.demo.model.StatusReceipt;
import com.example.demo.model.TotalPriceProjectionOfReceipt;
import com.example.demo.repository.ReceiptAndInfromationCustomer;
import com.example.demo.repository.ReceiptRepository;

@Service
public class ReceiptService {

	@Autowired
	private ReceiptRepository receiptRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private StatusReceiptService statusReceiptService;
	
	
	@Autowired
	private ReceiptDetailService receiptDetailService;
	
	public void createReceiptCustomer(Long idCustomer) {
		Customer c = customerService.findCustomerById(idCustomer);
		
		Receipt r = new Receipt();
		r.setCustomer(c);		
		receiptRepository.save(r);
	}
	
	
	
	public Receipt findReceiptStatusNullAndDateNull() {
		Receipt r = receiptRepository.findReceiptStatusNullAndDateNull();
		return r;
	}
	
	
	public Long countReceiptStatusNullAndDateNull() {
		Long count = receiptRepository.countReceiptStatusNullAndDateNull();
		return count;
	}
	
	public long updateReceipt(Long idCustomer) {
		
		List<Receipt> r = receiptRepository.findReceiptByIdCustomerToUpdate(idCustomer);
		long size = r.size();
		if(size == 1) {
			Receipt r1 = receiptRepository.findReceiptByIdCustomerToPay(idCustomer);
			//we find status đang chờ duyệt by id
			long idStatus = 1;
			StatusReceipt s = statusReceiptService.findStatusReceiptById(idStatus);
			
			//we find total price of the receipt
			TotalPriceProjectionOfReceipt t = receiptDetailService.displayTotalPriceProjectionOfReceipt(idCustomer);
			Long totalPriceReceipt = t.getTotalPriceProjectionReceipt();
			r1.setDate(LocalDate.now());
			r1.setStatusReceipt(s);
			r1.setTotalPriceReceipt(totalPriceReceipt);
			receiptRepository.save(r1);
			return 0;
		}else {
			return 1;
		}
	}
	
	public List<ReceiptAndInfromationCustomer> displayListReceiptHasNotApprovedYet(){
		List<ReceiptAndInfromationCustomer> listReceipt = receiptRepository.displayListReceiptHasNotApprovedYet();
		return listReceipt;
	}
	
	
	public void approveReceipt(Long idReceipt) {
		Receipt r = receiptRepository.findReceiptById(idReceipt);
		long idStatusReceiptAfterApproved = 2;
		StatusReceipt s = statusReceiptService.findStatusReceiptById(idStatusReceiptAfterApproved);
		r.setStatusReceipt(s);
		receiptRepository.save(r);
	}
	
	public Receipt findReceiptById(Long idReceipt) {
		Receipt r = receiptRepository.findReceiptById(idReceipt);
		return r;
	}
	
	public List<ReceiptAndInfromationCustomer> displayListReceiptApproved(){
		List<ReceiptAndInfromationCustomer> listReceipt = receiptRepository.displayListReceiptApproved();
		return listReceipt;
	}
	
	public void deleteReceiptById(Long idReceipt) {
		
		receiptDetailService.deleteReceiptDetailByIdReceipt(idReceipt);
		receiptRepository.deleteReceiptById(idReceipt);
	}
}
