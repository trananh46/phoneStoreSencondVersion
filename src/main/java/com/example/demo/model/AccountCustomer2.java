package com.example.demo.model;

public class AccountCustomer2 {

	private Long id_account;
	
	private String name_customer;
	
	private String email;
	
	private String password;

	public Long getId_account() {
		return id_account;
	}

	public void setId_account(Long id_account) {
		this.id_account = id_account;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountCustomer2(Long id_account, String name_customer, String email, String password) {
		super();
		this.id_account = id_account;
		this.name_customer = name_customer;
		this.email = email;
		this.password = password;
	}
	
	public AccountCustomer2() {
		
	}
}
