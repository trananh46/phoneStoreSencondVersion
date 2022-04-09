package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.StatusReceipt;
import com.example.demo.repository.StatusReceiptRepository;

@Service
public class StatusReceiptService {

	@Autowired
	private StatusReceiptRepository statusReceiptRepository;
	
	public List<StatusReceipt> displayListStatusReceipt(){
		List<StatusReceipt> listStatusReceipt = statusReceiptRepository.displayListStatusReceipt();
		return listStatusReceipt;
	}
	
	public void insertStatusReceipt(String nameStatusReceipt) {
		StatusReceipt s = new StatusReceipt();
		s.setNameTrangThai(nameStatusReceipt);
		statusReceiptRepository.save(s);
	}
	
	public StatusReceipt findStatusReceiptById(Long idStatusReceipt) {
		StatusReceipt s = statusReceiptRepository.findStatusReceiptById(idStatusReceipt);
		return s;
	}
}
