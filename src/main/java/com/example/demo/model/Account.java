package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_account")
	private Long idAccount;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="user_type")
	private Long userType;
	
	@OneToOne
	@JoinColumn(name="id_customer")
	private Customer customer;
	
	
	@OneToOne
	@JoinColumn(name="id_staff")
	private Staff staff;
	
	
	@ManyToOne
	@JoinColumn(name="id_role")
	private Role role;


	public Long getIdAccount() {
		return idAccount;
	}


	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
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


	public Long getUserType() {
		return userType;
	}


	public void setUserType(Long userType) {
		this.userType = userType;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Staff getStaff() {
		return staff;
	}


	public void setStaff(Staff staff) {
		this.staff = staff;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Account(Long idAccount, String email, String password, Long userType, Customer customer, Staff staff,
			Role role) {
		super();
		this.idAccount = idAccount;
		this.email = email;
		this.password = password;
		this.userType = userType;
		this.customer = customer;
		this.staff = staff;
		this.role = role;
	}

	public Account() {
		
	}
	
	
}
