package com.example.demo.model;

public class AccountStaff2 {

	private Long id_account;
	
	private String name_staff;
	
	private String email;
	
	private String password;

	public Long getId_account() {
		return id_account;
	}

	public void setId_account(Long id_account) {
		this.id_account = id_account;
	}

	public String getName_staff() {
		return name_staff;
	}

	public void setName_staff(String name_staff) {
		this.name_staff = name_staff;
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

	public AccountStaff2(Long id_account, String name_staff, String email, String password) {
		super();
		this.id_account = id_account;
		this.name_staff = name_staff;
		this.email = email;
		this.password = password;
	}

	public AccountStaff2() {
		
	}
}
