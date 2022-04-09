package com.example.demo.repository;

import java.time.LocalDate;

public interface ReceiptAndInfromationCustomer {

	Long getId_receipt();
	
	Long getId_customer();
	
	LocalDate getDate();
	
	String getNote();
	
	Long getId_trang_thai();
	
	String getTotal_price_receipt();
	
	String getName_customer();
	
	
}
