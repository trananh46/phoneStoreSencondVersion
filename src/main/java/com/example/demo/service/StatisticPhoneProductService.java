package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.StatisticBudget;
import com.example.demo.DTO.StatisticBudgetByKindOfPhone;
import com.example.demo.DTO.StatisticBudgetDTO;
import com.example.demo.DTO.TimeComparator;
import com.example.demo.DTO.informationPhoneAndReceiptDetailFromIdReceipt;
import com.example.demo.DTO.year;
import com.example.demo.model.PhoneProductAndDetail;
import com.example.demo.repository.HangDienThoaiRepository;
import com.example.demo.repository.PhoneProductRepository;
import com.example.demo.repository.ReceiptRepository;

@Service
public class StatisticPhoneProductService {

	@Autowired
	private PhoneProductRepository phoneProductRepository;
	
	@Autowired
	private ReceiptRepository receiptRepository;
	
	@Autowired
	private HangDienThoaiRepository hangDienThoaiRepository;
	
	public List<StatisticBudget> statisticBudgetYear(){
		List<StatisticBudget> listStatisticBudgetYear = phoneProductRepository.statisticBudget();
		return listStatisticBudgetYear;
	}
	
	public List<StatisticBudgetDTO> statisticBudgetMonth(Long idYear){
		List<StatisticBudget> listStatisticBudgetMonth1 = phoneProductRepository.statisticBudgetMonth(idYear);
		
		
		
		ModelMapper modelMapper = new ModelMapper();	
		List<StatisticBudgetDTO> listStatisticBudgetMonth2 = new ArrayList<StatisticBudgetDTO>();
		
		for (StatisticBudget a : listStatisticBudgetMonth1) {
			StatisticBudgetDTO b = modelMapper.map(a,StatisticBudgetDTO.class);
			b.setTime(a.getTime());
			b.setTotalMoney(a.getTotalMoney());
			b.setQuantity(a.getQuantity());
			listStatisticBudgetMonth2.add(b);
		}
		
		for(int i = 1;i<13;i++) {
			boolean flag = true;
			for (StatisticBudgetDTO a : listStatisticBudgetMonth2) {
				if(a.getTime() == i) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				StatisticBudgetDTO c = new StatisticBudgetDTO();
				c.setTime(i);
				long totalMoney = 0;
				c.setTotalMoney(totalMoney);
				long quantity = 0;
				c.setQuantity(quantity);
				listStatisticBudgetMonth2.add(c);
				
			}
		}
		
		
		Collections.sort(listStatisticBudgetMonth2, new TimeComparator());
		
		return listStatisticBudgetMonth2;
	}
	
	public List<informationPhoneAndReceiptDetailFromIdReceipt> displayListPhoneProductByYearAndMonth(Long year,Long month){
		List<informationPhoneAndReceiptDetailFromIdReceipt> listPhoneProduct = phoneProductRepository.displayListPhoneProductByYearAndMonth(year, month);
		return listPhoneProduct;
	}
	
	public List<year> displayListYearsOfReceipt(){
		List<year> listYearsOfReceipt = receiptRepository.listYearsOfReceipt();
		return listYearsOfReceipt;
	}
	
	public List<StatisticBudgetByKindOfPhone> displayStatisticByKindOfPhone(){
		List<StatisticBudgetByKindOfPhone> listStatisticByKindOfPhone = hangDienThoaiRepository.listStatisticBudgetKindOfPhone();
		return listStatisticByKindOfPhone;
	}
	
	public List<informationPhoneAndReceiptDetailFromIdReceipt> displayListPhoneProductByIdHangDienThoai(Long idHangDienThoai){
		List<informationPhoneAndReceiptDetailFromIdReceipt> listPhoneProduct = hangDienThoaiRepository.displayListPhoneProductByIdHangDienThoai(idHangDienThoai);
		return listPhoneProduct;
	}
}
