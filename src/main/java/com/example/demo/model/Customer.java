package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_customer")
	private Long idCustomer;
	
	@Column(name="name_customer")
	private String nameCustomer;
	
	@Column(name="city")
	private String city;
	
	@Column(name="district")
	private String district;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="birthday")
	private LocalDate birthday;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="phuong_xa")
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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
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

	public Customer(Long idCustomer, String nameCustomer, String city, String district, String address,
			String phoneNumber, LocalDate birthday, String gender, String phuongXa) {
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

	public Customer() {
		
	}
	
	@OneToOne(mappedBy = "customer")
	private Account account;
	
	@OneToMany(mappedBy = "customer")
	private List<Receipt> receipt;
	
	@OneToMany(mappedBy = "customer")
	private List<Comment> listComments;
	
}
