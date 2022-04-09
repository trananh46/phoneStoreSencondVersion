package com.example.demo.model;

public class ListStaff2 {

	private Long id_staff;
	
	private String name_staff;
	
	private String email;
	
	private String role_name;
	
	private String sdt;

	public Long getId_staff() {
		return id_staff;
	}

	public void setId_staff(Long id_staff) {
		this.id_staff = id_staff;
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

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public ListStaff2(Long id_staff, String name_staff, String email, String role_name, String sdt) {
		super();
		this.id_staff = id_staff;
		this.name_staff = name_staff;
		this.email = email;
		this.role_name = role_name;
		this.sdt = sdt;
	}
	
	public ListStaff2() {
		
	}
}
