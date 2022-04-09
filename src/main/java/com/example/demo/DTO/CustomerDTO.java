package com.example.demo.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CustomerDTO {

	private Long idCustomer;
	
	private String nameCustomer;
	
	private String city;
	
	private String district;
	
	private String address;
	
	private String phoneNumber;
	
	private String birthday;
	
	private String gender;
	
	private String phuongXa;

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhuongXa() {
		return phuongXa;
	}

	public void setPhuongXa(String phuongXa) {
		this.phuongXa = phuongXa;
	}

	public CustomerDTO(Long idCustomer, String nameCustomer, String city, String district, String address,
			String phoneNumber, String birthday, String gender, String phuongXa) {
		super();
		this.idCustomer = idCustomer;
		this.nameCustomer = nameCustomer;
		this.city = city;
		this.district = district;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.gender = gender;
		this.phuongXa = phuongXa;
	}
	
	public CustomerDTO() {
		
	}
	
}
