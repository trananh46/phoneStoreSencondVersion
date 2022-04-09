package com.example.demo.repository;

public interface PhoneProductInCart {
	Long getId_customer();
	Long getId_phone_product();
	Long getId_receipt_detail();
	String getName_phone_product();
	String getImage();
	Long getPrice();
	String getName_color();
	String getName_memory();
	Long getTotal_price();
	Long getQuantity();
}
