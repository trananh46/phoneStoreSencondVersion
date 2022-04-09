package com.example.demo.model;

public class CustomerList2 {

	private Long id_customer;
	
	private String name_customer;
	
	private String email;
	
	private String phone_number;

	public Long getId_customer() {
		return id_customer;
	}

	public void setId_customer(Long id_customer) {
		this.id_customer = id_customer;
	}

	public String getName_customer() {
		return name_customer;
	}

	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public CustomerList2(Long id_customer, String name_customer, String email, String phone_number) {
		super();
		this.id_customer = id_customer;
		this.name_customer = name_customer;
		this.email = email;
		this.phone_number = phone_number;
	}

	public CustomerList2() {
		
	}
	
	
	
}
